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

public class Carousel implements ActiveEditorDrop {

    private boolean autoChange = true;
    private boolean pauseOnHover = true;
    private boolean repeatOnce = false;
    private boolean allowKeyboard = true;
    private boolean showIndicators = true;
    private int startIndex = 0;
    private int numberOfSlides = 1;
    private int autoChangeInterval = 5000;

    public Carousel() {
    }

    public String generateBody() {
        StringBuilder code = new StringBuilder();
        code.append("<div id=\"carousel-1\" class=\"carousel slide\" data-ride=\"carousel\"");
        if (autoChange) {
            code.append(" data-interval=\"").append(autoChangeInterval).append("\"");
        } else {
            code.append(" data-interval=\"false\"");
        }
        if (!pauseOnHover) {
            code.append(" data-pause=\"false\"");
        }
        if (repeatOnce) {
            code.append(" data-wrap=\"false\"");
        }
        if (!allowKeyboard) {
            code.append(" data-keyboard=\"false\"");
        }
        code.append(">\n");
        if (showIndicators) {
            code.append("   <!-- Indicators -->\n")
                    .append("   <ol class=\"carousel-indicators\">\n");
            for (int i = 0; i < numberOfSlides; i++) {
                code.append("       <li data-target=\"#carousel-1\" data-slide-to=\"").append(i).append("\"");
                if (i == startIndex) {
                    code.append(" class=\"active\"");
                }
                code.append("></li>\n");
            }
            code.append("   </ol>\n");
        }
        code.append("   <!-- Wrapper for slides -->\n")
                .append("   <div class=\"carousel-inner\" role=\"listbox\">\n");
        for (int i = 0; i < numberOfSlides; i++) {
            code.append("       <div class=\"item");
            if (i == startIndex) {
                code.append(" active");
            }
            code.append("\">\n")
                    .append("           <img src=\"http://via.placeholder.com/494x393?text=Slide+Preview\" alt=\"Carousel Image\">\n")
                    .append("           <div class=\"carousel-caption\">\n")
                    .append("               <h3>Caption Heading</h3>\n")
                    .append("               <p>Caption Body goes here</p>\n")
                    .append("           </div>\n")
                    .append("       </div>\n");
        }
        code.append("   </div>\n");
        code.append("   <!-- Controls -->\n")
                .append("   <a class=\"left carousel-control\" href=\"#carousel-1\" role=\"button\" data-slide=\"prev\">\n")
                .append("       <span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>\n")
                .append("       <span class=\"sr-only\">Previous</span>\n")
                .append("   </a>\n")
                .append("   <a class=\"right carousel-control\" href=\"#carousel-1\" role=\"button\" data-slide=\"next\">\n")
                .append("       <span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>\n")
                .append("       <span class=\"sr-only\">Next</span>\n")
                .append("   </a>\n");
        code.append("</div>\n");
        return code.toString();
    }

    public void setAutoChange(boolean autoChange) {
        this.autoChange = autoChange;
    }

    public void setPauseOnHover(boolean pauseOnHover) {
        this.pauseOnHover = pauseOnHover;
    }

    public void setRepeatOnce(boolean repeatOnce) {
        this.repeatOnce = repeatOnce;
    }

    public void setAllowKeyboard(boolean allowKeyboard) {
        this.allowKeyboard = allowKeyboard;
    }

    public void setShowIndicators(boolean showIndicators) {
        this.showIndicators = showIndicators;
    }

    public void setAutoChangeInterval(int autoChangeInterval) {
        this.autoChangeInterval = autoChangeInterval;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setNumberOfSlides(int numberOfSlides) {
        this.numberOfSlides = numberOfSlides;
    }

    @Override
    public boolean handleTransfer(JTextComponent targetComponent) {
        CarouselCustomizer c = new CarouselCustomizer(this);
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
