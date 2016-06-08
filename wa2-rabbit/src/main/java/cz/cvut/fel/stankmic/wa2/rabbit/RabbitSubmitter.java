package cz.cvut.fel.stankmic.wa2.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.Closeable;
import java.io.IOException;

public class RabbitSubmitter implements Closeable {

    private final String queueName;
    private final Connection connection;

    public RabbitSubmitter(String queueName, Connection connection) {
        this.queueName = queueName;
        this.connection = connection;
    }

    public void submit(byte[] message) {
        try {
            Channel channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicQos(1);
            channel.basicPublish("", queueName, null, message);
            channel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
