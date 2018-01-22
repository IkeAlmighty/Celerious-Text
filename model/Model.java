package model;

import java.util.ArrayList;

import controller.Controller;

import viewer.AutoViewer;

public class Model{

    private Controller controller;
    private Dictionary dictionary;
    private AutoViewer viewer;

    private ArrayList<String> suggestions;

    public Model(AutoViewer viewer, Controller controller, Dictionary dictionary){
        this.viewer = viewer;
        this.controller = controller;
        this.dictionary = dictionary;
    }

    public void findAndSetWordSuggestions(String str){
        ArrayList<String> suggestions = dictionary.lookup(str);
        viewer.setWordSuggestions(suggestions);
    }
}