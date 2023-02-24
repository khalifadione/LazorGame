package modele;

public class BlocPrismatique extends Bloc {

    public BlocPrismatique(){
        super(false);
    }
    public BlocPrismatique(boolean fixe){
        super(fixe);
    }

    @Override
    public String getType() {
        return "BlocPrismatique";
    } 


    @Override
    public int deviationLaser(int x, int y, int angle){
        if(y % 2 == 1 ){
            switch(angle){
                case 45 : return 0;
                case 315 : return 180;
                case 135 : return 0;
                case 225 : return 180; 
            }           
        } else if(x % 2 == 1 ){
            switch(angle){
                case 45 : return 90;
                case 135 : return 270;
                case 315 : return 90;
                case 225 : return 270; 
            }
        }
        return angle;
    }   
}
