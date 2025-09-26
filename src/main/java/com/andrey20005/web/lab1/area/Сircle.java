package com.andrey20005.web.lab1.area;

import com.andrey20005.web.lab1.Point;

public class Сircle implements Area{
    private final double radius;
    private final double x;
    private final double y;

    public Сircle(double radius, double x, double y) {
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean hit(Point point) {
        return Math.sqrt(Math.pow(point.x/ point.r + x, 2) + Math.pow(point.y/ point.r - y, 2)) <= radius;
    }
}
