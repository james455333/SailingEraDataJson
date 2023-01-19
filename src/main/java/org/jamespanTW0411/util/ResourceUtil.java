package org.jamespanTW0411.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.Properties;

public class ResourceUtil {
    private static final Logger logger = LogManager.getLogger(PropertyUtil.class);
    private static final ClassLoader classLoader = ResourceUtil.class.getClassLoader();
    public static String dataFolderPath;
    public static String userSettingPath;
    public static void init() throws IOException {
        Properties prop = new Properties();
        InputStream in = classLoader.getResourceAsStream("SystemSetting.properties");
        prop.load(in);
        dataFolderPath = (String) prop.get("folder.set.data.name");
        userSettingPath = (String) prop.get("folder.set.user.name");
    }

    public static URL dataURL(String name){
        logger.info(dataFolderPath +  name + ".json");
        if (classLoader != null)
            return classLoader.getResource(dataFolderPath +  name + ".json");
        throw new RuntimeException("ResourceUtil not been initialed");
    }

    public static URL getResourceURL(String name){
        if (classLoader != null)
            return classLoader.getResource(name);
        throw new RuntimeException("ResourceUtil not been initialed");
    }

    public static Properties getProperties(String name) throws IOException {
        if (classLoader != null){
            Properties prop = new Properties();
            InputStream in = classLoader.getResourceAsStream(name + ".properties");
            prop.load(in);
            return prop;
        }
        throw new RuntimeException("ResourceUtil not been initialed");
    }

    public static void storeProperties(String name, Properties prop){
        if (classLoader != null){
            try(FileOutputStream fos = new FileOutputStream(classLoader.getResource(name + ".properties").getPath())){
                prop.store(fos, null);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            throw new RuntimeException("ResourceUtil not been initialed");
        }
    }
}
