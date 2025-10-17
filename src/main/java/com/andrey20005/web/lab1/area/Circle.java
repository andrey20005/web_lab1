package com.andrey20005.web.lab1.area;

import com.andrey20005.web.lab1.Point;

import java.math.BigDecimal;

public record Circle(double radius, double x, double y) implements Area {

    @Override
    public boolean hit(Point point) {
        return Math.sqrt(Math.pow(point.x.doubleValue() / point.r.doubleValue() + x, 2) +
                Math.pow(point.y.doubleValue() / point.r.doubleValue() - y, 2)) <= radius;
    }
}
