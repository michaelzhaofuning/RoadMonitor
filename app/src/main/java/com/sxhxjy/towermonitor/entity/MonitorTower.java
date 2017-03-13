package com.sxhxjy.towermonitor.entity;

/**
 * Created by dell on 2017/3/12.
 */

public class MonitorTower {
    /**
     * typeName : 三管塔
     * alarmNum : 0
     * typeId : 4028811557217d2d01572190d005001d
     * typeNum : 1
     */

    private String typeName;
    private String alarmNum;
    private String typeId;
    private String typeNum;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAlarmNum() {
        return alarmNum;
    }

    public void setAlarmNum(String alarmNum) {
        this.alarmNum = alarmNum;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(String typeNum) {
        this.typeNum = typeNum;
    }
}
