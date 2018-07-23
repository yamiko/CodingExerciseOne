package org.feed.twitter.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader {

	/**
	 * Properties of the class
	 */
	private String fileName; // Filename that has to be parsed
	private String keyValueDelimiter; // Delimiter to be used for separating rows in key,value pairs
	private String valuesDelimiter; // Delimiter to be used for separating the value element into a list

	/**
	 * Constructor that creates a TextFileReader object using a file name, key value
	 * delimiter and row values delimiter.
	 * 
	 * @param fileName
	 *            The file name to be used when constructing a TextFileReader
	 *            object.
	 * @param keyValueDelimiter
	 *            The key, value delimiter to be used when constructing a TextFileReader
	 *            object.
	 * @param valuesDelimiter
	 *            The row, values delimiter to be used when constructing a TextFileReader
	 *            object.
	 */
	public TextFileReader(String fileName, String keyValueDelimiter, String valuesDelimiter) {
		this.fileName = fileName;
		this.keyValueDelimiter = keyValueDelimiter;
		this.valuesDelimiter = valuesDelimiter;
	}

	/**
	 * A method that reads a text file and returns a list of FileRow objects
	 * 
	 * @return The Map of <Key, List> objects.
	 */
	public List<FileRow> readTextFile() {
		List<FileRow> returnRows = new ArrayList<FileRow>();

		BufferedReader reader = null;

		try {
			// Reading the text file
			reader = new BufferedReader(new FileReader(fileName));

			String line = "";

			// Reading lines from text file
			while ((line = reader.readLine()) != null) {
				String[] readColumns = line.split(this.keyValueDelimiter);
				int counter = 0;
				String key = "";
				List<String> valueList = new ArrayList<String>();
				for (String readColumn : readColumns) {
					if (counter == 0) {
						key = readColumn.trim();
					} else {
						if (this.valuesDelimiter.equals("")) {
							valueList.add(readColumn.trim());
						} else {
							String[] readValues = readColumn.split(this.valuesDelimiter);
							for (String readValue : readValues) {
								valueList.add(readValue.trim());
							}
						}
					}
					counter++;
				}

				if (!key.trim().equals("")) {
					returnRows.add(new FileRow(key, valueList));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException ie) {
				System.out.println("Error occured while closing the BufferedReader");
				ie.printStackTrace();
			}
		}

		return returnRows;
	}

	/**
	 * 
	 * Getters and setters for the private members of the class
	 * 
	 */
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getKeyValueDelimiter() {
		return keyValueDelimiter;
	}

	public void setKeyValueDelimiter(String keyValueDelimiter) {
		this.keyValueDelimiter = keyValueDelimiter;
	}

	public String getValuesDelimiter() {
		return valuesDelimiter;
	}

	public void setValuesDelimiter(String valuesDelimiter) {
		this.valuesDelimiter = valuesDelimiter;
	}
}