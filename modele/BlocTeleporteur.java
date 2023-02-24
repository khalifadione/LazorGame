package modele;

public class BlocTeleporteur extends Bloc {


    public BlocTeleporteur() {
        super(false);
    }
    public BlocTeleporteur(boolean fixe){
        super(fixe);
    }
    @Override
    public String getType(){
            return "BlocTeleporteur";
        }

    @Override
    public int deviationLaser(int x, int y, int angle){
        return -1;
    }   
}
