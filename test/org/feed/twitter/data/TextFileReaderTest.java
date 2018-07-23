package org.feed.twitter.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextFileReaderTest {

	TextFileReader messagesTextFileReader;
	TextFileReader usersTextFileReader;

	@Before
	public void setUp() throws Exception {
		messagesTextFileReader = new TextFileReader("tweet.txt", ">", "");
		usersTextFileReader = new TextFileReader("user.txt", "follows", ",");
	}

	@Test
	public void testTextFileReader() {
		// Check that constructor can construct with one or both delimiters, in addition
		// to the file name
		assertEquals(messagesTextFileReader.readTextFile().size(), 3);
		assertEquals(usersTextFileReader.readTextFile().size(), 3);
		assertNotEquals(usersTextFileReader.readTextFile().size(), 4);
	}

	@Test
	public void testReadTextFile() {
		// Check that the values for each row is read in correctly i.e. Users first row
		// from user text file with one value item
		assertEquals(usersTextFileReader.readTextFile().get(0).getValues().size(), 1);
		assertNotEquals(usersTextFileReader.readTextFile().get(0).getValues().size(), 4);
	}

}
