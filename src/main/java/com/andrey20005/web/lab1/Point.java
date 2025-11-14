package com.andrey20005.web.lab1;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Point {
    public BigDecimal r;
    public BigDecimal x;
    public BigDecimal y;

    public Point(BigDecimal r, BigDecimal x, BigDecimal y) {
        this.r = r;
        this.x = x;
        this.y = y;
    }

    private static final Pattern argsPattern = Pattern.compile("^r=([-\\d.]+)&x=([-\\d.]+)&y=([-\\d.]+)");

    public static Point parse(String prompt) throws URLArgsExeption, NumberFormatException {
        Matcher matcher = argsPattern.matcher(prompt);
        if (matcher.find()) {
            BigDecimal r = new BigDecimal(matcher.group(1));
            BigDecimal x = new BigDecimal(matcher.group(2));
            BigDecimal y = new BigDecimal(matcher.group(3));
            checkArgs(r, x, y);
            return new Point(r, x, y);
        } else {
            throw new URLArgsExeption("Args not find");
        }
    }

    private static void checkArgs(BigDecimal r, BigDecimal x, BigDecimal y) throws URLArgsExeption {
        if (
                x.compareTo(new BigDecimal(-4)) < 0 ||
                x.compareTo(new BigDecimal(4)) > 0 ||
                y.compareTo(new BigDecimal(-3)) < 0 ||
                y.compareTo(new BigDecimal(3)) > 0 ||
                r.compareTo(new BigDecimal(1)) < 0 ||
                r.compareTo(new BigDecimal(3)) > 0
        ) {
            throw new URLArgsExeption("Args out of range");
        }
    }
}
