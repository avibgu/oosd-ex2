package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import rss.RssHandler;

import config.Feed;
import exception.FatalErrorException;

public class SimpleXMLReader {

	/**
	 *  the xml file
	 */
	private FileInputStream file;

	/**
	 *  the rss feed
	 */
	private Feed feed;

	/**
	 *  the handler we use
	 */
	private DefaultHandler handler;

	/**
	 * first ctor
	 *
	 * @param _file the file to parse from
	 * @param _handler the handler
	 * @throws FatalErrorException 
	 */
	public SimpleXMLReader(FileInputStream _file, DefaultHandler _handler){

		this.handler = _handler;
		this.file = _file;
		this.feed = null;
	}

	/**
	 * second ctor
	 *
	 * @param _feed the feed to parse from
	 * @param _handler the handler
	 * @throws FatalErrorException 
	 */
	public SimpleXMLReader(Feed _feed, DefaultHandler _handler){

		this.handler = _handler;
		((RssHandler) this.handler).setConfigFeed(_feed);
		this.file = null;
		this.feed = _feed;
	}

	/**
	 * this method reads information from XML file
	 * 
	 * @throws FatalErrorException if a fatal error occurred
	 * @throws MalformedURLException 
	 * @throws MalformedURLException if the url is invalid
	 */
	public void read() throws MalformedURLException {

		try{

			SAXParserFactory spf = SAXParserFactory.newInstance();

			SAXParser saxParser = spf.newSAXParser();

			// if there is a file - perform this
			if (this.file != null){

				XMLReader rdr;

				rdr = saxParser.getXMLReader();

				rdr.setContentHandler( this.handler );

				InputSource src1 = new InputSource( this.file );

				rdr.parse( src1 );

			}
			// if there is a remote rss feed - perform this
			else {

				InputStream src2 = this.feed.getStream();
				
				if (src2 == null) throw new MalformedURLException();
				
				saxParser.parse( src2 , this.handler );
			}
		}
		catch (MalformedURLException e) {
			throw new MalformedURLException();
		}
		
		catch (IOException e) {

			
		}
		catch (SAXException e) {

			System.out.println("if \"Fatal Error\" the program stopped reading from feed: ");
			System.out.println("if \"Error\" or \"Warning\" the program continued reading from feed ");
		}
		catch (ParserConfigurationException e) {

			System.out.println("can't read from - " + this.feed.getAddress().toString() );
		}
		
	}
}
