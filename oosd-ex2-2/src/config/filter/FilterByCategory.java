package config.filter;

import java.util.Vector;

import rss.Item;

/**
 * this class represent filter by Category
 */
public class FilterByCategory extends Filter{

	/**
	 * ctor
	 * 
	 * @param _name the filter name
	 * @param _arg the argument
	 */
	public FilterByCategory(String _name, String _arg) {

		super(_name, _arg);
	}

	/**
	 * this method is overridden.
	 * it implements filter by Categories
	 * 
	 * @param item the item that we want to filter
	 * @return true if the item passes the filter and false otherwise
	 */
	public boolean filterByType(Item item) {
		
		Vector<String> categories = item.getCategories();
		
		for(String catergory : categories){
			
			if(catergory.equals(this.arg)) return true;
		}
		
		return false;
	}
}
