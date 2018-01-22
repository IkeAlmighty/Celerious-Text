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


    


    // @Deprecated
    // public void getAndSetAutoSuggestion(String str){
    //     ArrayList<String> matches = dictionary.lookup(str);//gets top x matches TODO define x
    //     System.out.println(matches);
    //     StringBuilder sb = new StringBuilder();
    //     for(int i = 0; i < 5; i++){
    //         sb.append(matches.get(i));
    //         if(i != matches.size() - 1){
    //             sb.append(" ");
    //         }
    //     }

    //     viewer.setAutoCompleteSuggestions(sb.toString());
    // }

    // @Deprecated
    // public void selectSuggestion(int suggestionNum){
    //     viewer.appendSuggestion(suggestionNum);//viewer will parse out the suggestions from its suggestion label.
    // }

    // public void lookupAndSetSuggestions(String searchTerm){
    //     ArrayList<String> matches = dictionary.lookup(searchTerm);
    //     viewer.setSuggestions(matches);//sets the suggestion list, and also puts the first suggestion in.
    // }

    // public void displayNextSuggestion(){
    //     viewer.displayNextSuggestion();
    // }

    // public void confirmCurrentWord(){
    //     viewer.confirmCurrentWord();
    // }
}