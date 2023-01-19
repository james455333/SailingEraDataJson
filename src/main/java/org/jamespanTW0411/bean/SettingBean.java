package org.jamespanTW0411.bean;

import org.jamespanTW0411.bean.menu.MenuBean;

public class SettingBean {
    private String forward;
    private String backward;
    private String confirm;
    private String cancel;
    private MenuBean[] menu;

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getBackward() {
        return backward;
    }

    public void setBackward(String backward) {
        this.backward = backward;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public MenuBean[] getMenu() {
        return menu;
    }

    public void setMenu(MenuBean[] menu) {
        this.menu = menu;
    }
}
