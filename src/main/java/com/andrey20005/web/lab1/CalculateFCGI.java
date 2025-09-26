package com.andrey20005.web.lab1;

import com.andrey20005.web.lab1.area.Area;
import com.fastcgi.FCGIInterface;

import java.io.PrintStream;
import java.time.LocalTime;

public class CalculateFCGI {
    private static String htmlAns = """
            <div class="output">
                <p class="output_x">%f</p>
                <p class="output_y">%f</p>
                <p class="output_r">%f</p>
                <p class="output_res">%b</p>
                <p class="now_time">%s</p>
                <p class="exec_time">%s</p>
            </div>
            """;
    private static String htmlErr = """
            <div class="output">
                <p class="output_err">%s</p>
                <p class="now_time">%s</p>
                <p class="exec_time">%s</p>
            </div>
            """;

    public static void run(Area area) {
        PrintStream so = System.out;
        while (new FCGIInterface().FCGIaccept()>= 0) {
            long startTime = System.nanoTime();
            so.print("новый запрос\n\t");
            so.println(System.getProperties().getProperty("QUERY_STRING"));
            try {
                Point point = Point.parse(System.getProperties().getProperty("QUERY_STRING"));
                System.out.println("Content-type: html\n");
                System.out.printf(
                        htmlAns,
                        point.r,
                        point.x,
                        point.y,
                        area.hit(point),
                        System.nanoTime() - startTime,
                        LocalTime.now()
                );
            } catch (Exception e) {
                System.out.println("Content-type: html\n");
                System.out.printf(htmlErr, e.getMessage(), System.nanoTime() - startTime, LocalTime.now());
            }
        }
    }
}
