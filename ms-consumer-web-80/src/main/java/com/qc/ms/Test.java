package com.qc.ms;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import feign.Util;

/**
 * quincey
 * Date 2020/6/25 15:08
 */


public class Test {
    public static void main(String[] args) {
        String s = "Basic " + Base64.encode(("qianfeng:java").getBytes(Util.ISO_8859_1));
        System.err.println(s);


    }
}
