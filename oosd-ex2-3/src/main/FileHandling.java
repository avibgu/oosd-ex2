package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import exception.AbortException;
import exception.GiveUpException;

/**
 * this class represent actions that we can do with files
 */
public class FileHandling {

	/**
	 * ctor
	 */
	public FileHandling(){}
	
	/**
	 * this method open a file for reading
	 * 
	 * @param tFilename the file that we want to open
	 * @return the opened File object
	 * 
	 * @throws GiveUpException when we give up with this process
	 */
	public FileInputStream openFileForReading(String tFilename) throws GiveUpException {
		
		FileInputStream file = null;
		
		while (true) {

			try {
				
				if ( tFilename == null || tFilename.isEmpty() )

					tFilename = this.promptFile();

				try {

					file = new FileInputStream( new File(tFilename) );

					return file;
				}
				catch (IOException e) {
	
					this.reportError();
					tFilename = null;
					continue;
				}
			}
			catch (AbortException e) {

				this.reportGiveUp();
				throw new GiveUpException();
			}
		}
	}

	/**
	 * this method ask the user for a valid file 
	 * 
	 * @return the new file name
	 * @throws AbortException when the user don't want to proceed
	 */
	private String promptFile() throws AbortException {

		System.out.println("Please insert file name including suffix (e.g " + "myfile.xml" + "):\n");

		BufferedReader tIn = new BufferedReader( new InputStreamReader(System.in));

		try {

			String tFilename = tIn.readLine();

			if (tFilename == null || tFilename.isEmpty())

					throw new AbortException();

			return tFilename;
		}
		catch (IOException e) {

			throw new AbortException();
		}
	}

	/**
	 * this method write a string to a file
	 * 
	 * @param tFilename the file that we want to open
	 * @param toWrite the string we want to write
	 * 
	 * @throws GiveUpException when we give up with this process
	 */
	public void writeToFile(String tFilename, String toWrite) throws GiveUpException {

		while (true) {

			try {

				if ( tFilename == null || tFilename.isEmpty() )

					tFilename = this.promptFile();

				try {

					PrintWriter tOut = new PrintWriter(
											new FileWriter(new File(tFilename)));

					tOut.print(toWrite);

					tOut.close();

					return;
				}
				catch (IOException e) {

					this.reportError();
					tFilename = null;
					continue;
				}
			}
			catch (AbortException e) {

				this.reportGiveUp();
				throw new GiveUpException();
			}
		}
	}

	/**
	 * this method represents the action to perform when an error encountered
	 */
	private void reportError(){

		System.out.println("an error occured while trying to open the file");
		System.out.println("Cause: the file was typed incorrectly or does not exist");
	}

	/**
	 * this method represents the action to perform when we give up
	 */
	private void reportGiveUp(){

		System.out.println("give up");
	}
}