package com.boot.web.utils;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    public static void main(String[] args) throws Exception {
        //get();
        post();
    }

    public static void post() throws Exception {
        String encoding = "UTF-8";
        //post的form参数(json兼职对)
        String params = "[{\"addTime\":\"2011-09-19 14:23:02\"[],\"iccid\":\"1111\",\"id\":0,\"imei\":\"2222\",\"imsi\":\"3333\",\"phoneType\":\"4444\",\"remark\":\"aaaa\",\"tel\":\"5555\"}]";
        String path = "http://localhost:8081/chapter02/upload";
        byte[] data = params.getBytes(encoding);
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

//        conn.setRequestProperty("Content-Type", "application/x-javascript; charset=" + encoding);
//        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        conn.setConnectTimeout(5 * 1000);
        conn.connect();
        OutputStream outStream = conn.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();
        System.out.println(conn.getResponseCode()); // 响应代码 200表示成功
        if (conn.getResponseCode() == 200) {
            InputStream inStream = conn.getInputStream();
            String result = new String(toByteArray(inStream), "UTF-8");
            System.out.println(result); // 响应代码 200表示成功
        }
    }
    private static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }
}
