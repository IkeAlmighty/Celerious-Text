package viewer;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

@Deprecated
public class Viewer extends JFrame{

    // private JFrame mainFrame;
    private JPanel contentPane;
    private JScrollPane editAreaScrollPane;
    private JPanel autoCompletePane;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem newButton;
    private JMenuItem saveButton;
    private JMenuItem openButton;
    private JMenuItem saveAsButton;
    private JPanel eastPane;//just for padding
    private JPanel westPane;//just for padding
    private JPanel southPane;
    private JTextArea editArea;
    private JLabel autoCompleteLabel;

    private ArrayList<String> suggestions;
    private int suggestionIndex;

    public Viewer(int width, int height){
        super("Celerious Text");

        suggestions = new ArrayList<String>();
        suggestionIndex = 0;

        // mainFrame = new JFrame("Celerious Text");
        autoCompletePane = new JPanel();
        contentPane = new JPanel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu("FILE");
        newButton = new JMenuItem("new..");
        saveButton = new JMenuItem("save");
        openButton = new JMenuItem("open..");
        saveAsButton = new JMenuItem("save as..");
        eastPane = new JPanel();
        westPane = new JPanel();
        southPane = new JPanel();
        editArea = new JTextArea();
        editAreaScrollPane = new JScrollPane(editArea);
        autoCompleteLabel = new JLabel("\nsuggestions go here\n");

        //contentPane settings:
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Color.BLACK);

        //autoCompleteLabel settings:
        autoCompleteLabel.setMinimumSize(new Dimension(0, 200));
        autoCompletePane.setMaximumSize(new Dimension(1000, 500));
        autoCompletePane.setSize(new Dimension(100, 500));
        
        //editArea settings:
        editArea.setLineWrap(true);
        editArea.setWrapStyleWord(true);
        editArea.setAutoscrolls(true);
        editArea.setBackground(Color.BLACK);
        editArea.setForeground(Color.GRAY);
        editArea.setMargin(new Insets(25, 10, 0, 0));

        //textPane settings:
        editAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        editAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //borderPane settings:
        eastPane.setBackground(Color.DARK_GRAY);
        westPane.setBackground(Color.DARK_GRAY);
        southPane.setMinimumSize(new Dimension(100, 100));

        //frame settings:
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(0,0,width,height);
        super.setBackground(Color.BLACK);

        //component addition:
        super.add(contentPane, BorderLayout.CENTER);
        super.add(menuBar, BorderLayout.NORTH);
        super.add(eastPane, BorderLayout.EAST);
        super.add(westPane, BorderLayout.WEST);

        contentPane.add(editAreaScrollPane);
        contentPane.add(autoCompletePane);

        autoCompletePane.add(autoCompleteLabel);

        menuBar.add(fileMenu);

        fileMenu.add(newButton);
        fileMenu.add(saveButton);
        fileMenu.add(saveAsButton);
        fileMenu.add(openButton);

        //set frame visible:
        super.setVisible(true);
    }

    @Deprecated
    public void setAutoCompleteSuggestions(String suggestions){
        autoCompleteLabel.setText(suggestions);
    }

    @Deprecated
    public void appendSuggestion(int suggestionNum){
        System.out.println("suggestion num: " + suggestionNum);
        String suggestions = autoCompleteLabel.getText();        
        System.out.println("suggestions: " + suggestions);
       
        int start = 0;
        for(int sCount = 0; sCount < suggestionNum; sCount++){
            System.out.println(sCount + " " + suggestionNum);
            for(;start < suggestions.length() && suggestions.charAt(start) != 32; start++){
                System.out.println("start " + start);
            }
            start++;
        }
        if(start >= suggestions.length()){return;}//return if there are no more words left in the suggestions.
        if(suggestions.charAt(start) == 32){start++;}//skip over spaces for start.

        int end = start;
        if(end <= suggestions.length()){
            for(;end < suggestions.length() && suggestions.charAt(end) != 32; end++);
        }
        else {
            start = 0;//there is only one word
            end = suggestions.length();
        }

        System.out.println("parsed=" + suggestions.substring(start, end));

        removeLastWord();
        editArea.append(suggestions.substring(start, end) + " ");
    }

    public void setSuggestions(ArrayList<String> suggestions){
        this.suggestions = suggestions;
        System.out.println(suggestions);
        
        if(suggestions.size() == 0){
            return;
        }
        //first, place the first suggestion after the cursor:
        int caretPos = editArea.getCaretPosition();
        //removeLastWord(); 
        trimToNextWord();

        try{
            editArea.getDocument().insertString(caretPos, suggestions.get(0), null);
        } catch(BadLocationException exc){exc.printStackTrace();}
        editArea.setCaretPosition(caretPos);
    }

    public void confirmCurrentWord(){

        if(suggestions.size() == 0){
            return;
        }

        int caretPos = editArea.getCaretPosition();
        //get rid of space just created:
        try{
            editArea.getDocument().remove(caretPos - 1, caretPos);
        } catch(BadLocationException exc){
            exc.printStackTrace();
        }

        //forward cursor end of word:
        String text = editArea.getText();
        for(; caretPos < text.length() && text.charAt(caretPos) != ' '; caretPos++);
        editArea.setCaretPosition(caretPos);

        //re-add the space:
        editArea.append(" ");
    }

    public void displayNextSuggestion(){
        //loop back around to the beginning of the last suggestion
        //has been passed:
        if(suggestionIndex + 1 >= suggestions.size()){
            suggestionIndex = -1;
        }

        removeLastWord();
        suggestionIndex++;
        editArea.append(suggestions.get(suggestionIndex));
    }

    private void removeLastWord(){
        int end = editArea.getText().length() - 1;
        for(; end > 0 && editArea.getText().charAt(end) == ' '; end--);
        for(; end > 0 && editArea.getText().charAt(end) != ' ' && editArea.getText().charAt(end) != 10; end--);
        
        if(end == 0){end--;}
        System.out.println("end: " + end);
        try{
            editArea.getDocument().remove(end + 1, editArea.getText().length() - (end + 1));
        } catch(BadLocationException exc){
            exc.printStackTrace();
        }
    }

    private void trimToNextWord(){
        int caretPos = editArea.getCaretPosition();
        String text = editArea.getText();
        
        //return if there is nothing to trim:
        if(caretPos + 1 >= text.length()){
            return;
        }

        //else, trim:
        int endOfTrim = caretPos + 1;
        for(; text.charAt(endOfTrim) != ' '; endOfTrim++);

        try{
            editArea.getDocument().remove(caretPos, endOfTrim);
        } catch(BadLocationException exc){exc.printStackTrace();}
    }

    @Override
    public void addKeyListener(KeyListener l){
        editArea.addKeyListener(l);
    }
}