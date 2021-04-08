package tamagoshis.graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import tamagoshis.Tamagoshi;
//import tamagoshi.jeu.NewTamaGame;
import tamagoshis.graphics.TamaGameFrame;

/**
 * @author Davio
 *
 */
/**
 * @author Davio
 *
 */
@SuppressWarnings("serial")
public class TamaFrame extends javax.swing.JFrame implements ActionListener{
	
	private TamaJPanel panel;
	private Tamagoshi tamagoshi;
	private JPanel buttons;
	private JButton nourrir;
	private JButton jouer;
	private TamaGameFrame game;
	private JTextArea dialogue;
	
	/**construsteut pour la TamaFrame
	 * @param game the instance of the game the frame is in
	 */
	public TamaFrame(TamaGameFrame game) {
		panel = new TamaJPanel();
		
		tamagoshi = null;
		dialogue = new JTextArea();
		
		buttons = new JPanel(new FlowLayout());
		JPanel textArea = new JPanel(new FlowLayout());
		
		nourrir = new JButton("nourrir");
		jouer = new JButton("jouer");
		
		nourrir.addActionListener(this);
		jouer.addActionListener(this);

		buttons.add(nourrir);
		buttons.add(jouer);
		
		
		textArea.add(dialogue);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(BorderLayout.SOUTH,buttons);
		getContentPane().add(BorderLayout.CENTER,panel);
		getContentPane().add(BorderLayout.NORTH,textArea);
		
		this.game = game;
		
	}
	
	
	
	/**fonction pemettant d'interagir avec les bouttons
	 * @param buttonName name of the button you want to interact with
	 * @param state the state you want to put the button on
	 */
	public void buttonInteraction(String buttonName, boolean state) {
		if(tamagoshi.isAlive()) {
			if(buttonName.equals(nourrir.getText())) {
				nourrir.setEnabled(state);
			}
			if(buttonName.equals(jouer.getText())) {
				jouer.setEnabled(state);
			}
		}else {
			nourrir.setEnabled(false);
			jouer.setEnabled(false);
		}
	}
	
	/**
	 * remplace dans la zone de texte le message du tamagoshi et dis que le tamagoshi est mort
	 */
	public void displayDeath() {
		dialogue.setText("je suis mort");
		repaint();
		
	}
	
	/**
	 *permet d'acceder a la fonction clique de la TamaGame et effectuer les action sur le tamagoshi
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		game.cliquer(e.getActionCommand());
		if(e.getSource() == nourrir) {
			tamagoshi.mange();
		}
		if(e.getSource() == jouer) {
			tamagoshi.jouer();
		}
	}
	
	/**
	 * permet d'afficher l'etat du tamagoshi
	 */
	public void talk() {
		tamagoshi.parle();
		dialogue.setText(tamagoshi.getState());
		repaint();
	}
	
	/**
	 * @return the tamagoshi of the frame
	 */
	public Tamagoshi getTamagoshi() {
		return this.tamagoshi;
	}
	
	/**
	 * @param t the tamagoshi that will be set to the frame
	 */
	public void setTamagoshi(Tamagoshi t) {
		this.tamagoshi = t;
	}
	
//	public ArrayList<JButton> getButtons() {
//		return this.buttonsList;
//	}
	
	/**
	 * @return if the tamagoshi inside the frame is alive or not
	 */
	public boolean isAlive() {
		return tamagoshi.isAlive();
	}
	
	/**
	 * @return the state of both buttons
	 */
	public boolean getButtonsState() {
		return nourrir.isEnabled() && jouer.isEnabled();
	}
	
	/**
	 * Methode retournant les information du Tamagoshi inside the frame
	 * @return the readable information of the Tamagoshi inside the frame
	 */
	@Override
	public String toString() {
		return tamagoshi.toString();
	}
	
	/**
	 * allows to display the frame
	 */
	public void initi() {
		this.setTitle("Tamagoshi");
		this.setTitle(tamagoshi.getName());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLocationRelativeTo(null);
		
		this.setLocation(0,0);
		
		
		this.setSize(400, 400);
		this.setVisible(true);
		
		this.requestFocus();
	}	
}
