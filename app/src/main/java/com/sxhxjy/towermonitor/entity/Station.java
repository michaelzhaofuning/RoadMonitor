package com.sxhxjy.towermonitor.entity;

/**
 * 2016/9/14
 *
 * @author Michael Zhao
 */
public class Station {
    /**
     * pointName : 渗压计
     * id : fc234e7b587be57901587be93a3b0006
     * picLeft : 640
     * name : 渗压7号
     * pointId : fc234e7b587b56a801587bab0e760003
     * picTop : 400
     * code : SY-7
     */

    private String pointName;
    private String id;
    private int picLeft;
    private String name;
    private String pointId;
    private int picTop;
    private String code;

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPicLeft() {
        return picLeft;
    }

    public void setPicLeft(int picLeft) {
        this.picLeft = picLeft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public int getPicTop() {
        return picTop;
    }

    public void setPicTop(int picTop) {
        this.picTop = picTop;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
