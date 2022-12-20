package tla;

/*
Description d'un commutateur : coordonnées x,y et état

Attention à ne pas confondre la description d'un commutateur (décrit dans cette classe)
et les effets des commutateurs sur les portes fermées (doivent être décrit dans hookApresDeplacement)
*/

class Commutateur {
    private int x;
    private int y;
    private boolean etat;

    Commutateur(int x, int y) {
        this.x = x;
        this.y = y;
        etat = false;
    }

    void reset() {
        etat=false;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    boolean getEtat() {
        return etat;
    }

    boolean commute() {
        etat = !etat;
        return etat;
    }
}
