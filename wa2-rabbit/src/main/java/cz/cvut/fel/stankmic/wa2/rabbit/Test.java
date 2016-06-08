package cz.cvut.fel.stankmic.wa2.rabbit;

import com.rabbitmq.client.*;
import cz.cvut.fel.stankmic.wa2.rabbit.config.RabbitConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.PrintStream;

public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {
        Test t = new Test();
        t.test(System.out);
    }

    private void test(PrintStream out) throws IOException, InterruptedException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RabbitConfig.class);
        Connection rabbitConnection = ctx.getBean(Connection.class);
        send(rabbitConnection, Main.QUEUE_NAME, "This is my message!".getBytes());
        byte[] received = receive(rabbitConnection, Main.QUEUE_NAME);
        rabbitConnection.close();
        out.println(new String(received, "UTF-8"));
    }

    private void send(Connection connection, String queueName, byte[] message) throws IOException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName, Main.DURABLE, false, false, null);
        channel.basicQos(Main.PREFETCH_COUNT);
        channel.basicPublish("", queueName, null, message);
        channel.close();
    }

    private byte[] receive(Connection connection, String queueName) throws IOException, InterruptedException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName, Main.DURABLE, false, false, null);
        channel.basicQos(Main.PREFETCH_COUNT);
        MyConsumer consumer = new MyConsumer(channel);
        channel.basicConsume(queueName, Main.AUTO_ACK, consumer);
        channel.close();
        return consumer.getReceived().getBytes();
    }

}
