package com.andrey20005.web.lab1;

import com.andrey20005.web.lab1.area.Area;
import com.fastcgi.FCGIInterface;

import java.io.PrintStream;
import java.time.LocalTime;

public class CalculateFCGI {
private static final String jsonAns = """
        Content-type: json
        
        {
            "r" : %.7f,
            "x" : %.7f,
            "y" : %.7f,
            "res" : %b,
            "now_time" : "%s",
            "exec_time" : %d
        }
        """;
    private static final String jsonErr = """
        Content-type: json
        
        {
            "now_time" : "%s",
            "exec_time" : %d
        }
        """;

    public static void run(Area area) {
        PrintStream console = System.out;
        FCGIInterface fcgi = new FCGIInterface();
        while (true) {
            console.println("ждем подключение");
            int acceptResult = fcgi.FCGIaccept();
            if (acceptResult >= 0) {
                console.print("новый запрос\n\t");
                long startTime = System.nanoTime();
                try {
                    Point point = Point.parse(System.getProperties().getProperty("QUERY_STRING"));
                    String res = String.format(
                            java.util.Locale.US,
                            jsonAns,
                            point.r,
                            point.x,
                            point.y,
                            area.hit(point),
                            LocalTime.now(),
                            System.nanoTime() - startTime
                    );
                    System.out.print(res);
                    console.println(res);
                } catch (URLArgsExeption e) {
                    console.printf(java.util.Locale.US, jsonErr, LocalTime.now(), System.nanoTime() - startTime);
                    System.out.printf(
                            java.util.Locale.US,
                            jsonErr,
                            LocalTime.now(),
                            System.nanoTime() - startTime
                    );
                } catch (Exception e) {
                    e.printStackTrace(console);
                }
            }
        }
    }
}
