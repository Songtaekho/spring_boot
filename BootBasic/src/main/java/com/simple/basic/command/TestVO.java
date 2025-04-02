package com.simple.basic.command;

import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor //기본 생성자
@AllArgsConstructor //멤버변수를 받는 생성자
//@Getter
//@Setter
//@ToString //toString메서드 오버라이딩
@Data // Getter + Setter + ToString 를 합침
@Builder
public class TestVO {


    private Integer mno;
    private String id;
    @Pattern(regexp = "[0-9]{4}")
    private String pw;
    private String name;
    private int salary;
    private String address;
    private LocalDateTime hiredate;
    @Size(min = 5 , message = "메모는 5글자 이상입니다")
    private String memo;
    @Pattern(regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}" , message = "010-0000-0000 유형입니다")
    private String phone;
    private String secret;



}
