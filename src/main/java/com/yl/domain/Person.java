package com.yl.domain;

import java.io.Serializable;

/**
 * @author yuanli
 * @create 2019-05-13 21:37
 **/
public class Person implements Serializable {
    private static final long serialVersionUID = 4088414063690202957L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
