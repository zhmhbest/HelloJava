package org.example.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.example.IO;

import java.io.FileInputStream;

import static org.example.config.HelloProperties.getProperties;


public class HelloLogger {
    public static void demoLevel(Logger logger) {
        logger.setLevel(Level.DEBUG);
        logger.debug("DEBUG: debug");
        logger.info("DEBUG: info");
        logger.warn("DEBUG: warn");
        logger.error("DEBUG: error");
        logger.fatal("DEBUG: fatal");

        logger.setLevel(Level.INFO);
        logger.debug("INFO: debug");
        logger.info("INFO: info");
        logger.warn("INFO: warn");
        logger.error("INFO: error");
        logger.fatal("INFO: fatal");

        logger.setLevel(Level.WARN);
        logger.debug("WARN: debug");
        logger.info("WARN: info");
        logger.warn("WARN: warn");
        logger.error("WARN: error");
        logger.fatal("WARN: fatal");

        logger.setLevel(Level.ERROR);
        logger.debug("ERROR: debug");
        logger.info("ERROR: info");
        logger.warn("ERROR: warn");
        logger.error("ERROR: error");
        logger.fatal("ERROR: fatal");

        logger.setLevel(Level.FATAL);
        logger.debug("FATAL: debug");
        logger.info("FATAL: info");
        logger.warn("FATAL: warn");
        logger.error("FATAL: error");
        logger.fatal("FATAL: fatal");
    }


    public static void main(String[] args) {
        Logger logger = Logger.getLogger(HelloLogger.class);

        // 使用默认的配置信息，不需要写log4j.properties
        BasicConfigurator.configure();
        demoLevel(logger);

        // 加载resources下的log4j.properties
        PropertyConfigurator.configure(
                getProperties(IO.getResourceStream("/log4j.properties"))
        );
        demoLevel(logger);
    }
}
