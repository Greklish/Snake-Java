package snake;

import javax.swing.*;

public class MainClass extends JFrame {
    public static void main(String[] args) {
        //create a new wondow
        MainClass window = new MainClass();

    }

    public MainClass(){
        add(new Field());
        setTitle("Snake");
        //location where to window spawns on the screen
        setLocation(300,300);
        // window gamesize
        setSize(345,370);
        //close program when closed
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //makes window visable
        setVisible(true);
    }


}
