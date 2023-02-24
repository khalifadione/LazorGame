package controleur;

import modele.*;
import vue.*;

import javax.swing.*;
import java.util.LinkedList;

/*  */

public class Controleur {

	private Plateau plateau;
	private VuePlateau rect;

        /*  CONSTRUCTEUR  */
	public Controleur(){
		this.rect = new VuePlateau();
	}
	
        /*   LES DIFFERENTS NIVEAUX   */

	public static Plateau initNiveau1(){
		LinkedList<Laser> l1 = new LinkedList<Laser>();
		l1.add(new Laser(8, 1, 45));
		Cible[] c1 = new Cible[1];
		c1[0] = new Cible(7, 4);
		Plateau plateau = new Plateau(5, 5, l1, c1);
		for (int i = 0; i < plateau.height; i++) {
			for (int j = 0; j < plateau.width; j++) {
				plateau.cases[i][j] = new CaseVisible();
			}
		}
		plateau.cases[1][4] = new CaseCachee();
		plateau.cases[2][2] = new CaseCachee();
		plateau.cases[3][2] = new CaseCachee();
		plateau.cases[3][4] = new CaseCachee();
		plateau.cases[4][1] = new CaseCachee();
		plateau.cases[4][4] = new CaseCachee();

		plateau.cases[1][2] = new CaseVisible(new BlocReflechissant());
		plateau.cases[2][1] = new CaseVisible(new BlocReflechissant());
		plateau.cases[4][3] = new CaseVisible(new BlocReflechissant());
		plateau.initLaser();
		return plateau;
	}
	public static Plateau initNiveau2(){
		LinkedList<Laser> l1 = new LinkedList<Laser>();
		l1.add(new Laser(7, 8, 225));
		Cible[] c1 = new Cible[1];
		c1[0] = new Cible(4, 1);
		Plateau plateau = new Plateau(7, 7, l1, c1);
		for (int i = 0; i < plateau.height; i++) {
			for (int j = 0; j < plateau.width; j++) {
				plateau.cases[i][j] = new CaseVisible();
			}
		}
		plateau.cases[1][3] = new CaseCachee();
		plateau.cases[3][2] = new CaseCachee();
		plateau.cases[3][3] = new CaseCachee();
		plateau.cases[6][1] = new CaseCachee();

		plateau.cases[3][1] = new CaseVisible(new BlocSemiReflechissant());
		plateau.cases[3][4] = new CaseVisible(new BlocPlus());
		plateau.cases[5][2] = new CaseVisible(new BlocReflechissant());
		plateau.cases[2][4] = new CaseVisible(new BlocReflechissant());
		plateau.initLaser();
		return plateau;
	}
	public static Plateau initNiveau3() {
		LinkedList<Laser> l = new LinkedList<Laser>();
		l.add(new Laser(6, 1, 45));
		Cible[] c = new Cible[2];
		c[0] = new Cible(2, 5);
                c[1] = new Cible(5, 6);
                
		Plateau plat = new Plateau(4, 5, l, c);

		for (int i = 0; i < plat.height; i++) {
			for (int j = 0; j < plat.width; j++) {
				plat.cases[i][j] =  new CaseVisible();
			}
		}

                
		plat.cases[2][4] = new CaseVisible(new BlocPrismatique());
		plat.cases[1][2] = new CaseVisible(new BlocReflechissant());
        plat.cases[2][1] = new CaseVisible(new BlocReflechissant());
        plat.cases[3][4] = new CaseVisible(new BlocReflechissant());
        plat.cases[3][4] = new CaseVisible(new BlocReflechissant());

		plat.initLaser();
		return plat;
	}
    public static Plateau initNiveau4() {
		LinkedList<Laser> l = new LinkedList<Laser>();
		l.add(new Laser(0, 3, 225));
                l.add(new Laser(3, 4, 45));
                l.add(new Laser(6, 3, 135));
                l.add(new Laser(6, 1, 45));
		Cible[] c = new Cible[2];
		c[0] = new Cible(3, 0);
                c[1] = new Cible(1, 6);
                
		Plateau plat = new Plateau(4, 4, l, c);

		for (int i = 0; i < plat.height; i++) {
			for (int j = 0; j < plat.width; j++) {
				plat.cases[i][j] =  new CaseVisible();
			}
		}
                
                plat.cases[1][1] = new CaseCachee();
                plat.cases[3][3] = new CaseCachee();
                
		plat.cases[2][1] = new CaseVisible(new BlocAbsorbant());
		plat.cases[2][2] = new CaseVisible(new BlocAbsorbant());
                plat.cases[2][3] = new CaseVisible(new BlocAbsorbant());

		plat.initLaser();
		return plat;
	}
    public static Plateau initNiveau5() {
		LinkedList<Laser> l = new LinkedList<Laser>();
		l.add(new Laser(5, 0, 315));
		Cible[] c = new Cible[4];
		c[0] = new Cible(2, 5);
                c[1] = new Cible(5, 2);
                c[2] = new Cible(5, 8);
                c[3] = new Cible(8, 5);
                
		Plateau plat = new Plateau(6, 6, l, c);

		for (int i = 0; i < plat.height; i++) {
			for (int j = 0; j < plat.width; j++) {
				plat.cases[i][j] =  new CaseVisible();
			}
		}
                
                plat.cases[4][5] = new CaseCachee();
                plat.cases[5][3] = new CaseCachee();
                
		plat.cases[2][5] = new CaseVisible(new BlocReflechissant());
		plat.cases[4][5] = new CaseVisible(new BlocTeleporteur());
                plat.cases[5][4] = new CaseVisible(new BlocTeleporteur());

		plat.initLaser();
		return plat;
	}
        
        
        
    public static Plateau initNiveau6() {
		LinkedList<Laser> l = new LinkedList<Laser>();
		l.add(new Laser(0, 5, 225));
		Cible[] c = new Cible[2];
		c[0] = new Cible(3, 0);
                c[1] = new Cible(5, 6);

                
		Plateau plat = new Plateau(4, 4, l, c);

		for (int i = 0; i < plat.height; i++) {
			for (int j = 0; j < plat.width; j++) {
				plat.cases[i][j] =  new CaseVisible();
			}
		}
		plat.cases[3][2] = new CaseCachee();
                plat.cases[3][3] = new CaseCachee();
                
		plat.cases[1][1] = new CaseVisible(new BlocPrismatique());
		plat.cases[1][2] = new CaseVisible(new BlocAbsorbant());
                plat.cases[2][2] = new CaseVisible(new BlocReflechissant());
                plat.cases[3][1] = new CaseVisible(new BlocSemiReflechissant());


		plat.initLaser();
		return plat;
	}
	public static Plateau initNiveau7() {

		LinkedList<Laser> l = new LinkedList<Laser>();
		l.add(new Laser(0, 5, 225));
		Cible[] c = new Cible[1];
		c[0] = new Cible(5, 2);



		Plateau plat = new Plateau(5, 5, l, c);

		for (int i = 0; i < plat.height; i++) {
			for (int j = 0; j < plat.width; j++) {
				plat.cases[i][j] =  new CaseVisible();
			}
		}
		plat.cases[2][1] = new CaseCachee();

		plat.cases[1][3] = new CaseVisible(new BlocTeleporteur(false));
		plat.cases[2][4] = new CaseVisible(new BlocTeleporteur(false));
		plat.cases[2][1] = new CaseVisible(new BlocAbsorbant(true));
		plat.cases[3][2] = new CaseVisible(new BlocAbsorbant(true));
		plat.cases[4][1] = new CaseVisible(new BlocReflechissant(false));


		plat.initLaser();
		return plat;
	}
	public static Plateau initNiveau8() {
		LinkedList<Laser> l = new LinkedList<Laser>();
		l.add(new Laser(1, 8, 225));
		Cible[] c = new Cible[1];
		c[0] = new Cible(0, 7);



		Plateau plat = new Plateau(5, 5, l, c);

		for (int i = 0; i < plat.height; i++) {
			for (int j = 0; j < plat.width; j++) {
				plat.cases[i][j] =  new CaseVisible();
			}
		}
		plat.cases[1][4] = new CaseCachee();
		plat.cases[4][3] = new CaseCachee();

		plat.cases[1][1] = new CaseVisible(new BlocTeleporteur(true));
		plat.cases[2][2] = new CaseVisible(new BlocTeleporteur(false));
		plat.cases[4][4] = new CaseVisible(new BlocReflechissant(false));


		plat.initLaser();
		return plat;
	}
	public static Plateau initNiveau9() {
		LinkedList<Laser> l = new LinkedList<Laser>();
		l.add(new Laser(3, 8, 225));
		Cible[] c = new Cible[7];
		c[0] = new Cible(3, 6);
		c[1] = new Cible(3, 4);
		c[2] = new Cible(4, 3);
		c[3] = new Cible(6, 3);
		c[4] = new Cible(7, 4);
		c[5] = new Cible(7, 6);
		c[6] = new Cible(6, 7);




		Plateau plat = new Plateau(6, 6, l, c);

		for (int i = 0; i < plat.height; i++) {
			for (int j = 0; j < plat.width; j++) {
				plat.cases[i][j] =  new CaseVisible();
			}
		}
		plat.cases[1][1] = new CaseCachee();
		plat.cases[1][5] = new CaseCachee();
		plat.cases[3][4] = new CaseCachee();
		plat.cases[5][1] = new CaseCachee();
		plat.cases[5][5] = new CaseCachee();

		plat.cases[2][1] = new CaseVisible(new BlocReflechissant(false));
		plat.cases[3][3] = new CaseVisible(new BlocReflechissant(true));
		plat.cases[4][1] = new CaseVisible(new BlocReflechissant(false));
		plat.cases[4][5] = new CaseVisible(new BlocReflechissant(false));
		plat.cases[5][2] = new CaseVisible(new BlocReflechissant(false));
		plat.cases[5][4] = new CaseVisible(new BlocReflechissant(false));


		plat.initLaser();
		return plat;
	}
	public static Plateau initNiveau10() {
		LinkedList<Laser> l = new LinkedList<Laser>();
		l.add(new Laser(3, 8, 225));
		Cible[] c = new Cible[7];
		c[0] = new Cible(3, 6);
		c[1] = new Cible(3, 4);
		c[2] = new Cible(4, 3);
		c[3] = new Cible(6, 3);
		c[4] = new Cible(7, 4);
		c[5] = new Cible(7, 6);
		c[6] = new Cible(6, 7);




		Plateau plat = new Plateau(6, 6, l, c);

		for (int i = 0; i < plat.height; i++) {
			for (int j = 0; j < plat.width; j++) {
				plat.cases[i][j] =  new CaseVisible();
			}
		}
		plat.cases[1][1] = new CaseCachee();
		plat.cases[1][5] = new CaseCachee();
		plat.cases[3][4] = new CaseCachee();
		plat.cases[5][1] = new CaseCachee();
		plat.cases[5][5] = new CaseCachee();

		plat.cases[3][3] = new CaseVisible(new BlocReflechissant(false));
		plat.cases[4][1] = new CaseVisible(new BlocReflechissant(true));
		plat.cases[4][5] = new CaseVisible(new BlocPrismatique(false));
		plat.cases[5][2] = new CaseVisible(new BlocReflechissant(false));
		plat.cases[5][4] = new CaseVisible(new BlocReflechissant(false));

		plat.initLaser();
		return plat;
	}

	public void commencerJeu(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(rect);
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
}
