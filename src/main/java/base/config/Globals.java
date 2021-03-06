package base.config;

import java.util.Scanner;

public class Globals {
    final public static Scanner scanner = new Scanner(System.in);
    final public static String path_for_notes = "src/main/java/base/files/notes/";
    final public static String path_for_logfiles = "src/main/java/base/files/logfiles/";
// !!! be careful to change this (program is able to wipe whole directories of yours: do not misconfigure the path) !!!
}
