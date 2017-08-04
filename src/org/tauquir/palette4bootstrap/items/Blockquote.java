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

public class Blockquote implements ActiveEditorDrop {

    private String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.";
    private String footerText = "Someone famous in <cite title=\"Source Title\">Source Title</cite>";
    private boolean showFooter = true;
    private boolean isReverse = false;

    public Blockquote() {
    }

    public String generateBody() {
        StringBuilder code = new StringBuilder();
        code.append("<blockquote");
        if (isReverse) {   // Adding .blockquote-isReverse
            code.append(" class=\"blockquote-reverse\"");
        }
        code.append(">\n    <p>").append(text).append("</p>\n");
        if (showFooter) {   // Adding footer
            code.append("   <footer>").append(footerText).append("</footer>\n");
        }
        code.append("</blockquote>");
        return code.toString();
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIsReverse(boolean isReverse) {
        this.isReverse = isReverse;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }

    public void setShowFooter(boolean showFooter) {
        this.showFooter = showFooter;
    }

    @Override
    public boolean handleTransfer(JTextComponent targetComponent) {
        BlockquoteCustomizer c = new BlockquoteCustomizer(this);
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
