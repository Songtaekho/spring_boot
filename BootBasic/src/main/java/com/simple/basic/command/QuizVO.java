package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizVO {

    @Pattern(regexp = "[a-zA-Z0-9]{8,}" , message = "아이디는 8자 이상이어야합니다.")
    private String id;


    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}" , message = "비밀번호는 소문자,대문자,특수문자 각 1개이상 사용하며 8자리 이상이어야합니다.")
    private String pw;


}
