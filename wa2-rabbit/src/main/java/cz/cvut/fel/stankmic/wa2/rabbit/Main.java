package cz.cvut.fel.stankmic.wa2.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import cz.cvut.fel.stankmic.wa2.data.service.AsyncResultService;
import cz.cvut.fel.stankmic.wa2.rabbit.config.RabbitConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main implements Runnable {

    public static final String QUEUE_NAME = "wa2";
    public static final boolean AUTO_ACK = false;
    public static final boolean DURABLE = false;
    public static final int PREFETCH_COUNT = 1;
    public static final int DUMMY_JOB_SLEEP = 60_000;

    public static void main(String[] args) throws InterruptedException {
        Thread t = startMainThread();
        createShutDownHook(t);
    }

    private static Thread startMainThread() {
        Thread t = new Thread(new Main());
        t.start();
        return t;
    }

    @Override
    public void run() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RabbitConfig.class);
        Connection rabbitConnection = null;
        try {
            rabbitConnection = ctx.getBean(ConnectionFactory.class).newConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AsyncResultService asyncResultService = ctx.getBean(AsyncResultService.class);
        System.out.println("Rabbit consumer ready...");
        while(true) {
            try {
                @SuppressWarnings("unused")
                MyMessage received = receive(rabbitConnection);
                System.out.println(received);
                asyncResultService.update(received.getId(), "Unicorns does not exist.");
            } catch (Exception ex) {
                // do nothing
            } finally {
                // TODO
                // store some dummy result
            }
        }
    }

    private MyMessage receive(Connection connection) throws IOException, InterruptedException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, DURABLE, false, false, null);
        channel.basicQos(PREFETCH_COUNT);
        MyConsumer consumer = new MyConsumer(channel);
        channel.basicConsume(QUEUE_NAME, AUTO_ACK, consumer); // do not use the channel below this point
        return consumer.getReceived();
    }

    private static void createShutDownHook(Thread t) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("\n---");
                System.out.println("Shutting down Rabbit consumer...");
                t.interrupt();
                try {
                    t.join(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Rabbit consumer exited.");
            }
        }));
    }

}
