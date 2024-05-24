package org.springframework.amqp.tutorials.tut2;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListenerServiceImpl implements ListenerService {

    private Logger logger = LoggerFactory.getLogger(ListenerServiceImpl.class);

    @RabbitListener(queues = "hello.queue")
    public void receiveMessage(MessageDto messageDto) throws JsonProcessingException {
        logger.info("===== Received Success | <{}>", messageDto);
    }

}
