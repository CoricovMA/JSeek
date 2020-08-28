package org.jseek.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JSeekConfig {

    private static Map<String, Object> configs = new HashMap<>();
    private static JSeekConfig instance;

    public enum Property{
        AVAILABLE_COMMANDS("commands","Available Commands", new String[]{"info", "job"});

        private String key;
        private String description;
        private Object value;

        Property(String key, String description, Object value){
            this.key = key;
            this.value = value;
            this.description = description;
            configs.put(key, value);
        }

        public Object getValue() {
            return value;
        }
    }

    private JSeekConfig(){
        configs.put(Property.AVAILABLE_COMMANDS.key, Property.AVAILABLE_COMMANDS.value);
    }

    public static JSeekConfig getInstance(){
        if(instance == null){
            instance = new JSeekConfig();
        }
        return  instance;
    }

    public Map<String, Object> getProperties(){
        return configs;
    }

}