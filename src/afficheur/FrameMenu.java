package afficheur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.border.*;

import java.awt.GridBagLayout;

public class FrameMenu extends JFrame implements ActionListener{

    public JPanel pannel, pannelB, pAll;
    public JLabel label;
    public JButton jouer, option, quitter, credits;
    GridBagConstraints c = new GridBagConstraints();
    public boolean play = false;
    
    

    public FrameMenu(String titre){
        super(titre);

        pAll = new JPanel(new BorderLayout());

        pannel = new JPanel();
        pannel.setBorder(new EmptyBorder(100,10,10,10));
        pannel.setBackground(Color.GREEN);//Couleur temporaire en attendant l'image

        pannelB = new JPanel(new GridBagLayout());
        pannelB.setBorder( new EmptyBorder(10, 10,20 ,10) );
        pannelB.setBackground(Color.GREEN);//Couleur temporaire en attendant l'image
        
        label = new JLabel("Pirate Bay");
        label.setFont(new Font("", Font.BOLD, 20));
        label.setForeground(Color.DARK_GRAY);
        pannel.add(label);


        /**
         * Bouton pour jouer au jeu
         */
        jouer = new JButton("Jouer");
        jouer.setFont(new Font("", Font.BOLD, 20));
        jouer.setForeground(Color.DARK_GRAY);
        jouer.setBackground(Color.LIGHT_GRAY);
        jouer.addActionListener(this);
        c.fill=GridBagConstraints.BOTH;
        c.gridwidth=2;
        //c.weightx=3;
        c.gridx=1;
        c.gridy=1;
        c.insets = new Insets(2,2,2,2);
        pannelB.add(jouer,c);
        
        
        /**
         * Bouton des options du jeu
         */
        option = new JButton("Options");
        option.setFont(new Font("", Font.BOLD, 16));
        //option.setBorder(new EmptyBorder(10,10,10,10));
        option.setForeground(Color.DARK_GRAY);
        option.setBackground(Color.LIGHT_GRAY);
        option.addActionListener(this);
        //c.fill=GridBagConstraints.CENTER;
        //c.gridwidth=2;
		//c.weightx=2;
		c.gridx=0;
		c.gridy=2;
        pannelB.add(option,c);
        
        /**
         * Bouton des crédits du jeu
         */
        credits= new JButton("Credits");
        credits.setFont(new Font("", Font.BOLD, 16));
        //credits.setBorder(new EmptyBorder(10,10,10,10));
        credits.setForeground(Color.DARK_GRAY);
        credits.setBackground(Color.LIGHT_GRAY);
        credits.addActionListener(this);
        //c.fill=GridBagConstraints.NONE;
        //c.gridwidth=2;
		//c.weightx=2;
		c.gridx=2;
		c.gridy=2;
        pannelB.add(credits,c);

        /**
         * Bouton "quitter" du jeu
         */
        quitter = new JButton("Quitter");
        quitter.setFont(new Font("", Font.BOLD, 20));
        quitter.setForeground(Color.DARK_GRAY);
        quitter.setBackground(Color.LIGHT_GRAY);
        quitter.addActionListener(this);
        //c.fill=GridBagConstraints.CENTER;
        //c.gridwidth=3;
		//c.weightx=3;
		c.gridx=1;
		c.gridy=3;
        pannelB.add(quitter,c);
        
        
        //Ajout des pannels à la frame
        pAll.add(pannel, BorderLayout.NORTH);
        pAll.add(pannelB);

        this.add(pAll);

    }


    /**
     * Actions des différents boutons disponibles 
     */
    public void actionPerformed(ActionEvent e){
        
        Object s=e.getSource();
        if(s.equals(jouer)){
            play = true;
        }
        if(s.equals(option)){
            //pAll.removeAll();
            MenuOption o = new MenuOption("Options");
            Menu test = new Menu();
            test.main2(o);
            // pAll.add(o);
            // pAll.validate();
            // pAll.repaint();
        }
        // if(s.equals(credits));
        if(s.equals(quitter))System.exit(0);;
    }
}