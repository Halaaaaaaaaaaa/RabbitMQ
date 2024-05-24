package org.springframework.amqp.tutorials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitAmqpTutorialsApplication  {

    private static Logger logger = LoggerFactory.getLogger(RabbitAmqpTutorialsApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(RabbitAmqpTutorialsApplication.class, args);
        logger.info("=============================================================================");
    }

}
