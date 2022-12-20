package tla;

/*
Description d'une trappe
x,y : ou se situe la trappe
direction : dans quelle direction aller pour passer dans la trappe
destination_x,destination_y : ou mène la trappe
*/

class Trappe {
    private int x;
    private int y;
    private Direction direction;
    private int destination_x;
    private int destination_y;

    Trappe(int x, int y, Direction direction, int destination_x, int destination_y) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.destination_x = destination_x;
        this.destination_y = destination_y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Direction getDirection() {
        return direction;
    }

    int getDestinationX() {
        return destination_x;
    }

    int getDestinationY() {
        return destination_y;
    }
}
