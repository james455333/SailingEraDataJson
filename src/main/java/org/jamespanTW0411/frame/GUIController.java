package org.jamespanTW0411.frame;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jamespanTW0411.FileName;
import org.jamespanTW0411.bean.UserSetting;
import org.jamespanTW0411.bean.menu.MenuBean;
import org.jamespanTW0411.bean.SettingBean;
import org.jamespanTW0411.bean.normal.Language;
import org.jamespanTW0411.frame.listener.InitialListener;
import org.jamespanTW0411.util.json.JsonReader;
import org.jamespanTW0411.util.PropertyUtil;
import org.jamespanTW0411.util.ResourceUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class GUIController {
    private final UserSetting userSetting;
    private JFrame mainFrame;
    private JPanel headerPanel;
    private JPanel contextPanel;
    private static final Logger logger = LogManager.getLogger(GUIController.class);
    private final Gson gson = new Gson();
    PropertyUtil<UserSetting> ustPropertyUtil;
    private JsonReader gsonReader = gsonReader = new JsonReader();
    Font userFont;

    public GUIController() throws IOException {
        this.userSetting = new UserSetting(ResourceUtil.getProperties("UserSetting"));
        this.ustPropertyUtil = new PropertyUtil<>(this.userSetting);
    }

    public void initJFrame(){
        // 建立一個視窗，並將標題設定為「視窗程式」
        // 標題可用jFrame.setTitle("視窗程式");代替
        mainFrame = new JFrame("Sailing Era Utility");

        // 設定圖示
        ImageIcon imageIcon = new ImageIcon(ResourceUtil.getResourceURL("icon.png"));
        mainFrame.setIconImage(imageIcon.getImage());

        // 設定視窗大小(長,寬)
        mainFrame.setSize(800, 600);

        // 設定視窗開啟時的位置，有以下兩種常用設定方法
        mainFrame.setLocation(0,0); // --> 設定視窗開啟時左上角的座標，也可帶入Point物件
        mainFrame.setLocationRelativeTo(null); // --> 設定開啟的位置和某個物件相同，帶入null則會在畫面中間開啟

        // 關閉選項(右上角的叉叉圖示)按下後的動作
        // EXIT_ON_CLOSE：點選關閉時，關閉程式
        // DISPOSE_ON_CLOSE：點選關閉時，關閉顯示的視窗以及使用的資源，程式仍在背景執行
        // HIDE_ON_CLOSE：點選關閉時，僅隱藏顯示的視窗，程式仍在背景執行
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 設定視窗顯示，若未設定視窗只會在背景執行
        mainFrame.setVisible(true);

        // set universal font
        Font orgFont = mainFrame.getFont();
        userFont = new Font(!userSetting.getFont().equals("") ? userSetting.getFont() : orgFont.getName()
                , userSetting.getFontStyle() != -1 ? userSetting.getFontStyle() : orgFont.getStyle()
                , userSetting.getFontSize() != -1 ? userSetting.getFontSize() : orgFont.getSize());


        // set Container panel
        JPanel mainPanel = new JPanel();
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints panelGbc = new GridBagConstraints();
        headerPanel = new JPanel();
        panelGbc.gridx = 0;
        panelGbc.gridy = 0;
        panelGbc.gridwidth = 1;
        panelGbc.gridheight = 1;
        panelGbc.weightx = 1;
        panelGbc.weighty = 0.095;
        mainPanel.add(headerPanel, panelGbc);

        GridBagConstraints jsepGbc = new GridBagConstraints();
        jsepGbc.gridx = 0;
        jsepGbc.gridy = 1;
        jsepGbc.gridwidth = 1;
        jsepGbc.gridheight = 1;
        jsepGbc.weightx = 1;
        jsepGbc.weighty = 0.05;
        jsepGbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(new JSeparator(JSeparator.HORIZONTAL), jsepGbc);

        contextPanel = new JPanel();
        panelGbc.gridx = 0;
        panelGbc.gridy = 2;
        panelGbc.gridwidth = 1;
        panelGbc.gridheight = 1;
        panelGbc.weightx = 1;
        panelGbc.weighty = 0.9;
        panelGbc.fill = GridBagConstraints.NONE;
        mainPanel.add(contextPanel, panelGbc);
    }
    
    public void start() throws IOException, IllegalAccessException {
        initJFrame();
        logger.info("init : " + userSetting.getInit());
        if (!userSetting.getInit() || userSetting.getLanguage() == null){
            openLanguageSet();
        }else{
            setMenuStartView();
        }
    }

    public void setMenuStartView() {
        logger.info("now language set : " + userSetting.getLanguage());
        SettingBean settingBean = gsonReader.read(ResourceUtil.getResourceURL("menu/" + userSetting.getLanguage() + ".json"), SettingBean.class);
        logger.info(gson.toJson(settingBean));
        MenuBean[] majorBeans = settingBean.getMenu();

        headerPanel.setLayout(new GridLayout(1, majorBeans.length));
        logger.info(gson.toJson(userSetting));

        for (int i = 0; i < majorBeans.length; i++) {
            MenuBean majorBean = majorBeans[i];
            JButton majorBot = new JButton(majorBean.getDesp());
            majorBot.setFont(userFont);
            headerPanel.add(majorBot);
            if (i < majorBeans.length - 1)
                headerPanel.add(Box.createHorizontalStrut(10));
        }


        mainFrame.setVisible(true);
    }

    public void openLanguageSet(JPanel tarPanel){
        logger.info("openLanguageSet start");
        List<Language> languages = dateGsonReader(FileName.Language, Language.class);
        tarPanel.setLayout(new GridLayout(2, 1));
        // first row
        JLabel desp = new JLabel("請選擇語言 / choose your language", JLabel.CENTER);
        tarPanel.add(desp);

        // second row
        JPanel controlPanel = new JPanel();

        // lanauage scroll in secode row
        controlPanel.setLayout(new FlowLayout());
        final DefaultComboBoxModel<String> lanList = new DefaultComboBoxModel<>();
        for (Language language : languages) {
            lanList.addElement(language.getNo()+"." + Arrays.toString(language.getName()));
        }
        final JComboBox<String> listCombo = new JComboBox<>(lanList);
        listCombo.setSelectedIndex(0);
        controlPanel.add(new JScrollPane(listCombo));

        // confirm button in second row
        JButton confirmBot = new JButton("確認 / Confirm");
        confirmBot.addActionListener(new InitialListener(this, listCombo, languages, userSetting, ustPropertyUtil));
        controlPanel.add(confirmBot);

        tarPanel.add(controlPanel);
        mainFrame.setVisible(true);
        logger.info("openLanguageSet end");
    }
    public void openLanguageSet(){
        openLanguageSet(contextPanel);
    }

    private <T> List<T> dateGsonReader(FileName name, Class<T> dataClass) {
        URL resource = ResourceUtil.dataURL(name.name());
        return gsonReader.readAsList(resource, dataClass);
    }

}
