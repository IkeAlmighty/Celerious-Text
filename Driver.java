import viewer.AutoViewer;

import model.Model;
import model.Dictionary;

import controller.Controller;

public class Driver{
    public static void main(String[] args){
        AutoViewer viewer = new AutoViewer(600, 600);
        Controller controller = new Controller();
        Dictionary dictionary = new Dictionary("lookuptable.txt");

       Model model = new Model(viewer, controller, dictionary);

       controller.setModel(model);

       viewer.addKeyListener(controller);
    }
}