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

public class Alert implements ActiveEditorDrop {

    private String text = "<strong>Alert!</strong> This is an alert.";
    private final ArrayList<String> attributeList;
    private boolean dismissable = false;    // Adds close-button if dismissable is true

    public Alert() {
        this.attributeList = new ArrayList<>(4);
        addAttribute("alert-success");
    }

    public String generateBody() {
        StringBuilder code = new StringBuilder();
        code.append("<div class=\"alert");
        for (int i = 0; i < attributeList.size(); i++) {
            code.append(" ").append(attributeList.get(i));  // Add the attribute
        }
        code.append("\">\n");                               // Ending '">' of h1
        if (dismissable) {   // Following code block is for adding close button
            code.append("   <button class=\"close\" type=\"button\" data-dismiss=\"alert\" aria-label=\"Close\">")
                    .append("\n      <span aria-hidden=\"true\">&times;</span>")
                    .append("\n   </button>\n");
        }
        code.append("   ").append(text);    //Empty space is for formatting only
        code.append("\n</div>\n");          //Generates </div>
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

    public void resetAttributes() {
        attributeList.clear();
        addAttribute("alert-success");
    }

    public void setDismissable(boolean dismissable) {
        this.dismissable = dismissable;
    }

    @Override
    public boolean handleTransfer(JTextComponent targetComponent) {
        AlertCustomizer c = new AlertCustomizer(this);
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
