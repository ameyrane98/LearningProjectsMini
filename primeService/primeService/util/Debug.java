package primeService.util;

import primeService.util.LogLevel;

public class Debug {
    private static Debug instance = null;
    private LogLevel currentLogLevel;

    private Debug() {
        // Initialize with a default log level, can be changed later
        this.currentLogLevel = LogLevel.DEBUG;
    }

    public static Debug getInstance() {
        if (instance == null) {
            instance = new Debug();
        }
        return instance;
    }

    public void setLogLevel(LogLevel level) {
        this.currentLogLevel = level;
    }

    public void log(String message, LogLevel level) {
        // Only log messages if their level is equal to or higher than the current log level
        if (level.ordinal() >= this.currentLogLevel.ordinal()) {
            System.out.println("[" + level + "] " + message);
        }
    }
}
