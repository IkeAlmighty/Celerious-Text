package viewer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class TextArea extends JTextArea{

    ArrayList<String> suggestions;
    int suggestionIndex;

    public TextArea(){
        super();
        suggestionIndex = 0;
        suggestions = new ArrayList<String>();
    }

    /**
     * trims off all the characters after the current caret position
     * until a space, tab, newline or end of text is reached.
     */
    private void trimToEndOfWord(){

    }

    /**
     * Trims off all the characters before the current caret position
     * until a space, tab, newline, or beginning of text is reached.
     */
    private void trimToBeginningOfWord(){
        
    }

    /**
     * Sets the wordSuggestion list to the given list, then updates
     * the graphical display of it.
     */
    public void setWordSuggestions(ArrayList<String> suggestions){
        this.suggestions = suggestions;

        int caretPos = super.getCaretPosition();

        trimToEndOfWord();

        try{
            super.getDocument().insertString(caretPos + 1, suggestions.get(0), null);
        } catch(BadLocationException exc){
            exc.printStackTrace();
        }
    }

    /**
     * Confirms a suggestion that is currently showing
     */
    public void confirmSuggestion(){

    }

    /**
     * Shows the next suggestion, and goes back to the beginning if there are no
     * more left
     */
    public void nextSuggestion(){

    }
}