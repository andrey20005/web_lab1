package com.andrey20005.web.lab1.utils;

import java.io.*;
import java.time.LocalDateTime;

public class FileLogger {
    private static String infoPath;
    private static String warnPath;
    private static String errorPath;

    public static void init(String path) {
        infoPath = path;
        warnPath = path;
        errorPath = path;
    }

    private static void println(String path, String out) {
        try (Writer writer = new FileWriter(path, true)) {
            writer.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void info(String s) {
        println(infoPath, "---- INFO ----\n\t" + LocalDateTime.now());
        println(infoPath, "\n" + s + "\n");
    }

    public static void warn(String s) {
        println(warnPath, "---- WARN ----\n\t" + LocalDateTime.now());
        println(warnPath, "\n" + s + "\n");
    }

    public static void error(String s) {
        println(errorPath, "---- ERROR ----\n\t" + LocalDateTime.now());
        println(errorPath, "\n" + s + "\n");
    }
}
