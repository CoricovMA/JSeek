package org.jseek.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JSeekConfig {

    private static Map<String, Object> configs = new HashMap<>();
    private static JSeekConfig instance;

    public enum Property{
        AVAILABLE_COMMANDS("commands","Available Commands", new String[]{"info", "job"}),
        INFO_COMMAND("info", "The info command returns information about all available commands, or a specific command.\n\n" +
                "For example: ```jseek info <command name>```\n" +
                "or: ```jseek info```", ""),
        JOB_COMMAND("job", "The Job command returns a given number of found jobs, for a requested keyword(s).", "");

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
        configs.put(Property.INFO_COMMAND.key, Property.INFO_COMMAND.description);
        configs.put(Property.JOB_COMMAND.key, Property.JOB_COMMAND.description);
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