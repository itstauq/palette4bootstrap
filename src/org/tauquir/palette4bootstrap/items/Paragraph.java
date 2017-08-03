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

public class Paragraph implements ActiveEditorDrop {

    private String text = "Paragraph";
    private final ArrayList<String> attributeList;

    public Paragraph() {
        this.attributeList = new ArrayList<>(4);
    }

    public String generateBody() {
        StringBuilder code = new StringBuilder();
        code.append("<p");                                 // Generates <p
        for (int i = 0; i < attributeList.size(); i++) {
            if (i==0) {                                 // Append class=" on the first iteration
                code.append(" class=\"");
            }
            if (i>0) {                                  // Add spaces between attributes
                code.append(" ");
            }
            code.append(attributeList.get(i));          // Add the attribute
            if (i==attributeList.size()-1){             // Append " on the last iteration
                code.append("\"");
            }
        }
        code.append(">");                               // Ending '>' of h1
        code.append(text);
        code.append("</p>\n");                          //Generates </p>
        return code.toString();
    }

    public void setText(String text) {
        this.text = text;
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

    public void removeAllAttributes() {
        attributeList.clear();
    }

    @Override
    public boolean handleTransfer(JTextComponent targetComponent) {
        ParagraphCustomizer c = new ParagraphCustomizer(this);
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
