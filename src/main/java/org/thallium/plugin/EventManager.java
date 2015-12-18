package org.thallium.plugin;

import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.Validate;
import org.thallium.ThalliumHandler;
import org.thallium.event.*;
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
                if(parameterClass.getSuperclass().isAssignableFrom(Event.class)){
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
                            method.invoke(method.getDeclaringClass().newInstance(), (PlayerJoinEvent) event);
                        } catch (Exception e){
                            ThalliumHandler.apiLogger.info("Failed to pass event PlayerJoinEvent!");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if(event instanceof PluginStartEvent){
            for(Method method : eventMethods){
                Class[] parameterClasses = method.getParameterTypes();
                for (Class parameterClass : parameterClasses){
                    if(parameterClass == PluginStartEvent.class){
                        try {
                            method.invoke(method.getDeclaringClass().newInstance(), (PluginStartEvent) event);
                        } catch (Exception e){
                            ThalliumHandler.apiLogger.info("Failed to pass event PluginStartEvent!");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if(event instanceof EntityAddedEvent){
            for(Method method : eventMethods){
                Class[] parameterClasses = method.getParameterTypes();
                for (Class parameterClass : parameterClasses){
                    if(parameterClass == EntityAddedEvent.class){
                        try {
                            method.invoke(method.getDeclaringClass().newInstance(), (EntityAddedEvent) event);
                        } catch (Exception e){
                            ThalliumHandler.apiLogger.info("Failed to pass event EntityAddedEvent!");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if(event instanceof EntityRemovedEvent){
            for(Method method : eventMethods){
                Class[] parameterClasses = method.getParameterTypes();
                for (Class parameterClass : parameterClasses){
                    if(parameterClass == EntityRemovedEvent.class){
                        try {
                            method.invoke(method.getDeclaringClass().newInstance(), (EntityRemovedEvent) event);
                        } catch (Exception e){
                            ThalliumHandler.apiLogger.info("Failed to pass event EntityRemovedEvent!");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if(event instanceof PlayerHeldItemChangeEvent){
            for(Method method : eventMethods){
                Class[] parameterClasses = method.getParameterTypes();
                for (Class parameterClass : parameterClasses){
                    if(parameterClass == PlayerHeldItemChangeEvent.class){
                        try {
                            method.invoke(method.getDeclaringClass().newInstance(), (PlayerHeldItemChangeEvent) event);
                        } catch (Exception e){
                            ThalliumHandler.apiLogger.info("Failed to pass event PlayerHeldItemChangeEvent!");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if(event instanceof EntityMoveEvent){
            for(Method method : eventMethods){
                Class[] parameterClasses = method.getParameterTypes();
                for (Class parameterClass : parameterClasses){
                    if(parameterClass == EntityMoveEvent.class){
                        try {
                            method.invoke(method.getDeclaringClass().newInstance(), (EntityMoveEvent) event);
                        } catch (Exception e){
                            ThalliumHandler.apiLogger.info("Failed to pass event EntityMoveEvent!");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
