package com.luck.luckcloud.framelibrary.entity;

public class UploadLiHuaEntity {
    private String gid;
    private int count = 0;
    private String symbol = "";

    public UploadLiHuaEntity() {
    }

    public UploadLiHuaEntity(String gid, String symbol, int count) {
        this.gid = gid;
        this.symbol = symbol;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "UploadLiHuaEntity{" +
                "gid='" + gid + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }

    public void addSymbol(String symbol) {
        this.symbol += symbol + ",";
    }
}
