package infnet.edu.petfriends_transporte_service.infrastructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue transporteQueue() {
        return new Queue("transporteQueue", true);
    }
}
