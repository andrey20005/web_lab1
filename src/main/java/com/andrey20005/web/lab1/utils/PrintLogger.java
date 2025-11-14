package com.andrey20005.web.lab1.utils;

import java.io.PrintStream;
import java.time.LocalDateTime;

public class PrintLogger {
    private static final PrintStream cons = System.out;

    public static void info(String s) {
        cons.println("---- INFO ----\n\t" + LocalDateTime.now());
        cons.println(s + "\n");
        FileLogger.info(s);
    }

    public static void warn(String s) {
        cons.println("---- WARN ----\n\t" + LocalDateTime.now());
        cons.println(s + "\n");
        FileLogger.warn(s);
    }

    public static void error(String s) {
        cons.println("---- ERROR ----\n\t" + LocalDateTime.now());
        cons.println(s + "\n");
        FileLogger.error(s);
    }
}
