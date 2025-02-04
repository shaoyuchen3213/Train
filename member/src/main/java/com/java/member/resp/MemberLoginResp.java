package com.java.member.resp;

public class MemberLoginResp {
    private Long id;

    private String mobile;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MemberLoginResp{");
        sb.append("id=").append(id);
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}