package frames;

import static java.awt.GridBagConstraints.BOTH;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ErrorFrame extends JFrame {
	
	
	private static final long serialVersionUID = 424213107587587259L;
	private JLabel error;
	GridBagConstraints c;
	
	public ErrorFrame(String label)  {
		super("Error");
		setLayout(new GridBagLayout());
		error = new JLabel(label);
		add(error);
		c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0; c.gridheight = 1; c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER; c.fill = BOTH;
		add(error, c);
		JButton tOK = new JButton("OK");
		c.gridx = 2; c.gridy = 2; c.gridwidth = 1; c.fill = GridBagConstraints.NONE;
		add(tOK, c);
		tOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

	public void addButton(String buttonName, int gridx, int gridy){
		JButton button = new JButton("buttonName");
		c.gridx = gridx; c.gridy = gridy; c.gridwidth = 1;
		add(button, c);
	}
	
	public JButton getButton(int gridx, int gridy){
		return (JButton) getComponentAt(gridx, gridy);
	}

}
