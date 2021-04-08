package testPackage;

import mesObjets.Salarie;

public class Personne implements Cloneable, Comparable{

  private String name;
 
  public Personne(String nom) {
    name = nom;
  }
 
  @Override
  public String toString() {
    return /*super.toString()+*/" name = \""+name+"\"";
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
	  return this.name;
  }
@Override
public int compareTo(Object s2) {
	// TODO Auto-generated method stub
	Personne p = (Personne)s2;
	return this.name.compareTo(p.getName());
}
  


}