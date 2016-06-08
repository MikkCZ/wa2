package cz.cvut.fel.stankmic.wa2.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class MyConsumer extends DefaultConsumer {

    private MyMessage received;

    public MyConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public synchronized void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        try {
            Thread.sleep(Main.DUMMY_JOB_SLEEP);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.received = new MyMessage(body);
        this.getChannel().basicAck(envelope.getDeliveryTag(), false);
        this.getChannel().close();
        notifyAll();
    }

    public synchronized MyMessage getReceived() throws InterruptedException {
        while (received == null) {
            wait();
        }
        return received;
    }
}
