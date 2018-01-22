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
        if(e.getKeyChar() > ' ' && e.getKeyChar() <= '~'){
            word.append(e.getKeyChar());
            model.findAndSetWordSuggestions(word.toString());
        }
        else if(e.getKeyChar() == ' ' || e.getKeyChar() == '\t' || e.getKeyChar() == '\n'){
            word.setLength(0);
            model.findAndSetWordSuggestions(word.toString());
        }
    }

    @Override
    public void keyReleased(KeyEvent e){

    }

    @Override
    public void keyTyped(KeyEvent e){

    }
}