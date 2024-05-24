package org.springframework.amqp.tutorials.tut2;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//RabbitMQ 설정 파일.
@Configuration
public class RabbitmqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    /**
     * Direct Exchange - "hello.exchange" 이름으로 구성
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("hello.exchange");
    }

    /**
     * Queue - "hello.queue" 이름으로 구성
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue("hello.queue", false);
    }

    /**
     * DirectExchange&Queue 바인딩 - "hello.key" 이름으로 바인딩
     * 모든 메시지는 Queue로 직접 전달되지 않고, 반드시 Exchange에서 먼저 받고,
     * Exchange Type과 Binding 규칙에 따라 Queue로 전달된다.
     * @param directExchange
     * @param queue
     * @return
     */
    @Bean
    Binding binding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with("hello.key");
    }

    /**
     * RabbitMQ 연결을 설정하고 관리하기 위한 ConnectionFactory 구성
     * application.properties의 RabbitMQ 사용자 정보를 가져와서 토끼랑 연결에 필요한 거 설정
     * 상단에 있는 정보 - host, port, username, password
     * @return
     */
    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
            connectionFactory.setHost(host);
            connectionFactory.setPort(port);
            connectionFactory.setUsername(username);
            connectionFactory.setPassword(password);
        return connectionFactory;
    }

    /**
     * RabbitMQ에서 송수신 되는 메세지를 JSON 타입으로 변환하기 위한 구성
     * Jackson2JsonMessageConverter 사용해서 메시지 변환을 수행하고, JSON 형식으로 메시지 송수신.
     * @return
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * RabbitMQ와의 통신을 위해 템플릿 구성
     * 토끼에 메세지를 송수신하기 위한 중 클래스로, connectionFactory와 messageConverter를 설정
     * @param connectionFactory
     * @param jsonMessageConverter
     * @return
     */
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
            rabbitTemplate.setConnectionFactory(connectionFactory);
            rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }

}
