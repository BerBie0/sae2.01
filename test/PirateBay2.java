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

/**
 *
 * @author Pierre-Frederic Villard
 */

import main.BouclePrincipale;
//import miscellaneous.MurCool;
import afficheur.Afficheur;
//import afficheur.Repere;
import controle.ControleurClavier;
import physique.Monde;
import physique.MoteurPhysique;
//import physique.ObjetMur;
//import physique.ObjetMurDynamique;
import physique.ObjetPotion;
//import afficheur.Sprites;
import main.JeuPhysique;
//import miscellaneous.BouclePrincipaleCool;
//import miscellaneous.MonMondeCool;
import afficheur.FrameMenu;
import afficheur.Menu;

public class PirateBay2 {
    
    
    public static void main(String[] args) throws Exception {

        FrameMenu fm = new FrameMenu("Menu");

        Menu f = new Menu();
        f.main2(fm);

        while (!(fm.play)){
                Thread.sleep(1);
        }


        fm.dispose();

        if (fm.play) {
                //le moteur physique
                MoteurPhysique moteurPhys;
                //le rendu
                Afficheur affiche;    
                //le controler
                ControleurClavier cClavier=new ControleurClavier(true);    
                

                
                
                // Construction du monde
                
                // Essai de changement d'apparence pour collisions
                //MonMondeCool monMonde= new MonMondeCool();
                Monde monMonde= new Monde();
                
                //////////////////////
                // Les MURS
                /////////////////////

                monMonde.addMur(0,120,600,20);
                monMonde.addMur(800,120,600,20);
                monMonde.addMur(1075,-10,300,20);

                monMonde.addMur(1475,-10,2,3);
                monMonde.addMur(1575,40,2,3);

                monMonde.addMur(1400,410,250,20);

                monMonde.addMur(1600,135,250,20);
                
                monMonde.addMur(1850,99,20,56);
                monMonde.addMur(1850,99,50,20);

                monMonde.addMur(1900,19,20,100);
                monMonde.addMur(2010,21,450,20);
                monMonde.addMur(2010,41,1,1);
                monMonde.addMur(2460,21,20,500);

                //monMonde.addMur(2010,75,1,1);//pixel troll
        

                monMonde.addMur(600,120,20,100);
                monMonde.addMur(800,120,20,200);
                monMonde.addMur(800,215,21,20);
                monMonde.addMur(1400,120,20,300);
                monMonde.addMur(1200,215,20,300);
                monMonde.addMur(1380,230,35,20);
                monMonde.addMur(1210,370,11,6);
                
                monMonde.addMur(0,140,20,1500); //--> mur derrière (obligatoire sinon bug affichage)

                //monMonde.addMur(1200,140,1,1);
                


                
                
                //ObjetMurDynamique murDyn+amique = new ObjetMurDynamique(150, 80, 100, 20);// ascenceur 
                //monMonde.addObjet(murDynamique);

                //////////////////////
                // Les MONSTRES
                ///////////////////// 

                //Début du parcours
                monMonde.addMonstre(0.3,0,450,140);

                //monMonde.addMonstre(4,0,1300,140);
                monMonde.addMonstre(2,0,1250,140);
                monMonde.addMonstre(0.5,0,1270,140);

                //Fin du parcours
                monMonde.addMonstre(0.2,0,1300,140);
                monMonde.addMonstre(0.2,0,2100,41);
                //monMonde.addMonstre(2,0,2000,41);
                //monMonde.addMonstre(0.9,0,2210,41);
                //monMonde.addMonstre(0.7,0,2220,41);
                //monMonde.addMonstre(30,0,2230,41);
                //monMonde.addMonstre(0.01,0,2290,41);
                monMonde.addMonstre(0.9,0,2200,41);
                //monMonde.addMonstre(1.5,0,2250,41);
                
                //////////////////////
                // Les potions de vies
                /////////////////////

                monMonde.addObjet(new ObjetPotion(1380,260));
                monMonde.addObjet(new ObjetPotion(1700,160));
                
        
                
                //on creer le moteur physique
                moteurPhys=new MoteurPhysique();

                //On ajoute le monde au moteur
                moteurPhys.monde=monMonde;

                //on creer l'afficheur du monde
                affiche=new Afficheur(moteurPhys.monde);

                // Gestion de la boucle principale
                BouclePrincipale maBoucle= new BouclePrincipale();

                //BouclePrincipaleCool maBoucle= new BouclePrincipaleCool();
                // Ajout du controler à le fenêtre
                maBoucle.cClavier=cClavier;

                // Ajout du jeu physique
                JeuPhysique MonJeuPhysique = new JeuPhysique();
                maBoucle.jeuPhysique=MonJeuPhysique;

                // Ajout de la vue au jeu
                maBoucle.jeuPhysique.affiche=affiche;
                

                // Ajout du jeu à la boucle
                maBoucle.jeuPhysique.moteurPhys=moteurPhys;

                // Change l'image de fond
                //affiche.decor.changeImage("background2.jpg");

                // Passage à une vue non subjective
                //Repere.isSubjective=false;
                
                // Test to change the image
                //monMonde.balle.sprites.assignNewImage("hero2.png");

                // Test to change the gravity value
                moteurPhys.gravityValue=-0.04f;

                // Test to remove the gravity
                //moteurPhys.gravity=true;
                
                // Change les FPS
                maBoucle.setFPS(144);
                maBoucle.lanceBouclePrincipale();
        }
    }
}
