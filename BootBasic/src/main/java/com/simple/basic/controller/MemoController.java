package com.simple.basic.controller;

import com.simple.basic.command.TestVO;
import com.simple.basic.memoservice.MemoService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {
    
      //멤버변수 주입
//    @Autowired
//    @Qualifier("memoService")
//    private MemoService memoService;

    //생성자 주입
//    @Autowired
//    public MemoController(MemoService service) {
//        this.memoService = memoService;
//    }

    private final MemoService memoService; //반드시 final 필드로 생성

    //글작성화면
    @GetMapping("/memoWrite")
    public String memoWrite() {

        return "memo/memoWrite";
    }

    @PostMapping("/memoForm")
    public String Write(@Valid @ModelAttribute("vo") TestVO vo,
                        @RequestParam("memo") String memo,
                        @RequestParam("phone") String phone,
                        @RequestParam("pw") String pw,
                        @RequestParam("secret") String secret,
                        Model model,
                        BindingResult br) {

                 vo = TestVO.builder()
                .memo(memo)
                .phone(phone)
                .pw(pw)
                .secret(secret)
                .build();

        int result = memoService.Write(vo);

        model.addAttribute("result", result);
        if(br.hasErrors()) {
            return "memo/memoWrite";
        }

        return "redirect:/memo/memoList";
    }

    @GetMapping("/memoList")
        public String memoList(Model model) {
        List<TestVO> list = memoService.getList();
        model.addAttribute("list", list);
        return "memo/memoList";
    }

}
