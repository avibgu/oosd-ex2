package gui;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NONE;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui extends JPanel {

	private static final long serialVersionUID = -7886147914342084854L;

	public Gui() {

		super(new GridBagLayout());

		super.setBackground(Color.decode("77777777"));

		GridBagConstraints tProto = new GridBagConstraints();
		tProto.insets = new Insets(5,2,5,2);

		GridBagConstraints tLabelConst = (GridBagConstraints)tProto.clone();
		tLabelConst.anchor = EAST;
		tLabelConst.fill = NONE;
		tLabelConst.weightx = 1.0;

		GridBagConstraints tTextConst = (GridBagConstraints)tProto.clone();
		tTextConst.anchor = EAST;
		tTextConst.fill = HORIZONTAL;
		tTextConst.weightx = 5.0;

		GridBagConstraints tButtonsConst = (GridBagConstraints)tProto.clone();
		tButtonsConst.anchor = CENTER;
		tButtonsConst.fill = HORIZONTAL;
		tButtonsConst.weightx = 5.0;

		GridBagConstraints tButtonConst = (GridBagConstraints)tProto.clone();
		tButtonConst.anchor = CENTER;
		tButtonConst.fill = NONE;
		tButtonConst.weightx = 0.0;

		
	    // (0-4,0) Buttons
		GridBagConstraints tConst = (GridBagConstraints)tButtonsConst.clone();
		tConst.gridx = 1; tConst.gridy = 0;
		tConst.gridwidth = 3;

	    Panel buttonGroup = new Panel();

	    buttonGroup.add( new JButton("Button1") );
	    buttonGroup.add( new JButton("Button2") );
	    buttonGroup.add( new JButton("Button3") );
	    buttonGroup.add( new JButton("Button4") );

	    add(buttonGroup, tConst);

	    
		// (0,1) "Add New Feed"
		tConst = (GridBagConstraints)tLabelConst.clone();
		tConst.gridx = 0; tConst.gridy = 1;
		JLabel text = new JLabel("Add New Feed:\t");
		text.setFont(new Font("Time New Roman",Font.BOLD,16));
		add(text, tConst);

		// (1,1) "URL"
		tConst =  (GridBagConstraints)tLabelConst.clone();
		tConst.gridx = 1; tConst.gridy = 1;
		text = new JLabel("URL");
		text.setFont(new Font("Time New Roman",Font.ITALIC,14));
		add(text, tConst);

		// (2,1) [textField]
		tConst = (GridBagConstraints)tTextConst.clone();
		tConst.gridx = 2; tConst.gridy = 1;
		add(new JTextField(20), tConst);

		// (3,1) "Refresh Rate"
		tConst = (GridBagConstraints)tLabelConst.clone();
		tConst.gridx = 3; tConst.gridy = 1;
		text = new JLabel("Refresh Rate");
		text.setFont(new Font("Time New Roman",Font.ITALIC,14));
		add(text, tConst);

		// (4,1) [textField]
		tConst = (GridBagConstraints)tTextConst.clone();
		tConst.gridx = 4; tConst.gridy = 1;
		add(new JTextField(10), tConst);

		// (5,1) "Add" button
		tConst = (GridBagConstraints)tButtonConst.clone();
		tConst.gridx = 5; tConst.gridy = 1;
		add(new JButton("Add"), tConst);



		GridBagConstraints tListConst = (GridBagConstraints)tProto.clone();
		tListConst.anchor = CENTER;
		tListConst.weightx = 1.0;
		tListConst.weighty = 1.0;
		tListConst.insets = new Insets(2, 2, 2, 2);


		// (0-1,3-9) List of feeds
		tConst = (GridBagConstraints)tListConst.clone();
		tConst.fill = BOTH;
		tConst.gridx = 0; tConst.gridy = 3;
		tConst.gridwidth = 2; tConst.gridheight = 8;

	    JList list = new JList(new Vector<String>());
	    add(list, tConst);
	    
	    
		// (0-1,10) "Remove" button
		tConst = (GridBagConstraints)tButtonConst.clone();
		tConst.gridx = 0; tConst.gridy = 11;
		tConst.gridwidth = 2; tConst.gridheight = 1;
		add(new JButton("Remove Feed"), tConst);


		// (2,3-6) List of titles
		tConst = (GridBagConstraints)tListConst.clone();
		tConst.fill = BOTH;
		tConst.gridx = 2; tConst.gridy = 3;
		tConst.gridwidth = 5; tConst.gridheight = 4;

		String[] listEnrties2 = {"enrty_1","enrty_2","enrty_3","enrty_4","enrty_5"};
	    list = new JList(listEnrties2);
	    add(list, tConst);


		// (2,7-10)Text Area
	    JTextArea textArea = new JTextArea(15, 30);

		textArea.setBorder(BorderFactory.createEtchedBorder());

		tConst = new GridBagConstraints();
		tConst.insets = new Insets(2, 2, 2, 2);
		tConst.fill = BOTH;
		tConst.anchor = CENTER;
		tConst.weighty = 1.0;
		tConst.gridx = 2; tConst.gridy = 7;
		tConst.gridwidth = 5; tConst.gridheight = 4;

		add(textArea, tConst);
	}
}
