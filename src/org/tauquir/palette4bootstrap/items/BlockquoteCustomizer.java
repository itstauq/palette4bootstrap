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

public class BlockquoteCustomizer extends javax.swing.JPanel {

    private boolean dialogOK = false;
    private JDialog jd;
    /* If WebView is changed to a local variable, webEngine will start
    returning null on closing and reopening the dialog
    */
    private WebView browser;
    private WebEngine webEngine;
    private final JFXPanel fxPanel;
    private final Blockquote blockquote;

    /**
     * Creates new form BlockquoteCustomizer
     */
    public BlockquoteCustomizer(Blockquote blockquote) {
        this.blockquote = blockquote;
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
                + " " + NbBundle.getMessage(HeadingCustomizer.class, "TYPOGRAPHY.BLOCKQUOTE.NAME"));
        jd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        jd.add(this);
        jd.setLocationRelativeTo(null);
        jd.setVisible(true);
        jd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                /* Reset every parameter of Blockquote object so that old values
                   does not appear on re-opening the dialog */
                blockquote.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.");
                blockquote.setIsReverse(false);
                blockquote.setShowFooter(true);
                blockquote.setFooterText("Someone famous in <cite title=\"Source Title\">Source Title</cite>");
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
                   from blockquote.generateBody() after the webEngine loads the page
                 */
                webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            String processedString = blockquote.generateBody().trim().replaceAll("\n", "&nbsp;");
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
        codeGenerated.setText(blockquote.generateBody());
        // Update the live preview area in javafx thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String processedString = blockquote.generateBody().trim().replaceAll("\n", "&nbsp;");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        dialogOKBtn = new javax.swing.JButton();
        dialogCancelBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        codeGenerated = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        blockquoteText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        alignmentComboBox = new javax.swing.JComboBox<>();
        showFooterCheckBox = new javax.swing.JCheckBox();
        footerText = new javax.swing.JTextField();

        jPreview.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("windowBorder")));
        jPreview.setFocusable(false);
        jPreview.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(BlockquoteCustomizer.class, "BlockquoteCustomizer.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(BlockquoteCustomizer.class, "BlockquoteCustomizer.jLabel2.text")); // NOI18N

        jPanel1.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(dialogOKBtn, org.openide.util.NbBundle.getMessage(BlockquoteCustomizer.class, "BlockquoteCustomizer.dialogOKBtn.text")); // NOI18N
        dialogOKBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogBtnActionPerformed(evt);
            }
        });
        jPanel1.add(dialogOKBtn, java.awt.BorderLayout.WEST);

        org.openide.awt.Mnemonics.setLocalizedText(dialogCancelBtn, org.openide.util.NbBundle.getMessage(BlockquoteCustomizer.class, "BlockquoteCustomizer.dialogCancelBtn.text")); // NOI18N
        dialogCancelBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogBtnActionPerformed(evt);
            }
        });
        jPanel1.add(dialogCancelBtn, java.awt.BorderLayout.EAST);

        codeGenerated.setEditable(false);
        codeGenerated.setColumns(20);
        codeGenerated.setRows(5);
        codeGenerated.setText("<blockquote>\n    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>\n   <footer>Someone famous in <cite title=\"Source Title\">Source Title</cite></footer>\n</blockquote>"); // NOI18N
        codeGenerated.setFocusable(false);
        jScrollPane1.setViewportView(codeGenerated);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(BlockquoteCustomizer.class, "BlockquoteCustomizer.jLabel3.text")); // NOI18N
        jLabel3.setToolTipText(org.openide.util.NbBundle.getMessage(BlockquoteCustomizer.class, "BlockquoteCustomizer.blockquoteText.toolTipText")); // NOI18N

        blockquoteText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante."); // NOI18N
        blockquoteText.setToolTipText(org.openide.util.NbBundle.getMessage(BlockquoteCustomizer.class, "BlockquoteCustomizer.blockquoteText.toolTipText")); // NOI18N
        blockquoteText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textBoxFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(BlockquoteCustomizer.class, "BlockquoteCustomizer.jLabel4.text")); // NOI18N
        jLabel4.setToolTipText(org.openide.util.NbBundle.getMessage(BlockquoteCustomizer.class, "BlockquoteCustomizer.alignmentComboBox.toolTipText")); // NOI18N

        alignmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Reverse" }));
        alignmentComboBox.setToolTipText(org.openide.util.NbBundle.getMessage(BlockquoteCustomizer.class, "BlockquoteCustomizer.alignmentComboBox.toolTipText")); // NOI18N
        alignmentComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                alignmentComboBoxItemStateChanged(evt);
            }
        });

        showFooterCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(showFooterCheckBox, org.openide.util.NbBundle.getMessage(BlockquoteCustomizer.class, "BlockquoteCustomizer.showFooterCheckBox.text")); // NOI18N
        showFooterCheckBox.setToolTipText(org.openide.util.NbBundle.getMessage(BlockquoteCustomizer.class, "BlockquoteCustomizer.showFooterCheckBox.toolTipText")); // NOI18N
        showFooterCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                showFooterCheckBoxItemStateChanged(evt);
            }
        });

        footerText.setText("Someone famous in <cite title=\"Source Title\">Source Title</cite>"); // NOI18N
        footerText.setToolTipText(org.openide.util.NbBundle.getMessage(BlockquoteCustomizer.class, "BlockquoteCustomizer.footerText.toolTipText")); // NOI18N
        footerText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textBoxFocusLost(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showFooterCheckBox)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(blockquoteText, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                            .addComponent(alignmentComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(footerText)))
                    .addComponent(jPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(blockquoteText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alignmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showFooterCheckBox)
                    .addComponent(footerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void showFooterCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_showFooterCheckBoxItemStateChanged
        if (showFooterCheckBox.isSelected()) {
            footerText.setEnabled(true);
            blockquote.setShowFooter(true);
        } else {
            footerText.setEnabled(false);
            blockquote.setShowFooter(false);
        }
        updatePreviews();
    }//GEN-LAST:event_showFooterCheckBoxItemStateChanged

    private void alignmentComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_alignmentComboBoxItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {    // Ignore ItemEvent.DESELECTED
            blockquote.setIsReverse(evt.getItem().equals("Reverse"));
            updatePreviews();
        }
    }//GEN-LAST:event_alignmentComboBoxItemStateChanged

    private void textBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textBoxFocusLost
        blockquote.setText(blockquoteText.getText());
        blockquote.setFooterText(footerText.getText());
        updatePreviews();
    }//GEN-LAST:event_textBoxFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> alignmentComboBox;
    private javax.swing.JTextField blockquoteText;
    private javax.swing.JTextArea codeGenerated;
    private javax.swing.JButton dialogCancelBtn;
    private javax.swing.JButton dialogOKBtn;
    private javax.swing.JTextField footerText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPreview;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox showFooterCheckBox;
    // End of variables declaration//GEN-END:variables
}
