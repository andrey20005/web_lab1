package com.andrey20005.web.lab1;

import com.andrey20005.web.lab1.area.Area;
import com.fastcgi.FCGIInterface;

import java.io.PrintStream;
import java.time.LocalTime;

public class CalculateFCGI {
private static final String jsonAns = """
        Status: 200 OK
        Content-type: json
        
        {
            "r" : "%s",
            "x" : "%s",
            "y" : "%s",
            "res" : %b,
            "now_time" : "%s",
            "exec_time" : %d
        }
        """;
    private static final String jsonErr = """
        Status: 400 %s
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
                console.print("новый запрос\n");
                long startTime = System.nanoTime();
                try {
                    console.println(System.getProperties().getProperty("QUERY_STRING"));
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
                    String res = String.format(
                            java.util.Locale.US,
                            jsonErr,
                            e.getMessage(),
                            LocalTime.now(),
                            System.nanoTime() - startTime
                    );
                    console.println(res);
                    System.out.println(res);
                } catch (Exception e) {
                    e.printStackTrace(console);
                }
            }
        }
    }
}
