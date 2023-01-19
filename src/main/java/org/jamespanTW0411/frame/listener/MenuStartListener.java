package org.jamespanTW0411.frame.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuStartListener implements ActionListener {
    private final MODE mode;
    private final JPanel tarPanel;

    public enum MODE{
        QUERY, EDIT, SETTING
    }

    public MenuStartListener(MODE mode, JPanel tarPanel){
        this.mode = mode;
        this.tarPanel = tarPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (this.mode){
            case QUERY:
                queryListener();
                break;
            case EDIT:
                editListener();
                break;
            case SETTING:
                settingListenr();
                break;
        }
    }
    private void queryListener() {
    }


    private void editListener() {
    }

    private void settingListenr() {
    }
}
