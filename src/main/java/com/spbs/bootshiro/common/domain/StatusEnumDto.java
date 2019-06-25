package com.spbs.bootshiro.common.domain;

public enum StatusEnumDto {

    ;
    private String name;
    private int num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    StatusEnumDto(String name, int num) {
        this.name = name;
        this.num = num;
    }

    @Override
    public String toString() {
        return "EnumModelType{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
