/**
 * palette4bootstrap - A netbeans palette plugin for the Bootstrap
 * Copyright (c) 2017-2018 Tauquir Ahmed (tauquirahmed93@gmail.com)
 * Licensed under the MIT License.
 */
package org.tauquir.palette4bootstrap;

import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.openide.text.NbDocument;

public class bsPaletteUtilities {

    public static void insert(final String s, final JTextComponent target) throws BadLocationException {
        final StyledDocument doc = (StyledDocument) target.getDocument();
        class AtomicChange implements Runnable {

            @Override
            public void run() {
                Document value = target.getDocument();
                if (value == null) {
                    return;
                }
                try {
                    insert(s, target, doc);
                } catch (BadLocationException e) {
                }
            }
        }
        try {
            NbDocument.runAtomicAsUser(doc, new AtomicChange());
        } catch (BadLocationException ex) {
        }
    }

    private static int insert(String s, JTextComponent target, Document doc) throws BadLocationException {
        int start = -1;
        try {
            //firstly, find selected text range:
            Caret caret = target.getCaret();
            int p0 = Math.min(caret.getDot(), caret.getMark());
            int p1 = Math.max(caret.getDot(), caret.getMark());
            doc.remove(p0, p1 - p0);
            //then, replace selected text range with the inserted one:
            start = caret.getDot();
            doc.insertString(start, s, null);
        } catch (BadLocationException ble) {
        }
        return start;
    }

    /**
     * This function is used to get the location of the file where the palette
     * item was dragged and dropped
     *
     * @param targetComponent The JTextComponent of the editor where the palette
     * item was dropped
     * @return Absolute path of the file opened in the editor represented by the
     * JTextComponent
     */
    public static String getDocumentPath(JTextComponent targetComponent) {
        return org.netbeans.modules.editor.NbEditorUtilities.getFileObject(targetComponent.getDocument()).getPath();
    }

    /**
     * Finds relative path from a given absolute path against the relativeTo
     * path
     *
     * @param absolutePath The absolute path whose relative path is required to
     * be calculated
     * @param relativeTo The base path against which the relative path will be
     * be calculated
     * @return absolutePath if there are no common roots between absolutePath
     * and relativeTo paths. Otherwise returns the calculated relative path. Note:
     * The path returned by this function will always contain forward slash
     */
    public static String findRelativePath(String absolutePath, String baseDir) {
        // First convert backslashes to forward slashes
        String absolutePathWithForwardSlash = absolutePath.replace("\\", "/");
        String baseDirWithForwardSlash = baseDir.replace("\\", "/");
        String[] absoluteDirectories = absolutePathWithForwardSlash.split("/");  // 4 backslashes in regex equals 1 backslash
        String[] baseDirectories = baseDirWithForwardSlash.split("/");

        //Get the shortest of the two paths
        int length = absoluteDirectories.length < baseDirectories.length ? absoluteDirectories.length : baseDirectories.length;

        //Use to determine where in the loop we exited
        int lastCommonRoot = -1;
        int index;

        //Find common root
        for (index = 0; index < length; index++) {
            if (absoluteDirectories[index].equals(baseDirectories[index])) {
                lastCommonRoot = index;
            } else {
                break;
            }
        }
        //If we didn't find a common root then return absolutePath
        if (lastCommonRoot == -1) {
            return "file:///" + absolutePathWithForwardSlash;
        }

        //Build up the relative path
        StringBuilder relativePath = new StringBuilder();

        //append ../ for every directory in baseDirectories after the commonRoot
        for (int i = lastCommonRoot + 1; i < baseDirectories.length; i++) {
            relativePath.append("../");
        }
        for (int i = lastCommonRoot + 1; i < absoluteDirectories.length; i++) { // Start loop after lastCommonRoot+1
            relativePath.append(absoluteDirectories[i]);
            if (i < absoluteDirectories.length - 1) {   // If not the last iteration, append backslash separator
                relativePath.append("/");
            }
        }
        return relativePath.toString();
    }
}
