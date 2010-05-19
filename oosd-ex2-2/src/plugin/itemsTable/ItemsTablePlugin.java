package plugin.itemsTable;

import java.awt.Component;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import rss.RSSFeed;

public class ItemsTablePlugin {

	public Component make() {

		JTable tTable = new JTable( new ItemsTablePluginModel() );

		tTable.setDefaultRenderer( Vector.class, new ItemsTablePluginCellRenderer() );
		
//		tTable.getColumnModel().getSelectionModel().
//            addListSelectionListener(new ColumnListener());
		
		tTable.setFillsViewportHeight(true);
		
		tTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		
		tTable.setBorder( BorderFactory.createEtchedBorder() );

		return tTable;
	}
	
	public void setListener(JComponent comp, ListSelectionListener listener){
		
		((JTable)comp).getSelectionModel().addListSelectionListener( listener );
	}
	
	public void setFeed(JComponent comp, RSSFeed feed){
		
		((ItemsTablePluginModel)((JTable)comp).getModel()).setFeed( feed );
	}
	
	public void clearSelection( JComponent comp ){
		
		((JTable)comp).clearSelection();
	}
	
	public Object getSelectedValue( JComponent comp ){
		
		int row = ((JTable)comp).getSelectedRow();
		
		return ((JTable)comp).getModel().getValueAt(row, 0);
	}
}
