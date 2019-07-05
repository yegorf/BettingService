package com.yegorf.bookmaker.encoding;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder implements HashEncoder{
    public String encode(String data) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert digest != null;
        byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hash);
    }
}
