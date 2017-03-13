package com.sxhxjy.towermonitor.entity;

/**
 * Created by dell on 2017/3/13.
 */

public class WeatherParam {
    /**
     * result : success
     * fsnew : 疾风
     * resultCode : 200
     * fxnew_data : 19.0
     * resultMessage : 查询成功
     * date_now : 1489375143000
     * fsnew_data : 14.0
     * fxnew : 东偏北        &nbsp;&nbsp; 71.0°
     */

    private String result;
    private String fsnew;
    private String resultCode;
    private double fxnew_data;
    private String resultMessage;
    private long date_now;
    private double fsnew_data;
    private String fxnew;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFsnew() {
        return fsnew;
    }

    public void setFsnew(String fsnew) {
        this.fsnew = fsnew;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public double getFxnew_data() {
        return fxnew_data;
    }

    public void setFxnew_data(double fxnew_data) {
        this.fxnew_data = fxnew_data;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public long getDate_now() {
        return date_now;
    }

    public void setDate_now(long date_now) {
        this.date_now = date_now;
    }

    public double getFsnew_data() {
        return fsnew_data;
    }

    public void setFsnew_data(double fsnew_data) {
        this.fsnew_data = fsnew_data;
    }

    public String getFxnew() {
        return fxnew;
    }

    public void setFxnew(String fxnew) {
        this.fxnew = fxnew;
    }
}
