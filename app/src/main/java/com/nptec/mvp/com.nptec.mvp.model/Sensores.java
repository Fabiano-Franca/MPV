package com.nptec.mvp.com.nptec.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sensores implements Serializable {
    @SerializedName("TEMP")
    private String temp;
    @SerializedName("PH")
    private String ph;
    @SerializedName("RPM")
    private String rpm;
    @SerializedName("C1")
    private String c1;
    @SerializedName("C2")
    private String c2;
    @SerializedName("T1")
    private String t1;
    @SerializedName("T2")
    private String t2;
    @SerializedName("N")
    private  String n;

    public Sensores() {
    }

    public Sensores(String temp, String ph, String rpm, String c1, String c2, String t1, String t2, String n) {
        this.temp = temp;
        this.ph = ph;
        this.rpm = rpm;
        this.c1 = c1;
        this.c2 = c2;
        this.t1 = t1;
        this.t2 = t2;
        this.n = n;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getRpm() {
        return rpm;
    }

    public void setRpm(String rpm) {
        this.rpm = rpm;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "Sensores{" +
                "temp=" + temp +
                ", ph=" + ph +
                ", rpm=" + rpm +
                ", c1=" + c1 +
                ", c2=" + c2 +
                ", t1=" + t1 +
                ", t2=" + t2 +
                ", n=" + n +
                '}';
    }
}
