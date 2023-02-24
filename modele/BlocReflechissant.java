package modele;

import java.awt.*;

public class BlocReflechissant extends Bloc {

    public BlocReflechissant() {
        super(false);
    }
    public BlocReflechissant(boolean fixe){
        super(fixe);
    }

        
    @Override
    public String getType() {
        return "BlocReflechissant";
    }


    @Override
    public int deviationLaser(int x, int y, int angle) {
        if (x % 2 == 1 && y % 2 == 0) {
            switch (angle) {
                case 45:
                    return 135;
                case 135:
                    return 45;
                case 225:
                    return 315;
                case 315:
                    return 225;
            }
        } else if (x % 2 == 0 && y % 2 == 1) {
            switch (angle) {
                case 45:
                    return 315;
                case 135:
                    return 225;
                case 225:
                    return 135;
                case 315:
                    return 45;
            }
        }
        return angle;
    }

}
