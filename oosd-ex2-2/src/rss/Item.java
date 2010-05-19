package rss;

import java.util.Vector;

public class Item extends RSSElement{

	private String m_guid;
	private String m_author;
	private Vector<String> m_categories;

	/**
	 * creates a new Item
	 */
	public Item(){

		this.m_guid = "";
		this.m_author = "";
		this.m_categories = new Vector<String>();
	}
	
	public Item(String guid, String author, Vector<String> categories){

		this.m_guid = guid;
		this.m_author = author;
		this.m_categories = categories;
	}

	/**
	 * sets the item's global unique identifier
	 * @param guid the item's unique identifier
	 */
	public void setGuid(String guid){
		this.m_guid = guid;
	}

	/**
	 * sets the item's title
	 * @param title the title to be set
	 */
	public void setTitle(String title){
		this.m_title = title;
	}

	/**
	 * sets the item's link
	 * @param link the link to be set
	 */
	public void setLink(String link){
		this.m_link = link;
	}

	/**
	 * sets the item's description
	 * @param description the description to be set
	 */
	public void setDescription(String description){
		this.m_description = description;
	}

	/**
	 * sets the item's author
	 * @param author the author to be set
	 */
	public void setAuthor(String author){
		this.m_author = author;
	}
	
	/**
	 * sets the item's publish date
	 * @param pubDate the date to be set
	 */
	public void setPubDate(String pubDate){
		this.m_pubDate = pubDate;
	}

	/**
	 * returns the item's global unique identifier
	 * @return the item's global unique identifier
	 */
	public String getGuid() {
		return this.m_guid;
	}

	/**
	 * returns the item's author
	 * @return the item's author
	 */
	public String getAuthor() {
		return this.m_author;
	}

	/**
	 * returns the item's categories
	 * @return the item's categories
	 */
	public Vector<String> getCategories() {
		return this.m_categories;
	}
}
