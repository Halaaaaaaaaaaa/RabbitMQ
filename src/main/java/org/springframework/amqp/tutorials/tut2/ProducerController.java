package org.springframework.amqp.tutorials.tut2;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/producer")
public class ProducerController {

    private Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    private ProducerService producerService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto) throws JsonProcessingException {

        ApiResponse ar;
        HttpStatus status;

        try {
            producerService.sendMessage(messageDto);

            ar = ApiResponse.builder()
                    .result("Success")
                    .resultCode(ResultCode.OK.getCode())
                    .resultMessage(ResultCode.OK.getMessage())
                    .build();
            status = HttpStatus.OK;

        } catch (JsonProcessingException jpe) {
            logger.info("===== JsonProcessingException Error: {}", jpe.getMessage());
            ar = ApiResponse.builder()
                    .result("Error processing message")
                    .resultCode(ResultCode.INTERNAL_SERVER_ERROR.getCode())
                    .resultMessage(ResultCode.INTERNAL_SERVER_ERROR.getMessage())
                    .build();
            status = HttpStatus.INTERNAL_SERVER_ERROR;

        } catch (Exception e) {
            //logger.info("===== Exception Error: {}", e.getMessage());
            ar = ApiResponse.builder()
                    .result("Unexpected Error")
                    .resultCode(ResultCode.INTERNAL_SERVER_ERROR.getCode())
                    .resultMessage(ResultCode.INTERNAL_SERVER_ERROR.getMessage())
                    .build();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(ar, status);

    }

}
