package com.springboot.javascript.model;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@XmlJavaTypeAdapter(MyJavaTypeAdapter.class)
@XmlAccessorType(value = XmlAccessType.PUBLIC_MEMBER)
@XmlAccessorOrder(value = XmlAccessOrder.ALPHABETICAL)
@XmlRootElement(name = "person")
public class Person {

    private String name;
    private int age;
    private String gender;
    private String addr;
    private Date date;
    private Set<Keys> key = new HashSet<>();

    public Person(String name, String gender, String addr, int age) {
        this.name = name;
        this.gender = gender;
        this.addr = addr;
        this.age = age;
        key.add(new Keys("001"));//向集合中添加两个Key对象
        key.add(new Keys("002"));
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @XmlElement
    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    /**
     * 表示person节点的属性
     * @param gender
     */
    @XmlAttribute
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddr() {
        return addr;
    }

    @XmlElement
    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Date getDate() {
        return date;
    }

    @XmlElement
    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Keys> getKey() {
        return key;
    }

    @XmlElementWrapper(name = "keys")
    @XmlElement
    public void setKey(Set<Keys> key) {
        this.key = key;
    }
}
