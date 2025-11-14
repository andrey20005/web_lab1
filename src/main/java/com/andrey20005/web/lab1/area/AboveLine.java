package com.andrey20005.web.lab1.area;

import com.andrey20005.web.lab1.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record AboveLine(double centerX, double centerY, double normX, double normY) implements Area {
    @Override
    public boolean hit(Point point) {
//        (p.x/p.r-x)*norm_x + (p.y/p.r-y)*norm_y >= 0
        return point.x.divide(point.r, 200, RoundingMode.HALF_UP)
                .subtract(new BigDecimal(centerX))
                .multiply(new BigDecimal(normX))
                .add(point.y
                        .divide(point.r, 200, RoundingMode.HALF_UP)
                        .subtract(new BigDecimal(centerY))
                        .multiply(new BigDecimal(normY)))
                .compareTo(new BigDecimal(0)) >= 0;
    }
}
