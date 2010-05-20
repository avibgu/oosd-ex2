package plugin.itemsTable;

import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;

import rss.Item;
import rss.RSSFeed;

import gui.ItemComponent;


public class ItemTableComponent extends JTable implements ItemComponent {

	private static final long serialVersionUID = 7247067012118787580L;

	public ItemTableComponent(ItemsTablePluginModel itemsTablePluginModel) {

		super(itemsTablePluginModel);
	}

	public Object getSelectedValue() {

		int row = getSelectedRow();
		
		return (Item)getModel().getValueAt( row, 0 );
	}
	
	public void setListener( ListSelectionListener listener ){
		
		getSelectionModel().addListSelectionListener( listener );
	}

	public void setFeed(RSSFeed feed) {
		
		((ItemsTablePluginModel)getModel()).setFeed(feed);
	}
}
