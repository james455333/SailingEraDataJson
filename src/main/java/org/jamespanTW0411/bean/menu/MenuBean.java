package org.jamespanTW0411.bean.menu;

import org.jamespanTW0411.bean.CommonBean;

public class MenuBean extends CommonBean {

    public enum MODE{
        QUERY, EDIT, SETTING
    }

    private MODE mode;

    public MODE getMode() {
        return mode;
    }

    public void setMode(MODE mode) {
        this.mode = mode;
    }
}
