package cz.cvut.fel.stankmic.wa2.rabbit.config;

import com.rabbitmq.client.ConnectionFactory;
import cz.cvut.fel.stankmic.wa2.data.config.DataConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataConfig.class)
public class RabbitConfig {

    private static final String RABBIT_HOST = "localhost";
    public static final String QUEUE_NAME = "wa2";

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RABBIT_HOST);
        return connectionFactory;
    }

}
