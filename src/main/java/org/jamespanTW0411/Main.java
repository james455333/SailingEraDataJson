package org.jamespanTW0411;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jamespanTW0411.frame.GUIController;
import org.jamespanTW0411.util.ResourceUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        try {
            logger.info("start");
            init();
            GUIController guiController = new GUIController();
            guiController.start();
        } catch (IOException e) {
            logger.error(e);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    private static void init() throws IOException {
        ResourceUtil.init();
    }


}
