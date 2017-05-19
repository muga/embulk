package org.embulk.standards;

import com.google.common.collect.ImmutableMap;
import org.embulk.spi.EmbulkLogger;
import org.embulk.spi.Exec;
import org.embulk.spi.LoggerPlugin;

import java.util.Map;

public class StdoutLoggerPlugin
        implements LoggerPlugin
{
    @Override
    public void log(EmbulkLogger.LogLevel level, String message)
    {
        Map<String, Object> log = ImmutableMap.<String, Object>of("message", message);
        System.out.println("MN $ log_level: " + level + ", log: " + log);
        System.err.println("MN $ log_level: " + level + ", log: " + log);
        Exec.getLogger(this.getClass()).info("#MN logger");
    }
}
