package me.piedpiper.businesslogic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {
    /**
     * Az indentálás aktuális pozíióját tárolja
     */
    public static int tabcount = -1;
    /**
     * A teljes konzolra logolást kapcsolja ki-be
     */
    public static boolean logOnConsole = true;
    /**
     * A getterek konzolra logolását kapcsolja ki-be
     */
    public static boolean logGetter = false;
    /**
     * A setterek konzolra logolását kapcsolja ki-be
     */
    public static boolean logSetter = false;
    /**
     * A konstruktorok konzolra logolását kapcsolja ki-be
     */
    public static boolean logCtor = true;

    /**
     * A paraméterül kapott üzenetet kiírja a konzolra megfelelő indentálással
     */
    public static void logMessage(String message) {
        tabcount++;

        /**
         * A gettereket kiszűrő regex
         */
        if (!logGetter) {
            Pattern pattern = Pattern.compile(".Get");
            Matcher matcher = pattern.matcher(message);

            int count = 0;
            while(matcher.find()) 
                count++;
            if (count > 0) return;
        }

        /**
         * A settereket kiszűrő regex
         */
        if (!logSetter) {
            Pattern pattern = Pattern.compile(".Set");
            Matcher matcher = pattern.matcher(message);

            int count = 0;
            while(matcher.find()) 
                count++;
            if (count > 0) return;
        }

        /**
         * A konstruktorokat kiszűrő regex
         */
        if (!logCtor) {
            Pattern pattern = Pattern.compile(".Ctor");
            Matcher matcher = pattern.matcher(message);

            int count = 0;
            while(matcher.find()) 
                count++;
            if (count > 0) return;
        }

        /**
         * Kiírja az üzenetet a konzolra, ha engedélyezve van a logolás
         */
        if (logOnConsole) {
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < tabcount; ++i) { tmp.append("\t"); }
            tmp.append(message);
            System.out.println(tmp);
        }
    }
}