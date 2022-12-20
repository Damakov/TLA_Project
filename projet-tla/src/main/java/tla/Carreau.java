package tla;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/*
Gère l'affichage d'un carreau, cf. EtatCarreau pour les différents états possibles
*/
class Carreau {
    private EtatCarreau etat;
    private ImageView imageView = new ImageView();

    Carreau(int x, int y, Pane parent) {
        setEtat(EtatCarreau.VIDE);
        imageView.setTranslateX(x * Plateau.LARGEUR_CARREAU);
        imageView.setTranslateY(y * Plateau.LARGEUR_CARREAU);
        Tooltip.install(imageView, new Tooltip("(" + x + ", " + y + ")"));
        parent.getChildren().add(imageView);
    }

    void setEtat(EtatCarreau etat) {
        this.etat = etat;
        switch (etat) {
            case VIDE:
                imageView.setImage(LibrairieImages.CarreauVide);
                imageView.setViewOrder(20);
                break;
            case MUR:
                imageView.setImage(LibrairieImages.imgMur);
                imageView.setViewOrder(0);
                break;
            case COMMUTATEUR_OFF:
                imageView.setImage(LibrairieImages.imgCommutateurOff);
                imageView.setViewOrder(0);
                break;
            case COMMUTATEUR_ON:
                imageView.setImage(LibrairieImages.imgCommutateurOn);
                imageView.setViewOrder(0);
                break;
            case PORTE_FERMEE:
                imageView.setImage(LibrairieImages.imgPorteFermee);
                imageView.setViewOrder(0);
                break;
            case SORTIE:
                imageView.setImage(LibrairieImages.imgSortie);
                imageView.setViewOrder(20);
                break;
        }
    }

    EtatCarreau getEtat() {
        return etat;
    }

}
