package com.andrey20005.web.lab1.area;

import com.andrey20005.web.lab1.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    @DisplayName("Circle area")
    void hit() {
        Area c1 = new Circle(2, 0, 0);
        Area c2 = new Circle(1, 0, 0);
        Area c3 = new Circle(2, 1, 0);
        Point p1 = new Point(new BigDecimal("1"), new BigDecimal("0"), new BigDecimal("0"));
        Point p2 = new Point(new BigDecimal("1"), new BigDecimal("0"), new BigDecimal("3"));
        Point p3 = new Point(new BigDecimal("1"), new BigDecimal("-1"), new BigDecimal("0"));
        Point p4 = new Point(new BigDecimal("3"), new BigDecimal("3"), new BigDecimal("4"));
        Point p5 = new Point(new BigDecimal("1"), new BigDecimal("0"), new BigDecimal("-1.000000000000000000000000000000000001"));
        assertAll(
                () -> assertTrue(c1.hit(p1)),
                () -> assertFalse(c1.hit(p2)),
                () -> assertTrue(c1.hit(p3)),
                () -> assertTrue(c2.hit(p1)),
                () -> assertFalse(c2.hit(p5)),
                () -> assertTrue(c3.hit(p4))
        );
    }
}