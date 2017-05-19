package org.embulk.spi;

public interface LoggerPlugin
{
    public void log(EmbulkLogger.LogLevel logLevel, String message);
}
