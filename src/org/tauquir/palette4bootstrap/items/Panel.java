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

public class Panel implements ActiveEditorDrop {

    private String panelStyle = "panel-default";
    private Boolean showHeader = true;
    private Boolean showBody = true;
    private Boolean showFooter = false;

    public String generateBody() {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"panel ").append(panelStyle).append("\">\n");
        if (showHeader) {
            sb.append("  <div class=\"panel-heading\">\n")
                    .append("    <h3 class=\"panel-title\">Title</h3>\n")
                    .append("  </div>\n");
        }
        if (showBody) {
            sb.append("  <div class=\"panel-body\">\n")
                    .append("    <!-- You might wish to remove the next line and insert a new, customized <span> from the palette -->\n")
                    .append("    <!--  the <span> tag is located inside Bootstrap Typography/More palette -->\n")
                    .append("    <span>Body</span>\n")
                    .append("  </div>\n");
        }
        if (showFooter) {
            sb.append("  <div class=\"panel-footer\">\n")
                    .append("    <span>Footer</span>\n")
                    .append("  </div>\n");
        }
        sb.append("</div>\n");
        return sb.toString();
    }

    @Override
    public boolean handleTransfer(JTextComponent jtc) {
        PanelCustomizer c = new PanelCustomizer(this);
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

    public void setPanelStyle(String panelStyle) {
        this.panelStyle = panelStyle;
    }

    public void setShowHeader(Boolean showHeader) {
        this.showHeader = showHeader;
    }

    public void setShowBody(Boolean showBody) {
        this.showBody = showBody;
    }

    public void setShowFooter(Boolean showFooter) {
        this.showFooter = showFooter;
    }

    public void resetParameters() {
        panelStyle = "panel-default";
        showHeader = true;
        showBody = true;
        showFooter = false;
    }
}
