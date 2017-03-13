package com.sxhxjy.towermonitor.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zm on 2016/11/16.
 */

public class RecordResult implements Serializable{

    /**
     * data : {"content":[{"newPicList":[{"id":41,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312320890.jpeg","x1Change":0.07073,"y1Change":-0.08193,"x2Change":0.31482,"y2Change":-0.07472,"x3Change":-0.0115,"y3Change":-0.06468,"x4Change":0.34969,"y4Change":-0.05931,"saveTime":1481510404000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312320890_press.jpeg"},{"id":40,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312315859.jpeg","x1Change":-0.00707,"y1Change":-0.08951,"x2Change":0.33569,"y2Change":-0.08984,"x3Change":0.0259,"y3Change":-0.07978,"x4Change":-0.29187,"y4Change":-0.09644,"saveTime":1481424004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312315859_press.jpeg"},{"id":39,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312315012.jpeg","x1Change":-0.00766,"y1Change":-0.04942,"x2Change":0.68635,"y2Change":-0.06113,"x3Change":0.01609,"y3Change":-0.04157,"x4Change":-0.14112,"y4Change":-0.04294,"saveTime":1481337604000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312315012_press.jpeg"},{"id":38,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312313921.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481251204000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312313921_press.jpeg"},{"id":37,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312312484.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481164804000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312312484_press.jpeg"},{"id":36,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312311346.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481078404000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312311346_press.jpeg"},{"id":35,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312310093.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480992004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312310093_press.jpeg"},{"id":34,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480902004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312304884_press.jpeg"}],"oldPicDetail":{"id":30,"devId":"SXT05","picDirFilename":"E:\\Pic\\Pic4\\2016120312304884.jpeg","baseX":"341","baseY":"1262","lengthX":"41","lengthY":"41","isSelectBase":1,"rangePixel":"0.00017062","moniter1X":"1282","moniter1Y":"730","moniter2X":"0","moniter2Y":"0","moniter3X":"0","moniter3Y":"0","moniter4X":"0","moniter4Y":"0","moniterNum":"1","deleteState":"0","picPathPress":"uploadPhotos\\Pic5\\2016120312304884_press.jpeg","picState":"new"}},{"newPicList":[{"id":31,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312320890.jpeg","x1Change":0.07073,"y1Change":-0.08193,"x2Change":0.31482,"y2Change":-0.07472,"x3Change":-0.0115,"y3Change":-0.06468,"x4Change":0.34969,"y4Change":-0.05931,"saveTime":1481510404000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312315859_press.jpeg"},{"id":28,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312315859.jpeg","x1Change":-0.00707,"y1Change":-0.08951,"x2Change":0.33569,"y2Change":-0.08984,"x3Change":0.0259,"y3Change":-0.07978,"x4Change":-0.29187,"y4Change":-0.09644,"saveTime":1481424004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312315859_press.jpeg"},{"id":25,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312315012.jpeg","x1Change":-0.00766,"y1Change":-0.04942,"x2Change":0.68635,"y2Change":-0.06113,"x3Change":0.01609,"y3Change":-0.04157,"x4Change":-0.14112,"y4Change":-0.04294,"saveTime":1481337604000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312315012_press.jpeg"},{"id":22,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312313921.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481251204000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312313921_press.jpeg"},{"id":19,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312312484.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481164804000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312312484_press.jpeg"},{"id":16,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312311346.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481078404000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312311346_press.jpeg"},{"id":13,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312310093.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480992004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312310093_press.jpeg"},{"id":10,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312304884.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480902004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg"}],"oldPicDetail":{"id":29,"devId":"SXT04","picDirFilename":"E:\\Pic\\Pic4\\2016120312295578.jpeg","baseX":"638","baseY":"546","lengthX":"41","lengthY":"41","isSelectBase":1,"rangePixel":"0.00016835","moniter1X":"1288","moniter1Y":"395","moniter2X":"1294","moniter2Y":"850","moniter3X":"1496","moniter3Y":"653","moniter4X":"1488","moniter4Y":"403","moniterNum":"4","deleteState":"0","picPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","picState":"new"}},{"newPicList":[{"id":29,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312320165.jpeg","x1Change":-0.29449,"y1Change":0.365,"x2Change":-0.49493,"y2Change":0.18911,"x3Change":-0.45183,"y3Change":0.22142,"x4Change":0,"y4Change":0,"saveTime":1480909204000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312320165_press.jpeg"},{"id":32,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312321118.jpeg","x1Change":-2.2044,"y1Change":1.2216,"x2Change":-2.2065,"y2Change":1.2329,"x3Change":-2.1373,"y3Change":1.2934,"x4Change":0,"y4Change":0,"saveTime":1480905604000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312321118_press.jpeg"},{"id":17,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312311700.jpeg","x1Change":0.01971,"y1Change":-0.40313,"x2Change":-0.04979,"y2Change":-0.5157,"x3Change":-0.04783,"y3Change":-0.52509,"x4Change":0,"y4Change":0,"saveTime":1480822804000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312311700_press.jpeg"},{"id":20,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312312896.jpeg","x1Change":0.41676,"y1Change":0.53975,"x2Change":-0.08166,"y2Change":-0.07299,"x3Change":-0.07918,"y3Change":-0.08052,"x4Change":0,"y4Change":0,"saveTime":1480819204000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312312896_press.jpeg"},{"id":23,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312314215.jpeg","x1Change":-0.27174,"y1Change":0.17518,"x2Change":-0.25922,"y2Change":0.19589,"x3Change":0.15178,"y3Change":0.02843,"x4Change":0,"y4Change":0,"saveTime":1480815604000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312314215_press.jpeg"},{"id":26,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312315340.jpeg","x1Change":0.29076,"y1Change":-0.18353,"x2Change":0.28786,"y2Change":-0.16956,"x3Change":0.3355,"y3Change":-0.14357,"x4Change":0,"y4Change":0,"saveTime":1480812004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312315340_press.jpeg"},{"id":8,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","x1Change":0,"y1Change":0,"x2Change":0,"y2Change":0,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480732804000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg"},{"id":11,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312305118.jpeg","x1Change":0.0642,"y1Change":0.65392,"x2Change":0.01668,"y2Change":-0.09149,"x3Change":0.01828,"y3Change":-0.09323,"x4Change":0,"y4Change":0,"saveTime":1480729204000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312305118_press.jpeg"},{"id":14,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312310543.jpeg","x1Change":0.2159,"y1Change":-0.03249,"x2Change":-0.00495,"y2Change":-0.16491,"x3Change":-0.00309,"y3Change":-0.16759,"x4Change":0,"y4Change":0,"saveTime":1480725604000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312310543_press.jpeg"}],"oldPicDetail":{"id":28,"devId":"SXT03","picDirFilename":"E:\\Pic\\Pic3\\2016120312295931.jpeg","baseX":"191","baseY":"778","lengthX":"41","lengthY":"41","isSelectBase":1,"rangePixel":"0.00020156","moniter1X":"1070","moniter1Y":"707","moniter2X":"882","moniter2Y":"529","moniter3X":"824","moniter3Y":"223","moniter4X":"0","moniter4Y":"0","moniterNum":"3","deleteState":"0","picPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","picState":"new"}},{"newPicList":[{"id":33,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312321387.jpeg","x1Change":0.55844,"y1Change":0.42031,"x2Change":0.1346,"y2Change":0.33528,"x3Change":0.46361,"y3Change":0.37267,"x4Change":0,"y4Change":0,"saveTime":1480736404000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312321387_press.jpeg"},{"id":21,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312313212.jpeg","x1Change":0.30062,"y1Change":0.52647,"x2Change":-0.09119,"y2Change":0.40079,"x3Change":0.48206,"y3Change":0.35466,"x4Change":0,"y4Change":0,"saveTime":1480650004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312313212_press.jpeg"},{"id":24,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312314553.jpeg","x1Change":-0.05682,"y1Change":0.22811,"x2Change":0.34749,"y2Change":0.10637,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480646409000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312314553_press.jpeg"},{"id":27,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312315653.jpeg","x1Change":0.14592,"y1Change":0.28078,"x2Change":0.15162,"y2Change":-0.02345,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480642814000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312315653_press.jpeg"},{"id":30,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312320331.jpeg","x1Change":0.1157,"y1Change":0.3308,"x2Change":0.2042,"y2Change":0.1313,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480639214000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312320331_press.jpeg"},{"id":9,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","x1Change":0,"y1Change":0,"x2Change":0,"y2Change":0,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480563576000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg"},{"id":12,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312305365.jpeg","x1Change":0.22446,"y1Change":0.51141,"x2Change":0.1653,"y2Change":0.35065,"x3Change":0.4436,"y3Change":0.45834,"x4Change":0,"y4Change":0,"saveTime":1480559983000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312305365_press.jpeg"},{"id":15,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312311040.jpeg","x1Change":0.21067,"y1Change":0.54147,"x2Change":0.13657,"y2Change":0.31885,"x3Change":0.46031,"y3Change":0.35042,"x4Change":0,"y4Change":0,"saveTime":1480556392000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312311040_press.jpeg"},{"id":18,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312312075.jpeg","x1Change":0.26677,"y1Change":0.48826,"x2Change":0.13806,"y2Change":0.29418,"x3Change":0.42065,"y3Change":0.18712,"x4Change":0,"y4Change":0,"saveTime":1480552798000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312312075_press.jpeg"}],"oldPicDetail":{"id":31,"devId":"SXT02","picDirFilename":"E:\\Pic\\Pic2\\2016120312300328.jpeg","baseX":"669","baseY":"812","lengthX":"41","lengthY":"41","isSelectBase":1,"rangePixel":"0.00018209","moniter1X":"736","moniter1Y":"539","moniter2X":"1158","moniter2Y":"599","moniter3X":"0","moniter3Y":"0","moniter4X":"0","moniter4Y":"0","moniterNum":"2","deleteState":"0","picPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","picState":"new"}}],"totalElements":"4"}
     * resultCode : 200
     * resultMessage : 查询成功
     */

    private DataBean data;
    private String resultCode;
    private String resultMessage;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean  implements Serializable{
        /**
         * content : [{"newPicList":[{"id":41,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312320890.jpeg","x1Change":0.07073,"y1Change":-0.08193,"x2Change":0.31482,"y2Change":-0.07472,"x3Change":-0.0115,"y3Change":-0.06468,"x4Change":0.34969,"y4Change":-0.05931,"saveTime":1481510404000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312320890_press.jpeg"},{"id":40,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312315859.jpeg","x1Change":-0.00707,"y1Change":-0.08951,"x2Change":0.33569,"y2Change":-0.08984,"x3Change":0.0259,"y3Change":-0.07978,"x4Change":-0.29187,"y4Change":-0.09644,"saveTime":1481424004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312315859_press.jpeg"},{"id":39,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312315012.jpeg","x1Change":-0.00766,"y1Change":-0.04942,"x2Change":0.68635,"y2Change":-0.06113,"x3Change":0.01609,"y3Change":-0.04157,"x4Change":-0.14112,"y4Change":-0.04294,"saveTime":1481337604000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312315012_press.jpeg"},{"id":38,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312313921.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481251204000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312313921_press.jpeg"},{"id":37,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312312484.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481164804000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312312484_press.jpeg"},{"id":36,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312311346.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481078404000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312311346_press.jpeg"},{"id":35,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312310093.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480992004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312310093_press.jpeg"},{"id":34,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480902004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312304884_press.jpeg"}],"oldPicDetail":{"id":30,"devId":"SXT05","picDirFilename":"E:\\Pic\\Pic4\\2016120312304884.jpeg","baseX":"341","baseY":"1262","lengthX":"41","lengthY":"41","isSelectBase":1,"rangePixel":"0.00017062","moniter1X":"1282","moniter1Y":"730","moniter2X":"0","moniter2Y":"0","moniter3X":"0","moniter3Y":"0","moniter4X":"0","moniter4Y":"0","moniterNum":"1","deleteState":"0","picPathPress":"uploadPhotos\\Pic5\\2016120312304884_press.jpeg","picState":"new"}},{"newPicList":[{"id":31,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312320890.jpeg","x1Change":0.07073,"y1Change":-0.08193,"x2Change":0.31482,"y2Change":-0.07472,"x3Change":-0.0115,"y3Change":-0.06468,"x4Change":0.34969,"y4Change":-0.05931,"saveTime":1481510404000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312315859_press.jpeg"},{"id":28,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312315859.jpeg","x1Change":-0.00707,"y1Change":-0.08951,"x2Change":0.33569,"y2Change":-0.08984,"x3Change":0.0259,"y3Change":-0.07978,"x4Change":-0.29187,"y4Change":-0.09644,"saveTime":1481424004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312315859_press.jpeg"},{"id":25,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312315012.jpeg","x1Change":-0.00766,"y1Change":-0.04942,"x2Change":0.68635,"y2Change":-0.06113,"x3Change":0.01609,"y3Change":-0.04157,"x4Change":-0.14112,"y4Change":-0.04294,"saveTime":1481337604000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312315012_press.jpeg"},{"id":22,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312313921.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481251204000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312313921_press.jpeg"},{"id":19,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312312484.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481164804000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312312484_press.jpeg"},{"id":16,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312311346.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481078404000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312311346_press.jpeg"},{"id":13,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312310093.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480992004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312310093_press.jpeg"},{"id":10,"devCode":"SXT04","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic4\\2016120312295578.jpeg","comPicPath":"E:\\Pic\\Pic4\\2016120312304884.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480902004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","comPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg"}],"oldPicDetail":{"id":29,"devId":"SXT04","picDirFilename":"E:\\Pic\\Pic4\\2016120312295578.jpeg","baseX":"638","baseY":"546","lengthX":"41","lengthY":"41","isSelectBase":1,"rangePixel":"0.00016835","moniter1X":"1288","moniter1Y":"395","moniter2X":"1294","moniter2Y":"850","moniter3X":"1496","moniter3Y":"653","moniter4X":"1488","moniter4Y":"403","moniterNum":"4","deleteState":"0","picPathPress":"uploadPhotos\\Pic4\\2016120312295578_press.jpeg","picState":"new"}},{"newPicList":[{"id":29,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312320165.jpeg","x1Change":-0.29449,"y1Change":0.365,"x2Change":-0.49493,"y2Change":0.18911,"x3Change":-0.45183,"y3Change":0.22142,"x4Change":0,"y4Change":0,"saveTime":1480909204000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312320165_press.jpeg"},{"id":32,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312321118.jpeg","x1Change":-2.2044,"y1Change":1.2216,"x2Change":-2.2065,"y2Change":1.2329,"x3Change":-2.1373,"y3Change":1.2934,"x4Change":0,"y4Change":0,"saveTime":1480905604000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312321118_press.jpeg"},{"id":17,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312311700.jpeg","x1Change":0.01971,"y1Change":-0.40313,"x2Change":-0.04979,"y2Change":-0.5157,"x3Change":-0.04783,"y3Change":-0.52509,"x4Change":0,"y4Change":0,"saveTime":1480822804000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312311700_press.jpeg"},{"id":20,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312312896.jpeg","x1Change":0.41676,"y1Change":0.53975,"x2Change":-0.08166,"y2Change":-0.07299,"x3Change":-0.07918,"y3Change":-0.08052,"x4Change":0,"y4Change":0,"saveTime":1480819204000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312312896_press.jpeg"},{"id":23,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312314215.jpeg","x1Change":-0.27174,"y1Change":0.17518,"x2Change":-0.25922,"y2Change":0.19589,"x3Change":0.15178,"y3Change":0.02843,"x4Change":0,"y4Change":0,"saveTime":1480815604000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312314215_press.jpeg"},{"id":26,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312315340.jpeg","x1Change":0.29076,"y1Change":-0.18353,"x2Change":0.28786,"y2Change":-0.16956,"x3Change":0.3355,"y3Change":-0.14357,"x4Change":0,"y4Change":0,"saveTime":1480812004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312315340_press.jpeg"},{"id":8,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","x1Change":0,"y1Change":0,"x2Change":0,"y2Change":0,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480732804000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg"},{"id":11,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312305118.jpeg","x1Change":0.0642,"y1Change":0.65392,"x2Change":0.01668,"y2Change":-0.09149,"x3Change":0.01828,"y3Change":-0.09323,"x4Change":0,"y4Change":0,"saveTime":1480729204000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312305118_press.jpeg"},{"id":14,"devCode":"SXT03","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic3\\2016120312295931.jpeg","comPicPath":"E:\\Pic\\Pic3\\2016120312310543.jpeg","x1Change":0.2159,"y1Change":-0.03249,"x2Change":-0.00495,"y2Change":-0.16491,"x3Change":-0.00309,"y3Change":-0.16759,"x4Change":0,"y4Change":0,"saveTime":1480725604000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","comPicPathPress":"uploadPhotos\\Pic3\\2016120312310543_press.jpeg"}],"oldPicDetail":{"id":28,"devId":"SXT03","picDirFilename":"E:\\Pic\\Pic3\\2016120312295931.jpeg","baseX":"191","baseY":"778","lengthX":"41","lengthY":"41","isSelectBase":1,"rangePixel":"0.00020156","moniter1X":"1070","moniter1Y":"707","moniter2X":"882","moniter2Y":"529","moniter3X":"824","moniter3Y":"223","moniter4X":"0","moniter4Y":"0","moniterNum":"3","deleteState":"0","picPathPress":"uploadPhotos\\Pic3\\2016120312295931_press.jpeg","picState":"new"}},{"newPicList":[{"id":33,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312321387.jpeg","x1Change":0.55844,"y1Change":0.42031,"x2Change":0.1346,"y2Change":0.33528,"x3Change":0.46361,"y3Change":0.37267,"x4Change":0,"y4Change":0,"saveTime":1480736404000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312321387_press.jpeg"},{"id":21,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312313212.jpeg","x1Change":0.30062,"y1Change":0.52647,"x2Change":-0.09119,"y2Change":0.40079,"x3Change":0.48206,"y3Change":0.35466,"x4Change":0,"y4Change":0,"saveTime":1480650004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312313212_press.jpeg"},{"id":24,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312314553.jpeg","x1Change":-0.05682,"y1Change":0.22811,"x2Change":0.34749,"y2Change":0.10637,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480646409000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312314553_press.jpeg"},{"id":27,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312315653.jpeg","x1Change":0.14592,"y1Change":0.28078,"x2Change":0.15162,"y2Change":-0.02345,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480642814000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312315653_press.jpeg"},{"id":30,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312320331.jpeg","x1Change":0.1157,"y1Change":0.3308,"x2Change":0.2042,"y2Change":0.1313,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480639214000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312320331_press.jpeg"},{"id":9,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","x1Change":0,"y1Change":0,"x2Change":0,"y2Change":0,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480563576000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg"},{"id":12,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312305365.jpeg","x1Change":0.22446,"y1Change":0.51141,"x2Change":0.1653,"y2Change":0.35065,"x3Change":0.4436,"y3Change":0.45834,"x4Change":0,"y4Change":0,"saveTime":1480559983000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312305365_press.jpeg"},{"id":15,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312311040.jpeg","x1Change":0.21067,"y1Change":0.54147,"x2Change":0.13657,"y2Change":0.31885,"x3Change":0.46031,"y3Change":0.35042,"x4Change":0,"y4Change":0,"saveTime":1480556392000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312311040_press.jpeg"},{"id":18,"devCode":"SXT02","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic2\\2016120312300328.jpeg","comPicPath":"E:\\Pic\\Pic2\\2016120312312075.jpeg","x1Change":0.26677,"y1Change":0.48826,"x2Change":0.13806,"y2Change":0.29418,"x3Change":0.42065,"y3Change":0.18712,"x4Change":0,"y4Change":0,"saveTime":1480552798000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","comPicPathPress":"uploadPhotos\\Pic2\\2016120312312075_press.jpeg"}],"oldPicDetail":{"id":31,"devId":"SXT02","picDirFilename":"E:\\Pic\\Pic2\\2016120312300328.jpeg","baseX":"669","baseY":"812","lengthX":"41","lengthY":"41","isSelectBase":1,"rangePixel":"0.00018209","moniter1X":"736","moniter1Y":"539","moniter2X":"1158","moniter2Y":"599","moniter3X":"0","moniter3Y":"0","moniter4X":"0","moniter4Y":"0","moniterNum":"2","deleteState":"0","picPathPress":"uploadPhotos\\Pic2\\2016120312300328_press.jpeg","picState":"new"}}]
         * totalElements : 4
         */

        private String totalElements;
        private List<ContentBean> content;

        public String getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(String totalElements) {
            this.totalElements = totalElements;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean implements Serializable{
            /**
             * newPicList : [{"id":41,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312320890.jpeg","x1Change":0.07073,"y1Change":-0.08193,"x2Change":0.31482,"y2Change":-0.07472,"x3Change":-0.0115,"y3Change":-0.06468,"x4Change":0.34969,"y4Change":-0.05931,"saveTime":1481510404000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312320890_press.jpeg"},{"id":40,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312315859.jpeg","x1Change":-0.00707,"y1Change":-0.08951,"x2Change":0.33569,"y2Change":-0.08984,"x3Change":0.0259,"y3Change":-0.07978,"x4Change":-0.29187,"y4Change":-0.09644,"saveTime":1481424004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312315859_press.jpeg"},{"id":39,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312315012.jpeg","x1Change":-0.00766,"y1Change":-0.04942,"x2Change":0.68635,"y2Change":-0.06113,"x3Change":0.01609,"y3Change":-0.04157,"x4Change":-0.14112,"y4Change":-0.04294,"saveTime":1481337604000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312315012_press.jpeg"},{"id":38,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312313921.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481251204000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312313921_press.jpeg"},{"id":37,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312312484.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481164804000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312312484_press.jpeg"},{"id":36,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312311346.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1481078404000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312311346_press.jpeg"},{"id":35,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312310093.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480992004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312310093_press.jpeg"},{"id":34,"devCode":"SXT05","oldPicId":null,"oldPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","comPicPath":"E:\\Pic\\Pic5\\2016120312304884.jpeg","x1Change":null,"y1Change":null,"x2Change":null,"y2Change":null,"x3Change":0,"y3Change":0,"x4Change":0,"y4Change":0,"saveTime":1480902004000,"deleteState":"0","isProcess":"1","oldPicPathPress":"uploadPhotos\\Pic4\\2016120312304884_press.jpeg","comPicPathPress":"uploadPhotos\\Pic5\\2016120312304884_press.jpeg"}]
             * oldPicDetail : {"id":30,"devId":"SXT05","picDirFilename":"E:\\Pic\\Pic4\\2016120312304884.jpeg","baseX":"341","baseY":"1262","lengthX":"41","lengthY":"41","isSelectBase":1,"rangePixel":"0.00017062","moniter1X":"1282","moniter1Y":"730","moniter2X":"0","moniter2Y":"0","moniter3X":"0","moniter3Y":"0","moniter4X":"0","moniter4Y":"0","moniterNum":"1","deleteState":"0","picPathPress":"uploadPhotos\\Pic5\\2016120312304884_press.jpeg","picState":"new"}
             */

            private OldPicDetailBean oldPicDetail;
            private List<NewPicListBean> newPicList;

            public OldPicDetailBean getOldPicDetail() {
                return oldPicDetail;
            }

            public void setOldPicDetail(OldPicDetailBean oldPicDetail) {
                this.oldPicDetail = oldPicDetail;
            }

            public List<NewPicListBean> getNewPicList() {
                return newPicList;
            }

            public void setNewPicList(List<NewPicListBean> newPicList) {
                this.newPicList = newPicList;
            }

            public static class OldPicDetailBean  implements Serializable{
                /**
                 * id : 30
                 * devId : SXT05
                 * picDirFilename : E:\Pic\Pic4\2016120312304884.jpeg
                 * baseX : 341
                 * baseY : 1262
                 * lengthX : 41
                 * lengthY : 41
                 * isSelectBase : 1
                 * rangePixel : 0.00017062
                 * moniter1X : 1282
                 * moniter1Y : 730
                 * moniter2X : 0
                 * moniter2Y : 0
                 * moniter3X : 0
                 * moniter3Y : 0
                 * moniter4X : 0
                 * moniter4Y : 0
                 * moniterNum : 1
                 * deleteState : 0
                 * picPathPress : uploadPhotos\Pic5\2016120312304884_press.jpeg
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
                private String deleteState;
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

                public String getDeleteState() {
                    return deleteState;
                }

                public void setDeleteState(String deleteState) {
                    this.deleteState = deleteState;
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

            public static class NewPicListBean  implements Serializable{
                /**
                 * id : 41
                 * devCode : SXT05
                 * oldPicId : null
                 * oldPicPath : E:\Pic\Pic5\2016120312304884.jpeg
                 * comPicPath : E:\Pic\Pic5\2016120312320890.jpeg
                 * x1Change : 0.07073
                 * y1Change : -0.08193
                 * x2Change : 0.31482
                 * y2Change : -0.07472
                 * x3Change : -0.0115
                 * y3Change : -0.06468
                 * x4Change : 0.34969
                 * y4Change : -0.05931
                 * saveTime : 1481510404000
                 * deleteState : 0
                 * isProcess : 1
                 * oldPicPathPress : uploadPhotos\Pic4\2016120312304884_press.jpeg
                 * comPicPathPress : uploadPhotos\Pic5\2016120312320890_press.jpeg
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
        }
    }
}
