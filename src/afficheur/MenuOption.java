package afficheur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;

import controle.ControleurClavier;

public class MenuOption extends JFrame implements ActionListener, KeyListener{

    public JPanel pannel, pannelB;
    public JLabel label, leftl, rightl, upl, downl, spacel, pausel, quitl;
    public JButton left, right, up, down, space, pause, quit, back;
    GridBagConstraints c = new GridBagConstraints();
    public boolean play = false;
    public boolean leftb, rightb, upb, downb, spaceb, pauseb, quitb;
    
    

    public MenuOption(String titre){
        super(titre);

        this.setLayout(new BorderLayout());

        pannel = new JPanel();
        pannel.setBorder(new EmptyBorder(100,10,10,10));
        pannel.setBackground(Color.GREEN);//Couleur temporaire en attendant l'image

        pannelB = new JPanel(new GridBagLayout());
        pannelB.setBorder( new EmptyBorder(10, 10,20 ,10) );
        pannelB.setBackground(Color.GREEN);//Couleur temporaire en attendant l'image
        
        pannelB.addKeyListener(this);
        
        label = new JLabel("Option");
        label.setFont(new Font("", Font.BOLD, 20));
        label.setForeground(Color.DARK_GRAY);
        pannel.add(label);


        /**
         * Bouton pour touche gauche
         */
        left = new JButton("Touche Gauche");
        left.setFont(new Font("", Font.BOLD, 20));
        left.setForeground(Color.DARK_GRAY);
        left.setBackground(Color.LIGHT_GRAY);
        left.addActionListener(this);
        c.fill=GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.gridy=1;
        c.insets = new Insets(2,2,2,2);
        pannelB.add(left,c);

        leftl = new JLabel(KeyEvent.getKeyText(ControleurClavier.left));
        c.gridwidth = GridBagConstraints.REMAINDER;
        pannelB.add(leftl,c);
        
        
        /**
         * Bouton pour touche droite
         */
        right = new JButton("Touche Droite");
        right.setFont(new Font("", Font.BOLD, 20));
        //right.setBorder(new EmptyBorder(10,10,10,10));
        right.setForeground(Color.DARK_GRAY);
        right.setBackground(Color.LIGHT_GRAY);
        right.addActionListener(this);
        c.gridwidth = 1;
		c.gridy=2;
        pannelB.add(right,c);

        rightl = new JLabel(KeyEvent.getKeyText(ControleurClavier.right));
        c.gridwidth = GridBagConstraints.REMAINDER;
        pannelB.add(rightl,c);
        
        /**
         * Bouton pour touche haut
         */
        up= new JButton("Touche Haut");
        up.setFont(new Font("", Font.BOLD, 20));
        //up.setBorder(new EmptyBorder(10,10,10,10));
        up.setForeground(Color.DARK_GRAY);
        up.setBackground(Color.LIGHT_GRAY);
        up.addActionListener(this);
		c.gridy=3;
        c.gridwidth = 1;
        pannelB.add(up,c);

        upl = new JLabel(KeyEvent.getKeyText(ControleurClavier.up));
        c.gridwidth = GridBagConstraints.REMAINDER;
        pannelB.add(upl,c);

        /**
         * Bouton pour touche bas
         */
        down = new JButton("Touche Base");
        down.setFont(new Font("", Font.BOLD, 20));
        down.setForeground(Color.DARK_GRAY);
        down.setBackground(Color.LIGHT_GRAY);
        down.addActionListener(this);
		c.gridy=4;
        c.gridwidth = 1;
        pannelB.add(down,c);

        downl = new JLabel(KeyEvent.getKeyText(ControleurClavier.down));
        c.gridwidth = GridBagConstraints.REMAINDER;
        pannelB.add(downl,c);

        /**
         * Bouton pour touche attaque
         */
        space = new JButton("Touche Attaque");
        space.setFont(new Font("", Font.BOLD, 20));
        space.setForeground(Color.DARK_GRAY);
        space.setBackground(Color.LIGHT_GRAY);
        space.addActionListener(this);
		c.gridy=5;
        c.gridwidth = 1;
        pannelB.add(space,c);

        downl = new JLabel(KeyEvent.getKeyText(ControleurClavier.down));
        c.gridwidth = GridBagConstraints.REMAINDER;
        pannelB.add(downl,c);

        /**
         * Bouton pour touche quitter
         */
        quit = new JButton("Touche Quitter");
        quit.setFont(new Font("", Font.BOLD, 20));
        quit.setForeground(Color.DARK_GRAY);
        quit.setBackground(Color.LIGHT_GRAY);
        quit.addActionListener(this);
		c.gridy=6;
        c.gridwidth = 1;
        pannelB.add(quit,c);

        quitl = new JLabel(KeyEvent.getKeyText(ControleurClavier.quit));
        c.gridwidth = GridBagConstraints.REMAINDER;
        pannelB.add(quitl,c);

        /**
         * Bouton pour touche pause
         */
        pause = new JButton("Touche Pause");
        pause.setFont(new Font("", Font.BOLD, 20));
        pause.setForeground(Color.DARK_GRAY);
        pause.setBackground(Color.LIGHT_GRAY);
        pause.addActionListener(this);
		c.gridy=7;
        c.gridwidth = 1;
        pannelB.add(pause,c);

        back = new JButton("Retour");
        back.setFont(new Font("", Font.BOLD, 20));
        back.setForeground(Color.DARK_GRAY);
        back.setBackground(Color.LIGHT_GRAY);
        back.addActionListener(this);

        pausel = new JLabel(KeyEvent.getKeyText(ControleurClavier.pause));
        c.gridwidth = GridBagConstraints.REMAINDER;
        pannelB.add(pausel,c);
        
        
        //Ajout des pannels à la frame
        this.add(pannel, BorderLayout.NORTH);
        this.add(pannelB);
        this.add(back, BorderLayout.SOUTH);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object s = e.getSource();

        if (s.equals(left)){
            leftb = true;
        }
        else if (s.equals(back)){
            this.dispose();
        }

        this.requestFocusInWindow();
        
    }



    @Override
    public void keyTyped(KeyEvent e) {

        System.out.println("b");

        if (leftb){
            ControleurClavier.left = e.getKeyCode();
            System.out.println(ControleurClavier.left);
            //leftb = false;
        }
        
    }



    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("a");

        
    }



    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("r");
        
    }
    
}
