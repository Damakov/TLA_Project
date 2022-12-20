package tla;

import java.util.List;

/*
description d'un niveau
*/
abstract class Niveau {

    /*
    initialisation des carreaux
    */
    String INIT_CARREAUX;

    /*
    liste des trappes
    */
    List<Trappe> TRAPPES;

    /*
    liste des fantomes
    (pas une constante car un fantome possède un état)
    */
    List<Fantome> fantomes;

    /*
    liste des commutateurs
    (pas une constante car un commutateur possède un état)
    */
    List<Commutateur> commutateurs;

    /*
    placement des portes fermées selon l'état des commutateurs, à appliquer
    à l'initialisation du niveau et après chaque déplacement du joueur

    il est également possible de décrire dans hookApresDeplacement() d'autres effets
    qui doivent également être appliqués à l'initialisation du niveau et après chaque déplacement du joueur
    */
    abstract void hookApresDeplacement(Plateau plateau);
}
