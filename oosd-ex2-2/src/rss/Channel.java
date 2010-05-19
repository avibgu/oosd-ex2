package rss;

import java.util.Vector;

public class Channel extends RSSElement{

	private Vector<Item> m_items;

	/**
	 * creates a new Channel
	 */
	public Channel(){
		this.m_items = new Vector<Item>();

	}

	/**
	 * returns the channel's items
	 * @return the channel's items
	 */
	public Vector<Item>getItems(){
		return this.m_items;
	}

	/**
	 * sets the channel's title
	 * @param title the title to be set
	 */
	public void setTitle(String title){
		this.m_title = title;

	}

	/**
	 * sets the channel's link
	 * @param link the link to be set
	 */
	public void setLink(String link){
		this.m_link = link;

	}

	/**
	 * sets the channel's description
	 * @param description the description to be set
	 */
	public void setDescription(String description){
		this.m_description = description;

	}

	/**
	 * sets the channel's items by conducting a deep copy
	 * of the items parameter
	 * @param items the items to be copied
	 */
	public void setItems(Vector<Item> items){
		this.m_items.clear();
		for (int i=0; i < items.size(); i++){
			this.m_items.add(items.get(i));
		}
	}
	
	/**
	 * sets the channel's publish date 
	 * @param pubDate the date to be set
	 */
	public void setPubDate(String pubDate){
		this.m_pubDate = pubDate;
	}
}
