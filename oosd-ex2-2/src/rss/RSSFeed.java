package rss;

import java.net.URL;
import java.util.Vector;

public class RSSFeed {

	
	private Vector<Channel> m_channels;
	
	private String _name;
	
	private URL feedAddress;

	/**
	 * creates a new RSSFeed
	 */
	public RSSFeed(String name){
		
		this.setName(name);
		this.m_channels = new Vector<Channel>();
	}
	
	/**
	 * returns the feed's channels
	 * @return the feed's channels
	 */
	public Vector<Channel> getChannels(){
		
		return this.m_channels;
	}

	public void setName(String _name) {
		
		this._name = _name;
	}

	public String getName() {
		
		return _name;
	}
	
	public void setAddress(URL address){
		feedAddress = address;
	}
	
	public URL getAddress(){
		return feedAddress;
	}
	
	public boolean equals(Object other){
		return this.m_channels.get(0).getLink().equals((((RSSFeed) other).getChannels().get(0)).getLink());
	}
	
	public String toString(){
		
		return getName();
	}
}
