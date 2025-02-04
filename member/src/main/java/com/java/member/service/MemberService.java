package com.java.member.service;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.java.member.domain.Member;
import com.java.member.domain.MemberExample;
import com.java.member.mapper.MemberMapper;
import com.java.member.req.MemberRegisterReq;
import com.java.member.req.MemberSendCodeReq;
import com.java.train.common.Util.SnowUtil;
import com.java.train.common.exception.BusinessException;
import com.java.train.common.exception.BusinessExceptionEnum;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;
    public int count() {
        return (int) memberMapper.countByExample(null);
    }

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

    public long register(MemberRegisterReq req) {

        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        if(CollUtil.isNotEmpty(list)) {
//            return list.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);

        }

        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);


        memberMapper.insert(member);
        return member.getId();
    }


    public void SendCode(MemberSendCodeReq req) {

        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);


        if(CollUtil.isEmpty(list)) {
            LOG.info("mobile no exist");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        }else {
            LOG.info("Already register");
        }
        //otp
        String code = RandomUtil.randomString(4);
        LOG.info("One time code send:{}", code);

        //Save Message record, mobile, otp, expiration, used?
        LOG.info("save OTC");
        //对接短信通道，发送短信
        LOG.info("send otc");
    }
}
