package org.thallium.plugin;

import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.Validate;
import org.thallium.ThalliumHandler;
import org.thallium.event.PlayerJoinEvent;
import org.thallium.event.types.Event;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Manages events.
 * @author PizzaCrust
 */
public class EventManager {
    private static ArrayList<Method> eventMethods = new ArrayList<Method>();

    public EventManager(){}

    public static EventManager instance(){ return new EventManager(); }

    /**
     * Register a handler class
     * @param handlerClass the class
     */
    public void registerHandler(Class handlerClass) {
        Method[] methods = handlerClass.getDeclaredMethods();
        for (Method method : methods){
            Class[] parameterTypes = method.getParameterTypes();
            for (Class parameterClass : parameterTypes){
                if(parameterClass.isAssignableFrom(Event.class)){
                    eventMethods.add(method);
                }
            }
        }
    }

    /**
     * Calls a event
     * @param event the event
     */
    public void callEvent(Event event){
        Validate.notNull(event);
        if(event instanceof PlayerJoinEvent){
            for(Method method : eventMethods){
                Class[] parameterClasses = method.getParameterTypes();
                for (Class parameterClass : parameterClasses){
                    if(parameterClass == PlayerJoinEvent.class){
                        try {
                            method.invoke(method.getDeclaringClass().newInstance(), null);
                        } catch (Exception e){
                            ThalliumHandler.apiLogger.info("Failed to pass event PlayerJoinEvent!");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
