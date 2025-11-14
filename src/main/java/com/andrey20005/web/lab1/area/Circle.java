package com.andrey20005.web.lab1.area;

import com.andrey20005.web.lab1.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Circle(double radius, double x, double y) implements Area {

    @Override
    public boolean hit(Point point) {
//        (p.x/p.r-x)^2 + (p.y/p.r-y)^2 <= 1
        return (
                point.x
                        .divide(point.r, 200, RoundingMode.HALF_UP)
                        .subtract(new BigDecimal(x))
                ).pow(2)
                .add(
                        (
                        point.y
                                .divide(point.r, 200, RoundingMode.HALF_UP)
                                .subtract(new BigDecimal(y))
                        ).pow(2)
                )
                .compareTo(new BigDecimal(radius)) <= 0;
    }
}
