/*
 * palette4bootstrap - A netbeans palette plugin for the Bootstrap
 * Copyright (c) 2017-2018 Tauquir Ahmed (tauquirahmed93@gmail.com)
 * Licensed under the MIT License.
 */
package org.tauquir.palette4bootstrap.items;

import java.util.HashMap;
import java.util.Map;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;
import org.tauquir.palette4bootstrap.bsPaletteUtilities;

public class GridCol implements ActiveEditorDrop {

    /*
     * The "attributes" variable will have 16 key-value pairs
     * like col-lg for key and 12 for value
     * Together they will be used to generate css classes for bootstrap
     * using a for-each loop inside generateBody() method
     * if value is greater than 0
     */
    private final HashMap<String, Integer> attributes;

    public GridCol() {
        this.attributes = new HashMap<>();
        resetAttributes();
    }

    public String generateBody() {
        if (!(attributes.containsKey("col-lg")          // Return empty string
                || attributes.containsKey("col-md")     // if attributes has no
                || attributes.containsKey("col-sm")     // col-*-* key.
                || attributes.containsKey("col-xs"))) {
            return "";
        }
        StringBuilder code = new StringBuilder();
        code.append("<div class=\"");
        for (Map.Entry<String, Integer> entry : attributes.entrySet()) {
            if (entry.getValue() > 0) {
                if (code.length() > 12) {   // at this point, length() will return 12 on first iteration
                    code.append(" ");       // so we add space if this is not the first entry
                }
                code.append(entry.getKey());
                code.append("-");
                code.append(entry.getValue());
            }
        }
        code.append("\">\n\n</div>");
        return code.toString();
    }

    @Override
    public boolean handleTransfer(JTextComponent targetComponent) {
        GridColCustomizer c = new GridColCustomizer(this);
        resetAttributes();
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

    public void resetAttributes() {
        this.attributes.clear();
        this.attributes.put("col-xs", 1);
    }

    public void clearAllAttributes(){
        attributes.clear();
    }

    public void putAttribute(String key, Integer value){
        attributes.put(key, value);
    }
    public void removeAttribute(String key){
        attributes.remove(key);
    }

}
