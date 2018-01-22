package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Model;

public class Controller implements KeyListener{

    private Model model;
    private StringBuilder word;

    private int spaceCount;

    private long spaceTStamp;

    public Controller(){
        word = new StringBuilder();
        spaceCount = 0;
        spaceTStamp = System.currentTimeMillis();
    }

    public void setModel(Model model){
        this.model = model;
    }

    @Override
    public void keyPressed(KeyEvent e){
        //if the key pressed is displayable:
        if(e.getKeyChar() > ' ' && e.getKeyChar() <= '~'){
            word.append(e.getKeyChar());
            model.lookupAndSetSuggestions(word.toString());
        }
        else if(e.getKeyChar() == ' '){
            word.setLength(0);
            model.lookupAndSetSuggestions(word.toString());
        }
        else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            // if(word.length() > 1){//edge case for when there are no suggestions
            //     word.delete(word.length() - 1, word.length());
            // } else { 
            //     word.setLength(0);
            // }

            // model.lookupAndSetSuggestions(word.toString());
        }
    }

    // @Override
    // public void keyPressed(KeyEvent e){
    //     if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
    //         if(currWord.length() > 1){
    //             currWord.delete(currWord.length() - 1, currWord.length());
    //         } else {
    //             currWord.setLength(0);
    //         }
    //     }
    //     else if(e.getKeyChar() > ' ' && e.getKeyChar() <= '~'){
    //         currWord.append((char)e.getKeyChar());
    //         spaceCount = 0;
    //     }
        
    //     else if (e.getKeyChar() == ' ') {
    //         spaceCount++;
    //         if (spaceCount > 1) {
    //             model.selectSuggestion(spaceCount - 2);//-2 so we can 0 index it
    //         }
    //         currWord.setLength(0);

    //         return;//we don't want to reset the word on a space.
    //     }

    //     else if(e.getKeyChar() == '\n' || e.getKeyChar() == '\t'){
    //         currWord.setLength(0);
    //         spaceCount = 0;
    //     }

        

    //     model.getAndSetAutoSuggestion(currWord.toString());
    //     model.selectSuggestion(spaceCount - 2);//-2 to 0 index it
    // }

    @Override
    public void keyReleased(KeyEvent e){

    }

    @Override
    public void keyTyped(KeyEvent e){

    }
}