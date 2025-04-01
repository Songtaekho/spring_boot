package com.simple.basic.command;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor //기본 생성자
@AllArgsConstructor //멤버변수를 받는 생성자
//@Getter
//@Setter
//@ToString //toString메서드 오버라이딩
@Data // Getter + Setter + ToString 를 합침
@Builder
public class TestVO {

    //단축키 alt + insert
    
    private String id;
    private String pw;
    private String name;
    private int salary;
    private String address;
    private LocalDateTime hiredate;


}
