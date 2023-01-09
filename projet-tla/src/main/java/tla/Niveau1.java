package tla;

import java.util.Arrays;

import static tla.Direction.*;

class Niveau1 extends Niveau {

    Niveau1() {
        INIT_CARREAUX =
"####################"+
"####################"+
"####################"+
"####################"+
"####################"+
"####################"+
"####################"+
"####################"+
"####################"+
"####################"+
"####################"+
"####################"+
"####################"+
"###################*"
;
TRAPPES = Arrays.asList(

new Trappe(8, 9, DROITE, 1, 2));
fantomes = Arrays.asList(

new Fantome(8,9, Arrays.asList(GAUCHE
,GAUCHE
,DROITE
,DROITE
,DROITE
))
);
commutateurs = Arrays.asList(

new Commutateur(2,10));
 } 
 void hookApresDeplacement(Plateau plateau) { };}
 