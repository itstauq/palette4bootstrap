/*
 * palette4bootstrap - A netbeans palette plugin for the Bootstrap
 * Copyright (c) 2017-2018 Tauquir Ahmed (tauquirahmed93@gmail.com)
 * Licensed under the MIT License.
 */
package org.tauquir.palette4bootstrap.items;

import java.io.File;
import java.util.ArrayList;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;
import org.tauquir.palette4bootstrap.bsPaletteUtilities;

public class Image implements ActiveEditorDrop {

    private String src = "http://via.placeholder.com/350x150";  // Img location
    private String alt = "";  // Image alt text
    private String width = "";  // Image width
    private String height = "";  // Image height
    private final ArrayList<String> classesList;    // Stores all the classes

    public Image() {
        this.classesList = new ArrayList<>();
    }

    public String generateBody() {
        StringBuilder code = new StringBuilder();
        code.append("<img");                                // Generates <img
        code.append(" src=\"").append(src).append("\"");    // src=""
        if (!alt.isEmpty()) {
            code.append(" alt=\"").append(alt).append("\"");// alt=""
        }
        if (!width.isEmpty()) {
            code.append(" width=\"").append(width).append("\"");// width=""
        }
        if (!height.isEmpty()) {
            code.append(" height=\"").append(height).append("\"");// height=""
        }
        for (int i = 0; i < classesList.size(); i++) {
            if (i==0) {                                 // Append class=" on the first iteration
                code.append(" class=\"");
            }
            if (i>0) {                                  // Add spaces between attributes
                code.append(" ");
            }
            code.append(classesList.get(i));          // Add the attribute
            if (i==classesList.size()-1){             // Append " on the last iteration
                code.append("\"");
            }
        }
        code.append("/>");                               // Ending '/>' of <img
        return code.toString();
    }

    public boolean addClass(String attrName) {
        if (classesList.contains(attrName)) {
            return false;
        } else {
            classesList.add(attrName);
            return true;
        }
    }

    public boolean removeClass(String attrName) {
        return classesList.remove(attrName);
    }

    public void removeAllClasses() {
        classesList.clear();
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public boolean handleTransfer(JTextComponent targetComponent) {
        String currentlyOpenedFilePath = bsPaletteUtilities.getDocumentPath(targetComponent);
        File currentFile = new File(currentlyOpenedFilePath);
        ImageCustomizer c = new ImageCustomizer(this, currentFile);
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
