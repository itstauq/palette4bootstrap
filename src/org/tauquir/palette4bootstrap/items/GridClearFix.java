/*
 * palette4bootstrap - A netbeans palette plugin for the Bootstrap
 * Copyright (c) 2017-2018 Tauquir Ahmed (tauquirahmed93@gmail.com)
 * Licensed under the MIT License.
 */
package org.tauquir.palette4bootstrap.items;

import java.util.ArrayList;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;
import org.tauquir.palette4bootstrap.bsPaletteUtilities;

public class GridClearFix implements ActiveEditorDrop {

    private final ArrayList<String> attributeList;

    public GridClearFix() {
        this.attributeList = new ArrayList<>(4);
    }

    /*
     * The "visibilityMap" variable will be used to store
     * which checkboxes are active and then cssClasses will
     * be generated accordingly
     */
    public String generateBody() {
        StringBuilder code = new StringBuilder();
        code.append("<div class=\"clearfix");
        for (int i = 0; i < attributeList.size(); i++) {
            code.append(" ").append(attributeList.get(i));
        }
        code.append("\"></div>");
        return code.toString();
    }

    @Override
    public boolean handleTransfer(JTextComponent jtc) {
        GridClearFixCustomizer c = new GridClearFixCustomizer(this);
        attributeList.clear();
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

    public boolean addAttribute(String attrName) {
        if (attributeList.contains(attrName)) {
            return false;
        } else {
            attributeList.add(attrName);
            return true;
        }
    }

    public boolean removeAttribute(String attrName) {
        return attributeList.remove(attrName);
    }

}
