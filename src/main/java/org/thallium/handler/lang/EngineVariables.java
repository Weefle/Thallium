package org.thallium.handler.lang;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents variables for LanguageHandler
 */
public class EngineVariables {
    private HashMap<String, Object> variableMap = new HashMap<String, Object>();

    public EngineVariables(){}

    public void addVariable(String varName, Object value){
        variableMap.put(varName, value);
    }

    public void removeVariable(String varName){
        variableMap.remove(varName);
    }

    public Map<String, Object> convertToMap(){
        return this.variableMap;
    }
}
