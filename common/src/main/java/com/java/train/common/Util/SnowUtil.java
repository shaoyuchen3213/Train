package com.java.train.common.Util;

import cn.hutool.core.util.IdUtil;

/*
    Escapsulation snowflake
 */
public class SnowUtil {
    private static long dataCenter = 1;
    private static long workId = 1;

    public static long getSnowflakeNextId() {
        return IdUtil.getSnowflake(workId, dataCenter).nextId();

    }
    public static String getSnowflakeNextIdStr () {
        return IdUtil.getSnowflake(workId, dataCenter).nextIdStr();
    }
}
