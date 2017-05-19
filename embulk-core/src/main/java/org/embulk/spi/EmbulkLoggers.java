package org.embulk.spi;

import com.google.common.collect.ImmutableMap;
import org.embulk.config.ConfigSource;
import org.embulk.plugin.PluginType;

public class EmbulkLoggers
{
    public static EmbulkLogger newEmbulkLogger(ExecSession execSession, ConfigSource configSource)
    {
        final ImmutableMap.Builder<EmbulkLogger.LogType, LoggerPlugin> loggerPlugins = ImmutableMap.builder();
        if (!configSource.has("logger")) {
            throw new RuntimeException(); // TODO
        }

        final ConfigSource loggerConfig = configSource.get(ConfigSource.class, "logger");
        for (EmbulkLogger.LogType logType : EmbulkLogger.LogType.values()) {
            if (loggerConfig.has(logType.toString())) {
                final ConfigSource loggerPluginConfig = loggerConfig.get(ConfigSource.class, logType.toString());
                final PluginType loggerPluginType = loggerPluginConfig.get(PluginType.class, "type");
                loggerPlugins.put(logType, execSession.newPlugin(LoggerPlugin.class, loggerPluginType));
            }
            else {
                throw new RuntimeException(); // TODO
            }
        }

        return EmbulkLogger.createEmbulkLogger(loggerPlugins.build());
    }
}
