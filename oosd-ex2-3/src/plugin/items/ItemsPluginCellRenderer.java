package plugin.items;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import rss.Item;

public class ItemsPluginCellRenderer extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Color _ALT = new Color(220, 220, 220);
	boolean[] visited = new boolean[100];
	
	    
	public Component getListCellRendererComponent(JList list, Object value,
			int index,boolean isSelected, boolean cellHasFocus) {
		
		
		super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
		
		
		Item i = (Item) value;
		
        if(visited[index] == false ) setFont(new Font("Serif",Font.BOLD,12));
        
        if(i.getAuthor() != "") setText(i.getPubDate() + " by " + i.getAuthor() + " -     " + i.getTitle());
		
		else setText(i.getPubDate() + " -     " + i.getTitle());
        
        if (!isSelected && index % 2 == 0) setBackground(_ALT);
        
        if(isSelected) {
        	visited[index] = true;
        	setFont(new Font("Serif",Font.PLAIN,12));
        }
        
        
        return this;

}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
/*		
		
		Item i = (Item) value;
				
		if(i.getAuthor() != "") setText(i.getPubDate() + " by " + i.getAuthor() + " -     " + i.getTitle());
		
		else setText(i.getPubDate() + " -     " + i.getTitle());
			
		if (!isSelected && index % 2 == 0) setBackground(_ALT);

		return this;
	}
	*/
}
