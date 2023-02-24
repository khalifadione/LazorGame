package modele;

import java.io.Serializable;

public abstract class Case implements Serializable{
	
	
    public void afficheCase(){ }
    public void setBloc(Bloc b){}
    public abstract boolean blocPresent();
    public abstract String getType();
    public abstract Bloc getBloc();
    public abstract void enleverBloc();
    public abstract boolean ajouterBloc(Bloc bloc);

}