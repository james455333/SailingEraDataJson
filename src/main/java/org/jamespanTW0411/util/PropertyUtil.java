package org.jamespanTW0411.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertyUtil<T> {
    private static final Logger logger = LogManager.getLogger(PropertyUtil.class);
    T target;
    public PropertyUtil(T target){
        this.target = target;
    }

    public void storeProperty() throws IOException, IllegalAccessException {

        String targetName = target.getClass().getSimpleName();
        Properties prop = ResourceUtil.getProperties(targetName);

        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = field.get(target);
            if (fieldValue != null){
                String name = field.getName();
                logger.info(fieldValue);
                if (fieldValue instanceof Boolean)
                    prop.setProperty(name, Boolean.toString((Boolean) field.get(target)));
                else if (fieldValue instanceof String)
                    prop.setProperty(name, (String) field.get(target));
                else if (fieldValue instanceof Integer)
                    prop.setProperty(name, String.valueOf(field.get(target)));
            }
        }
        ResourceUtil.storeProperties(targetName, prop);

    }
}
