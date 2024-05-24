package org.springframework.amqp.tutorials.tut2;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ListenerService {

    void receiveMessage(MessageDto messageDto) throws JsonProcessingException;

}
