/*
 * palette4bootstrap - A netbeans palette plugin for the Bootstrap
 * Copyright (c) 2017-2018 Tauquir Ahmed (tauquirahmed93@gmail.com)
 * Licensed under the MIT License.
 */
package org.tauquir.palette4bootstrap.items;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;
import org.tauquir.palette4bootstrap.bsPaletteUtilities;

public class FontAwesome implements ActiveEditorDrop {

    private String faIconName = "address-book";
    private final List<String> classesList;
    private boolean ariaHidden = true;


    public FontAwesome() {
        classesList = new ArrayList<>();
    }

    public String generateBody() {
        StringBuilder code = new StringBuilder();
        code.append("<span class=\"fa fa-").append(faIconName);
        for (String string : classesList) {
            code.append(" ").append(string);
        }
        code.append("\"");
        if (ariaHidden) {
            code.append(" aria-hidden=\"true\"");
        }
        code.append("></span>");
        return code.toString();
    }

    public void setFaIconName(String faIconName) {
        this.faIconName = faIconName;
    }

    public void setAriaHidden(boolean ariaHidden) {
        this.ariaHidden = ariaHidden;
    }

    public boolean addClass(String className) {
        if (classesList.contains(className)) {
            return false;
        } else {
            classesList.add(className);
            return true;
        }
    }

    public boolean removeClass(String className) {
        return classesList.remove(className);
    }

    public void resetClasses() {
        classesList.clear();
    }

    @Override
    public boolean handleTransfer(JTextComponent targetComponent) {
        FontAwesomeCustomizer c = new FontAwesomeCustomizer(this);
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
