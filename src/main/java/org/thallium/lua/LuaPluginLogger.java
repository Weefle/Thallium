package org.thallium.lua;

import org.apache.logging.log4j.Logger;

/**
 * Log4j logger for lua plugins
 * @author PizzaCrust
 */
public class LuaPluginLogger {
    private Logger logger;

    public LuaPluginLogger(Logger logger){
        this.logger = logger;
    }

    public void info(String msg){
        logger.info(msg);
    }

    public void warn(String msg){
        logger.warn(msg);
    }

    public void fatal(String msg){
        logger.fatal(msg);
    }

    public void error(String msg){
        logger.error(msg);
    }

    public void debug(String msg){
        logger.debug(msg);
    }
}
