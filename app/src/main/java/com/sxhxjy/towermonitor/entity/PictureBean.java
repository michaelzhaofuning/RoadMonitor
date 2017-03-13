package com.sxhxjy.towermonitor.entity;

/**
 * Created by dell on 2017/3/13.
 */

public class PictureBean {
    /**
     * newPic : {"id":50,"devCode":"SXTTTL01","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic_TieTa\\2017030417200140.jpeg","comPicPath":"E:\\Pic\\Pic_TieTa\\2017030418030768.jpeg","x1Change":-0.22282,"y1Change":0.78447,"x2Change":-0.25802,"y2Change":0.42757,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1489135515000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic_TieTa\\2017030417200140_press.jpeg","comPicPathPress":"uploadPhotos\\Pic_TieTa\\2017030418030768_press.jpeg"}
     * oldPic : {"id":32,"devId":"SXTTTL01","picDirFilename":"E:\\Pic\\Pic_TieTa\\2017030417200140.jpeg","baseX":"513","baseY":"612","lengthX":"41","lengthY":"41","isSelectBase":1,"rangePixel":"0.0065212","moniter1X":"880","moniter1Y":"667","moniter2X":"1350","moniter2Y":"673","moniter3X":"0","moniter3Y":"0","moniter4X":"0","moniter4Y":"0","moniterNum":"2","picPathPress":"uploadPhotos\\Pic_TieTa\\2017030417200140_press.jpeg","picState":"new"}
     */

    private NewPicBean newPic;
    private OldPicBean oldPic;

    public NewPicBean getNewPic() {
        return newPic;
    }

    public void setNewPic(NewPicBean newPic) {
        this.newPic = newPic;
    }

    public OldPicBean getOldPic() {
        return oldPic;
    }

    public void setOldPic(OldPicBean oldPic) {
        this.oldPic = oldPic;
    }

    public static class NewPicBean {
        /**
         * id : 50
         * devCode : SXTTTL01
         * oldPicId : null
         * oldPicPath : E:\Pic\Pic_TieTa\2017030417200140.jpeg
         * comPicPath : E:\Pic\Pic_TieTa\2017030418030768.jpeg
         * x1Change : -0.22282
         * y1Change : 0.78447
         * x2Change : -0.25802
         * y2Change : 0.42757
         * x3Change : 0.0
         * y3Change : 0.0
         * x4Change : 0.0
         * y4Change : 0.0
         * saveTime : 1489135515000
         * deleteState : 0
         * isProcess : 1
         * oldPicPathPress : uploadPhotos\Pic_TieTa\2017030417200140_press.jpeg
         * comPicPathPress : uploadPhotos\Pic_TieTa\2017030418030768_press.jpeg
         */

        private int id;
        private String devCode;
        private Object oldPicId;
        private String oldPicPath;
        private String comPicPath;
        private double x1Change;
        private double y1Change;
        private double x2Change;
        private double y2Change;
        private double x3Change;
        private double y3Change;
        private double x4Change;
        private double y4Change;
        private long saveTime;
        private String deleteState;
        private String isProcess;
        private String oldPicPathPress;
        private String comPicPathPress;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDevCode() {
            return devCode;
        }

        public void setDevCode(String devCode) {
            this.devCode = devCode;
        }

        public Object getOldPicId() {
            return oldPicId;
        }

        public void setOldPicId(Object oldPicId) {
            this.oldPicId = oldPicId;
        }

        public String getOldPicPath() {
            return oldPicPath;
        }

        public void setOldPicPath(String oldPicPath) {
            this.oldPicPath = oldPicPath;
        }

        public String getComPicPath() {
            return comPicPath;
        }

        public void setComPicPath(String comPicPath) {
            this.comPicPath = comPicPath;
        }

        public double getX1Change() {
            return x1Change;
        }

        public void setX1Change(double x1Change) {
            this.x1Change = x1Change;
        }

        public double getY1Change() {
            return y1Change;
        }

        public void setY1Change(double y1Change) {
            this.y1Change = y1Change;
        }

        public double getX2Change() {
            return x2Change;
        }

        public void setX2Change(double x2Change) {
            this.x2Change = x2Change;
        }

        public double getY2Change() {
            return y2Change;
        }

        public void setY2Change(double y2Change) {
            this.y2Change = y2Change;
        }

        public double getX3Change() {
            return x3Change;
        }

        public void setX3Change(double x3Change) {
            this.x3Change = x3Change;
        }

        public double getY3Change() {
            return y3Change;
        }

        public void setY3Change(double y3Change) {
            this.y3Change = y3Change;
        }

        public double getX4Change() {
            return x4Change;
        }

        public void setX4Change(double x4Change) {
            this.x4Change = x4Change;
        }

        public double getY4Change() {
            return y4Change;
        }

        public void setY4Change(double y4Change) {
            this.y4Change = y4Change;
        }

        public long getSaveTime() {
            return saveTime;
        }

        public void setSaveTime(long saveTime) {
            this.saveTime = saveTime;
        }

        public String getDeleteState() {
            return deleteState;
        }

        public void setDeleteState(String deleteState) {
            this.deleteState = deleteState;
        }

        public String getIsProcess() {
            return isProcess;
        }

        public void setIsProcess(String isProcess) {
            this.isProcess = isProcess;
        }

        public String getOldPicPathPress() {
            return oldPicPathPress;
        }

        public void setOldPicPathPress(String oldPicPathPress) {
            this.oldPicPathPress = oldPicPathPress;
        }

        public String getComPicPathPress() {
            return comPicPathPress;
        }

        public void setComPicPathPress(String comPicPathPress) {
            this.comPicPathPress = comPicPathPress;
        }
    }

    public static class OldPicBean {
        /**
         * id : 32
         * devId : SXTTTL01
         * picDirFilename : E:\Pic\Pic_TieTa\2017030417200140.jpeg
         * baseX : 513
         * baseY : 612
         * lengthX : 41
         * lengthY : 41
         * isSelectBase : 1
         * rangePixel : 0.0065212
         * moniter1X : 880
         * moniter1Y : 667
         * moniter2X : 1350
         * moniter2Y : 673
         * moniter3X : 0
         * moniter3Y : 0
         * moniter4X : 0
         * moniter4Y : 0
         * moniterNum : 2
         * picPathPress : uploadPhotos\Pic_TieTa\2017030417200140_press.jpeg
         * picState : new
         */

        private int id;
        private String devId;
        private String picDirFilename;
        private String baseX;
        private String baseY;
        private String lengthX;
        private String lengthY;
        private int isSelectBase;
        private String rangePixel;
        private String moniter1X;
        private String moniter1Y;
        private String moniter2X;
        private String moniter2Y;
        private String moniter3X;
        private String moniter3Y;
        private String moniter4X;
        private String moniter4Y;
        private String moniterNum;
        private String picPathPress;
        private String picState;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDevId() {
            return devId;
        }

        public void setDevId(String devId) {
            this.devId = devId;
        }

        public String getPicDirFilename() {
            return picDirFilename;
        }

        public void setPicDirFilename(String picDirFilename) {
            this.picDirFilename = picDirFilename;
        }

        public String getBaseX() {
            return baseX;
        }

        public void setBaseX(String baseX) {
            this.baseX = baseX;
        }

        public String getBaseY() {
            return baseY;
        }

        public void setBaseY(String baseY) {
            this.baseY = baseY;
        }

        public String getLengthX() {
            return lengthX;
        }

        public void setLengthX(String lengthX) {
            this.lengthX = lengthX;
        }

        public String getLengthY() {
            return lengthY;
        }

        public void setLengthY(String lengthY) {
            this.lengthY = lengthY;
        }

        public int getIsSelectBase() {
            return isSelectBase;
        }

        public void setIsSelectBase(int isSelectBase) {
            this.isSelectBase = isSelectBase;
        }

        public String getRangePixel() {
            return rangePixel;
        }

        public void setRangePixel(String rangePixel) {
            this.rangePixel = rangePixel;
        }

        public String getMoniter1X() {
            return moniter1X;
        }

        public void setMoniter1X(String moniter1X) {
            this.moniter1X = moniter1X;
        }

        public String getMoniter1Y() {
            return moniter1Y;
        }

        public void setMoniter1Y(String moniter1Y) {
            this.moniter1Y = moniter1Y;
        }

        public String getMoniter2X() {
            return moniter2X;
        }

        public void setMoniter2X(String moniter2X) {
            this.moniter2X = moniter2X;
        }

        public String getMoniter2Y() {
            return moniter2Y;
        }

        public void setMoniter2Y(String moniter2Y) {
            this.moniter2Y = moniter2Y;
        }

        public String getMoniter3X() {
            return moniter3X;
        }

        public void setMoniter3X(String moniter3X) {
            this.moniter3X = moniter3X;
        }

        public String getMoniter3Y() {
            return moniter3Y;
        }

        public void setMoniter3Y(String moniter3Y) {
            this.moniter3Y = moniter3Y;
        }

        public String getMoniter4X() {
            return moniter4X;
        }

        public void setMoniter4X(String moniter4X) {
            this.moniter4X = moniter4X;
        }

        public String getMoniter4Y() {
            return moniter4Y;
        }

        public void setMoniter4Y(String moniter4Y) {
            this.moniter4Y = moniter4Y;
        }

        public String getMoniterNum() {
            return moniterNum;
        }

        public void setMoniterNum(String moniterNum) {
            this.moniterNum = moniterNum;
        }

        public String getPicPathPress() {
            return picPathPress;
        }

        public void setPicPathPress(String picPathPress) {
            this.picPathPress = picPathPress;
        }

        public String getPicState() {
            return picState;
        }

        public void setPicState(String picState) {
            this.picState = picState;
        }
    }
}
