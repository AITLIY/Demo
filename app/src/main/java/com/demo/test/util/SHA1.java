package com.demo.test.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/12/10.
 */

public class SHA1 {

    public static String  encryptToSHA(String info ) {
        byte[] resultBytes = new byte[0];
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA1");
            byte[] srcBytes = info.getBytes();
            // 使用srcBytes更新摘要
            sha.update(srcBytes);
            // 完成哈希计算，得到result
            resultBytes = sha.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将摘要转为字符串
        String rs = hexString(resultBytes);
        return rs;
    }

    //byte字节转换成16进制的字符串MD5Utils.hexString
    public static String hexString(byte[] bytes){
        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
