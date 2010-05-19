package plugin.itemsTable;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import rss.Item;

public class ItemsTablePluginCellRenderer extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 7318261549021180210L;
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);

		Item i = (Item) value;
		
		switch (column){
		
			case 0:
				
				setText( i.getTitle() );
				return this;
				
			case 1:
				
				setText( i.getAuthor() );
				return this;
				
			case 2:
				
				Vector<String> cats = i.getCategories();
				
				String text = "";
				
				for (String cat: cats) text += cat;
				
				setText( text );
				
				return this;

			default: setText( "" );
		}

		return this;
	}
}
