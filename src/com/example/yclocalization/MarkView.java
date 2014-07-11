package com.example.yclocalization;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.View;

public class MarkView extends View{
	private Paint myPaint;
    private PointF myPosition, relativePosition; //real position and relative position
    private float myRadius; // circle radius
    
	public MarkView(Context context) {
		
		super(context);
		// TODO Auto-generated constructor stub
		    myPaint = new Paint();
	        myPaint.setColor(Color.BLUE);
	        myPaint.setAlpha(150);
	        myPosition = new PointF(0, 0);
	        relativePosition = new PointF(0, 0);
	        myRadius = 25f;
	}
	 public void setPosition(float x, float y) {
	        myPosition.x = x;
	        myPosition.y = y;
	 }
	 public void drawThePositionOnMap(Canvas canvas, float[] matrixValues) {
	        relativePosition.x = matrixValues[2] + myPosition.x * matrixValues[0];
	        relativePosition.y = matrixValues[5] + myPosition.y * matrixValues[4];
	        canvas.drawCircle(relativePosition.x, relativePosition.y, myRadius, myPaint);
	    }

}
