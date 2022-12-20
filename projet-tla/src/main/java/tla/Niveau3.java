package tla;

import java.util.Arrays;

import static tla.Direction.*;

class Niveau3 extends Niveau {
    Niveau3() {
        INIT_CARREAUX =
            "                 ###" +
            "###############  #  " +
            "                   #" +
            "   ##############   " +
            "                 #  " +
            "################  ##" +
            " #                  " +
            "    ################" +
            "                    " +
            "#######    #########" +
            "                   #" +
            "###   ###########  #" +
            "   ###              " +
            "*                   ";

        TRAPPES = Arrays.asList(
                new Trappe(9,4,DROITE, 7,4),
                new Trappe(19,3,DROITE, 9,6)
        );

        fantomes = Arrays.asList(
                new Fantome(7,9, Arrays.asList(
                        DROITE,
                        DROITE
                )),
                new Fantome(8,9, Arrays.asList(
                        DROITE,
                        DROITE
                ))
        );

        commutateurs = Arrays.asList(
                new Commutateur(7,12),
                new Commutateur(9,12),
                new Commutateur(11,12),
                new Commutateur(13,12),
                new Commutateur(15,12)
        );

    }

    void hookApresDeplacement(Plateau plateau) {

        plateau.carreaux[ 3 + 13*Plateau.LARGEUR_PLATEAU].setEtat(
                (commutateurs.get(1).getEtat() && !commutateurs.get(2).getEtat()) ||
                (commutateurs.get(1).getEtat() && commutateurs.get(2).getEtat() && commutateurs.get(4).getEtat()) ?
                        EtatCarreau.VIDE : EtatCarreau.PORTE_FERMEE
        );
        plateau.carreaux[ 4 + 13*Plateau.LARGEUR_PLATEAU].setEtat(
                (commutateurs.get(2).getEtat()) ?
                        EtatCarreau.VIDE : EtatCarreau.PORTE_FERMEE
        );
        plateau.carreaux[ 5 + 13*Plateau.LARGEUR_PLATEAU].setEtat(
                (commutateurs.get(0).getEtat() && !commutateurs.get(1).getEtat()) ||
                        (!commutateurs.get(0).getEtat() && commutateurs.get(1).getEtat()) ?
                        EtatCarreau.VIDE : EtatCarreau.PORTE_FERMEE
        );
    };
}
