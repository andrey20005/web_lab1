package com.andrey20005.web.lab1;

import com.andrey20005.web.lab1.area.*;
import com.andrey20005.web.lab1.utils.FileLogger;
import com.andrey20005.web.lab1.utils.PrintLogger;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Area area = getArea();
        FileLogger.init("web1.log");
        PrintLogger.info("начало работы");
        CalculateFCGI.run(area);
    }

    private static OrArea getArea() {
        AndArea quarterCircle = new AndArea(new ArrayList<>());
        quarterCircle.addArea(new AboveLine(0, 0, 1, 0));
        quarterCircle.addArea(new AboveLine(0, 0, 0, 1));
        quarterCircle.addArea(new Circle(1, 0, 0));
        AndArea lowerTriangle = new AndArea(new ArrayList<>());
        lowerTriangle.addArea(new AboveLine(0, 0, 1, 0));
        return getOrArea(lowerTriangle, quarterCircle);
    }

    private static OrArea getOrArea(AndArea lowerTriangle, AndArea quarterCircle) {
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
        return area;
    }
}
