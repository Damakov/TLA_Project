package tla;

import java.util.ArrayList;
import java.util.Arrays;

import static tla.Direction.*;

class Niveau2 extends Niveau {

    Niveau2() {
        INIT_CARREAUX =
            "     ####           " +
            "            ########" +
            "########      #     " +
            "                  ##" +
            "################# # " +
            "                #   " +
            "  #####    #    #   " +
            "          #     #   " +
            "     ### #  ########" +
            "       # # #        " +
            "  # #  # # #   ###  " +
            "  # #  # # #   #    " +
            "  ###  # # #   # ###" +
            "           #   #   *";

        TRAPPES = Arrays.asList(
                new Trappe(6,1,GAUCHE, 15,5),
                new Trappe(6,1,DROITE, 15,5),
                new Trappe(7,1,GAUCHE, 15,5),
                new Trappe(0,13,GAUCHE, 13,3),
                new Trappe(17,5,BAS, 14,9)
        );

        fantomes = new ArrayList<>();

        commutateurs = Arrays.asList(
                new Commutateur(17,0),
                new Commutateur(6,1),
                new Commutateur(8,7),
                new Commutateur(3,11)
        );

    }

    void hookApresDeplacement(Plateau plateau) {
        plateau.carreaux[12 +  0*Plateau.LARGEUR_PLATEAU].setEtat(commutateurs.get(2).getEtat() ? EtatCarreau.VIDE : EtatCarreau.PORTE_FERMEE);
        plateau.carreaux[ 3 + 10*Plateau.LARGEUR_PLATEAU].setEtat(commutateurs.get(0).getEtat() ? EtatCarreau.VIDE : EtatCarreau.PORTE_FERMEE);
        plateau.carreaux[17 + 11*Plateau.LARGEUR_PLATEAU].setEtat(commutateurs.get(1).getEtat() ? EtatCarreau.VIDE : EtatCarreau.PORTE_FERMEE);
        plateau.carreaux[17 +  4*Plateau.LARGEUR_PLATEAU].setEtat(commutateurs.get(3).getEtat() ? EtatCarreau.VIDE : EtatCarreau.PORTE_FERMEE);
    };
}
