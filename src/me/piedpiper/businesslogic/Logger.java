package me.piedpiper.businesslogic;

public class Logger {
    public static int tabcount = -1;
    public static boolean logOnConsole = true;
    public static void logMessage(String message) {
        tabcount++;
        if (logOnConsole) {
            String tmp = "";
            for (int i = 0; i < tabcount; ++i) { tmp += "\t"; }
            tmp += message;
            System.out.println(tmp);
        }
    }
}