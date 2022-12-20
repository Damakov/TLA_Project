package tla;

/*
Enumération des états possibles d'un carreau
*/
enum EtatCarreau {
    VIDE,               // carreau vide, le joueur peut se rendre dessus
    MUR,                // bloque le passage
    SORTIE,             // le joeur gagne s'il se rend dessus
    COMMUTATEUR_OFF,    // levier commutateur tournée à gauche, le joueur peut se rendre dessus
    COMMUTATEUR_ON,     // levier commutateur tournée à droite, le joueur peut se rendre dessus
    PORTE_FERMEE        // bloque le passage
};
