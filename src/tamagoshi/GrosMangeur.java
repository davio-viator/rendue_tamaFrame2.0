package tamagoshi;

public class GrosMangeur extends Tamagoshi {
	


	public GrosMangeur(String nom) {
		super(nom);
	}
	
	
	@Override
	public boolean consommeEnergie() {
		this.energy--;
		return super.consommeEnergie();
	}

}
