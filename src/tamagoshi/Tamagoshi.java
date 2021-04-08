/**
 * 
 */
package tamagoshi;

import java.util.Random;

/**
 * @author socia
 *
 */
public class Tamagoshi {
	
	private int age;
	private int maxEnergy;
	protected int energy;
	private String name;
	private static int lifeTime = 9;
	private int maxFun;
	protected int fun;
	
	private boolean isAlive;
	
	private String state;
	
	/**
     * Constructeur de la classe Tamagoshi
     * @param nom Nom du tamagoshi
     */
	public Tamagoshi(String name) {
		this.name = name;
		this.age = 0;
		this.maxEnergy = randomMinMax(5,9);
		this.energy = randomMinMax(3,7);
		this.maxFun = randomMinMax(5,9);
		this.fun = randomMinMax(3,7);
		
		isAlive = true;
	}
	
	/**
	 * Methode renvoyant un nombre aleatoir compris entre le nom minimal et maxiaml donnée
	 * @param min valeur minimum voulue
	 * @param max valeur maximum voulue
	 * @return a random number within those two value
	 */
	public int randomMinMax(int min,int max) {
		if(min >= max) {
			 throw new IllegalArgumentException("max doit etre plus grand que min");
		}
		Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Tamagoshi t = new Tamagoshi("odof");
//		t.parle();
//		t.mange();
//		t.consommeEnergie();
	}
	
	/**
     * Méthode qui fait parler les Tamagoshis en fonction de leur niveau d'energie et de fun
     * @return the state of the tamagoshi
     */
	public boolean parle() {
		
		if(this.energy<=4 && this.fun<=4) {
			state = " affamé et je m'ennuie a mourrir";
			System.out.println(this.name + state);
			return false;
		}
		else if(this.fun<=4) {
			state = " je m'ennuie a mourrir";
			System.out.println(this.name + state);
			return false;
		}
		else if(this.energy>4) {
			state = " heureux";
			System.out.println(this.name + state);
			return true;
		}
		state = " affamé";
		System.out.println(this.name + state);
		return false;
	}
	
	public String getState() {
		return this.state;
	}
	
	/**
     * Méthode qui fait manger les tamagoshis et nous donne une repoonse en fonction de leur niveau d'énergie
     * @return if the tamagoshi was happy with the choice
     */
	public boolean mange() {
		if(this.energy<this.maxEnergy) {
			this.energy += randomMinMax(1,3);
			System.out.println(this.name + " appécie");
			return true;
		}
		System.out.println(this.name + " n'appécie pas");
		return false;
	}
	
	/**
     * Méthode qui consomme l'énergie d'un Tamagoshis 
     * @return if the tamagoshi is dead out of exhaustion or not 
     */
	public boolean consommeEnergie() {
		this.energy--;
		if(energy<=0) {
			System.out.println("Je suis KO plus d'energie");
			isAlive = false;
			return false;
		}
		return true;
	}
	
	/**
	 * Methode qui fait veillir le Tamagoshie
	 * @return if the Tamagoshi is dead or not
	 */
	public boolean viellir() {
		if(age<lifeTime) {
			age++;
			return false;
		}
		//System.out.println("je suis KO");
		isAlive = true;
		return true;
	}
	
	/**
     * Méthode qui fait jouer le Tamagoshis et nous donne une reponse en fonction de son niveau d'energie
     * @return if the Tamagoshi played or not
     */
	public boolean jouer() {
		if(this.fun<this.maxFun) {
			this.fun += randomMinMax(1,3);
			System.out.println("On se marre");
			return true;
		}
		System.out.println("Laisse moi je bouquine !!");
		return false;
	}
	
	
	/**
     * Méthode qui consomme le fun du Tamagoshis
     * @return if the tamagoshi is dead out of boredom or not 
     */
	public boolean ennui() {
		this.fun--;
		if(this.fun<=0) {
			System.out.println("Je suis KO d'ennui");
			isAlive = false;
			return false;
		}
		return true;
	}
	
	
	/**
	 * Methode retournant le nom du Tamagoshi
	 * @return the name of the tamagoshi
	 */
	public String getName() {
		return this.name;
	}
	
	
	/**
	 * Methode retournant l'age actuel du Tamagoshi
	 * @return the age of the Tamagoshi
	 */
	public int getAge() {
		return this.age;
	}
	
	/**
	 * Methode retournant la durée de vie maximal du tamagoshi
	 * @return the maximum life of the Tamagoshi
	 */
	public static int getLifeTime() {
		return lifeTime;
	}
	
	/**
	 * methode retounant l'energy actuelle du tamagoshi
	 * @return the energy of the tamagoshi
	 */
	public int getEnergy() {
		return energy;
	}
	
	/**
	 * methode retournant l'energy maximum que le tamagoshi peux avoir
	 * @return the max possible energy the tamagoshi can have
	 */
	public int getMaxEnergy() {
		return maxEnergy;
	}
	
	/**
	 * methode retournant le fun actuelle du tamagoshi
	 * @return the fun level of the tamagoshi
	 */
	public int getFun() {
		return fun;
	}

	/**
	 * methode retournant le fun maximum que le tamagoshi peux avoir
	 * @return the max possible fun the tamagoshi can have
	 */
	public int getMaxFun() {
		return maxFun;
	}
	
	/**
	 * Methode retournant les information du Tamagoshi
	 * @return the readable information of the Tamagoshi
	 */
	@Override
	public String toString() {
		return "Tamagoshi [age=" + age + ", maxEnergy=" + maxEnergy + ", energy=" + energy + ", name=" + name + "]";
	}
	
	/**
	 * 
	 * @return if the tamagoshi is alive of dead
	 */
	public boolean isAlive() {
		return isAlive;
	}
	
	
	

}
