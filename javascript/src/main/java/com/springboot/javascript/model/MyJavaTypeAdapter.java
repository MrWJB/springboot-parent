package com.springboot.javascript.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyJavaTypeAdapter extends XmlAdapter<String, Date> {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public Date unmarshal(String v) throws Exception {
        return sdf.parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return sdf.format(v);
    }
}
