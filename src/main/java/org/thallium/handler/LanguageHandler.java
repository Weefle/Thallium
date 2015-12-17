package org.thallium.handler;

import org.thallium.handler.lang.EngineVariables;
import org.thallium.handler.lang.Languages;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.Reader;
import java.util.Map;

/**
 * Allows easy execution of supported programming languages for Thallium.
 * @author PizzaCrust
 */
public class LanguageHandler {
    private ScriptEngineManager manager;

    public LanguageHandler(ScriptEngineManager manager){
        this.manager = manager;
    }

    protected void setupVariables(ScriptEngine engine, EngineVariables vars){
        for(Map.Entry<String, Object> entry : vars.convertToMap().entrySet()){
            engine.put(entry.getKey(), entry.getValue());
        }
    }

    public void runCode(Languages lang, String code, EngineVariables variables) throws ScriptException{
        ScriptEngine jsEngine = manager.getEngineByName("JavaScript");
        ScriptEngine luaEngine = manager.getEngineByName("luaj");
        switch (lang){
            case JAVASCRIPT:
                setupVariables(jsEngine, variables);
                jsEngine.eval(code);
                break;
            case LUA:
                setupVariables(luaEngine, variables);
                luaEngine.eval(code);
                break;
        }
    }

    public void runCode(Languages lang, Reader reader, EngineVariables variables) throws ScriptException{
        ScriptEngine jsEngine = manager.getEngineByName("JavaScript");
        ScriptEngine luaEngine = manager.getEngineByName("luaj");
        switch (lang){
            case JAVASCRIPT:
                setupVariables(jsEngine, variables);
                jsEngine.eval(reader);
                break;
            case LUA:
                setupVariables(luaEngine, variables);
                luaEngine.eval(reader);
                break;
        }
    }
}
