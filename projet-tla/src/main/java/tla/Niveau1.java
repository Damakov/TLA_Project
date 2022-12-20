package tla;

import java.util.Arrays;

import static tla.Direction.*;

class Niveau1 extends Niveau {

    Niveau1() {
        INIT_CARREAUX =
            "     #       #      " +
            "#### # ##### # #### " +
            "     #   # # #    # " +
            " ####### # # ## # # " +
            "       # # #    # # " +
            " ##### # # ###### # " +
            " #       #        # " +
            " # ######### ###### " +
            " # #    *  # # #    " +
            " # # ##### # # # ###" +
            " # #     # # # #    " +
            "   ##### # # # #### " +
            "## #     #   #      " +
            "   #################";

        TRAPPES = Arrays.asList(
                new Trappe(3, 0, DROITE, 10, 2),
                new Trappe(5, 4, GAUCHE, 8, 6)
        );

        fantomes = Arrays.asList(
                new Fantome(8,4, Arrays.asList(
                        BAS,
                        BAS,
                        GAUCHE,
                        GAUCHE,
                        GAUCHE,
                        GAUCHE,
                        GAUCHE
                )),
                new Fantome(0,11, Arrays.asList(
                        DROITE,
                        DROITE,
                        BAS,
                        BAS,
                        GAUCHE,
                        GAUCHE,
                        DROITE,
                        DROITE,
                        HAUT,
                        HAUT,
                        GAUCHE,
                        GAUCHE
                )),
                new Fantome(12,7, Arrays.asList(
                        BAS,
                        BAS,
                        BAS,
                        BAS
                ))
        );

        commutateurs = Arrays.asList(
                new Commutateur(3,2)
        );

    }

    void hookApresDeplacement(Plateau plateau) {
        plateau.carreaux[190].setEtat(commutateurs.get(0).getEtat() ? EtatCarreau.VIDE : EtatCarreau.PORTE_FERMEE);
    };
}
