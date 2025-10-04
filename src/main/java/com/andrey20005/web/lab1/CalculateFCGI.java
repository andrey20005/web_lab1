package com.andrey20005.web.lab1;

import com.andrey20005.web.lab1.area.Area;
import com.fastcgi.FCGIInterface;

import java.io.PrintStream;
import java.time.LocalTime;

public class CalculateFCGI {
//    private static final String htmlAns = """
//            <div class="output">
//                <p class="output_x">%f</p>
//                <p class="output_y">%f</p>
//                <p class="output_r">%f</p>
//                <p class="output_res">%b</p>
//                <p class="now_time">%s</p>
//                <p class="exec_time">%s</p>
//            </div>
//            """;
//    private static final String htmlErr = """
//            <div class="output">
//                <p class="output_err">%s</p>
//                <p class="now_time">%s</p>
//                <p class="exec_time">%s</p>
//            </div>
//            """;
private static final String jsonAns = """
        {
            "r" : %f,
            "x" : %f,
            "y" : %f,
            "res" : %b,
            "now_time" : "%s",
            "exec_time" : %d
        }
        """;
    private static final String jsonErr = """
        {
            "err" : %s,
            "now_time" : "%s",
            "exec_time" : %d
        }
        """;

    public static void run(Area area) {
        PrintStream so = System.out;
        while (new FCGIInterface().FCGIaccept()>= 0) {
            long startTime = System.nanoTime();
            so.print("новый запрос\n\t");
            so.println(System.getProperties().getProperty("QUERY_STRING"));
            try {
                Point point = Point.parse(System.getProperties().getProperty("QUERY_STRING"));
                System.out.println("Content-type: json\n");
                System.out.printf(
                        jsonAns,
                        point.r,
                        point.x,
                        point.y,
                        area.hit(point),
                        LocalTime.now(),
                        System.nanoTime() - startTime
                );
            } catch (Exception e) {
                System.out.println("Content-type: json\n");
                so.printf(jsonErr, e.getMessage(), LocalTime.now(), System.nanoTime() - startTime);
                System.out.printf(
                        jsonErr,
                        e.getMessage(),
                        LocalTime.now(),
                        System.nanoTime() - startTime
                );
            }
        }
    }
}
