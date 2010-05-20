package plugin.itemsTable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import rss.Item;
import rss.RSSFeed;


public class ItemsTablePluginModel implements TableModel {

	private final String[] _columnNames = {"Title", "Author", "Categories"};
    
	private Vector< Vector<Item> > _data;
    
	private Collection<TableModelListener> _listeners;
	
	public ItemsTablePluginModel(){
		
		this._data = new Vector< Vector<Item> >();
		
		this._data.add( new Vector<Item>() );
		this._data.add( new Vector<Item>() );
		this._data.add( new Vector<Item>() );
		
		setListeners( new LinkedList<TableModelListener>() );
	}

	public int getRowCount() {

		return this._data.get(0).size();
	}

	public Class<?> getColumnClass(int columnIndex) {

		return Vector.class;
	}

	public String getColumnName(int columnIndex) {

		return this._columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return true;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		this._data.get( columnIndex ).set( rowIndex, (Item)aValue );
		
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {

		return this._data.get( columnIndex ).get( rowIndex );
	}


	
//-------------------| Listeners |----------------------------------------
	
	public void setListeners(Collection<TableModelListener> listeners) {
		
		this._listeners = listeners;
	}

	public Collection<TableModelListener> getListeners() {
		
		return _listeners;
	}
	
	public void addTableModelListener(TableModelListener l) {
		
		getListeners().add(l);
	}

	public void removeTableModelListener(TableModelListener l) {

		getListeners().remove(l);
	}

	
	
//-------------| Columns |------------------
	
	public String[] getColumnNames() { return _columnNames; }

	public int getColumnCount() { return getColumnNames().length; }




//-------------| New Feed |------------------
	
	public void setFeed(RSSFeed feed) {
			
			Vector<Item> items = feed.getChannels().get(0).getItems();
			
			this._data.set(0, items);
			this._data.set(1, items);
			this._data.set(2, items);
			
			TableModelEvent tEvt = new TableModelEvent(this);
			
			for (TableModelListener tListener : getListeners()){
			
				tListener.tableChanged( tEvt );
			}
	}
}
