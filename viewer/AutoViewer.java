package viewer;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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

public class AutoViewer extends JFrame{

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
    private TextArea editArea;
    private JLabel autoCompleteLabel;

    public AutoViewer(int width, int height){
        super("Celerious Text");

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
        editArea = new TextArea();
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

    public void setWordSuggestions(ArrayList<String> suggestions){
        editArea.setWordSuggestions(suggestions);
    }

    @Override
    public void addKeyListener(KeyListener l){
        editArea.addKeyListener(l);
    }
}