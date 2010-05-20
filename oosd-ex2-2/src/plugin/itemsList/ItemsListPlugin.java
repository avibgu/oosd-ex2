package plugin.itemsList;


import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

import rss.RSSFeed;


public class ItemsListPlugin {

	public Component make() {

		JList tList = new JList();
		
		tList.setModel( new ItemsListPluginModel() );
		tList.setCellRenderer( new ItemsListPluginCellRenderer() );
		
		return tList;
	}
	
	public void setListener(JComponent comp, ListSelectionListener listener){
		
		((JList)comp).addListSelectionListener( listener );
	}
	
	public void setFeed(JComponent comp, RSSFeed feed){
		
		((ItemsListPluginModel)((JList)comp).getModel()).setFeed( feed );
	}
	
	public void clearSelection( JComponent comp ){
		
		((JList)comp).clearSelection();
	}
	
	public Object getSelectedValue( JComponent comp ){
		
		return ((JList)comp).getSelectedValue();
	}
}
