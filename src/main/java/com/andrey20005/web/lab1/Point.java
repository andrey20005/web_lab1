package com.andrey20005.web.lab1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Point {
    public double r;
    public double x;
    public double y;

    public Point(double r, double x, double y) {
        this.r = r;
        this.x = x;
        this.y = y;
    }

    private static final Pattern argsPattern = Pattern.compile("^r=([-\\d.]+)&x=([-\\d.]+)&y=([-\\d.]+)");

    public static Point parse(String prompt) throws URLArgsExeption, NumberFormatException {
        Matcher matcher = argsPattern.matcher(prompt);
        if (matcher.find()) {
            double r = Double.parseDouble(matcher.group(1));
            double x = Double.parseDouble(matcher.group(2));
            double y = Double.parseDouble(matcher.group(3));
            return new Point(r, x, y);
        } else {
            throw new URLArgsExeption("Args not find");
        }
    }
}
