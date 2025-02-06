package com.java.member.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.java.member.domain.Member;
import com.java.member.domain.MemberExample;
import com.java.member.mapper.MemberMapper;
import com.java.member.req.MemberLoginReq;
import com.java.member.req.MemberRegisterReq;
import com.java.member.req.MemberSendCodeReq;
import com.java.member.resp.MemberLoginResp;
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
        Member memberDB = selectByMobile(mobile);

        if(ObjectUtil.isNull(memberDB)) {
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
        Member memberDB = selectByMobile(mobile);


        if(ObjectUtil.isNull(memberDB)) {
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
        LOG.info("One time code send:{}", "8888");

        //Save Message record, mobile, otp, expiration, used?
        LOG.info("save OTC");
        //对接短信通道，发送短信
        LOG.info("send otc");
    }

    public MemberLoginResp login(MemberLoginReq req) {

        String mobile = req.getMobile();
        String code = req.getCode();
        Member memberDB = selectByMobile(mobile);


        if(ObjectUtil.isNull(memberDB)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOTEXIST);
        }
        LOG.info(code);
        //verify code
        if(!"8888".equals(code)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_INVALID);
        }
        return BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
    }


    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);

        List<Member> list = memberMapper.selectByExample(memberExample);
        if(CollUtil.isEmpty(list)) {
            return null;
        }else {
            return list.get(0);
        }

    }
}
