package com.innovandoapps.library.kernel.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by windows 8.1 on 25/02/2018.
 */

public class EncrytacionUtils {

    public static String encriptarMD5(String cadena){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[]b=md.digest(cadena.getBytes());
            int size=b.length;
            StringBuffer h=new StringBuffer(size);
            for(int i=0;i<size;i++){
                int u=b[i] & 255;
                if(u<16){
                    h.append("0"+Integer.toHexString(u));
                }else{
                    h.append(Integer.toHexString(u));
                }
            }
            return h.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String encriptarSHA512(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
