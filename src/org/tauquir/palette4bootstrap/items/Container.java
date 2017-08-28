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

public class Container implements ActiveEditorDrop {
    private boolean isFluid = false;

    public String generateBody() {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"");
        if (isFluid) sb.append("container-fluid");
        else sb.append("container");
        sb.append("\">\n</div>");
        return sb.toString();
    }

    @Override
    public boolean handleTransfer(JTextComponent jtc) {
        ContainerCustomizer c = new ContainerCustomizer(this);
        boolean accept = c.showDialog();
        if (accept) {
            String body = generateBody();
            try {
                bsPaletteUtilities.insert(body, jtc);
            } catch (BadLocationException ble) {
                accept = false;
            }
        }
        return accept;
    }

    public void setIsFluid(boolean isFluid) {
        this.isFluid = isFluid;
    }
}
