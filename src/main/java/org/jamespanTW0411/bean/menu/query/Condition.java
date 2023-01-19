package org.jamespanTW0411.bean.menu.query;

import org.jamespanTW0411.bean.menu.MenuBean;

public class Condition extends MenuBean {



    public enum INPUT{
        TEXT, SELECT, LABEL
    }


    private INPUT input;
    private String option;

}
