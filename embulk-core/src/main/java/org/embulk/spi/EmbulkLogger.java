package org.embulk.spi;

import java.util.Map;

public class EmbulkLogger
{
    public enum LogType
    {
        ERROR_DATA("error_data"); // TODO , ALERT, NORMAL;

        private final String name;

        LogType(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

    }

    public enum LogLevel
    {
        INFO, WARN, ERROR;
    }

    static EmbulkLogger createEmbulkLogger(final Map<LogType, LoggerPlugin> loggerPlugins)
    {
        return new EmbulkLogger(loggerPlugins);
    }

    private final Map<LogType, LoggerPlugin> loggerPlugins;

    private EmbulkLogger(final Map<LogType, LoggerPlugin> loggerPlugins)
    {
        this.loggerPlugins = loggerPlugins;
    }

    public void log(LogType type, LogLevel level, String message)
    {
        loggerPlugins.get(type).log(level, message);
    }

}
