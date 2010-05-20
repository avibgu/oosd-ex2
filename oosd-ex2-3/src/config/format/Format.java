package config.format;

import java.util.Vector;

import config.ConfImpl;

import rss.Channel;
import rss.Item;
import rss.RSSFeed;

/**
 * this abstract class represents the output type of the feeds
 */
public abstract class Format extends ConfImpl {

	/**
	 * the format of the output
	 */
	private String format;

	/**
	 * ctor
	 *
	 * @param _format the format of the output
	 */
	public Format(String _format){

		this.format = _format;
	}

	/**
	 * @return the format type
	 */
	public String getFormat(){

		return this.format;
	}

	/**
	 * this is a final method which determine the policy of the converting to
	 * one of the formats
	 * 
	 * @param rssFeeds the vector of feeds that we want to convert
	 * @return a String object of the output
	 */
	public final String convertToMyFormat(Vector<RSSFeed> rssFeeds){

		String ans = "";

		ans += this.before();

		for (RSSFeed feed: rssFeeds){

			Vector <Channel> channels = feed.getChannels();

			for (Channel channel: channels){

				Vector <Item> items = channel.getItems();

				for (Item item: items){

					ans += this.forEachItem(item);
				}
			}
		}

		ans += this.after();

		return ans;
	}

	/**
	 * this abstract method should be overridden by the derived classes
	 * every derived class should determine how to act before the convert process
	 * 
	 * @return a String object to add to the general output String
	 */
	protected abstract String before();

	/**
	 * this abstract method should be overridden by the derived classes
	 * every derived class should determine how to act for each converted item
	 * 
	 * @return a String object to add to the general output String
	 */
	protected abstract String forEachItem(Item item);

	/**
	 * this abstract method should be overridden by the derived classes
	 * every derived class should determine how to act after the convert process
	 * 
	 * @return a String object to add to the general output String
	 */
	protected abstract String after();
}
