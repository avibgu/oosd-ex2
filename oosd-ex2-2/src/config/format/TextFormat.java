package config.format;

import rss.Item;

/**
 * this class represents a Text format type
 */
public class TextFormat extends Format {

	/**
	 * ctor 
	 * 
	 * @param _format the format type
	 */
	public TextFormat(String _format) {

		super(_format);
	}

	/**
	 * this method is overridden.
	 * it implements an action to perform before the convert process
	 * 
	 * @return a String object to add to the general output String
	 */
	protected String before() {

		return "";
	}

	/**
	 * this method is overridden.
	 * it implements an action to perform for each converted item
	 * 
	 * @return a String object to add to the general output String
	 */
	protected String forEachItem(Item item) {

		String ans = "";

		ans += "* " + item.getTitle() + "\n";
		
		String description = item.getDescription();
		
		if (!description.isEmpty()){
			
			String[] splitted = description.split("\n");
			
			for (int i=0; i < splitted.length; i++)
				
				ans +=  splitted[i] + "\n";
		}
		
		ans += "\n";

		return ans;
	}

	/**
	 * this method is overridden.
	 * it implements an action to perform after the convert process
	 * 
	 * @return a String object to add to the general output String
	 */
	protected String after() {

		return "";
	}
}
