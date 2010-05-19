package plugin.itemsList;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import rss.Item;
import rss.RSSFeed;

public class ItemsListPluginModel implements ListModel{

	private RSSFeed _feed;
	
	private List<Item> _items;
	
	private Collection<ListDataListener> _listeners;
	
	public ItemsListPluginModel() {
		
		setListeners( new LinkedList<ListDataListener>() );
		
		List<Item> emptyList = Collections.emptyList();
		
		setItems( emptyList );
	}
	
	private void setListeners(LinkedList<ListDataListener> linkedList) {
		
		this._listeners = linkedList;
	}
	
	private void setItems(List<Item> emptyList) {
		
		this._items = emptyList;
	}

	public RSSFeed getFeed() {
		
		return this._feed;
	}

	public void setFeed(RSSFeed feed) {
		
		this._feed = feed;
		
		int tOldSize = getSize();
		
		setItems(feed.getChannels().get(0).getItems());
		
		int tMax = Math.max(tOldSize, getSize());
		
		ListDataEvent tEvt = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED,
													0,tMax);
		
		for (ListDataListener tListener : getListeners()){
		
			tListener.contentsChanged(tEvt);
		}
	}

	private Collection<ListDataListener> getListeners() {

		return this._listeners;
	}
	
	public int getSize() {
		
		return getItems().size();
	}

	private List<Item> getItems() {
		
		return this._items;
	}

	public Object getElementAt(int index) {
	
		return getItems().get(index);
	}
	
	public void addListDataListener(ListDataListener l) {
		
		getListeners().add(l);
	}

	public void removeListDataListener(ListDataListener l) {
		
		getListeners().remove(l);
	}
}
