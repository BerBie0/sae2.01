package afficheur;


import javax.swing.*;
import java.awt.*;
// import java.awt.Image;
// import java.awt.Toolkit;
// import java.io.IOException;


public class Menu {

    public void main2(JFrame f) { 
        EventQueue.invokeLater(new Runnable() { 
            public void run() { 
                //FrameMenu f = new FrameMenu ("Menu");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.pack();
                f.setVisible(true);
                f.setSize(Afficheur.GAME_WIDTH,Afficheur.GAME_HEIGHT);
                f.setLocationRelativeTo(null);
            }
        }); 
    }
}