import controleur.Controleur;

/*  Classe main qui lance la partie grace a un controleur */

public class Main {

    public static void main(String[] args){
        /*try{
            Controleur.initNiveau1().sauvegarder("Niveau1");
            Controleur.initNiveau2().sauvegarder("Niveau2");
            Controleur.initNiveau3().sauvegarder("Niveau3");
            Controleur.initNiveau4().sauvegarder("Niveau4");
            Controleur.initNiveau5().sauvegarder("Niveau5");
            Controleur.initNiveau6().sauvegarder("Niveau6");
            Controleur.initNiveau7().sauvegarder("Niveau7");
            Controleur.initNiveau8().sauvegarder("Niveau8");
            Controleur.initNiveau9().sauvegarder("Niveau9");
            Controleur.initNiveau10().sauvegarder("Niveau10");
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        Controleur controleur = new Controleur();
        controleur.commencerJeu();

    }

}
