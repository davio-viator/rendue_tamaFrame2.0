package tamagoshi;

public class GrosJoueur extends Tamagoshi {
	
	
	
	public GrosJoueur(String nom) {
		super(nom);
	}

	@Override
	public boolean ennui() {
		fun--;
		return super.ennui();
	}
	
	
}
