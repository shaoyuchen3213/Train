package com.java.member.controller;


import com.java.member.req.MemberLoginReq;
import com.java.member.req.MemberRegisterReq;
import com.java.member.req.MemberSendCodeReq;
import com.java.member.resp.MemberLoginResp;
import com.java.member.service.MemberService;
import com.java.train.common.resp.CommonResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;
    @GetMapping("/count")
    public CommonResp<Integer> count() {
        int count  = memberService.count();
        CommonResp<Integer> commonResp = new CommonResp<>();
        commonResp.setContent(count);
        return commonResp;
    }
    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req) {
        long register  = memberService.register(req);
//        CommonResp<Long> commonResp = new CommonResp<>();
//        commonResp.setContent(register);
        return new CommonResp<>(register);

    }

    @PostMapping("/send-code")
    public CommonResp<Long> sendCode(@Valid MemberSendCodeReq req) {
        memberService.SendCode(req);
        return new CommonResp<>();

    }
    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid MemberLoginReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }
}
