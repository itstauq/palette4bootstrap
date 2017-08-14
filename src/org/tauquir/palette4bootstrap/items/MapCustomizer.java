/*
 * palette4bootstrap - A netbeans palette plugin for the Bootstrap
 * Copyright (c) 2017-2018 Tauquir Ahmed (tauquirahmed93@gmail.com)
 * Licensed under the MIT License.
 */
package org.tauquir.palette4bootstrap.items;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JDialog;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

public class MapCustomizer extends javax.swing.JPanel {

    private boolean dialogOK = false;
    private JDialog jd;
    private WebEngine webEngine;
    private final JFXPanel fxPanel;
    private final Map map;

    /**
     * Creates new form MapCustomizer
     */
    public MapCustomizer(Map map) {
        this.map = map;
        initComponents();
        showOptionPanelForMode("Place");
        fxPanel = new JFXPanel();           // JFXPanel is required to display JavaFX
        Platform.setImplicitExit(false);    // Prevents jfx thread from shutting down when dialog is closed
        createScene();
        jPreview.add(fxPanel, BorderLayout.CENTER);
        fxPanel.setVisible(true);
    }

    public boolean showDialog() {
        dialogOK = false;
        jd = new JDialog();
        jd.setSize(this.getPreferredSize());
        jd.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE));
        jd.setTitle(NbBundle.getMessage(getClass(), "Customizer.InsertPrefix")
                + " " + NbBundle.getMessage(getClass(), "MEDIA.MAP.NAME"));
        jd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        jd.add(this);
        jd.setLocationRelativeTo(null);
        jd.setVisible(true);
        jd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                /* Reset every parameter of Map object so that old values
                   does not appear on re-opening the dialog */
                map.resetParameters();
            }
        }
        );
        return dialogOK;
    }

    private void createScene() {
        // webView is used to display live preview of the components. It is a
        // JavaFX component so it must be run on the JavaFX thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Set up the embedded browser:
                WebView browser = new WebView();
                webEngine = browser.getEngine();
                webEngine.load(getClass().getResource("resources/preview.html").toExternalForm());
                fxPanel.setScene(new Scene(browser));
            }
        });
    }

    private void updatePreviews() {
        // Update the Generated Code textArea
        codeGenerated.setText(map.generateBody());
        // Update the live preview area in javafx thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String processedString;
                if (map.getMapMode().equals("Street View")) {
                    processedString = ("<h2 class=\"text-center\">Live preview for Street View is not supported</h2>");
                } else {
                    processedString = map.generateBody().trim().replaceAll("\n", "");
                }
                String script = "document.getElementById('previewContainer').innerHTML='" + processedString + "'";
                webEngine.executeScript(script);
            }
        });
    }

    /**
     * Shows the options jPanel for the given mode and hides the other jPanels
     *
     * @param mode The mode which should be shown
     */
    private void showOptionPanelForMode(String mode) {
        javax.swing.JPanel optionsPanelToShow = optionsForModePlace;
        javax.swing.JPanel[] optionPanelsList = {optionsForModePlace,
            optionsForModeDirections,
            optionsForModeSearch,
            optionsForModeView,
            optionsForModeStreetView};
        switch (mode) {
            case "Place":
                optionsPanelToShow = optionsForModePlace;
                break;
            case "Directions":
                optionsPanelToShow = optionsForModeDirections;
                break;
            case "Search":
                optionsPanelToShow = optionsForModeSearch;
                break;
            case "View":
                optionsPanelToShow = optionsForModeView;
                break;
            case "Street View":
                optionsPanelToShow = optionsForModeStreetView;
                break;
        }
        optionsPanelToShow.setVisible(true);
        for (javax.swing.JPanel optionPanel : optionPanelsList) {
            if (!optionPanel.equals(optionsPanelToShow)) {
                optionPanel.setVisible(false);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dialogOKBtn = new javax.swing.JButton();
        dialogCancelBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        mapMode = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        frameWidth = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        frameHeight = new javax.swing.JTextField();
        optionsPanel = new javax.swing.JPanel();
        optionsForModePlace = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        modePlaceAddress = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        modePlaceZoomLevel = new javax.swing.JSpinner();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        modePlaceMapType = new javax.swing.JComboBox<>();
        optionsForModeDirections = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        modeDirectionsOrigin = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        modeDirectionsDestination = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        modeDirectionsZoomLevel = new javax.swing.JSpinner();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        modeDirectionsMapType = new javax.swing.JComboBox<>();
        optionsForModeSearch = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        modeSearchTerms = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        modeSearchZoomLevel = new javax.swing.JSpinner();
        jPanel16 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        modeSearchMapType = new javax.swing.JComboBox<>();
        optionsForModeView = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        modeViewCoordinates = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        modeViewZoomLevel = new javax.swing.JSpinner();
        jPanel19 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        modeViewMapType = new javax.swing.JComboBox<>();
        optionsForModeStreetView = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        modeStreetViewCoordinates = new javax.swing.JTextField();
        jPreview = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        codeGenerated = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        apiKey = new javax.swing.JTextField();
        getKeyBtn = new javax.swing.JButton();

        jPanel1.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(dialogOKBtn, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "Customizer.OK")); // NOI18N
        dialogOKBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogButtonActionPerformed(evt);
            }
        });
        jPanel1.add(dialogOKBtn, java.awt.BorderLayout.WEST);

        org.openide.awt.Mnemonics.setLocalizedText(dialogCancelBtn, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "Customizer.Cancel")); // NOI18N
        dialogCancelBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogButtonActionPerformed(evt);
            }
        });
        jPanel1.add(dialogCancelBtn, java.awt.BorderLayout.EAST);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        mapMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Place", "Directions", "Search", "View", "Street View" }));
        mapMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mapModeItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(mapMode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(mapMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel2.add(jPanel4);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel7.text")); // NOI18N

        frameWidth.setText("100%"); // NOI18N
        frameWidth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                frameWidthFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frameWidth, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(frameWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jPanel2.add(jPanel7);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel8.text")); // NOI18N

        frameHeight.setText("320px"); // NOI18N
        frameHeight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                frameHeightFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frameHeight, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(frameHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jPanel2.add(jPanel8);

        optionsPanel.setLayout(new javax.swing.OverlayLayout(optionsPanel));

        optionsForModePlace.setLayout(new java.awt.GridLayout(1, 0));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel9.text")); // NOI18N

        modePlaceAddress.setText("India"); // NOI18N
        modePlaceAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                modePlaceAddressFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modePlaceAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(modePlaceAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        optionsForModePlace.add(jPanel9);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel4.text")); // NOI18N

        modePlaceZoomLevel.setModel(new javax.swing.SpinnerNumberModel(4, 0, 18, 1));
        modePlaceZoomLevel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                modePlaceZoomLevelStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modePlaceZoomLevel)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(modePlaceZoomLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        optionsForModePlace.add(jPanel6);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel3.text")); // NOI18N

        modePlaceMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Roadmap", "Satellite" }));
        modePlaceMapType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                modePlaceMapTypeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modePlaceMapType, 0, 1, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(modePlaceMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        optionsForModePlace.add(jPanel5);

        optionsPanel.add(optionsForModePlace);

        optionsForModeDirections.setLayout(new java.awt.GridLayout(1, 0));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel10.text")); // NOI18N

        modeDirectionsOrigin.setText("Delhi, India"); // NOI18N
        modeDirectionsOrigin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                modeDirectionsOriginFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modeDirectionsOrigin, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(modeDirectionsOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        optionsForModeDirections.add(jPanel10);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel12.text")); // NOI18N

        modeDirectionsDestination.setText("Mumbai, India"); // NOI18N
        modeDirectionsDestination.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                modeDirectionsDestinationFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modeDirectionsDestination, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(modeDirectionsDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        optionsForModeDirections.add(jPanel13);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel6.text")); // NOI18N

        modeDirectionsZoomLevel.setModel(new javax.swing.SpinnerNumberModel(5, 0, 18, 1));
        modeDirectionsZoomLevel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                modeDirectionsZoomLevelStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modeDirectionsZoomLevel, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(modeDirectionsZoomLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        optionsForModeDirections.add(jPanel11);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel11.text")); // NOI18N

        modeDirectionsMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Roadmap", "Satellite" }));
        modeDirectionsMapType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                modeDirectionsMapTypeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modeDirectionsMapType, 0, 163, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(modeDirectionsMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        optionsForModeDirections.add(jPanel12);

        optionsPanel.add(optionsForModeDirections);

        optionsForModeSearch.setLayout(new java.awt.GridLayout(1, 0));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel13.text")); // NOI18N

        modeSearchTerms.setText("India"); // NOI18N
        modeSearchTerms.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                modeSearchTermsFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modeSearchTerms, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(modeSearchTerms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        optionsForModeSearch.add(jPanel14);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel14.text")); // NOI18N

        modeSearchZoomLevel.setModel(new javax.swing.SpinnerNumberModel(4, 0, 18, 1));
        modeSearchZoomLevel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                modeSearchZoomLevelStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modeSearchZoomLevel)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(modeSearchZoomLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        optionsForModeSearch.add(jPanel15);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel15.text")); // NOI18N

        modeSearchMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Roadmap", "Satellite" }));
        modeSearchMapType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                modeSearchMapTypeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modeSearchMapType, 0, 1, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(modeSearchMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        optionsForModeSearch.add(jPanel16);

        optionsPanel.add(optionsForModeSearch);

        optionsForModeView.setLayout(new java.awt.GridLayout(1, 0));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel16, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel16.text")); // NOI18N

        modeViewCoordinates.setText("20.5937,78.9629"); // NOI18N
        modeViewCoordinates.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                modeViewCoordinatesFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modeViewCoordinates, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(modeViewCoordinates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        optionsForModeView.add(jPanel17);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel17, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel17.text")); // NOI18N

        modeViewZoomLevel.setModel(new javax.swing.SpinnerNumberModel(4, 0, 18, 1));
        modeViewZoomLevel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                modeViewZoomLevelStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modeViewZoomLevel)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(modeViewZoomLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        optionsForModeView.add(jPanel18);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel18, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel18.text")); // NOI18N

        modeViewMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Roadmap", "Satellite" }));
        modeViewMapType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                modeViewMapTypeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modeViewMapType, 0, 1, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(modeViewMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        optionsForModeView.add(jPanel19);

        optionsPanel.add(optionsForModeView);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel19, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel19.text")); // NOI18N

        modeStreetViewCoordinates.setText("27.1733511,78.042109"); // NOI18N
        modeStreetViewCoordinates.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                modeStreetViewCoordinatesFocusLost(evt);
            }
        });

        javax.swing.GroupLayout optionsForModeStreetViewLayout = new javax.swing.GroupLayout(optionsForModeStreetView);
        optionsForModeStreetView.setLayout(optionsForModeStreetViewLayout);
        optionsForModeStreetViewLayout.setHorizontalGroup(
            optionsForModeStreetViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsForModeStreetViewLayout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modeStreetViewCoordinates, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE))
        );
        optionsForModeStreetViewLayout.setVerticalGroup(
            optionsForModeStreetViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsForModeStreetViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(modeStreetViewCoordinates, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        optionsPanel.add(optionsForModeStreetView);

        jPreview.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("windowBorder")));
        jPreview.setFocusable(false);
        jPreview.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "Customizer.GeneratedCode")); // NOI18N

        codeGenerated.setEditable(false);
        codeGenerated.setText("<iframe allowfullscreen frameborder=\"0\" width=\"100%\" height=\"320px\" src=\"https://www.google.com/maps/embed/v1/place?key=YOUR_API_KEY&q=India&zoom=4\"></iframe>"); // NOI18N
        codeGenerated.setFocusable(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.jLabel5.text")); // NOI18N

        apiKey.setText("YOUR_API_KEY"); // NOI18N
        apiKey.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                apiKeyFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(getKeyBtn, org.openide.util.NbBundle.getMessage(MapCustomizer.class, "MapCustomizer.getKeyBtn.text")); // NOI18N
        getKeyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getKeyBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(codeGenerated, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(apiKey)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(getKeyBtn)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(apiKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(getKeyBtn)
                            .addComponent(jLabel5)))
                    .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codeGenerated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialogButtonActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
        if (button.equals(dialogOKBtn)) {
            dialogOK = true;
            jd.dispose();

        } else if (button.equals(dialogCancelBtn)) {
            dialogOK = false;
            jd.dispose();
        }
    }//GEN-LAST:event_dialogButtonActionPerformed

    private void getKeyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getKeyBtnActionPerformed
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new java.net.URL("https://developers.google.com/maps/documentation/embed/get-api-key").toURI());
            } catch (MalformedURLException ex) {
                Exceptions.printStackTrace(ex);
            } catch (URISyntaxException | IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }//GEN-LAST:event_getKeyBtnActionPerformed

    private void apiKeyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_apiKeyFocusLost
        map.setApiKey(apiKey.getText());
        updatePreviews();
    }//GEN-LAST:event_apiKeyFocusLost

    private void frameWidthFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_frameWidthFocusLost
        map.setWidth(frameWidth.getText());
        updatePreviews();
    }//GEN-LAST:event_frameWidthFocusLost

    private void frameHeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_frameHeightFocusLost
        map.setHeight(frameHeight.getText());
        updatePreviews();
    }//GEN-LAST:event_frameHeightFocusLost

    private void modePlaceAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_modePlaceAddressFocusLost
        map.setModePlaceAddress(modePlaceAddress.getText());
        updatePreviews();
    }//GEN-LAST:event_modePlaceAddressFocusLost

    private void modePlaceZoomLevelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_modePlaceZoomLevelStateChanged
        map.setModePlaceZoom(modePlaceZoomLevel.getValue().toString());
        updatePreviews();
    }//GEN-LAST:event_modePlaceZoomLevelStateChanged

    private void modePlaceMapTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_modePlaceMapTypeItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            map.setModePlaceMapType(modePlaceMapType.getSelectedItem().toString());
            updatePreviews();
        }
    }//GEN-LAST:event_modePlaceMapTypeItemStateChanged

    private void mapModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mapModeItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            map.setMapMode(evt.getItem().toString());
            updatePreviews();
            showOptionPanelForMode(evt.getItem().toString());
        }
    }//GEN-LAST:event_mapModeItemStateChanged

    private void modeDirectionsOriginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_modeDirectionsOriginFocusLost
        map.setModeDirectionsOrigin(modeDirectionsOrigin.getText());
        updatePreviews();
    }//GEN-LAST:event_modeDirectionsOriginFocusLost

    private void modeDirectionsZoomLevelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_modeDirectionsZoomLevelStateChanged
        map.setModeDirectionsZoom(modeDirectionsZoomLevel.getValue().toString());
        updatePreviews();
    }//GEN-LAST:event_modeDirectionsZoomLevelStateChanged

    private void modeDirectionsMapTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_modeDirectionsMapTypeItemStateChanged
        map.setModeDirectionsMapType(modeDirectionsMapType.getSelectedItem().toString());
        updatePreviews();
    }//GEN-LAST:event_modeDirectionsMapTypeItemStateChanged

    private void modeDirectionsDestinationFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_modeDirectionsDestinationFocusLost
        map.setModeDirectionsDestination(modeDirectionsDestination.getText());
        updatePreviews();
    }//GEN-LAST:event_modeDirectionsDestinationFocusLost

    private void modeSearchTermsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_modeSearchTermsFocusLost
        map.setModeSearchTerms(modeSearchTerms.getText());
        updatePreviews();
    }//GEN-LAST:event_modeSearchTermsFocusLost

    private void modeSearchZoomLevelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_modeSearchZoomLevelStateChanged
        map.setModeSearchZoom(modeSearchZoomLevel.getValue().toString());
        updatePreviews();
    }//GEN-LAST:event_modeSearchZoomLevelStateChanged

    private void modeSearchMapTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_modeSearchMapTypeItemStateChanged
        map.setModeSearchMapType(modeSearchMapType.getSelectedItem().toString());
        updatePreviews();
    }//GEN-LAST:event_modeSearchMapTypeItemStateChanged

    private void modeViewCoordinatesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_modeViewCoordinatesFocusLost
        map.setModeViewCoordinates(modeViewCoordinates.getText());
        updatePreviews();
    }//GEN-LAST:event_modeViewCoordinatesFocusLost

    private void modeViewZoomLevelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_modeViewZoomLevelStateChanged
        map.setModeViewZoom(modeViewZoomLevel.getValue().toString());
        updatePreviews();
    }//GEN-LAST:event_modeViewZoomLevelStateChanged

    private void modeViewMapTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_modeViewMapTypeItemStateChanged
        map.setModeViewMapType(modeViewMapType.getSelectedItem().toString());
        updatePreviews();
    }//GEN-LAST:event_modeViewMapTypeItemStateChanged

    private void modeStreetViewCoordinatesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_modeStreetViewCoordinatesFocusLost
        map.setModeStreetViewCoordinates(modeStreetViewCoordinates.getText());
        updatePreviews();
    }//GEN-LAST:event_modeStreetViewCoordinatesFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apiKey;
    private javax.swing.JTextField codeGenerated;
    private javax.swing.JButton dialogCancelBtn;
    private javax.swing.JButton dialogOKBtn;
    private javax.swing.JTextField frameHeight;
    private javax.swing.JTextField frameWidth;
    private javax.swing.JButton getKeyBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPreview;
    private javax.swing.JComboBox<String> mapMode;
    private javax.swing.JTextField modeDirectionsDestination;
    private javax.swing.JComboBox<String> modeDirectionsMapType;
    private javax.swing.JTextField modeDirectionsOrigin;
    private javax.swing.JSpinner modeDirectionsZoomLevel;
    private javax.swing.JTextField modePlaceAddress;
    private javax.swing.JComboBox<String> modePlaceMapType;
    private javax.swing.JSpinner modePlaceZoomLevel;
    private javax.swing.JComboBox<String> modeSearchMapType;
    private javax.swing.JTextField modeSearchTerms;
    private javax.swing.JSpinner modeSearchZoomLevel;
    private javax.swing.JTextField modeStreetViewCoordinates;
    private javax.swing.JTextField modeViewCoordinates;
    private javax.swing.JComboBox<String> modeViewMapType;
    private javax.swing.JSpinner modeViewZoomLevel;
    private javax.swing.JPanel optionsForModeDirections;
    private javax.swing.JPanel optionsForModePlace;
    private javax.swing.JPanel optionsForModeSearch;
    private javax.swing.JPanel optionsForModeStreetView;
    private javax.swing.JPanel optionsForModeView;
    private javax.swing.JPanel optionsPanel;
    // End of variables declaration//GEN-END:variables
}
