package com.company.scorh.util;

import java.security.MessageDigest;

public class CodeUtil{
    private static final String[] hexDigits=
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String encrypt(String origin){
        return MD5Encode(origin, "utf-8");
    }

    public static String encrypt(String origin,String charset){
        return MD5Encode(origin,charset);
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte b1 : b) resultSb.append(byteToHexString(b1));
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static String MD5Encode(String origin, String charsetName) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetName == null || "".equals(charsetName))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetName)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        //32bytes,lower case
        return resultString.toLowerCase();
    }



}
