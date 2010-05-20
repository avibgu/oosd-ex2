package plugin.betterContent;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ContentPlugin {

	public Component make() {

		final JTextArea tText = new JTextArea();
		
		String[] fontStrings = { "Arial", " 10 sec", " 30 sec", " 1 min", " 10 min" };
		
		JComboBox tFont = new JComboBox( fontStrings );

		tFont.setSelectedIndex(0);

		JButton tColor = new JButton( "Color" );
		
		tColor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JColorChooser tcc = new JColorChooser( tText.getForeground() );				
				tcc.setVisible(true);
			}
		});

		BorderLayout tBorder = new BorderLayout();
		
		tBorder.addLayoutComponent( tFont, BorderLayout.NORTH );
		tBorder.addLayoutComponent( tColor, BorderLayout.CENTER );
		tBorder.addLayoutComponent( tText, BorderLayout.SOUTH );
		
		JPanel tPanel = new JPanel( tBorder );

		return tPanel;
	}
}
