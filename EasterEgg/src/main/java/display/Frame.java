package display;

import object.Element;

import javax.swing.*;

import java.awt.*;

/**
 * Created by Selim on 25/04/2017.
 */
public class Frame extends JFrame {
    public Frame(Element[][] elements){
        this.setTitle("GLPOO Project - Easter Egg");
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
