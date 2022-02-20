package ru.iteco.teachbase.springjunior.account.ioc.homework;

public class ExternalInfo {
    private Integer id;
    private String info;

    public ExternalInfo(Integer id, String info) {
        this.id = id;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public ExternalInfo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public ExternalInfo setInfo(String info) {
        this.info = info;
        return this;
    }
}
