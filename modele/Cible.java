package modele;

import java.awt.Point;
//import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;

public class Cible implements Serializable{


    private static final long serialVersionUID = 7073363957831599093L;

    protected final Point p;
    protected boolean atteint;

    public Point getPoint() {
        return p;
    }

    public boolean isAtteint() {
        return atteint;
    }

    //Il ne peut que y qvoir des cibles à des positions (paire, impaire) où (impaire, paire)
    public Cible(int x, int y) {
        if(((x%2 == 0) && (y%2==0)) || ((x%2 == 1) && (y%2==1))){
            throw new IllegalArgumentException("Il ne peut pas y avoir de cible à cet endroit là: " + x + ", " + y);
        }
        atteint = false;
        this.p=new Point(x,y);
    }

}
