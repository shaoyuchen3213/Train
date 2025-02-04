package com.java.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class MemberSendCodeReq {

    @NotBlank(message = "Phone number can't be empty")
    @Pattern(regexp = "^1\\d{8}$", message = "phone number invalid")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberSendCodeReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }

    private String mobile;
}
