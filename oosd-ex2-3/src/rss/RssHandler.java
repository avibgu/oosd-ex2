package rss;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import config.Feed;
import exception.AbortException;

import java.util.Stack;
import java.util.Vector;


public class RssHandler extends DefaultHandler {

	private Stack<String> m_stack;
	private StringBuffer m_sb;
	private RSSFeed m_feed;
	private Feed m_configFeed;
	private boolean m_readingchannelspecs;

	/**
	 * creates a new RssHandler
	 */
	public RssHandler(){

		this.m_stack = new Stack<String>();
		this.m_sb = new StringBuffer();
		this.m_feed = new RSSFeed("rss_feed_temp_name__by_rss_handler");
		this.m_configFeed = null;
		this.m_readingchannelspecs = false;
	}

	/**
	 * returns the Rss feed being modified
	 * @return the Rss feed being modified
	 */
	public RSSFeed getRssFeed() throws AbortException{
		this.m_feed.setAddress(this.m_configFeed.getAddress());
		if(this.m_feed.getChannels().size() == 0){
			throw new AbortException();
		}
		return this.m_feed;
	}

	/**
	 * this method invoked when an element starts
	 *
	 * @param uri the uri name
	 * @param lName some other name
	 * @param qName the name that we use
	 * @param attribs the attributes
	 */
    public void startElement( String uri, String lName, String qName, Attributes attribs ) {

    	this.m_stack.push(qName);

    	if(qName.equals("channel")){
    		this.m_readingchannelspecs = true;
    		Channel channel = new Channel();
    		this.m_feed.getChannels().add(channel);
    		this.m_sb = new StringBuffer();

    	}

    	if(qName.equals("item")){
    		Item item = new Item();
    		Channel channel = this.m_feed.getChannels().lastElement();
    		channel.getItems().add(item);
    		this.m_readingchannelspecs = false;
			this.m_sb = new StringBuffer();
    	}
		this.m_sb = new StringBuffer();

    }

    /**
     * this method reads the text inside the body of an element
     *
     * @param ch the characters array
	 * @param start the start index
	 * @param len the length of the array
     */
    public void characters(char[] ch,int start,int len){

    	this.m_sb.append( ch, start, len );
    }

	/**
	 * this method invoked when an element ends
	 *
	 * @param uri the uri name
	 * @param lName some other name
	 * @param qName the name that we use
	 */
    public void endElement( String uri, String lName, String qName ) {


//parsing the title, link and description of the channel

    	if(qName.equals("title") && this.m_readingchannelspecs){
    		Channel channel = this.m_feed.getChannels().lastElement();
    		if(channel != null){
    			channel.setTitle(this.m_sb.toString().trim());
    			this.m_feed.setName(this.m_sb.toString().trim());
    			this.m_sb = new StringBuffer();
    		}

    	}

    	if(qName.equals("link") && this.m_readingchannelspecs){
    		Channel channel = this.m_feed.getChannels().lastElement();
			if(channel != null){
				String link = this.m_sb.toString();
				channel.setLink(link);
			}

			this.m_sb = new StringBuffer();

    	}

    	if(qName.equals("description") && this.m_readingchannelspecs){
    		Channel channel = this.m_feed.getChannels().lastElement();
    		if(channel != null){
    			channel.setDescription(this.m_sb.toString().trim());
    			this.m_sb = new StringBuffer();
    		}

    	}
    	
    	if(qName.equals("pubDate") && this.m_readingchannelspecs){
    		Channel channel = this.m_feed.getChannels().lastElement();
    		if(channel != null){
    			channel.setPubDate(this.m_sb.toString().trim());
    			this.m_sb = new StringBuffer();
    		}

    	}

//parsing the title, link and description of the item
    	if(qName.equals("title") && !this.m_readingchannelspecs){
    		Item item = this.m_feed.getChannels().lastElement().getItems().lastElement();
			if (item != null){
				item.setTitle(this.m_sb.toString().trim());
			}
			this.m_sb = new StringBuffer();

    	}

    	if(qName.equals("link")&& !this.m_readingchannelspecs){
    		Item item = this.m_feed.getChannels().lastElement().getItems().lastElement();
    		if (item != null){
	    		String link = this.m_sb.toString();
				item.setLink(link);
    		}
    		this.m_sb = new StringBuffer();

    	}
    	
    	if(qName.equals("pubDate")&& !this.m_readingchannelspecs){
    		Item item = this.m_feed.getChannels().lastElement().getItems().lastElement();
    		if (item != null){
	    		String pubDate = this.m_sb.toString();
				item.setPubDate(pubDate);
    		}
    		this.m_sb = new StringBuffer();

    	}

    	if(qName.equals("description") && !this.m_readingchannelspecs){
    		Item item = this.m_feed.getChannels().lastElement().getItems().lastElement();
    		if (item != null){
	    		item.setDescription(this.m_sb.toString().trim());
    		}
    		this.m_sb = new StringBuffer();

    	}

    	if(qName.equals("guid") && !this.m_readingchannelspecs){
    		Item item = this.m_feed.getChannels().lastElement().getItems().lastElement();
    		if (item != null){
	    		item.setGuid(this.m_sb.toString());
    		}
    		this.m_sb = new StringBuffer();
    	}

    	if(qName.equals("author") && !this.m_readingchannelspecs){
    		Item item = this.m_feed.getChannels().lastElement().getItems().lastElement();
    		if (item != null){
	    		item.setAuthor(this.m_sb.toString().trim());
    		}
    		this.m_sb = new StringBuffer();
    	}

    	if(qName.equals("category") && !this.m_readingchannelspecs){
    		Item item = this.m_feed.getChannels().lastElement().getItems().lastElement();
    		if (item != null){
	    		Vector<String> categories = item.getCategories();
	    		if(categories != null){
	    			categories.add(this.m_sb.toString().trim());
	    		}
    		}
    		this.m_sb = new StringBuffer();
    	}

    	if(qName.equals("item")){
    		this.m_readingchannelspecs = true;
    	}

    	this.m_stack.pop();
    }
    
    public void setConfigFeed(Feed feed){
    	this.m_configFeed = feed;
    }
}
