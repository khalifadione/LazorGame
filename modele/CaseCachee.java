package modele;

public class CaseCachee extends Case {

    @Override
    public boolean blocPresent() {
        return false;
    }

    @Override
    public Bloc getBloc() {
        return null;
    }

    @Override
    public void enleverBloc() {}

    @Override
    public boolean ajouterBloc(Bloc bloc) {
        return false;
    }

    @Override
    public String getType() {
        return "";
    }

}