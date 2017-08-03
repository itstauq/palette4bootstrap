/**
 * palette4bootstrap - A netbeans palette plugin for the Bootstrap
 * Copyright (c) 2017-2018 Tauquir Ahmed (tauquirahmed93@gmail.com)
 * Licensed under the MIT License.
 */
package org.tauquir.palette4bootstrap.items;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;
import org.tauquir.palette4bootstrap.bsPaletteUtilities;

public class BlankPage implements ActiveEditorDrop {

    private String pageTitle = "";
    private String bootstrapCSS = "";
    private String jQueryJS = "";
    private String bootstrapJS = "";
    private String bootstrapThemeCSS = "";
    private String containerType = "";
    private boolean addIEsupport = true;

    public BlankPage() {
    }

    // <editor-fold defaultstate="collapsed" desc="generateBody()">
    private String generateBody() {

        StringBuilder body = new StringBuilder();
        body.append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\">\n")
                .append("  <head>\n")
                .append("    <meta charset=\"utf-8\">\n")
                .append("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n")
                .append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n")
                .append("    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\n")
                .append("    <title>")
                .append(getPageTitle()) // Adds page title from BlankPageCustomizer.java
                .append("</title>\n");
        if (!"".equals(getBootstrapCSS())) {    // If getBootstrapCSS is not empty, add CSS
            body.append("    <!-- Bootstrap -->\n")
                    .append("    <link href=\"")
                    .append(getBootstrapCSS())
                    .append("\" rel=\"stylesheet\">\n");
        }
        if (!"".equals(getBootstrapThemeCSS())) {    // If getBootstrapThemeCSS is not empty, add CSS
            body.append("    <!-- Optional Bootstrap Theme -->\n")
                    .append("    <link href=\"")
                    .append(getBootstrapThemeCSS())
                    .append("\" rel=\"stylesheet\">\n");
        }
        if (isAddIEsupport()) { // If option for supporting old IE Browsers is selected in BlankPageCustomizer.java
            body.append("    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->\n")
                    .append("    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n")
                    .append("    <!--[if lt IE 9]>\n")
                    .append("      <script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\n")
                    .append("      <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n")
                    .append("    <![endif]-->\n");
        }
        body.append("  </head>\n")
                .append("  <body>\n");
        if (!"".equals(getContainerType())) {
            body.append("    <div class=\"")
                    .append(getContainerType())
                    .append("\">\n")
                    .append("        <!-- Insert/Drop Grid Row codes below -->\n\n")
                    .append("    </div>\n");
        }
        if (!"".equals(getjQueryJS())) {    // If getjQueryJS is not empty, add link to jQuery
            body.append("    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\n")
                    .append("    <script src=\"")
                    .append(getjQueryJS())
                    .append("\"></script>\n");
        }
        if (!"".equals(getBootstrapJS())) {
            body.append("    <!-- Include all compiled plugins (below), or include individual files as needed -->\n")
                    .append("    <script src=\"")
                    .append(getBootstrapJS())
                    .append("\"></script>\n");
        }
        body.append("  </body>\n")
                .append("</html>\n");
        return body.toString();
    }
    // </editor-fold>

    @Override
    public boolean handleTransfer(JTextComponent targetComponent) {
        BlankPageCustomizer c = new BlankPageCustomizer(this);
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

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getBootstrapCSS() {
        return bootstrapCSS;
    }

    public void setBootstrapCSS(String bootstrapCSS) {
        this.bootstrapCSS = bootstrapCSS;
    }

    public String getjQueryJS() {
        return jQueryJS;
    }

    public void setjQueryJS(String jQueryJS) {
        this.jQueryJS = jQueryJS;
    }

    public String getBootstrapJS() {
        return bootstrapJS;
    }

    public void setBootstrapJS(String bootstrapJS) {
        this.bootstrapJS = bootstrapJS;
    }

    public String getBootstrapThemeCSS() {
        return bootstrapThemeCSS;
    }

    public void setBootstrapThemeCSS(String bootstrapThemeCSS) {
        this.bootstrapThemeCSS = bootstrapThemeCSS;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public boolean isAddIEsupport() {
        return addIEsupport;
    }

    public void setAddIEsupport(boolean addIEsupport) {
        this.addIEsupport = addIEsupport;
    }

}
