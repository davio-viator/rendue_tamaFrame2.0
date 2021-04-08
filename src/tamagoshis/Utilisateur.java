package tamagoshis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utilisateur {
	
	/**
     * M�thode qui sert � r�cup�rer la saisie de l'utilisateur
     * @return la saisie de l'utilisateur
     */
  public static String saisieClavier(){

    /*il faut g�rer les exceptions car l'entr�e standard 
    peut ne pas �tre disponible : le constructeur de la 
    classe InputStreamReader peut renvoyer une exception.*/
    try{ 
      BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
      return clavier.readLine();
    }
    catch(IOException e){
      e.printStackTrace();
      System.exit(0);
      return null;
    }
  }
  
  // une m�thode main juste pour tester 
  public static void main(String[] args) {
    String saisie = Utilisateur.saisieClavier();
    System.out.println("la saisie est : "+saisie);
  }
}