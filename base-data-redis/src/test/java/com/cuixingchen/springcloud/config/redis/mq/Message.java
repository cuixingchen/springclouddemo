package com.cuixingchen.springcloud.config.redis.mq;

import java.io.Serializable;

/**
 * Created by cuipengfei on 17-7-11.
 */
public class Message implements Serializable{
    private String name;
    private int age;

    public Message(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
