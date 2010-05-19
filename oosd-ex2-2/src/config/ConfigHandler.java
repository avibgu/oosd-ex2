
package config;


import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import config.filter.Filter;
import config.filter.FilterByAuthor;
import config.filter.FilterByCategory;
import config.filter.FilterByContent;
import config.filter.FilterByTitle;
import config.format.Format;
import config.format.RssFormat;
import config.format.TextFormat;


import java.net.MalformedURLException;
import java.util.Stack;
import java.util.Vector;

/**
 * This class is the handler that we use to parse the configuration file
 */
public class ConfigHandler extends DefaultHandler{

	private Stack<String> stack;
	private StringBuffer sb;

	private Vector<Feed> feeds;
	private Vector<Filter> filters;
	private Vector<Format> formats;

	private String name;
	private String arg;

	/**
	 * default ctor
	 */
	public ConfigHandler(){

		this.stack = new Stack<String>();
		this.sb = new StringBuffer();

		this.feeds = new Vector<Feed>();
		this.filters = new Vector<Filter>();
		this.formats = new Vector<Format>();

		this.name = null;
		this.arg = null;
	}

	/**
	 * @return the feed
	 */
	public Vector<Feed> getFeeds(){

		return this.feeds;
	}

	/**
	 * @return the filters
	 */
	public Vector<Filter> getFilters(){

		return this.filters;
	}

	/**
	 * @return the format
	 */
	public Vector<Format> getFormats(){

		return this.formats;
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

    	this.stack.push(qName);
    	this.sb = new StringBuffer();
    }

    /**
     * this method reads the text inside the body of an element
     *
     * @param ch the characters array
	 * @param start the start index
	 * @param len the length of the array
     */
    public void characters(char[] ch,int start,int len){

    	this.sb.append( ch, start, len );
    }

	/**
	 * this method invoked when an element ends
	 *
	 * @param uri the uri name
	 * @param lName some other name
	 * @param qName the name that we use
	 */
    public void endElement( String uri, String lName, String qName ) {

    	if ( this.stack.peek().equals("name") ){

    		this.name = this.sb.toString().trim();
    		this.sb = new StringBuffer();
    	}

    	if ( this.stack.peek().equals("arg") ){

    		this.arg = this.sb.toString().trim();
    		this.sb = new StringBuffer();
    	}

    	if ( qName.equals("feed") ){

    		try {
				this.feeds.add( new Feed( this.sb.toString() ) );
			} catch (MalformedURLException e) {
				
				
			}
    		this.sb = new StringBuffer();
    	}

    	if ( qName.equals("format") ){

    		String formatType = this.sb.toString().trim();

    		if ( formatType.equals("rss") ){

    			this.formats.add( new RssFormat( formatType ) );
    		}
    		else if ( formatType.equals("text") ){

    			this.formats.add( new TextFormat( formatType ) );
    		}
    		else{
    			System.out.println("formats must match " + "\"rss\" or \"text\"" );
    		}
    		this.sb = new StringBuffer();
    	}

    	if ( qName.equals("filter") ){

    		Filter newFilter = null;

    		if (this.name.equals("title"))
    			newFilter = new FilterByTitle(this.name, this.arg);

    		else if (this.name.equals("category"))
    			newFilter = new FilterByCategory(this.name, this.arg);

    		else if (this.name.equals("content"))
    			newFilter = new FilterByContent(this.name, this.arg);

    		else if (this.name.equals("author"))
    			newFilter = new FilterByAuthor(this.name, this.arg);

    		else{
    			System.out.println("filters must match " + "\"title\", \"category\", \"content\" or \"author\"" );
    		}
    		if(newFilter != null) this.filters.add( newFilter );

    		this.name = null;
    		this.arg = null;
    		this.sb = new StringBuffer();
    	}

        this.stack.pop();
    }
}
