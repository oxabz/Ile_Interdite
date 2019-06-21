package Project.views;

import javax.swing.*;

import Project.views.Elements.EGameOver;
import Project.util.Message;
import Project.util.Observe;
import Project.util.Observeur;

public class VueGameOver extends Observe {

	/**
	 * ATTRIBUTES
	 */
	//
	private JFrame window;

	/**
	 * CONSTRUCTOR
	 */
	public VueGameOver() {
		this.window = new JFrame("Game over");
		this.window.setSize(Vue.getWINDOW_SIZE_X(), Vue.getWINDOW_SIZE_Y());

		this.window.add(new EGameOver(this));
		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * GETTERS/SETTERS
	 */
	//

	/**
	 * METHODS
	 */
	public void cacher() {
		this.window.setVisible(false);
	}

	public static void main(String args[]) {
		VueGameOver gameOver = new VueGameOver();
	}

}