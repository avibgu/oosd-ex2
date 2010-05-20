package config.format;

import java.util.Vector;

import rss.Item;

/**
 * this class represents a RSS format type
 */
public class RssFormat extends Format {

	/**
	 * ctor 
	 * 
	 * @param _format the format type
	 */
	public RssFormat(String _format) {

		super(_format);
	}

	/**
	 * this method is overridden.
	 * it implements an action to perform before the convert process
	 * 
	 * @return a String object to add to the general output String
	 */
	protected String before() {

		String ans = "";

		ans += "<rss>\n";
		ans += "\t<channel>\n";
		ans += "\t\t<title></title>\n";
		ans += "\t\t<link></link>\n";
		ans += "\t\t<description></description>\n";

		return ans;
	}

	/**
	 * this method is overridden.
	 * it implements an action to perform for each converted item
	 * 
	 * @return a String object to add to the general output String
	 */
	protected String forEachItem(Item item) {

		String ans = "";

		ans += "\t\t<item>\n";

		ans += "\t\t\t<guid>" + item.getGuid() + "</guid>\n";
		ans += "\t\t\t<title>" + item.getTitle() + "</title>\n";
		ans += "\t\t\t<link>" + item.getLink() + "</link>\n";
		ans += "\t\t\t<description>" + item.getDescription() + "</description>\n";
		ans += "\t\t\t<author>" + item.getAuthor() + "</author>\n";

		Vector<String> categories = item.getCategories();

		if (categories != null){

			for (int i=0; i < categories.size(); i++){

				ans += "\t\t\t<category>" + categories.get(i) + "</category>\n";
			}
		}

		ans += "\t\t</item>\n";

		return ans;
	}

	/**
	 * this method is overridden.
	 * it implements an action to perform after the convert process
	 * 
	 * @return a String object to add to the general output String
	 */
	protected String after() {

		String ans = "";

		ans += "\t</channel>\n";
		ans += "</rss>\n";

		return ans;
	}
}
