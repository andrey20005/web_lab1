package com.andrey20005.web.lab1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    @DisplayName("url parsing")
    void parse() {
        assertAll(
                () -> {
                    Point p = Point.parse("r=1&x=1&y=1");
                    assertEquals(new BigDecimal("1"), p.r);
                    assertEquals(new BigDecimal("1"), p.x);
                    assertEquals(new BigDecimal("1"), p.y);
                },
                () -> {
                    Point p = Point.parse("r=3&x=-1&y=0.0000000001");
                    assertEquals(new BigDecimal("3"), p.r);
                    assertEquals(new BigDecimal("-1"), p.x);
                    assertEquals(new BigDecimal("0.0000000001"), p.y);
                },
                () -> {
                    Point p = Point.parse("r=3&x=-4&y=-3.00000000");
                    assertEquals(new BigDecimal("3"), p.r);
                    assertEquals(new BigDecimal("-4"), p.x);
                    assertEquals(new BigDecimal("-3.00000000"), p.y);
                }
        );
    }
}