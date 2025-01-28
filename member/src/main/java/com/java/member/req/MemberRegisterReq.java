package com.java.member.req;

import jakarta.validation.constraints.NotBlank;


public class MemberRegisterReq {

    @NotBlank(message = "Phone number can't be empty")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberRegisterReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }

    private String mobile;
}
