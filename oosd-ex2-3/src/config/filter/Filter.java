package config.filter;

import java.util.Vector;

import config.ConfImpl;

import rss.Channel;
import rss.Item;
import rss.RSSFeed;

/**
 * This abstract class represent a feed' filter
 */
public abstract class Filter extends ConfImpl {

	/**
	 * the name of the filter
	 */
	protected String name;
	
	/**
	 * the argument that we filter by
	 */
	protected String arg;

	/**
	 * ctor
	 *
	 * @param _name the type of the filter
	 * @param _arg the argument
	 */
	public Filter(String _name, String _arg){

		this.name = _name;
		this.arg = _arg;
	}

	/**
	 * @return the type of the filter
	 */
	public String getName(){

		return this.name;
	}

	/**
	 * @return the argument
	 */
	public String getArg(){

		return this.arg;
	}

	/**
	 * this is a final method which determine the policy of the filtering process
	 * 
	 * @param rssFeeds the vector of feeds that we want to filter
	 * @return a vector of filtered feeds
	 */
	public final Vector<RSSFeed> filter (Vector<RSSFeed> rssFeeds){

		for (int i=0; i < rssFeeds.size(); i++){

			Vector <Channel> channels = rssFeeds.get(i).getChannels();

			for (int j=0; j < channels.size(); j++){

				Channel channel = channels.get(j);
				Vector <Item> items = channel.getItems();
				Vector <Item> newItems = new Vector<Item>( items.size() );

				for (int k=0; k < items.size(); k++){

					if (this.filterByType(items.get(k)))

						newItems.add(items.get(k));
				}

				channel.setItems(newItems);
			}
		}

		return rssFeeds;
	}

	/**
	 * this abstract method should be overridden by the derived classes
	 * every derived class should determine how to filer the given item
	 * 
	 * @param item the item that we want to filter
	 * @return true if the item passes the filter and false otherwise
	 */
	public abstract boolean filterByType(Item item);
}
