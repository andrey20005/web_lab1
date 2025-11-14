package com.andrey20005.web.lab1;

import com.andrey20005.web.lab1.area.Area;
import com.andrey20005.web.lab1.utils.PrintLogger;
import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalTime;
import java.util.Objects;

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
        FCGIInterface fcgi = new FCGIInterface();
        new Thread(() -> {
            while (true) {
                int acceptResult = fcgi.FCGIaccept();
                if (acceptResult >= 0) {
                    long startTime = System.nanoTime();
                    String url = FCGIInterface.request.params.getProperty("QUERY_STRING");
                    String method = FCGIInterface.request.params.getProperty("REQUEST_METHOD");
                    try {
                        PrintLogger.info("new request: " + url);
                        if (method == null) {
                            PrintLogger.warn("Unsupported HTTP method: null");
                            System.out.println("Unsupported HTTP method: null");
                            continue;
                        }
                        if (Objects.equals(method, "POST")) {
                            Point point = Point.parse(url);
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
                            PrintLogger.info("\trequest url: " + url + "\n\tresponse: \n" + res);
                            continue;
                        }
                        PrintLogger.warn("Unsupported HTTP method: " + method);
                        System.out.printf("Unsupported HTTP method: %s", method);
                    } catch (URLArgsExeption e) {
                        String res = String.format(
                                java.util.Locale.US,
                                jsonErr,
                                e.getMessage(),
                                LocalTime.now(),
                                System.nanoTime() - startTime
                        );
                        PrintLogger.warn("incorrect url arguments: " + url);
                        System.out.println(res);
                    } catch (Exception e) {
                        try (StringWriter stringWriter = new StringWriter();
                             PrintWriter printWriter = new PrintWriter(stringWriter)) {
                            e.printStackTrace(printWriter);
                            PrintLogger.error(stringWriter.toString());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        }).start();
    }
}
