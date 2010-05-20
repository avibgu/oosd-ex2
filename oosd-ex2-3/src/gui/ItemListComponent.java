package gui;

import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

import rss.RSSFeed;

public class ItemListComponent extends JList implements ItemComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3206812814239282104L;


	@Override
	public void setFeed(RSSFeed feed) {

		((ItemsListModel)getModel()).setFeed(feed);
	}


	@Override
	public void setListener(ListSelectionListener listener) {

		addListSelectionListener(listener);
	}
}
