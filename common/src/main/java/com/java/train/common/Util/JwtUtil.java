package com.java.train.common.Util;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.GlobalBouncyCastleProvider;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JwtUtil.class);

    private static final String key = "Dora12312";

    public static String createToken(Long id, String mobile) {
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        DateTime now = DateTime.now();
        DateTime expTime = now.offset(DateField.HOUR, 24);
        Map<String, Object> payload = new HashMap<>();
        //sign time
        payload.put(JWTPayload.ISSUED_AT, now);

        //expire time
        payload.put(JWTPayload.EXPIRES_AT, expTime);

        //start time
        payload.put(JWTPayload.NOT_BEFORE, now);

        payload.put("id", id);
        payload.put("mobile", mobile);
        String token = JWTUtil.createToken(payload, key.getBytes());
        LOG.info("Create token: {}", token);
        return token;
    }

    public static boolean validate(String token) {
        LOG.info("Start validate the JWT TOKEN, token:{}", token);
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        try{
            JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
            //validate
            boolean validate = jwt.validate(0);
            LOG.info("JWT token validate result: {}", validate);
            return validate;
        } catch(Exception e) {
            LOG.info("token invalid {}", e.getMessage());
            return false;
        }
    }

    public static JSONObject getJSONObject(String token) {
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        if(validate(token)) {
            return new JSONObject();
        }
        JSONObject payloads = jwt.getPayloads();
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.EXPIRES_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        LOG.info("Base on token get the data: {}", token);
        return payloads;
    }






}
