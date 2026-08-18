package com.manage.process;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

import com.util.SystemPropertiesConfiguration;

/** Focused smoke test for the external Lawyers.properties Log4j configuration. */
public final class LoggingConfigurationRegressionTest {
    private LoggingConfigurationRegressionTest() {
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: <properties-file> <temporary-log-prefix>");
        }

        SystemPropertiesConfiguration configuration = new SystemPropertiesConfiguration();
        try (FileInputStream input = new FileInputStream(args[0])) {
            configuration.load(input);
        }

        configuration.setProperty("log4j.appender.file.file", args[1]);
        ComponentProcessServlet.configureLogging(configuration);

        int appenderCount = 0;
        StringBuffer configuredAppenders = new StringBuffer();
        Enumeration appenders = Logger.getRootLogger().getAllAppenders();
        while (appenders.hasMoreElements()) {
            Appender appender = (Appender) appenders.nextElement();
            if (appender != null) {
                appenderCount++;
                if (configuredAppenders.length() > 0) {
                    configuredAppenders.append(',');
                }
                configuredAppenders.append(appender.getName()).append(':')
                        .append(appender.getClass().getName());
            }
        }
        if (appenderCount < 2) {
            throw new AssertionError("Expected console and file appenders, found "
                    + appenderCount + " [" + configuredAppenders + "]");
        }

        String marker = "logging-regression-marker";
        Logger.getLogger(LoggingConfigurationRegressionTest.class).error(marker);

        Path datedDirectory = Paths.get(args[1]
                + new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        Path logFile = datedDirectory.resolve("logs.txt");
        if (!Files.isRegularFile(logFile)
                || !new String(Files.readAllBytes(logFile), "UTF-8").contains(marker)) {
            throw new AssertionError("Log message was not written to " + logFile);
        }
    }
}
