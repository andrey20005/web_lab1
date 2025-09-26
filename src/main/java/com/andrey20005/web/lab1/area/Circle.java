package com.andrey20005.web.lab1.area;

import com.andrey20005.web.lab1.Point;

public record Circle(double radius, double x, double y) implements Area {

    @Override
    public boolean hit(Point point) {
        return Math.sqrt(Math.pow(point.x / point.r + x, 2) + Math.pow(point.y / point.r - y, 2)) <= radius;
    }
}
