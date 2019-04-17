package com.example.autofillrough1;

/**
 * Created by Akash Kumar on 18-Feb-18.
 */

public class User {

    private int id;
    private String name;
    private String tambon;
    private String province;
    private String code;

    public User(int id, String name, String tambon, String province, String code) {
        this.id = id;
        this.name = name;
        this.tambon = tambon;
        this.province = province;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTambon() {
        return tambon;
    }

    public void setTambon(String tambon) {
        this.tambon = tambon;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province ) {
        this.province = province ;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
