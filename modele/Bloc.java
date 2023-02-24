package modele;

import java.io.Serializable;

import javax.swing.*;

public abstract class Bloc implements Serializable{

	//S'il est initialisé à true alors le bloc n'est pas déplaçable
	public final boolean fixe;
        
        /*  CONSTRUCTEUR  */

	public Bloc(boolean fixe) {
		this.fixe=fixe;
	}

        /*  GETTER */

	public String getType(){
            return "";
        }

	public abstract int deviationLaser(int x, int y, int angle);
}
