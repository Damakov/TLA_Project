package tla;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/*
prépare différents objets de type javafx.scene.image.Image
permettant d'afficher les différentes formes de carreaux et les éléments mobiles du jeu
*/

class LibrairieImages {

    static final Image imgJoueurGrand = new Image(LibrairieImages.class.getResourceAsStream("/player_01.png"), 128, 128, true, false);
    static final Image imgJoueur = new Image(LibrairieImages.class.getResourceAsStream("/player_01.png"), Plateau.LARGEUR_CARREAU + 6, Plateau.LARGEUR_CARREAU + 6, true, false);
    static final Image imgFantome = new Image(LibrairieImages.class.getResourceAsStream("/elementMetal045.png"), Plateau.LARGEUR_CARREAU - 6, Plateau.LARGEUR_CARREAU - 6, true, false);
    static final Image imgMur = new Image(LibrairieImages.class.getResourceAsStream("/block_05.png"), Plateau.LARGEUR_CARREAU, Plateau.LARGEUR_CARREAU, true, false);
    static final Image imgSortie = new Image(LibrairieImages.class.getResourceAsStream("/crate_30.png"), Plateau.LARGEUR_CARREAU, Plateau.LARGEUR_CARREAU, true, false);
    static final Image CarreauVide = genererCarre(Color.gray(0.92), Color.gray(0.96));
    static final Image imgCommutateurOff = new Image(LibrairieImages.class.getResourceAsStream("/laserSwitchBlueOff.png"), Plateau.LARGEUR_CARREAU, Plateau.LARGEUR_CARREAU, true, false);
    static final Image imgCommutateurOn = new Image(LibrairieImages.class.getResourceAsStream("/laserSwitchBlueOn.png"), Plateau.LARGEUR_CARREAU, Plateau.LARGEUR_CARREAU, true, false);
    static final Image imgPorteFermee = new Image(LibrairieImages.class.getResourceAsStream("/platformPack_tile058.png"), Plateau.LARGEUR_CARREAU, Plateau.LARGEUR_CARREAU, true, false);

    /*
    générer une image de type javafx.scene.image.Image contenant un carré
    en spécifiant la couleur principale et la couleur de bordure
    */
    static Image genererCarre(Color couleurPrincipale, Color couleurBordure) {
        WritableImage img = new WritableImage(Plateau.LARGEUR_CARREAU, Plateau.LARGEUR_CARREAU);
        PixelWriter pw = img.getPixelWriter();
        for(int x = 0; x< Plateau.LARGEUR_CARREAU; x++) {
            for(int y = 0; y< Plateau.LARGEUR_CARREAU; y++) {
                boolean enBordure = x<1 || x>(Plateau.LARGEUR_CARREAU -2) || y<1 || y>(Plateau.LARGEUR_CARREAU -2);
                pw.setColor(
                        x,
                        y,
                        enBordure ? couleurBordure : couleurPrincipale
                );
            }
        }
        return img;
    }
}
