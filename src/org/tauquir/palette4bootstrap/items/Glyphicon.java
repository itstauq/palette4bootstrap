/*
 * palette4bootstrap - A netbeans palette plugin for the Bootstrap
 * Copyright (c) 2017-2018 Tauquir Ahmed (tauquirahmed93@gmail.com)
 * Licensed under the MIT License.
 */
package org.tauquir.palette4bootstrap.items;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;
import org.tauquir.palette4bootstrap.bsPaletteUtilities;

public class Glyphicon implements ActiveEditorDrop {

    private String glyphName = "glyphicon-asterisk";

    public Glyphicon() {
    }

    public String generateBody() {
        StringBuilder code = new StringBuilder();
        code.append("<span class=\"glyphicon ").append(glyphName).append("\"></span>");
        return code.toString();
    }

    public void setGlyphName(String glyphName) {
        this.glyphName = glyphName;
    }

    @Override
    public boolean handleTransfer(JTextComponent targetComponent) {
        GlyphiconCustomizer c = new GlyphiconCustomizer(this);
        boolean accept = c.showDialog();
        if (accept) {
            String body = generateBody();
            try {
                bsPaletteUtilities.insert(body, targetComponent);
            } catch (BadLocationException ble) {
                accept = false;
            }
        }
        return accept;
    }

}
