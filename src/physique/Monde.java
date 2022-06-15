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

import java.io.IOException;
import java.util.ArrayList;

import afficheur.Repere;
import controle.Controle;

//gere les objets du monde

/**
 *
 * @author Pierre-Frederic Villard
 */
public class Monde {
    /** le controleur */
	public Controle c;
    /** la balle pour une vue subjective */
        public ObjetHeros balle;
    /** les murs */
	public ArrayList<Objet> objets=new ArrayList<Objet>();
	
        public int nbMurs=0;

    /** les monstres */
	public ArrayList<ObjetMonstre> monstres=new ArrayList<ObjetMonstre>();
	
        public int nbHeros=0;

    /** les heros */
	public ArrayList<ObjetHeros> heros=new ArrayList<ObjetHeros>();

        public int nbMonstres=0;        

    /**
     * un monde par defaut
     * @throws IOException
     */
	public Monde() throws IOException
	{
		//getsion du controleur
		balle=new ObjetHeros();
		
		//gere la vision subjective
		Repere.h=balle;
	}
	
	/** Ajouter un mur
	 * @param x position x du mur
     * @param y position y du mur
     * @param dx largeur (width) du mur
     * @param dy hauteur (height) du mur
     */
	public void addMur(int x, int y, int dx, int dy){
		objets.add(new ObjetMur(x,y,dx,dy));
        objets.get(nbMurs).index=nbMurs;
        nbMurs++;            
    }
        
	/** ajouter monstre
	 * @param vx vitesse x du monstre
     * @param vy vitesse y du monstre
     * @param px position x du monstre
     * @param py position y du monstre
     * @throws java.io.IOException */
        public void addMonstre(double vx, double vy, int px, int py) throws IOException{
            monstres.add(new ObjetMonstre());
            //penser a le lier au monde
            monstres.get(nbMonstres).m=this;
            // propriétés du monstre
            monstres.get(nbMonstres).vx=vx;
            monstres.get(nbMonstres).vy=vy;
            monstres.get(nbMonstres).px=px;
            monstres.get(nbMonstres).py=py;
            monstres.get(nbMonstres).index=nbMonstres;
            
            nbMonstres++;
        }
        
        public void addObjet(Objet monObjet){ 
            objets.add(monObjet);
        }
             
        /** ajouter heros
     * @param vx vitesse x de la balle
     * @param vy vitesse y de la balle
     * @param px position x de la balle
     * @param py position y de la balle
     * @throws java.io.IOException */
        public void addHero(double vx, double vy, int px, int py) throws IOException{
            heros.add(new ObjetHeros(px, py));
            // propriétés du monstre
            heros.get(nbHeros).vx=vx;
            heros.get(nbHeros).vy=vy;
            heros.get(nbHeros).px=px;
            heros.get(nbHeros).py=py;
            heros.get(nbHeros).index=nbHeros;
            
            nbHeros++;
        }
}

