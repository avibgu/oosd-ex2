package plugin.itemsTable;

import java.awt.Component;
import java.util.Vector;

import javax.swing.ListSelectionModel;

public class ItemsTablePlugin {

	public Component make() {

		ItemTableComponent tTable = new ItemTableComponent( new ItemsTablePluginModel() );

		tTable.setDefaultRenderer( Vector.class, new ItemsTablePluginCellRenderer() );
		
//		tTable.getColumnModel().getSelectionModel().
//            addListSelectionListener(new ColumnListener());
		
		tTable.setFillsViewportHeight(true);
		
		tTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

		return ( tTable );
	}
}
