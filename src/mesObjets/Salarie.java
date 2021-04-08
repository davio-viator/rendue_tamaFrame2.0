package mesObjets;

import testPackage.Personne;

public class Salarie extends Personne implements Comparable {
	
	
	private String prenom;
	private double salaire;
	
	public Salarie(String nom,String prenom,double salaire) {
		super(nom);
		this.prenom = prenom;
		this.salaire = salaire;
	}
	
	public String toString() {
		return super.toString()+" prenom -> "+prenom+" salaire ->"+salaire;
	}
	
	public double getSalaire() {
		return salaire;
	}
	

	@Override
	public int compareTo(Object o) {
		Salarie x = (Salarie)o;
		if(this.getSalaire()>x.getSalaire()) {
			return 1;
		}else if(this.getSalaire()<x.getSalaire()) {
			return -1;
		}
		return 0;
	}
	
}
