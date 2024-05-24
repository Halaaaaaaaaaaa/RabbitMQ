package org.springframework.amqp.tutorials.tut2;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(MessageDto messageDto) throws JsonProcessingException {

//            RabbitMQ로 메시지를 보내는 데 사용. 목적지로 보낼 메시지와 함께 목적지의 교환기와 라우팅 키를 지정
//            "hello.exchange" 교환기로부터 "hello.key" 라우팅 키를 사용하여 objectToJSON JSON 형식의 메시지를 전송하는 작업을 수행
//            교환기와 라우팅 키는 RabbitmqConfig에서 설정한 DirectExchange, Binding 설정과 일치해야함.
//            Java 객체를 Json형식으로 변환해서 이를 rabbitMQ template의 convertAndSend 메소드를 사용해서 해당 메시지와 라우팅 키를 지정하여 전송.
//            토끼한테 메시지 전송할 때, Jackson2JsonMessageConverter 사용해서 객체를 자동으로 json으로 변환.
        rabbitTemplate.convertAndSend("hello.exchange", "hello.key", messageDto);
    }
}
