package org.thallium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thallium.plugin.EventManager;

/**
 * The main core of the API of Thallium
 * @author PizzaCrust
 */
public class ThalliumHandler {
    public static Logger apiLogger = LogManager.getLogger("Thallium");

    public static EventManager getEventManager(){
        return EventManager.instance();
    }
}
