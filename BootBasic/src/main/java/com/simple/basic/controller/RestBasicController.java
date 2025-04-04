package com.simple.basic.controller;

import com.simple.basic.command.SimpleVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.Loader;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController //일반컨트롤러랑은 다른의미 - return에 담기는 데이터가 요청한 곳으로 응답함
//@Controller + ResponseBody = RestController
public class RestBasicController {


    @GetMapping("/hello")
    public String hello() {
        return "<h3>hello world</h3>";
    }
    
    //데이터를 보내는 방법
    //1. 객체반환
    //ResponseBody와 Json-databind라이브러리가 해줍니다(자동으로)
    @GetMapping("/bye")
    public SimpleVO bye() {

        return new SimpleVO(1, "홍길동", LocalDateTime.now());
    }

    //2. 맵을 반환
    @GetMapping("/getMap")
    public Map<String, Object> getMap() {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "손유경");
        map.put("age", 20);
        map.put("date", new SimpleVO(1, "홍길동" , LocalDateTime.now()));

        return map;
    }

    //3. 리스트로 반환
    @GetMapping("/getList")
    public List<SimpleVO> getList() {

        List<SimpleVO> list = new ArrayList<>();
        list.add(new SimpleVO(1, "홍길동" , LocalDateTime.now()));
        list.add(new SimpleVO(2, "김민수" , LocalDateTime.now()));

        return list;
    }

    //값을 받는 방법
    //요청의 다양한 타입 get
    //@RequestParam이나, VO를 통해서 받을 수 있음
    //http://localhost:8181/getData?name=홍길동&sno=1
    @GetMapping("/getData")
    public String getData(@RequestParam("name") String name,
                        @RequestParam("sno") int sno,
                        HttpServletRequest request){

        log.info(request.getRemoteAddr());

        log.info(name+ ", " + sno);

        return "success";
    }

    //http://localhost:8181/getData2?name=홍길동&sno=1
    @GetMapping("/getData2")
    public String getData(SimpleVO vo) {
        log.info(vo.toString());
        return "success";
    }

    @GetMapping("/getData3/{name}/{sno}")
    public String getData3(@PathVariable("name") String name,
                           @PathVariable("sno") int sno) {

        log.info(name+ ", " + sno);

        return "success";
    }


    /////////////////////////////////////////////////////////////////////////////
    //post방식으로 데이터 받기
    //상대방이 데이터를 보내는 contentType을 지정함 (form타입, JSON타입)
    
    //보내는 입장이 form형식으로 보내는 경우
//    @PostMapping("/getForm")
//    public String getForm(SimpleVO vo) {
//
//        log.info(vo.toString());
//        return "success";
//    }

    @PostMapping("/getForm")
    public String getForm(@RequestParam("name") String name,
                          @RequestParam("sno") int sno) {

        log.info(name+ ", " + sno);
        return "success";
    }

    //보내는 입장이 json타입으로 보내는 경우 -> VO또는 MAP타입으로
    //@RequestBody -> JSON데이터를 Object맵핑
//    @PostMapping("/getJson")
//    public String getJson(@RequestBody SimpleVO vo) {
//
//        log.info(vo.toString());
//        return "success";
//    }

    @PostMapping("/getJson")
    public String getJson(@RequestBody Map<String, Object> map) {

        log.info(map.toString());
        return "success";
    }

    ////////////////////////////////////////////////////////////
    //produces - 서버에서 보내는 타입에 대한 명세 (아무것도 안적으면 기본 JSON타입)
    //consumers - "너 반드시 이렇게 데잉터 보내" 명세
    
    @PostMapping(value = "/getResult"
            , produces = "text/plain"
            , consumes = "text/plain")
    public String getResult(@RequestBody String str) {

        log.info(str);

        return "<h3>안녕하세요</h3>";
    }

    /// //////////////////////////////////////////////////////////////////
    //상담 문서 작성하기 ResponseEntity<보낼데이터타입>

    @PostMapping("/createEntity")
    public ResponseEntity<SimpleVO> createEntity(){

        SimpleVO vo = new SimpleVO(2, "홍길동", LocalDateTime.now());

        //응답문서에 헤더에 대한 내역을 추가할 수 있음.
        HttpHeaders headers = new HttpHeaders();
        headers.add("context-type", "application/json");
        headers.add("authorization", "JSON WEB TOKEN");
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<>(vo, headers, HttpStatus.OK);
    }
    /////////////////////////////////////////////////////////
    //명세에 맞춰 작성후 부메랑으로 호출
    
    /*
    요청주소 - /api/v1/getData
    메서드 - get
    클라이언트에서 보내는 데이터 - num, name
    서버에서 응답하는 데이터는 - SimpleVO
    responseEntity로 응답
    */
    @GetMapping("/api/v1/getData")
    public ResponseEntity<SimpleVO> getData(@RequestParam("num") int num
                                            ,@RequestParam("name") String name) {

        log.info(num + ", " + name);

        return new ResponseEntity<>(new SimpleVO(1, "이순신" , LocalDateTime.now()), HttpStatus.OK);

    }

    
    /*
    요청주소 - /api/v1/getInfo
    메서드 - post
    클라이언트에서 보내는 데이터 - JSON타입의 num, name
    서버에서 응답하는 데이터는 - List<SimpleVO>
    responseEntity로 응답
    */

    @PostMapping("/api/v1/getInfo")
    public ResponseEntity<List<SimpleVO>> getInfo(@RequestBody List<SimpleVO> list) {

        List<SimpleVO> voList = new ArrayList<>();
        list.add(new SimpleVO(1, "순신리" , LocalDateTime.now()));
        list.add(new SimpleVO(2, "민수킴" , LocalDateTime.now()));

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    
}

