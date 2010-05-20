package config;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class holds information about a feed
 */
public class Feed extends ConfImpl {

	/**
	 * the feed address
	 */
	private URL feedAddress;

	/**
	 * ctor
	 *
	 * @param address the address of the feed
	 * @throws MalformedURLException 
	 */
	public Feed(String address) throws MalformedURLException{

		this.feedAddress = null;

		try {

			this.feedAddress = new URL(address);
		}
		catch (MalformedURLException e) {

			throw new MalformedURLException();
		}
	}

	public Feed(URL address) {
	
		if ( address != null ) this.feedAddress = address;
	}

	/**
	 * @return the address of the feed
	 */
	public URL getAddress(){

		return this.feedAddress;
	}

	/**
	 * @return the input stream of the feed
	 * @throws IOException if there is a problem to open a stream
	 */
	public InputStream getStream() throws IOException{

		if (this.feedAddress == null) return null;
		
		InputStream is = this.feedAddress.openStream();
		
		return is;
	}
}
