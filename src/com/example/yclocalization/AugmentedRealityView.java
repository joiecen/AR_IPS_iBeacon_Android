package com.example.yclocalization;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.MotionEvent;

public class AugmentedRealityView extends View{
	Paint mPaint = new Paint();
	Paint mPaint2 = new Paint();
	TextPaint mPaint3 =new TextPaint();
	
	DisplayMetrics dm = getResources().getDisplayMetrics();
	private double screenWidth = dm.widthPixels;
	private double screenHeight = dm.heightPixels;
	
	/*****orientation*****/
	private double mapOrientationOffset = 0d;
	private double Compass = 0d;
	private double Pitch = 0d;
	
	/*****image*****/
	private Bitmap exitBitmap;
	private Bitmap officeBitmap;
	private Bitmap managerBitmap;
	private Bitmap meetingBitmap;
	private Bitmap toiletBitmap;
	private Bitmap financeBitmap;
	private Bitmap restBitmap;
	private Bitmap liftBitmap;
	private Bitmap textBitmap;
	private Bitmap chosenBitmap;
	private double imagex,imagey;
	private float textx,texty;
	
	/*****location******/
//	private Location currentLocation = new Location(new PointF(0,0),"wmlab");
//	private LocationMap mLocationMap = new LocationMap();
//	private ArrayList<Location> locations = new ArrayList<Location>();
//	
	public AugmentedRealityView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(50);
		mPaint.setAntiAlias(true);
		mPaint.setStrokeWidth(2);
		
		mPaint2.setColor(Color.BLACK);
		mPaint2.setTextSize(50);
		mPaint2.setAntiAlias(true);
		mPaint2.setStrokeWidth(2);
		mPaint2.setAlpha(220);
		
		mPaint3.setColor(Color.BLACK);
		mPaint3.setTextSize(50);
		Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		mPaint3.setTypeface(font);
		mPaint3.setAntiAlias(true);
		mPaint3.setStrokeWidth(2);
		mPaint3.setAlpha(220);
		
//		locations = mLocationMap.getLocations();
		
//		exitBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.exit);
//		officeBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.office);
//		managerBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.manager);
//		meetingBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.meeting);
//		toiletBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.toilet);
//		financeBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.finance);
//		restBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.rest);
//		liftBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lift);
//		textBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.text);
//		chosenBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.chosen);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		screenWidth = (double) w;
		screenHeight = (double) h;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		/***** Y ******/
		if(Pitch<0){
			Pitch = -Pitch;
		}
		if(Pitch<45){
			Pitch = 45;
		}
		if(Pitch>135){
			Pitch = 135;
		}
		Pitch = Pitch - 45;
		double yPositionInScreen = (Pitch / 90d) * screenHeight;
		
//		for (int i = 0; i < locations.size(); i++) {
//			Location objectLocation = locations.get(i);
//			
//			/******* filter locations *******/
//			if(!objectLocation.map.equals(currentLocation.map)){
//				continue;
//			}
//			
//			/**** distance****/
//			int distance = distance(currentLocation.point,objectLocation.point);
//			distance = distance*50/1250;
//			if(distance>100){
//				//if too far, do something...
//			}
//		
//			/***** X ******/
//			double orientation = orientation(currentLocation.point,objectLocation.point) - Compass;
//			if(orientation<0){
//				orientation = (orientation+360)%360;
//			}
//			double xPositionInScreen = (orientation / 60d) * screenWidth;
//			
//			Bitmap locationImage = chooseImage(objectLocation.type);
//			int imageCenterX = locationImage.getWidth() / 2;
//			int imageCenterY = locationImage.getHeight() / 2;
//			imagex=locationImage.getWidth();
//			imagey=locationImage.getHeight();
//			/****set the image position on the screen***/
//			double xPos = xPositionInScreen - imageCenterX;
//			double yPos = yPositionInScreen - imageCenterY;
//			//the camera's angle range. I chose 60 degrees. Perhaps it's not the best.
//			objectLocation.y = (float) yPos;
//			if (orientation <= 30) 
//			{
//				objectLocation.x = (float) ((screenWidth / 2) + xPos);
//				objectLocation.isshow=true;
//				canvas.drawBitmap(locationImage, objectLocation.x, objectLocation.y, mPaint); 
//				canvas.drawText(objectLocation.info, objectLocation.x + 180, objectLocation.y + 60, mPaint); 
//				canvas.drawText("距离约"+distance+"米", objectLocation.x + 180, objectLocation.y + 130, mPaint); 
//				if(objectLocation.isclick)
//				{
//						canvas.drawBitmap(chosenBitmap, objectLocation.x-7, objectLocation.y-7, mPaint);
//				}
//			}
//			else if (orientation >= 330) 
//			{
//				objectLocation.x = (float) ((screenWidth / 2) - ((screenWidth*6) - xPos));
//				objectLocation.isshow=true;
//				canvas.drawBitmap(locationImage, objectLocation.x, objectLocation.y, mPaint); 
//				canvas.drawText(objectLocation.info, objectLocation.x + 180, objectLocation.y + 60, mPaint); 
//				canvas.drawText("距离约"+distance+"米", objectLocation.x + 180, objectLocation.y + 130, mPaint); 
//				if(objectLocation.isclick)
//				{
//						canvas.drawBitmap(chosenBitmap, objectLocation.x-7, objectLocation.y-7, mPaint);
//				}
//			}
//			else
//			{
//				//objectLocation.x = (float) (float) (screenWidth*100); //somewhere off the screen
//				objectLocation.isshow=false;
//		
//			}
//			locations.set(i, objectLocation);
//		}
//		for(int i=0;i<locations.size();i++)
//		{
//			Location locat=locations.get(i);
//			if(locat.isclick)
//			{	
//					Log.v("textx", "text="+textBitmap.getWidth());
//					textx=(float)(screenWidth/2-textBitmap.getWidth()/2);
//					texty=(float)(screenHeight/2-textBitmap.getHeight()/3+100);
//					canvas.drawBitmap(textBitmap, textx,texty, mPaint2); 
//					canvas.drawText(locat.info, textx+60,texty+60, mPaint3); 
//					int j;
//					for(j=0;j<locat.text.length()/14;j++){
//						canvas.drawText(locat.text, 14*j, 14*(j+1), textx+60, texty+60*(j+2), mPaint2);
//					}
//					canvas.drawText(locat.text, 14*(j), locat.text.length(), textx+60, texty+60*(j+2), mPaint2);
//
//			}
//		}
	}
	
//	public boolean onTouchEvent (MotionEvent event){
//		super.onTouchEvent(event);
//		Location objectLocation;
//		 switch (event.getAction()) {
//	        /*点选屏幕*/ 
//	        case MotionEvent.ACTION_DOWN:
//	        {        	
//	        	break;
//	        }
//	        /*移动位置*/
//	       case MotionEvent.ACTION_MOVE: 
//            break;
//	        /*离开屏幕*/
//	        case MotionEvent.ACTION_UP:
//	        {
//	        	float clickx=event.getRawX();
//	    		float clicky=event.getY();
//	    		for(int i=0;i<locations.size();i++)
//	    		{
//	    			objectLocation = locations.get(i);
//	    		
//	    			if(objectLocation.isshow)
//	    			{	
//	    				if(clickx>=objectLocation.x&&(clickx<=objectLocation.x+imagex)&&clicky>=objectLocation.y&&(clicky<=objectLocation.y+imagey))
//	    				{   	
//	    					if(objectLocation.isclick==false){
//	    						objectLocation.setisclick(true);
//	    					}else {
//								objectLocation.setisclick(false);
//							}
//	    				}
//	    				else {
//							objectLocation.setisclick(false);
//						}
//	    			}
//	    			else {
//						objectLocation.setisclick(false);
//					}
//	    		}
//	            break;
//	        }
//		 }
//		return true;
//	}
//	
//	public void setMyIndoorLocation(PointF point, String map){
//		currentLocation.point = point;
//		currentLocation.map = map;
//	}
	
	public void setCompass(float compass) {
		this.Compass = compass;
	}
	
	public void setPitch(float pitch) {
		this.Pitch = pitch;
	}
	
	private int distance(PointF point1, PointF point2){
		//Euclidean distance
		int distance = (int) Math.sqrt( Math.pow((point1.x-point2.x), 2) + Math.pow((point1.y-point2.y), 2) ) ;
		return distance;
	}
	
	private double orientation(PointF point1, PointF point2){
		/*******************
		|a|=√[x1^2+y1^2]  a=(x2-x1,y2-y1)
		|b|=√[x2^2+y2^2]  b=(0,1)
		a*b=(x1,y1)(x2,y2)=x1x2+y1y2
		cos<a,b>=a*b/[|a|*|b|]
				=(x1x2+y1y2)/[√[x1^2+y1^2]*√[x2^2+y2^2]]
		*******************/
		double orientation = Math.acos((point1.y-point2.y)/distance(point1,point2))*(180/Math.PI);
		if(point2.x<point1.x){
			orientation = 360-orientation;
		}
		orientation += mapOrientationOffset;
		orientation = orientation%360;
		return orientation;
	}
	
	public void setMapOrientationOffset(double offset){
		this.mapOrientationOffset = offset;
	}
	
	private Bitmap chooseImage(String type){
		Bitmap bitmap = exitBitmap;
		if(type.equals("exit")){
			bitmap = exitBitmap;
		}else if(type.equals("office")){
			bitmap = officeBitmap;
		}else if(type.equals("manager")){
			bitmap = managerBitmap;
		}else if(type.equals("meeting")){
			bitmap = meetingBitmap;
		}else if(type.equals("toilet")){
			bitmap = toiletBitmap;
		}else if(type.equals("rest")){
			bitmap = restBitmap;
		}else if(type.equals("finance")){
			bitmap = financeBitmap;
		}else if(type.equals("lift")){
			bitmap = liftBitmap;
		}
		return bitmap;
	}

}
