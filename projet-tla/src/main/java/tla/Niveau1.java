package tla;

import java.util.Arrays;

import static tla.Direction.*;

class Niveau1 extends Niveau {

    Niveau1() {
        INIT_CARREAUX =
"                    "+
"################### "+
"                    "+
" ###################"+
"                    "+
"################### "+
"                    "+
" ###################"+
"                    "+
"################### "+
"                    "+
" ###################"+
"                    "+
" ##################*"
;
TRAPPES = Arrays.asList(

new Trappe(10, 6, DROITE, 0, 7),
new Trappe(20, 9, DROITE, 0, 11));
fantomes = Arrays.asList(

new Fantome(1,0, Arrays.asList(DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
))
,
new Fantome(1,4, Arrays.asList(DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
))
,
new Fantome(1,6, Arrays.asList(DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
))
,
new Fantome(1,10, Arrays.asList(DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
))
,
new Fantome(1,8, Arrays.asList(DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
))
,
new Fantome(1,14, Arrays.asList(DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
))
,
new Fantome(1,12, Arrays.asList(DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
,DROITE
))
);
commutateurs = Arrays.asList(

new Commutateur(11,8));
 } 
 void hookApresDeplacement(Plateau plateau) { };}
 