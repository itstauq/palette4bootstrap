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

public class HeadingCustomizer extends javax.swing.JPanel {

    private boolean dialogOK = false;
    private JDialog jd;
    private WebView browser; // If this is changed to a local variable, webEngine will start returning null on recreating the dialog
    private WebEngine webEngine;
    private final JFXPanel fxPanel;
    private final Heading heading;

    /**
     * Creates new form HeadingDialog
     */
    public HeadingCustomizer(Heading heading) {
        this.heading = heading;
        initComponents();
        fxPanel = new JFXPanel();   // JFXPanel is required to display JavaFX
        createScene();              // content inside Swing containers
        jPreview.add(fxPanel, BorderLayout.CENTER);
        fxPanel.setVisible(true);
    }

    public boolean showDialog() {
        dialogOK = false;
        jd = new JDialog();
        jd.setSize(this.getPreferredSize());
        jd.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE));
        jd.setTitle(NbBundle.getMessage(HeadingCustomizer.class, "Customizer.InsertPrefix")
                + " " + NbBundle.getMessage(HeadingCustomizer.class, "TYPOGRAPHY.HEADING.NAME"));
        jd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        jd.add(this);
        jd.setLocationRelativeTo(null);
        jd.setVisible(true);
        jd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                /* Reset every parameter of heading object so that old values
                   does not appear on re-opening the dialog */
                heading.setText("Bootstrap heading");
                heading.setType("h1");
                heading.removeAllAttributes();
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
                browser = new WebView();
                webEngine = browser.getEngine();
                webEngine.load(getClass().getResource("resources/preview.html").toExternalForm());
                fxPanel.setScene(new Scene(browser));

                /* The following code updates the preview window with content
                   from heading.generateBody() after the webEngine loads the page
                */
                webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            String processedString = heading.generateBody().trim().replaceAll("\n", "&nbsp;");
                            String script = "document.getElementById('previewContainer').innerHTML='" + processedString + "'";
                            webEngine.executeScript(script);
                        }
                    }
                });
            }
        });
    }

    private void updatePreviews() {
        // Update the Generated Code textArea
        codeGenerated.setText(heading.generateBody());
        // replace \n with &nbsp; or else Unexpected EOF error will occur inside executeScript()
        // Update the live preview area in javafx thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String processedString = heading.generateBody().trim().replaceAll("\n", "&nbsp;");
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

        jPreview = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        codeGenerated = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        headingText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        headingType = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        dialogOKBtn = new javax.swing.JButton();
        dialogCancelBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        nowrapCheckBox = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();

        jPreview.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("windowBorder")));
        jPreview.setFocusable(false);
        jPreview.setLayout(new java.awt.BorderLayout());

        codeGenerated.setEditable(false);
        codeGenerated.setColumns(20);
        codeGenerated.setLineWrap(true);
        codeGenerated.setRows(5);
        codeGenerated.setText("<h1>Bootstrap heading</h1>"); // NOI18N
        codeGenerated.setFocusable(false);
        jScrollPane1.setViewportView(codeGenerated);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "Customizer.GeneratedCode")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "Customizer.Preview")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jLabel3.text")); // NOI18N
        jLabel3.setToolTipText(org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.headingText.toolTipText")); // NOI18N

        headingText.setText("Bootstrap heading"); // NOI18N
        headingText.setToolTipText(org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.headingText.toolTipText")); // NOI18N
        headingText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                headingTextFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jLabel4.text")); // NOI18N
        jLabel4.setToolTipText(org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.headingType.toolTipText")); // NOI18N

        headingType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "h1", "h2", "h3", "h4", "h5", "h6" }));
        headingType.setToolTipText(org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.headingType.toolTipText")); // NOI18N
        headingType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                headingTypeItemStateChanged(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(dialogOKBtn, org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "Customizer.OK")); // NOI18N
        dialogOKBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogButtonActionPerformed(evt);
            }
        });
        jPanel1.add(dialogOKBtn, java.awt.BorderLayout.WEST);

        org.openide.awt.Mnemonics.setLocalizedText(dialogCancelBtn, org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "Customizer.Cancel")); // NOI18N
        dialogCancelBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogButtonActionPerformed(evt);
            }
        });
        jPanel1.add(dialogCancelBtn, java.awt.BorderLayout.EAST);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jLabel5.text")); // NOI18N
        jLabel5.setToolTipText(org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jComboBox1.toolTipText")); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "text-left", "text-center", "text-right", "text-justify" }));
        jComboBox1.setToolTipText(org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jComboBox1.toolTipText")); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jLabel6.text")); // NOI18N
        jLabel6.setToolTipText(org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jComboBox2.toolTipText")); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "text-lowercase", "text-uppercase", "text-capitalize" }));
        jComboBox2.setToolTipText(org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jComboBox2.toolTipText")); // NOI18N
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(nowrapCheckBox, org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.nowrapCheckBox.text")); // NOI18N
        nowrapCheckBox.setToolTipText(org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.nowrapCheckBox.toolTipText")); // NOI18N
        nowrapCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nowrapCheckBoxItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jLabel7.text")); // NOI18N
        jLabel7.setToolTipText(org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jComboBox3.toolTipText")); // NOI18N

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "text-muted", "text-primary", "text-success", "text-info", "text-warning", "text-danger" }));
        jComboBox3.setToolTipText(org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jComboBox3.toolTipText")); // NOI18N
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jLabel8.text")); // NOI18N
        jLabel8.setToolTipText(org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jComboBox4.toolTipText")); // NOI18N

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "bg-primary", "bg-success", "bg-info", "bg-warning", "bg-danger" }));
        jComboBox4.setToolTipText(org.openide.util.NbBundle.getMessage(HeadingCustomizer.class, "HeadingCustomizer.jComboBox4.toolTipText")); // NOI18N
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPreview, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(headingText)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(headingType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 87, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nowrapCheckBox)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(headingText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(headingType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nowrapCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
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

    private void headingTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_headingTextFocusLost
        heading.setText(headingText.getText());
        updatePreviews();
    }//GEN-LAST:event_headingTextFocusLost

    private void headingTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_headingTypeItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {    // Ignore ItemEvent.DESELECTED
            heading.setType(headingType.getSelectedItem().toString());
            updatePreviews();
        }
    }//GEN-LAST:event_headingTypeItemStateChanged

    private void jComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {    // Ignore ItemEvent.DESELECTED
            if (!evt.getItem().equals("Default")) {             // Add the selected item to the list
                heading.addAttribute(evt.getItem().toString()); // if "Default" option was not selected
                updatePreviews();
            }
        } else if (evt.getStateChange() == java.awt.event.ItemEvent.DESELECTED) {
            heading.removeAttribute(evt.getItem().toString());  // Remove the deselected item from the list
            updatePreviews();
        }
    }//GEN-LAST:event_jComboBoxItemStateChanged

    private void nowrapCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nowrapCheckBoxItemStateChanged
        if (nowrapCheckBox.isSelected()) {
            heading.addAttribute("text-nowrap");
        } else {
            heading.removeAttribute("text-nowrap");
        }
        updatePreviews();
    }//GEN-LAST:event_nowrapCheckBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea codeGenerated;
    private javax.swing.JButton dialogCancelBtn;
    private javax.swing.JButton dialogOKBtn;
    private javax.swing.JTextField headingText;
    private javax.swing.JComboBox<String> headingType;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPreview;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox nowrapCheckBox;
    // End of variables declaration//GEN-END:variables
}
