package com.sxhxjy.towermonitor.entity;

import java.util.List;

/**
 * Created by zm on 2016/12/23.
 */

public class States {


    /**
     * data : [{"pointName":"土岩深层位移","orgId":"4028812c57b6993b0157b6ab181f0002","saveTime":"2016-12-06 19:01:12.0","stationName":"土岩位移监测点6","orgName":"双塔窑洞","stationState":"0"},{"pointName":"测斜监测","orgId":"4028812c57b6993b0157b6ab181f0002","saveTime":"2016-12-19 14:48:23.0","stationName":"测斜监测点1","orgName":"双塔窑洞","stationState":"1"},{"pointName":"渗压计","orgId":"4028812c57b6993b0157b6ab181f0002","saveTime":"2016-12-18 18:29:55.0","stationName":"渗压监测点1","orgName":"双塔窑洞","stationState":"0"},{"pointName":"渗压计","orgId":"4028812c57b6993b0157b6ab181f0002","saveTime":"2016-12-18 18:29:55.0","stationName":"渗压监测点2","orgName":"双塔窑洞","stationState":"0"},{"pointName":"温度传感器","orgId":"4028812c57b6993b0157b6ab181f0002","saveTime":"2016-12-06 19:01:12.0","stationName":"温度监测点6","orgName":"双塔窑洞","stationState":"0"}]
     * resultCode : 200
     * resultMessage : 查询成功
     */

    private String resultCode;
    private String resultMessage;
    private List<DataBean> data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pointName : 土岩深层位移、、地点
         * orgId : 4028812c57b6993b0157b6ab181f0002
         * saveTime : 2016-12-06 19:01:12.0
         * stationName : 土岩位移监测点6。。传感器名称
         * orgName : 双塔窑洞  ，监测点
         * stationState : 0
         */

        private String pointName;
        private String orgId;
        private String saveTime;
        private String stationName;
        private String orgName;
        private String stationState;

        public String getPointName() {
            return pointName;
        }

        public void setPointName(String pointName) {
            this.pointName = pointName;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getSaveTime() {
            return saveTime;
        }

        public void setSaveTime(String saveTime) {
            this.saveTime = saveTime;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getStationState() {
            return stationState;
        }

        public void setStationState(String stationState) {
            this.stationState = stationState;
        }
    }
}
