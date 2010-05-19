package config.filter;

import rss.Item;

/**
 * this class represent filter by Title
 */
public class FilterByTitle extends Filter {

	/**
	 * ctor
	 * 
	 * @param _name the filter name
	 * @param _arg the argument
	 */
	public FilterByTitle(String _name, String _arg){

		super(_name, _arg);
	}

	/**
	 * this method is overridden.
	 * it implements filter by title
	 * 
	 * @param item the item that we want to filter
	 * @return true if the item passes the filter and false otherwise
	 */
	public boolean filterByType(Item item) {

		String title = item.getTitle();
		return title.contains(this.arg);
	}



}
