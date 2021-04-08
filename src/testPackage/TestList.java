package testPackage;

import mesObjets.*;
import java.util.ArrayList;
import java.util.Collections;

public class TestList {
	public static void main (String[] args) {
		ArrayList<Salarie> liste = new ArrayList<Salarie>();
	    liste.add(new Salarie("Pierre","Gold n Gold",60000));
	    liste.add(new Salarie("Jacques", "Clean n Dry",1000));
	    liste.add(new Salarie("Jules", "Clean n Dry",1000));
	    liste.add(new Salarie("Albert", "No where", 2000));
	    liste.add(new Salarie("Zizou", "RMCF", 60000));
	    liste.add(new Salarie("Charles","Dad n Son", 5000));
	    
	    System.out.println(toString(liste));
	    Collections.sort(liste);
	    System.out.println(toString(liste));
	}
	
	
	public static String toString (ArrayList<Salarie> liste) {
		String value = "[\n";
		for(Salarie s:liste) {
			value+=s.toString()+",\n";
		}
		value+="]";
		
		return value;
	}
}
