package org.jseek.config;

public enum JSeekConfig {

    AVAILABLE_COMMANDS("commands", (Object) new String[]{"info", "job"});

    private String key;
    private Object value;

    JSeekConfig(String key, Object... value){
        this.key = key;
        this.value = value;
    }

}