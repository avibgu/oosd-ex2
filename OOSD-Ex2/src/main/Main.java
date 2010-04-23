
package main;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		
		JFrame tFrame = new JFrame("Feeds Reader");
		tFrame.setContentPane(new gui.Gui());
		tFrame.pack();
		tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tFrame.setVisible(true);
	}
}