package com.andrey20005.web.lab1.area;

import com.andrey20005.web.lab1.Point;

import java.math.BigDecimal;

public record AboveLine(double centerX, double centerY, double normX, double normY) implements Area {
    @Override
    public boolean hit(Point point) {
        return (point.x.doubleValue() / point.r.doubleValue() - centerX) * normX +
                (point.y.doubleValue() / point.r.doubleValue() - centerY) * normY >= 0;
    }
}
