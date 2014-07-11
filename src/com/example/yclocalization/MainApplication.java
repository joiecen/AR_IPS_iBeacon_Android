package com.example.yclocalization;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;
import android.graphics.PointF;

public class MainApplication extends Application {
    /**
     * map**
     */
    private String displayMapName = new String();

    public void setDisplayMapName(String name) {
        this.displayMapName = name;
    }

    public String getDisplayMapName() {
        return displayMapName;
    }

    /**
     * position**
     */
    private Position mPosition = new Position();

    public Position getPosition() {
        return mPosition;
    }

    public void setPosition(float x, float y) {
        this.mPosition.position.x = x;
        this.mPosition.position.y = y;
    }

    public void setPosition(int id, float x, float y) {
        this.mPosition.PositionID = id;
      //  this.mPosition.Map = map;
        this.mPosition.position.x = x;
        this.mPosition.position.y = y;
    }

    /**
     * position mode on or off**
     */
    private boolean POSITION_MODE;

    public boolean positionModeOnOrOff() {
        return POSITION_MODE;
    }

    public void setPositionMode(boolean POSITION_MODE) {
        this.POSITION_MODE = POSITION_MODE;
    }

    /**
     * mark mode on or off**
     */
    private boolean MARK_MODE;

    public boolean markModeOnOrOff() {
        return MARK_MODE;
    }

    public void setMarkMode(boolean MARK_MODE) {
        this.MARK_MODE = MARK_MODE;
    }

    /**
     * mark point**
     */
    private Position markPosition;

    public int getMarkID() {
        return markPosition.PositionID;
    }

    public Position getMarkPosition() {
        return markPosition;
    }

    public void setMark(Position mark) {
        this.markPosition = mark;
    }
}

class Position {
    int PositionID;
    String Map = new String();
    PointF position = new PointF();
}