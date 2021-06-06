package com.springboot.javascript.model;

import javax.xml.bind.annotation.XmlElement;

public class Keys {

    private String name;

    public Keys(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
}
