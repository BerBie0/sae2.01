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
import afficheur.Afficheur;
import controle.ControleurClavier;
import physique.Monde;
import physique.MoteurPhysique;
import physique.ObjetMurDynamique;
import physique.ObjetPotion;
import main.JeuPhysique;
import afficheur.Menu;
import afficheur.FrameMenu;

public class PirateBay {
    
    
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
                //gros block
                
                monMonde.addMur(100,20,50,50);
                
                
                //MurCool murCool = new MurCool(100, 20, 50, 50);
                //monMonde.addObjet(murCool);

                //sol
                monMonde.addMur(0,-20,600,20);

                monMonde.addObjet(new ObjetPotion(50,50));

                //mur vertical centre 
                //MurCool monMur=new MurCool(250,65,50,200);
                //monMonde.addObjet(monMur);
                
                monMonde.addMur(200,60,50,200);
                monMonde.addMur(0,00,20,1500);
                
                monMonde.addMur(0,150,50,20);

                monMonde.addMur(250,00,10,5);
                monMonde.addMur(280,60,100,20);
                monMonde.addMur(350,00,10,5);
                monMonde.addMur(450,0,50,5);

                monMonde.addMur(200,200,600,20);

                monMonde.addMur(800,00,1500,20);
                monMonde.addMur(2300,60,200,20);
                monMonde.addMur(2550,170,150,20);
                monMonde.addMur(2950,240,50,20);

                monMonde.addMur(3000,00,20,1500);
                
                ObjetMurDynamique murDynamique = new ObjetMurDynamique(150, 80, 100, 20);
                monMonde.addObjet(murDynamique);

                //////////////////////
                // Les MONSTRES
                /////////////////////   
                monMonde.addMonstre(0.2,0,200,0);
                monMonde.addMonstre(-0.1,0,300,0);
                
                //////////////////////
                // Le Hero
                /////////////////////  
                
                
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
                
                maBoucle.setFPS(144);
                maBoucle.lanceBouclePrincipale();
        }
    }
}
