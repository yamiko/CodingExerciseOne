package org.feed.twitter.business;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MessageListTest {
	private MessageList messageList;
	private UserList userList;

	@Before
	public void setUp() throws Exception {
		this.messageList = new MessageList();
		this.userList = new UserList();
		userList.loadUsersFromTextFile("user.txt");

		messageList.setUserList(userList);
		messageList.loadMessagesFromTextFile("tweet.txt");
	}

	@Test
	public void testLoadMessagesFromTextFile() {
		// Test that the message list only has two users with message feeds
		assertEquals(messageList.getMessages().size(), 2);
		assertNotEquals(messageList.getMessages().size(), 7);
	}

	@Test
	public void testGetMessages() {
		// Test that we are able to get messages from Alan correctly
		assertEquals(messageList.getMessages("Alan").get(0),
				"@Alan:If you have a procedure with 10 parameters, you probably missed some.");
		assertNotEquals(messageList.getMessages("Alan").get(1), "Not valid");
	}
}
