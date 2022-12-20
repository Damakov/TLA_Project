package tla;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.List;

/*
gère le comportement d'un fantôme, à l'aide d'une séquence de direction
*/
class Fantome {

    // position de début de séquence du fantome
    private int depart_x;
    private int depart_y;

    // position courante du fantome
    private int x;
    private int y;

    // élément graphique du fantôme
    private ImageView imageViewFantome;

    // plateau sur lequel est placé le fantome
    private Plateau plateau;

    // séquence de déplacement
    private List<Direction> sequence;

    // position dans la séquence
    private int index;

    Fantome(int depart_x, int depart_y, List<Direction> sequence) {

        index = 0;

        this.depart_x = depart_x;
        this.depart_y = depart_y;
        this.sequence = sequence;

        x = depart_x;
        y = depart_y;

        imageViewFantome = new ImageView(LibrairieImages.imgFantome);
        imageViewFantome.setViewOrder(10);
    }
    Node getNode() {
        return imageViewFantome;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void reset() {
        index = 0;
        x = depart_x;
        y = depart_y;
        imageViewFantome.setTranslateX(x * Plateau.LARGEUR_CARREAU + 3);
        imageViewFantome.setTranslateY(y * Plateau.LARGEUR_CARREAU + 3);
    }

    /*
    replace l'élément graphique du fantôme avec transition
    */
    void anime() {

        if (index == 0 && (x != depart_x || y != depart_y )) {
            // en début de séquence et si nécessaire : replace le fantome à la position de départ
            // (sinon il finirait par sortir du plateau)
            x = depart_x;
            y = depart_y;
            imageViewFantome.setTranslateX(x * Plateau.LARGEUR_CARREAU + 3);
            imageViewFantome.setTranslateY(y * Plateau.LARGEUR_CARREAU + 3);
        } else {
            switch (sequence.get(index)) {
                case HAUT:
                    y = y - 1;
                    break;
                case DROITE:
                    x = x + 1;
                    break;
                case BAS:
                    y = y + 1;
                    break;
                case GAUCHE:
                    x = x - 1;
                    break;
            }
            index = (index + 1) % sequence.size();
        }

        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imageViewFantome);
        transition.setToX(x * Plateau.LARGEUR_CARREAU + 3);
        transition.setToY(y * Plateau.LARGEUR_CARREAU + 3);
        transition.setDuration(Duration.millis(150));
        transition.play();
    }

}
