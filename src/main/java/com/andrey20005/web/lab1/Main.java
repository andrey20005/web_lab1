package com.andrey20005.web.lab1;

import com.andrey20005.web.lab1.area.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("начало работы");
        AndArea quarterCircle = new AndArea(new ArrayList<>());
        quarterCircle.addArea(new AboveLine(0, 0, 1, 0));
        quarterCircle.addArea(new AboveLine(0, 0, 0, 1));
        quarterCircle.addArea(new Circle(1, 0, 0));
        AndArea lowerTriangle = new AndArea(new ArrayList<>());
        lowerTriangle.addArea(new AboveLine(0, 0, 1, 0));
        lowerTriangle.addArea(new AboveLine(0, 0, 0, -1));
        lowerTriangle.addArea(new AboveLine(0, -1, -1, 2));
        AndArea rectangle = new AndArea(new ArrayList<>());
        rectangle.addArea(new AboveLine(0, 0, -1, 0));
        rectangle.addArea(new AboveLine(0, 0, 0, 1));
        rectangle.addArea(new AboveLine(-1, 0.5, 1, 0));
        rectangle.addArea(new AboveLine(-1, 0.5, 0, -1));
        OrArea area = new OrArea(new ArrayList<>());
        area.addArea(quarterCircle);
        area.addArea(lowerTriangle);
        area.addArea(rectangle);
        CalculateFCGI.run(area);
    }
}
