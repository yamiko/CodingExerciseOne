package org.feed.twitter.data;

import java.util.List;

/**
 * This class is the <key, values> template for a row
 * 
 * @author Yamiko Joseph Msosa
 *
 */
public class FileRow {
	/**
	 * Private members of the class
	 */
	private String key;
	private List<String> values;

	/**
	 * Constructor that creates a FileRow object with key and values parameters
	 * 
	 * @param key
	 *            The key of the row object to be created
	 * @param values
	 *            The values that are to be stored using the key
	 */
	public FileRow(String key, List<String> values) {
		this.key = key;
		this.values = values;
	}

	/**
	 * Getter and setter methods of all the properties
	 */

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
}
