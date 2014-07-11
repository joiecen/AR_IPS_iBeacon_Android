package com.example.yclocalization;

import android.graphics.PointF;

public class Ibeaconsv {
	public PointF ibPointF = new PointF(0,0); //ibeacons在地图上的位置坐标
    public String type = new String();//定位点相应图片种类，是否需要？？？
    public String info = new String();//定位时显示的文字信息
   // public float x,y=0;//坐标
    public int ibmajor;
    public int ibminor;
    public int ibrssi;
    public int ibproximity;
    /**constructors*/
    public Ibeaconsv(String type,int ibrssi,PointF ibPointF,String info,int ibmajor, int ibminor,int ibproximity)
    {
    	this.type = type;
    	this.ibrssi = ibrssi;
    	this.ibPointF = ibPointF;
    	this.info = info;
    	this.ibmajor = ibmajor;
    	this.ibminor = ibminor;
    	this.ibproximity = ibproximity;
    }

}
