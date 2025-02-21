package com.java.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class MemberLoginReq {

    @NotBlank(message = "Phone number can't be empty")
    @Pattern(regexp = "^1\\d{10}$", message = "phone number invalid")
    private String mobile;
    public String getMobile() {
        return mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @NotBlank(message = "Please enter one time code")
    private String code = "8888";
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MemberLoginReq{");
        sb.append("mobile='").append(mobile).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
