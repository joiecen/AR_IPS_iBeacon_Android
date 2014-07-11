package com.example.yclocalization;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.View;

public class PositionView extends View{
	  private PointF myPosition, relativePosition; //real position and relative position
	    static private float myOrientation = 0;
	    private Bitmap myPositionImage;
	    private Matrix myPositionMatrix = new Matrix();
	    ;
	    private float displayWidth;
//		private float displayHeight;
	    
	    private Paint myPaint;
		private float myRadius; // circle radius

	public PositionView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		    myPosition = new PointF(0, 0);
	        relativePosition = new PointF(0, 0);
	        myPositionImage = ((BitmapDrawable) getResources().getDrawable(R.drawable.point)).getBitmap();
	        DisplayMetrics dm = getResources().getDisplayMetrics();
	        displayWidth = dm.widthPixels;
//			displayHeight = dm.heightPixels;
	        
			myPaint = new Paint();  
			myPaint.setColor(Color.BLUE);
			myPaint.setAlpha(40);
	}
	static public void setOrientation(float orientation) {
        myOrientation = orientation;
    }
	 public void setPosition(float x, float y) {
	        myPosition.x = x;
	        myPosition.y = y;
	    }
	  public void drawThePositionOnMap(Canvas canvas, float[] matrixValues) {
	        relativePosition.x = matrixValues[2] + myPosition.x * matrixValues[0];
	        relativePosition.y = matrixValues[5] + myPosition.y * matrixValues[4];
	        
	        myRadius = 80;
			canvas.drawCircle(relativePosition.x, relativePosition.y, myRadius, myPaint);

	        myPositionMatrix.setScale(displayWidth / (myPositionImage.getWidth() * 15f), displayWidth / (myPositionImage.getHeight() * 15f));
	        myPositionMatrix.postRotate(myOrientation, displayWidth / 30f, displayWidth / 30f);
	        myPositionMatrix.postTranslate(relativePosition.x - displayWidth / 30f, relativePosition.y - displayWidth / 30f);

	        canvas.drawBitmap(myPositionImage, myPositionMatrix, null);
	    }

}
