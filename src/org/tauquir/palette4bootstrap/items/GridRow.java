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

public class GridRow implements ActiveEditorDrop {

    private final String body =
            "<div class=\"row\">\n" +
            "    <!-- Insert/Drop Grid Column codes below-->\n\n" +
            "</div>";

    @Override
    public boolean handleTransfer(JTextComponent jtc) {
        try {
            bsPaletteUtilities.insert(body, jtc);
        } catch (BadLocationException ble) {
            return false;
        }
        return true;
    }
}
