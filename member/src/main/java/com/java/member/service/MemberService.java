package com.java.member.service;


import cn.hutool.core.collection.CollUtil;
import com.java.member.domain.Member;
import com.java.member.domain.MemberExample;
import com.java.member.mapper.MemberMapper;
import com.java.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;
    public int count() {
        return (int) memberMapper.countByExample(null);
    }

    public long register(MemberRegisterReq req) {

        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        if(CollUtil.isNotEmpty(list)) {
//            return list.get(0).getId();
            throw new RuntimeException("Mobile has been register");

        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);


        memberMapper.insert(member);
        return member.getId();
    }
}
