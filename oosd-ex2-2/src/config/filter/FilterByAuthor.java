package config.filter;

import rss.Item;

/**
 * this class represent filter by Author name
 */
public class FilterByAuthor extends Filter {

	/**
	 * ctor
	 * 
	 * @param _name the filter name
	 * @param _arg the argument
	 */
	public FilterByAuthor(String _name, String _arg) {

		super(_name, _arg);
	}

	/**
	 * this method is overridden.
	 * it implements filter by Author name
	 * 
	 * @param item the item that we want to filter
	 * @return true if the item passes the filter and false otherwise
	 */
	public boolean filterByType(Item item) {
		
		String author = item.getAuthor();
		return author.equals(this.arg);
	}
}
