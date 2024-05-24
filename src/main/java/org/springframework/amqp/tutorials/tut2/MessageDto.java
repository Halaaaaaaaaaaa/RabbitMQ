package org.springframework.amqp.tutorials.tut2;

import lombok.*;
/*
RabbitMQ 내에서 통신 수행할 객체 구성 - 메시지 구성
(access = AccessLevel.PROTECTED로 했으니 Setter 안해도 된다고 판단)
protected 선언되었으니까 외부에서 직접 호출 X
Builder 어노테이션이 지정된 생성자를 사용해서 객체를 생성할 때 각 필드를 설정할 수 있고,
매개변소가 없는 기본 생성자와 빌더 패턴을 통해서 객체 생성할 수 있다.
*/
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class MessageDto {

    private String title;
    private String message;




}
