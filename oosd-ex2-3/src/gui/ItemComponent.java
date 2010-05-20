package gui;

import javax.swing.event.ListSelectionListener;

import rss.RSSFeed;

public interface ItemComponent {
	
	public Object getSelectedValue();
	
	public void setFeed(RSSFeed feed);

	public void clearSelection();

	public void setListener(ListSelectionListener listener);

	public void setVisible(boolean b);
}
