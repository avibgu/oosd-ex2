package plugin.defaultItems;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import rss.RSSFeed;

public class ItemsPlugin {

	public Component make() {

		JList tList = new JList();

		tList.setCellRenderer( new ItemsListCellRenderer() );
		tList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

		tList.setBorder( BorderFactory.createEtchedBorder() );
		
		return tList;
	}
	
	public void setListener(JComponent comp, ListSelectionListener listener){
		
		((JList)comp).addListSelectionListener( listener );
	}
	
	public void setFeed(JComponent comp, RSSFeed feed){
		
		((ItemsListModel)((JList)comp).getModel()).setFeed( feed );
	}
	
	public void clearSelection( JComponent comp ){
		
		((JList)comp).clearSelection();
	}
	
	public Object getSelectedValue( JComponent comp ){
		
		return ((JList)comp).getSelectedValue();
	}
}
