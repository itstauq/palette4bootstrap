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

public class Heading implements ActiveEditorDrop {

    private String text = "Bootstrap heading";
    private String type = "h1";
    private final ArrayList<String> attributeList;

    public Heading() {
        this.attributeList = new ArrayList<>(4);
    }

    public String generateBody() {
        StringBuilder code = new StringBuilder();
        code.append("<").append(type);                  // Generates <h1
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
        code.append("</").append(type).append(">\n");   //Generates </h1>
        return code.toString();
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(String type) {
        this.type = type;
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
        HeadingCustomizer c = new HeadingCustomizer(this);
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
