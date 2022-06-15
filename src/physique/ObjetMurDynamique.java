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


package physique;

/**
 * un objet de type mur
 * @author Pierre-Frederic Villard
 */
public class ObjetMurDynamique extends ObjetMur {

	int i=0;
	
    /**
     * contructeur d'un objet mur dynamique
     */
    public ObjetMurDynamique()
	{
		//taille de mur diff�rente
		height=50;
		width=50;
		px=100;
		py=20;
	}
	
    /**
     * contructeur d'un objet mur dynamique
     * @param x position x
     * @param y position y
     * @param w largeur (width)
     * @param h hauteur (height)
     */
    public ObjetMurDynamique(int x,int y, int w,int h)
	{
		//taille de mur diff�rente
		height=h;
		width=w;
		px=x;
		py=y;
	}
	
    /**
     * surcharge la mise a jour
	 * Mur mobile
     */
	public void update()
	{
		sauveAnterieur();
		i++;
		if ((i/200)%2==0)
		{
			py=py+0.5;
		}
		else py=py-0.5;	
	}
	
	
}
