package me.piedpiper.businesslogic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {
    public static int tabcount = -1;
    public static boolean logOnConsole = true;
    public static boolean logGetterSetter = false;
    public static boolean logCtor = false;
    public static void logMessage(String message) {
        tabcount++;
        if (!logGetterSetter) {
            
        }
        
        if (logOnConsole) {
            String tmp = "";
            for (int i = 0; i < tabcount; ++i) { tmp += "\t"; }
            tmp += message;
            System.out.println(tmp);
        }
    }
}