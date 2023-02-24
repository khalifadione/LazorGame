package modele;

public class BlocPlus extends Bloc{
	public BlocPlus(){
		super(false);
	}
	public BlocPlus(boolean fixe){
		super(fixe);
	}

	@Override
	public String getType() {
		return "BlocPlus";
	}

	@Override
	public int deviationLaser(int x, int y, int angle) {
		return -1;
	}
}
