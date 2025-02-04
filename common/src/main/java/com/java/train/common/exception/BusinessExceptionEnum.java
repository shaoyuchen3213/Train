package com.java.train.common.exception;

public enum BusinessExceptionEnum {


    MEMBER_MOBILE_EXIST("Mobile already registered"),
    MEMBER_MOBILE_NOTEXIST("Get One time code first"),
    MEMBER_MOBILE_CODE_INVALID("Code Invalid");

    private String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BusinessExceptionEnum{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
