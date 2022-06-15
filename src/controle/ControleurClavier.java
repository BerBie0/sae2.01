/* ========================================================== */
/*                  Bibliotheque MoteurDeJeu                  */
/* --------------------------------------------               */
/* Bibliotheque pour aider la création de jeu video comme :   */
/* - Jeux de role                                             */
/* - Jeux de plateforme                                       */
/* - Jeux de combat                                           */
/* - Jeux de course                                           */
/* - Ancien jeu d'arcade (Pac-Man, Space Invider, Snake, ...) */
/* ========================================================== */

package controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//permet de faire un controleur de clavier

/**
 *
 * @author Pierre-Frederic Villard
 */
public class ControleurClavier implements KeyListener{

	public static int left = KeyEvent.VK_LEFT;
	public static int right = KeyEvent.VK_RIGHT;
	public static int up = KeyEvent.VK_UP;
	public static int down = KeyEvent.VK_DOWN;
	public static int space = KeyEvent.VK_SPACE;
	public static int pause = KeyEvent.VK_P;
	public static int quit = KeyEvent.VK_Q;

    /**
     * fin du jeu
     */
	public static boolean fin=false;
	
	//afficheur
	boolean affiche=false;
	AfficheControle afficheur;

    /**
     * la variable de controle
     */
	public Controle c;
	
    /**
     * constructeur avec affichage du controleur ou non.
     * @param affiche
     */
	public ControleurClavier(boolean affiche)
	{
		c=new Controle();
		this.affiche=affiche;
		if (affiche) 
			afficheur=new AfficheControle(c);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//vide		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//touche gauche
		if (e.getKeyCode()==left)
		{
			c.gauche=true;			
		}
		//touche droite
		if (e.getKeyCode()==right)
		{
			c.droite=true;
		}
		//touche up
		if (e.getKeyCode()==up)
		{
			c.haut=true;
		}
		//touche down
		if (e.getKeyCode()==down)
		{
			c.bas=true;
		}		
		//touche q
		if (e.getKeyCode()==quit)
		{
			fin=true;
		}
		//touche space
		if (e.getKeyCode()==space)
		{
			c.attack=true;
		}
		//touche p = pause
		if (e.getKeyCode()==pause)
		{
			c.pause=!c.pause;
		}

		if (affiche) afficheur.dessin();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//touche gauche
		if (e.getKeyCode()==left)
		{
			c.gauche=false;
		}
		//touche droite
		if (e.getKeyCode()==right)
		{
			c.droite=false;
		}
		//touche up
		if (e.getKeyCode()==up)
		{
			c.haut=false;
		}
                //touche down
		if (e.getKeyCode()==down)
		{
			c.bas=false;
		}
		//release space
		if (e.getKeyCode()==space)
		{
			c.attack = false;
		}
		if (affiche) afficheur.dessin();
		
	}
	
	

}
