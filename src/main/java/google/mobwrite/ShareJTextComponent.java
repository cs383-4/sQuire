package google.mobwrite;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import google.mobwrite.diff_match_patch.Diff;
import google.mobwrite.diff_match_patch.Operation;
import google.mobwrite.diff_match_patch.Patch;
import javafx.application.Platform;
import org.fxmisc.richtext.CodeArea;

public class ShareJTextComponent extends ShareObj {
    /**
     * The user-facing text component to be shared.
     */
    private CodeArea codeArea;

    /**
     * Constructor of shared object representing a text field.
     *
     * @param ca   Text component to share.
     * @param file Filename to share as.
     */
    public ShareJTextComponent(CodeArea ca, String file) {
        super(file);
        this.codeArea = ca;
    }

    /**
     * Retrieve the user's text.
     *
     * @return Plaintext content.
     */
    public String getClientText() {
        String text = this.codeArea.getText();
        // Numeric data should use overwrite mode.
        this.mergeChanges = true;
        return text;
    }

    /**
     * Set the user's text.
     *
     * @param text New text
     */
    public void setClientText(String text) {
        System.out.println("here");
        Platform.runLater(() -> {
            this.codeArea.replaceText(text);
        });
        // TODO: Fire synthetic change events.
    }

    /**
     * Modify the user's plaintext by applying a series of patches against it.
     *
     * @param patches Array of Patch objects.
     */
    public void patchClientText(LinkedList<Patch> patches) {
        Platform.runLater(() -> {
            if (!this.codeArea.isVisible()) {
                // If the field is not visible, there's no need to preserve the cursor.
                super.patchClientText(patches);
                return;
            }
            Vector<Integer> offsets = new Vector<Integer>();
            offsets.add(this.codeArea.getCaretPosition());
            System.out.println(this.codeArea.getCaretPosition());
            //this.mobwrite.logger.log(Level.INFO, "Cursor get: " + offsets.firstElement());
            offsets.add(this.codeArea.getSelection().getStart());
            offsets.add(this.codeArea.getSelection().getEnd());
            this.patch_apply_(patches, offsets);
            this.codeArea.positionCaret(offsets.get(0));
        });
        //this.mobwrite.logger.log(Level.INFO, "Cursor set: " + offsets.firstElement());
        //getting the selection to work will take a little bit of trial and error
        //this.codeArea.setSelectionStart(offsets.get(1));
        //this.codeArea.setSelectionEnd(offsets.get(2));
    }

    /**
     * Merge a set of patches onto the text.
     *
     * @param patches Array of patch objects.
     * @param offsets Offset indices to adjust.
     */
    protected void patch_apply_(LinkedList<Patch> patches, List<Integer> offsets) {
        if (patches.isEmpty()) {
            return;
        }
        int Match_MaxBits = 32;

        // Deep copy the patches so that no changes are made to originals.
        patches = dmp.patch_deepCopy(patches);

        // Lock the user out of the document for a split second while patching.
        this.codeArea.setEditable(false);
        try {
            String text = this.getClientText();

            String nullPadding = dmp.patch_addPadding(patches);
            text = nullPadding + text + nullPadding;
            dmp.patch_splitMax(patches);

            int x = 0;
            // delta keeps track of the offset between the expected and actual location
            // of the previous patch.  If there are patches expected at positions 10 and
            // 20, but the first patch was found at 12, delta is 2 and the second patch
            // has an effective expected position of 22.
            int delta = 0;
            for (Patch aPatch : patches) {
                int expected_loc = aPatch.start2 + delta;
                String text1 = dmp.diff_text1(aPatch.diffs);
                int start_loc;
                int end_loc = -1;
                if (text1.length() > Match_MaxBits) {
                    // patch_splitMax will only provide an oversized pattern in the case of
                    // a monster delete.
                    start_loc = dmp.match_main(text,
                            text1.substring(0, Match_MaxBits), expected_loc);
                    if (start_loc != -1) {
                        end_loc = dmp.match_main(text,
                                text1.substring(text1.length() - Match_MaxBits),
                                expected_loc + text1.length() - Match_MaxBits);
                        if (end_loc == -1 || start_loc >= end_loc) {
                            // Can't find valid trailing context.  Drop this patch.
                            start_loc = -1;
                        }
                    }
                } else {
                    start_loc = dmp.match_main(text, text1, expected_loc);
                }
                if (start_loc == -1) {
                    // No match found.  :(
                    // Subtract the delta for this failed patch from subsequent patches.
                    delta -= aPatch.length2 - aPatch.length1;
                } else {
                    // Found a match.  :)
                    delta = start_loc - expected_loc;
                    String text2;
                    if (end_loc == -1) {
                        text2 = text.substring(start_loc,
                                Math.min(start_loc + text1.length(), text.length()));
                    } else {
                        text2 = text.substring(start_loc,
                                Math.min(end_loc + Match_MaxBits, text.length()));
                    }
                    // Run a diff to get a framework of equivalent indices.
                    LinkedList<Diff> diffs = dmp.diff_main(text1, text2, false);
                    if (text1.length() > Match_MaxBits
                            && dmp.diff_levenshtein(diffs) / (float) text1.length()
                            > dmp.Patch_DeleteThreshold) {
                        // The end points match, but the content is unacceptably bad.
                    } else {
                        int index1 = 0;
                        for (Diff aDiff : aPatch.diffs) {
                            if (aDiff.operation != Operation.EQUAL) {
                                int index2 = dmp.diff_xIndex(diffs, index1);
                                if (aDiff.operation == Operation.INSERT) {
                                    // Insertion
                                    text = text.substring(0, start_loc + index2) + aDiff.text
                                            + text.substring(start_loc + index2);
                                    this.codeArea.insertText(start_loc + index2 - nullPadding.length(), aDiff.text);
                                    for (int i = 0; i < offsets.size(); i++) {
                                        if (offsets.get(i) + nullPadding.length()
                                                > start_loc + index2) {
                                            offsets.set(i, offsets.get(i) + aDiff.text.length());
                                        }
                                    }
                                } else if (aDiff.operation == Operation.DELETE) {
                                    // Deletion
                                    int del_start = start_loc + index2;
                                    int del_end = start_loc + dmp.diff_xIndex(diffs,
                                            index1 + aDiff.text.length());
                                    text = text.substring(0, del_start) + text.substring(del_end);
                                    this.codeArea.deleteText(del_start - nullPadding.length(), del_end);

                                    for (int i = 0; i < offsets.size(); i++) {
                                        if (offsets.get(i) + nullPadding.length() > del_start) {
                                            if (offsets.get(i) + nullPadding.length() < del_end) {
                                                offsets.set(i, del_start - nullPadding.length());
                                            } else {
                                                offsets.set(i, offsets.get(i) - (del_end - del_start));
                                            }
                                        }
                                    }
                                }
                            }
                            if (aDiff.operation != Operation.DELETE) {
                                index1 += aDiff.text.length();
                            }
                        }
                    }
                }
                x++;
            }
            // Strip the padding off.
            text = text.substring(nullPadding.length(), text.length()
                    - nullPadding.length());
        } finally {
            this.codeArea.setEditable(true);
        }
    }
    // TODO: Fire synthetic change events.
}
