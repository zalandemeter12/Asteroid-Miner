package me.piedpiper.businesslogic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {
    public static int tabcount = -1;
    public static boolean logOnConsole = true;
    public static boolean logGetter = false;
    public static boolean logSetter = false;
    public static boolean logCtor = true;
    public static void logMessage(String message) {
        tabcount++;
        if (!logGetter) {
            Pattern pattern = Pattern.compile(".Get");
            Matcher matcher = pattern.matcher(message);

            int count = 0;
            while(matcher.find()) 
                count++;
            if (count > 0) return;
        }

        if (!logSetter) {
            Pattern pattern = Pattern.compile(".Set");
            Matcher matcher = pattern.matcher(message);

            int count = 0;
            while(matcher.find()) 
                count++;
            if (count > 0) return;
        }

        if (!logCtor) {
            Pattern pattern = Pattern.compile(".Ctor");
            Matcher matcher = pattern.matcher(message);

            int count = 0;
            while(matcher.find()) 
                count++;
            if (count > 0) return;
        }
        
        if (logOnConsole) {
            String tmp = "";
            for (int i = 0; i < tabcount; ++i) { tmp += "\t"; }
            tmp += message;
            System.out.println(tmp);
        }
    }
}