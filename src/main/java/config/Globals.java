package config;

import java.util.Scanner;

final public class Globals {
    final public static Scanner scanner = new Scanner(System.in);
    final public static String path_for_notes = "files/notes/";
    final public static String path_for_logfiles = "files/logfiles/";
// !!! be careful to change this (program is able to wipe whole directories of yours: do not misconfigure the path) !!!
}
