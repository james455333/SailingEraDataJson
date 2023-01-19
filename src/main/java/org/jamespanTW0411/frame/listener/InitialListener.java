package org.jamespanTW0411.frame.listener;

import org.jamespanTW0411.bean.UserSetting;
import org.jamespanTW0411.bean.normal.Language;
import org.jamespanTW0411.frame.GUIController;
import org.jamespanTW0411.util.PropertyUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class InitialListener implements ActionListener {

    private final GUIController guiController;
    private final JComboBox<String> listCombo;
    private final List<Language> languages;
    private final UserSetting userSetting;
    private final PropertyUtil<UserSetting> ustPropertyUtil;

    public InitialListener(GUIController guiController, JComboBox<String> listCombo, List<Language> languages, UserSetting userSetting, PropertyUtil<UserSetting> ustPropertyUtil) {
        this.guiController = guiController;
        this.listCombo = listCombo;
        this.languages = languages;
        this.userSetting = userSetting;
        this.ustPropertyUtil = ustPropertyUtil;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listCombo.getSelectedIndex() != -1) {
            userSetting.setInit(true);
            userSetting.setLanguage(languages.get(listCombo.getSelectedIndex()).getFileName());
            try {
                ustPropertyUtil.storeProperty();
                guiController.setMenuStartView();
            } catch (IOException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
