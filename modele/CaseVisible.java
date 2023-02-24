package modele;

public class CaseVisible extends Case{


	protected Bloc bloc;

	public CaseVisible() {
		this(null);
	}

	public CaseVisible(Bloc bloc) {
		this.bloc=bloc;
	}


	@Override
	public void setBloc(Bloc b){
		this.bloc = b;
	}

	@Override
	public Bloc getBloc(){
		return this.bloc;
	}

	@Override
	public void enleverBloc(){
		this.bloc = null;
	}

	@Override
	public boolean ajouterBloc(Bloc bloc) {
		this.bloc = bloc;
		return true;
	}

	@Override
    public boolean blocPresent() {
        return this.bloc != null;
    }

    @Override
    public String getType() {
        return "";
    }

}
