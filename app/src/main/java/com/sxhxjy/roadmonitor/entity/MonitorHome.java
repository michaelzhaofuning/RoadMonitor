package com.sxhxjy.roadmonitor.entity;

/**
 * Created by dell on 2017/3/12.
 */

public class MonitorHome {
    /**
     * id : fc234e7b587be57901587be93a3b0002
     * name : 风向
     * saveTime : 1489195678000
     * sid : 4028812c57b6993b0157b6ab181f0002
     * monitorcode : FXL02
     * code : FXL-2
     * state : 0
     * cid : 4028812c57b7e2290157b82c1bf50006
     * sensorMonitoringPoints : {"id":"4028812c57b7e2290157b82c1bf50006","fatherId":"4028812c57b7e2290157b82883fb0005","name":"风向传感器","fieldOne":"方位","fieldTwo":"°","fieldThree":"","saveTime":null,"cstate":0,"remark":"4","cstype":"40288115568d152901568d3f0a4f0001","priDict":{"id":"40288115568d152901568d3f0a4f0001","word":"device_type","code":"1","value":"平面值","remark":"类似温度这样的平面显示值","saveTime":1471248534000},"unit":"","style":null,"parentPoint":null,"childrenPoint":null}
     * cname : 风向传感器
     * ctype : 40288115568d152901568d3f0a4f0001
     * cunit : N
     * priDict : {"id":"40288115568d152901568d3f0a4f0001","word":"device_type","code":"1","value":"平面值","remark":"类似温度这样的平面显示值","saveTime":1471248534000}
     * xmin : null
     * xmax : null
     * ymin : null
     * ymax : null
     * zmin : null
     * zmax : null
     * xOneThreshold : 无
     * xTwoThreshold : 无
     * xThreeThreshold : 无
     * xFourThreshold :
     * yOneThreshold :
     * yTwoThreshold :
     * yThreeThreshold :
     * yFourThreshold :
     * zOneThreshold :
     * zTwoThreshold :
     * zThreeThreshold :
     * zFourThreshold :
     * longitude : 112.640566
     * latitude : 37.835524
     * picLeft : 640
     * picTop : 80
     * gid : null
     * selectGroupId : null
     * pointName : null
     * count : 2
     * alarmCount : 0
     * runHour : 21
     */

    private String id;
    private String name;
    private long saveTime;
    private String sid;
    private String monitorcode;
    private String code;
    private String state;
    private String cid;
    private SensorMonitoringPointsBean sensorMonitoringPoints;
    private String cname;
    private String ctype;
    private String cunit;
    private PriDictBeanX priDict;
    private Object xmin;
    private Object xmax;
    private Object ymin;
    private Object ymax;
    private Object zmin;
    private Object zmax;
    private String xOneThreshold;
    private String xTwoThreshold;
    private String xThreeThreshold;
    private String xFourThreshold;
    private String yOneThreshold;
    private String yTwoThreshold;
    private String yThreeThreshold;
    private String yFourThreshold;
    private String zOneThreshold;
    private String zTwoThreshold;
    private String zThreeThreshold;
    private String zFourThreshold;
    private String longitude;
    private String latitude;
    private String picLeft;
    private String picTop;
    private Object gid;
    private Object selectGroupId;
    private Object pointName;
    private String count;
    private String alarmCount;
    private String runHour;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getMonitorcode() {
        return monitorcode;
    }

    public void setMonitorcode(String monitorcode) {
        this.monitorcode = monitorcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public SensorMonitoringPointsBean getSensorMonitoringPoints() {
        return sensorMonitoringPoints;
    }

    public void setSensorMonitoringPoints(SensorMonitoringPointsBean sensorMonitoringPoints) {
        this.sensorMonitoringPoints = sensorMonitoringPoints;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getCunit() {
        return cunit;
    }

    public void setCunit(String cunit) {
        this.cunit = cunit;
    }

    public PriDictBeanX getPriDict() {
        return priDict;
    }

    public void setPriDict(PriDictBeanX priDict) {
        this.priDict = priDict;
    }

    public Object getXmin() {
        return xmin;
    }

    public void setXmin(Object xmin) {
        this.xmin = xmin;
    }

    public Object getXmax() {
        return xmax;
    }

    public void setXmax(Object xmax) {
        this.xmax = xmax;
    }

    public Object getYmin() {
        return ymin;
    }

    public void setYmin(Object ymin) {
        this.ymin = ymin;
    }

    public Object getYmax() {
        return ymax;
    }

    public void setYmax(Object ymax) {
        this.ymax = ymax;
    }

    public Object getZmin() {
        return zmin;
    }

    public void setZmin(Object zmin) {
        this.zmin = zmin;
    }

    public Object getZmax() {
        return zmax;
    }

    public void setZmax(Object zmax) {
        this.zmax = zmax;
    }

    public String getXOneThreshold() {
        return xOneThreshold;
    }

    public void setXOneThreshold(String xOneThreshold) {
        this.xOneThreshold = xOneThreshold;
    }

    public String getXTwoThreshold() {
        return xTwoThreshold;
    }

    public void setXTwoThreshold(String xTwoThreshold) {
        this.xTwoThreshold = xTwoThreshold;
    }

    public String getXThreeThreshold() {
        return xThreeThreshold;
    }

    public void setXThreeThreshold(String xThreeThreshold) {
        this.xThreeThreshold = xThreeThreshold;
    }

    public String getXFourThreshold() {
        return xFourThreshold;
    }

    public void setXFourThreshold(String xFourThreshold) {
        this.xFourThreshold = xFourThreshold;
    }

    public String getYOneThreshold() {
        return yOneThreshold;
    }

    public void setYOneThreshold(String yOneThreshold) {
        this.yOneThreshold = yOneThreshold;
    }

    public String getYTwoThreshold() {
        return yTwoThreshold;
    }

    public void setYTwoThreshold(String yTwoThreshold) {
        this.yTwoThreshold = yTwoThreshold;
    }

    public String getYThreeThreshold() {
        return yThreeThreshold;
    }

    public void setYThreeThreshold(String yThreeThreshold) {
        this.yThreeThreshold = yThreeThreshold;
    }

    public String getYFourThreshold() {
        return yFourThreshold;
    }

    public void setYFourThreshold(String yFourThreshold) {
        this.yFourThreshold = yFourThreshold;
    }

    public String getZOneThreshold() {
        return zOneThreshold;
    }

    public void setZOneThreshold(String zOneThreshold) {
        this.zOneThreshold = zOneThreshold;
    }

    public String getZTwoThreshold() {
        return zTwoThreshold;
    }

    public void setZTwoThreshold(String zTwoThreshold) {
        this.zTwoThreshold = zTwoThreshold;
    }

    public String getZThreeThreshold() {
        return zThreeThreshold;
    }

    public void setZThreeThreshold(String zThreeThreshold) {
        this.zThreeThreshold = zThreeThreshold;
    }

    public String getZFourThreshold() {
        return zFourThreshold;
    }

    public void setZFourThreshold(String zFourThreshold) {
        this.zFourThreshold = zFourThreshold;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPicLeft() {
        return picLeft;
    }

    public void setPicLeft(String picLeft) {
        this.picLeft = picLeft;
    }

    public String getPicTop() {
        return picTop;
    }

    public void setPicTop(String picTop) {
        this.picTop = picTop;
    }

    public Object getGid() {
        return gid;
    }

    public void setGid(Object gid) {
        this.gid = gid;
    }

    public Object getSelectGroupId() {
        return selectGroupId;
    }

    public void setSelectGroupId(Object selectGroupId) {
        this.selectGroupId = selectGroupId;
    }

    public Object getPointName() {
        return pointName;
    }

    public void setPointName(Object pointName) {
        this.pointName = pointName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAlarmCount() {
        return alarmCount;
    }

    public void setAlarmCount(String alarmCount) {
        this.alarmCount = alarmCount;
    }

    public String getRunHour() {
        return runHour;
    }

    public void setRunHour(String runHour) {
        this.runHour = runHour;
    }

    public static class SensorMonitoringPointsBean {
        /**
         * id : 4028812c57b7e2290157b82c1bf50006
         * fatherId : 4028812c57b7e2290157b82883fb0005
         * name : 风向传感器
         * fieldOne : 方位
         * fieldTwo : °
         * fieldThree :
         * saveTime : null
         * cstate : 0
         * remark : 4
         * cstype : 40288115568d152901568d3f0a4f0001
         * priDict : {"id":"40288115568d152901568d3f0a4f0001","word":"device_type","code":"1","value":"平面值","remark":"类似温度这样的平面显示值","saveTime":1471248534000}
         * unit :
         * style : null
         * parentPoint : null
         * childrenPoint : null
         */

        private String id;
        private String fatherId;
        private String name;
        private String fieldOne;
        private String fieldTwo;
        private String fieldThree;
        private Object saveTime;
        private int cstate;
        private String remark;
        private String cstype;
        private PriDictBean priDict;
        private String unit;
        private Object style;
        private Object parentPoint;
        private Object childrenPoint;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFatherId() {
            return fatherId;
        }

        public void setFatherId(String fatherId) {
            this.fatherId = fatherId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFieldOne() {
            return fieldOne;
        }

        public void setFieldOne(String fieldOne) {
            this.fieldOne = fieldOne;
        }

        public String getFieldTwo() {
            return fieldTwo;
        }

        public void setFieldTwo(String fieldTwo) {
            this.fieldTwo = fieldTwo;
        }

        public String getFieldThree() {
            return fieldThree;
        }

        public void setFieldThree(String fieldThree) {
            this.fieldThree = fieldThree;
        }

        public Object getSaveTime() {
            return saveTime;
        }

        public void setSaveTime(Object saveTime) {
            this.saveTime = saveTime;
        }

        public int getCstate() {
            return cstate;
        }

        public void setCstate(int cstate) {
            this.cstate = cstate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCstype() {
            return cstype;
        }

        public void setCstype(String cstype) {
            this.cstype = cstype;
        }

        public PriDictBean getPriDict() {
            return priDict;
        }

        public void setPriDict(PriDictBean priDict) {
            this.priDict = priDict;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Object getStyle() {
            return style;
        }

        public void setStyle(Object style) {
            this.style = style;
        }

        public Object getParentPoint() {
            return parentPoint;
        }

        public void setParentPoint(Object parentPoint) {
            this.parentPoint = parentPoint;
        }

        public Object getChildrenPoint() {
            return childrenPoint;
        }

        public void setChildrenPoint(Object childrenPoint) {
            this.childrenPoint = childrenPoint;
        }

        public static class PriDictBean {
            /**
             * id : 40288115568d152901568d3f0a4f0001
             * word : device_type
             * code : 1
             * value : 平面值
             * remark : 类似温度这样的平面显示值
             * saveTime : 1471248534000
             */

            private String id;
            private String word;
            private String code;
            private String value;
            private String remark;
            private long saveTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public long getSaveTime() {
                return saveTime;
            }

            public void setSaveTime(long saveTime) {
                this.saveTime = saveTime;
            }
        }
    }

    public static class PriDictBeanX {
        /**
         * id : 40288115568d152901568d3f0a4f0001
         * word : device_type
         * code : 1
         * value : 平面值
         * remark : 类似温度这样的平面显示值
         * saveTime : 1471248534000
         */

        private String id;
        private String word;
        private String code;
        private String value;
        private String remark;
        private long saveTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getSaveTime() {
            return saveTime;
        }

        public void setSaveTime(long saveTime) {
            this.saveTime = saveTime;
        }
    }
}
