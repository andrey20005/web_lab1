package com.andrey20005.web.lab1.area;

import com.andrey20005.web.lab1.Point;

public class AboveLine implements Area{
    private final double centerX;
    private final double centerY;
    private final double normX;
    private final double normY;

    public AboveLine(double centerX, double centerY, double normX, double normY) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.normX = normX;
        this.normY = normY;
    }

    @Override
    public boolean hit(Point point) {
        return (point.x / point.r - centerX) * normX + (point.y / point.r - centerY) * normY >= 0;
    }
}
