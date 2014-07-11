package com.example.yclocalization;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class MapView extends ImageView {

    private MainApplication myApplication;

    // These matrices will be used to move and zoom image
    private Matrix myMatrix = new Matrix();
    private Matrix mySavedMatrix = new Matrix();

    //position view
    private PositionView position = new PositionView(getContext());
    private MarkView mark = new MarkView(getContext());

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float[] values = new float[9];
        myMatrix.getValues(values);

        myApplication = (MainApplication) getContext().getApplicationContext();

        Position currentPosition = myApplication.getPosition();

        /***set mark position***/
        Position markPosition = myApplication.getMarkPosition();
        if (myApplication.markModeOnOrOff()) {
            mark.setPosition(markPosition.position.x, markPosition.position.y);
            mark.drawThePositionOnMap(canvas, values);
        }

        /***set current position***/
        if (myApplication.positionModeOnOrOff()) {
            position.setPosition(currentPosition.position.x, currentPosition.position.y);
        	//position.setPosition(Sta.x, Sta.y);
        	//Log.i("tes", "x"+Sta.x+"y"+Sta.y);
            position.drawThePositionOnMap(canvas, values);
        }

    }

}