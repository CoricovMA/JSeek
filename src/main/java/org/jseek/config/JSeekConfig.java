package org.jseek.config;

import sun.jvm.hotspot.oops.ObjArrayKlass;

import java.util.HashMap;
import java.util.Map;

public class JSeekConfig {

    public static Map<String, Object> configs = new HashMap<>();

    public enum Property{
        AVAILABLE_COMMANDS("commands","Available Commands", (Object) new String[]{"info", "job"});

        private String key;
        private String description;
        private Object value;

        Property(String key, String description, Object... value){
            this.key = key;
            this.value = value;
            this.description = description;
            configs.put(key, value);
        }

        public Object getValue() {
            return value;
        }
    }

}