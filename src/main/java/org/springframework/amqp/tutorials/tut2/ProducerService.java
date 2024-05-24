package org.springframework.amqp.tutorials.tut2;

import com.fasterxml.jackson.core.JsonProcessingException;

//메시지를 전송하는 서비스를 구현하는 클래스에서 해당 메서드를 구현하도록 인터페이스 구성
public interface ProducerService {
    
    //메세지를 Queue로 전송
    void sendMessage(MessageDto messageDto) throws JsonProcessingException;
}
