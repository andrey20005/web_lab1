package com.andrey20005.web.lab1.area;

import com.andrey20005.web.lab1.Point;

public record AboveLine(double centerX, double centerY, double normX, double normY) implements Area {
    @Override
    public boolean hit(Point point) {
        return (point.x / point.r - centerX) * normX + (point.y / point.r - centerY) * normY >= 0;
    }
}
