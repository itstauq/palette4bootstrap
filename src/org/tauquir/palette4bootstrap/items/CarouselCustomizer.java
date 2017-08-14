/*
 * palette4bootstrap - A netbeans palette plugin for the Bootstrap
 * Copyright (c) 2017-2018 Tauquir Ahmed (tauquirahmed93@gmail.com)
 * Licensed under the MIT License.
 */
package org.tauquir.palette4bootstrap.items;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JDialog;
import org.openide.util.NbBundle;

public class CarouselCustomizer extends javax.swing.JPanel {

    private boolean dialogOK = false;
    private JDialog jd;
    private WebEngine webEngine;
    private final JFXPanel fxPanel;
    private final Carousel carousel;

    /**
     * Creates new form CarouselCustomizer
     */
    public CarouselCustomizer(Carousel carousel) {
        this.carousel = carousel;
        initComponents();
        fxPanel = new JFXPanel();           // JFXPanel is required to display JavaFX
        createScene();                      // content inside Swing containers
        Platform.setImplicitExit(false);    // <- Prevents jfx thread from shutting down when dialog is closed
        livePreview.add(fxPanel, BorderLayout.CENTER);
        fxPanel.setVisible(true);
    }

    public boolean showDialog() {
        dialogOK = false;
        jd = new JDialog();
        jd.setSize(this.getPreferredSize());
        jd.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE));
        jd.setTitle(NbBundle.getMessage(getClass(), "Customizer.InsertPrefix")
                + " " + NbBundle.getMessage(getClass(), "MEDIA.CAROUSEL.NAME"));
        jd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        jd.add(this);
        jd.setLocationRelativeTo(null);
        jd.setVisible(true);
        jd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                /* Reset every parameter of Carousel object so that old values
                   does not appear on re-opening the dialog */
                carousel.setAutoChange(true);
                carousel.setPauseOnHover(true);
                carousel.setRepeatOnce(false);
                carousel.setAllowKeyboard(true);
                carousel.setShowIndicators(true);
                carousel.setStartIndex(0);
                carousel.setNumberOfSlides(1);
                carousel.setAutoChangeInterval(5000);
            }
        });
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

                /* The following code updates the preview window with content
                   from carousel.generateBody() after the webEngine loads the page
                 */
                webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            String processedString = carousel.generateBody().trim().replaceAll("\n", "");
                            String script = "document.getElementById('previewContainer').innerHTML='" + processedString + "'";
                            // Set the carousel container to a fixed width to tackle issues with preview rendering
                            webEngine.executeScript("document.getElementById('previewContainer').setAttribute( 'style', 'width: 494px; height: 393px; padding:0px')");
                            webEngine.executeScript(script);
                        }
                    }
                });
            }
        });
    }

    private void updatePreviews() {
        // Update the Generated Code textArea
        codeGenerated.setText(carousel.generateBody());
        // Update the live preview area in javafx thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String processedString = carousel.generateBody().trim().replaceAll("\n", "");
                String script = "document.getElementById('previewContainer').innerHTML='" + processedString + "'";
                webEngine.executeScript(script);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        settingsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        autoChangeInterval = new javax.swing.JSpinner();
        startIndex = new javax.swing.JComboBox<>();
        numberOfSlides = new javax.swing.JSpinner();
        autoChange = new javax.swing.JCheckBox();
        pauseOnHover = new javax.swing.JCheckBox();
        repeatOnce = new javax.swing.JCheckBox();
        allowKeyboard = new javax.swing.JCheckBox();
        showIndicators = new javax.swing.JCheckBox();
        dialogButtons = new javax.swing.JPanel();
        dialogCancelBtn = new javax.swing.JButton();
        dialogOKBtn = new javax.swing.JButton();
        previewPanel = new javax.swing.JTabbedPane();
        livePreview = new javax.swing.JPanel();
        generatedCode = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        codeGenerated = new javax.swing.JTextArea();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CarouselCustomizer.class, "CarouselCustomizer.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(CarouselCustomizer.class, "CarouselCustomizer.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(CarouselCustomizer.class, "CarouselCustomizer.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(CarouselCustomizer.class, "CarouselCustomizer.jLabel4.text")); // NOI18N

        autoChangeInterval.setModel(new javax.swing.SpinnerNumberModel(5000, null, null, 100));
        autoChangeInterval.setEditor(new javax.swing.JSpinner.NumberEditor(autoChangeInterval, "#"));
        autoChangeInterval.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                autoChangeIntervalStateChanged(evt);
            }
        });

        startIndex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Slide 1" }));
        startIndex.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                startIndexItemStateChanged(evt);
            }
        });

        numberOfSlides.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        numberOfSlides.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                numberOfSlidesStateChanged(evt);
            }
        });

        autoChange.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(autoChange, org.openide.util.NbBundle.getMessage(CarouselCustomizer.class, "CarouselCustomizer.autoChange.text")); // NOI18N
        autoChange.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                autoChangeItemStateChanged(evt);
            }
        });

        pauseOnHover.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(pauseOnHover, org.openide.util.NbBundle.getMessage(CarouselCustomizer.class, "CarouselCustomizer.pauseOnHover.text")); // NOI18N
        pauseOnHover.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pauseOnHoverItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(repeatOnce, org.openide.util.NbBundle.getMessage(CarouselCustomizer.class, "CarouselCustomizer.repeatOnce.text")); // NOI18N
        repeatOnce.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                repeatOnceItemStateChanged(evt);
            }
        });

        allowKeyboard.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(allowKeyboard, org.openide.util.NbBundle.getMessage(CarouselCustomizer.class, "CarouselCustomizer.allowKeyboard.text")); // NOI18N
        allowKeyboard.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                allowKeyboardItemStateChanged(evt);
            }
        });

        showIndicators.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(showIndicators, org.openide.util.NbBundle.getMessage(CarouselCustomizer.class, "CarouselCustomizer.showIndicators.text")); // NOI18N
        showIndicators.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                showIndicatorsItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(autoChange))
                        .addGap(28, 28, 28))
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(settingsPanelLayout.createSequentialGroup()
                                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(autoChangeInterval)
                                    .addComponent(numberOfSlides)
                                    .addComponent(startIndex, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(settingsPanelLayout.createSequentialGroup()
                                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(showIndicators)
                                    .addComponent(allowKeyboard)
                                    .addComponent(repeatOnce)
                                    .addComponent(pauseOnHover))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autoChange)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(autoChangeInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(numberOfSlides, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(startIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pauseOnHover)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repeatOnce)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allowKeyboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showIndicators)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dialogButtons.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(dialogCancelBtn, org.openide.util.NbBundle.getMessage(CarouselCustomizer.class, "CarouselCustomizer.dialogCancelBtn.text")); // NOI18N
        dialogCancelBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogBtnActionPerformed(evt);
            }
        });
        dialogButtons.add(dialogCancelBtn, java.awt.BorderLayout.EAST);

        org.openide.awt.Mnemonics.setLocalizedText(dialogOKBtn, org.openide.util.NbBundle.getMessage(CarouselCustomizer.class, "CarouselCustomizer.dialogOKBtn.text")); // NOI18N
        dialogOKBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogBtnActionPerformed(evt);
            }
        });
        dialogButtons.add(dialogOKBtn, java.awt.BorderLayout.CENTER);

        livePreview.setLayout(new java.awt.BorderLayout());
        previewPanel.addTab(org.openide.util.NbBundle.getMessage(CarouselCustomizer.class, "CarouselCustomizer.livePreview.TabConstraints.tabTitle"), livePreview); // NOI18N

        jScrollPane1.setBorder(null);

        codeGenerated.setEditable(false);
        codeGenerated.setColumns(20);
        codeGenerated.setLineWrap(true);
        codeGenerated.setRows(5);
        codeGenerated.setText("<div id=\"carousel-1\" class=\"carousel slide\" data-ride=\"carousel\" data-interval=\"5000\">\n   <!-- Indicators -->\n   <ol class=\"carousel-indicators\">\n       <li data-target=\"#carousel-1\" data-slide-to=\"0\" class=\"active\"></li>\n   </ol>\n   <!-- Wrapper for slides -->\n   <div class=\"carousel-inner\" role=\"listbox\">\n       <div class=\"item active\">\n           <img src=\"http://via.placeholder.com/1400x600\" alt=\"Carousel Image\">\n           <div class=\"carousel-caption\">\n               <h3>Caption Heading</h3>\n               <p>Caption Body goes here</p>\n           </div>\n       </div>\n   </div>\n   <!-- Controls -->\n   <a class=\"left carousel-control\" href=\"#carousel-1\" role=\"button\" data-slide=\"prev\">\n       <span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>\n       <span class=\"sr-only\">Previous</span>\n   </a>\n   <a class=\"right carousel-control\" href=\"#carousel-1\" role=\"button\" data-slide=\"next\">\n       <span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>\n       <span class=\"sr-only\">Next</span>\n   </a>\n</div>"); // NOI18N
        codeGenerated.setWrapStyleWord(true);
        codeGenerated.setFocusable(false);
        jScrollPane1.setViewportView(codeGenerated);

        javax.swing.GroupLayout generatedCodeLayout = new javax.swing.GroupLayout(generatedCode);
        generatedCode.setLayout(generatedCodeLayout);
        generatedCodeLayout.setHorizontalGroup(
            generatedCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
        );
        generatedCodeLayout.setVerticalGroup(
            generatedCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
        );

        previewPanel.addTab(org.openide.util.NbBundle.getMessage(CarouselCustomizer.class, "CarouselCustomizer.generatedCode.TabConstraints.tabTitle"), generatedCode); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previewPanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dialogButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(previewPanel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dialogButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialogBtnActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
        if (button.equals(dialogOKBtn)) {
            dialogOK = true;
            jd.dispose();

        } else if (button.equals(dialogCancelBtn)) {
            dialogOK = false;
            jd.dispose();
        }
    }//GEN-LAST:event_dialogBtnActionPerformed

    private void autoChangeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_autoChangeItemStateChanged
        carousel.setAutoChange(autoChange.isSelected());
        autoChangeInterval.setEnabled(autoChange.isSelected());
        updatePreviews();
    }//GEN-LAST:event_autoChangeItemStateChanged

    private void pauseOnHoverItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pauseOnHoverItemStateChanged
        carousel.setPauseOnHover(pauseOnHover.isSelected());
        updatePreviews();
    }//GEN-LAST:event_pauseOnHoverItemStateChanged

    private void repeatOnceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_repeatOnceItemStateChanged
        carousel.setRepeatOnce(repeatOnce.isSelected());
        updatePreviews();
    }//GEN-LAST:event_repeatOnceItemStateChanged

    private void allowKeyboardItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_allowKeyboardItemStateChanged
        carousel.setAllowKeyboard(allowKeyboard.isSelected());
        updatePreviews();
    }//GEN-LAST:event_allowKeyboardItemStateChanged

    private void showIndicatorsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_showIndicatorsItemStateChanged
        carousel.setShowIndicators(showIndicators.isSelected());
        updatePreviews();
    }//GEN-LAST:event_showIndicatorsItemStateChanged

    private void autoChangeIntervalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_autoChangeIntervalStateChanged
        carousel.setAutoChangeInterval(Integer.parseInt(autoChangeInterval.getValue().toString()));
        updatePreviews();
    }//GEN-LAST:event_autoChangeIntervalStateChanged

    private void numberOfSlidesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_numberOfSlidesStateChanged
        int numberSelected = Integer.parseInt(numberOfSlides.getValue().toString());
        carousel.setNumberOfSlides(numberSelected);
        String[] modelStrings = new String[numberSelected];
        for (int i = 0; i < numberSelected; i++) {
            modelStrings[i] = "Slide "+ (i+1);
        }
        startIndex.setModel(new javax.swing.DefaultComboBoxModel<>(modelStrings));
        startIndex.setSelectedIndex(0);
        carousel.setStartIndex(0);
        updatePreviews();
    }//GEN-LAST:event_numberOfSlidesStateChanged

    private void startIndexItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_startIndexItemStateChanged
        carousel.setStartIndex(startIndex.getSelectedIndex());
        updatePreviews();
    }//GEN-LAST:event_startIndexItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox allowKeyboard;
    private javax.swing.JCheckBox autoChange;
    private javax.swing.JSpinner autoChangeInterval;
    private javax.swing.JTextArea codeGenerated;
    private javax.swing.JPanel dialogButtons;
    private javax.swing.JButton dialogCancelBtn;
    private javax.swing.JButton dialogOKBtn;
    private javax.swing.JPanel generatedCode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel livePreview;
    private javax.swing.JSpinner numberOfSlides;
    private javax.swing.JCheckBox pauseOnHover;
    private javax.swing.JTabbedPane previewPanel;
    private javax.swing.JCheckBox repeatOnce;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JCheckBox showIndicators;
    private javax.swing.JComboBox<String> startIndex;
    // End of variables declaration//GEN-END:variables
}
