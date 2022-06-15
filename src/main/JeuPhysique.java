package main;

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


import afficheur.Afficheur;
import physique.MoteurPhysique;
import java.io.IOException;

public class JeuPhysique{
	
	//le moteur physique
	public MoteurPhysique moteurPhys;
	
	//le rendu
	public Afficheur affiche;

        public final static short MONSTRE=1;
        public final static short HERO=2;
        public final static short DECORS=3;
        public final static short POTION=4;
	
	int i=0;
	
	//separation vue affichage
	public JeuPhysique() throws IOException
	{
		//on creer le moteur physique
		//moteurPhys=new MoteurPhysique();
		//on creer l'afficheur du monde
		//affiche=new Afficheur(moteurPhys.monde);

	}
	
	
	public void update()
	{
		if(!affiche.m.c.pause && !moteurPhys.isGameOver && !moteurPhys.playerWin){
			moteurPhys.update();			
		}
	}
	
	public void render()
	{
		if(!affiche.m.c.pause && !moteurPhys.playerWin){
			affiche.render();
		}
		if (moteurPhys.isGameOver) {
			affiche.drawGameOver();
		}
		if (affiche.m.c.pause) {
			affiche.drawButtons();
		}
		if (moteurPhys.playerWin) {
			affiche.drawWin();
		}	
	}
}