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

package afficheur;

import physique.Objet;
import physique.ObjetHeros;

/**
 *
 * @author Pierre-Frederic Villard
 */
public class Repere {
    /** controle si vue subjective ou non */
    public static boolean isSubjective=true;
    public static final int VIEW_OFFSET = (int)(100*Afficheur.SCALE);
    /**
     * besoin d'un lien vers le heros 
     * vue subjective
     */
	public static ObjetHeros h;
	 
    /**
     * permet de changer le repere pour l'affichage
     * retire 150
     * @param o Objet pour avoir ses coordonees
     * @return les quatre points de l'objet x, y, w, h 
     */
	public static int[] changeRepere(Objet o){
        int res[] = new int[4];
        res[1] = Afficheur.GAME_HEIGHT - (int) (59 * Afficheur.SCALE) - (int) o.py - (int) (o.height);
        res[2] = (int) o.width;
        res[3] = (int) o.height;
        if (isSubjective) {
            res[0] = VIEW_OFFSET + (int) o.px - (int) h.px;
        } else {
            res[0] = (int) o.px;
        }
        return (res);
	}
	
}
