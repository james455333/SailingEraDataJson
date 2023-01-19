package org.jamespanTW0411.bean;

import javax.swing.*;
import javax.swing.text.Style;
import java.util.Properties;

public class UserSetting {

    public UserSetting(Properties prop){
        this.init = Boolean.parseBoolean(prop.getProperty("init", "false"));
        this.language = prop.getProperty("language", "");
        this.font = prop.getProperty("font", "");
        this.fontStyle = Integer.parseInt(prop.getProperty("fontStyle", "-1"));
        this.fontSize = Integer.parseInt(prop.getProperty("fontSize", "-1"));
    }

    private boolean init;
    private String language;
    private int fontSize;
    private String font;
    private int fontStyle;

    public boolean getInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }
}
