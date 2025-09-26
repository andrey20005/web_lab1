package com.andrey20005.web.lab1;

import com.fastcgi.FCGIInterface;

import java.io.PrintStream;
import java.time.LocalTime;

public class GetTimeFCGI {
    public static void run() {
        PrintStream so = System.out;
        while (new FCGIInterface().FCGIaccept()>= 0) {
            so.print("новый запрос\n\t");
            so.println(System.getProperties().getProperty("QUERY_STRING").toString());
            System.out.println("Content-type: text\n");
            System.out.print(LocalTime.now());
        }
    }
}
