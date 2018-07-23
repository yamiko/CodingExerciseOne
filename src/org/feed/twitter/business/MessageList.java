package org.feed.twitter.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.feed.twitter.data.FileRow;
import org.feed.twitter.data.TextFileReader;

/**
 * This class reads allows an application or service to read messages from a
 * text file into a Map of <user, messages list> object pairs
 * 
 * @author Yamiko Joseph Msosa
 *
 */
public class MessageList {

	/**
	 * Properties of the class
	 */
	private Map<String, List<String>> messages;
	private UserList userList;

	/**
	 * A method that loads messages from a well-formed text file
	 * 
	 * @param fileName
	 */
	public void loadMessagesFromTextFile(String fileName) {
		TextFileReader messagesTextFileReader = new TextFileReader(fileName, ">", "");
		List<FileRow> rawMessages = messagesTextFileReader.readTextFile();

		messages = new HashMap<String, List<String>>();

		Iterator<FileRow> iterator = rawMessages.iterator();
		while (iterator.hasNext()) {
			FileRow fileRow = iterator.next();
			String userKey = fileRow.getKey();

			String message = "";

			if (!fileRow.getValues().isEmpty()) {
				message = "@" + userKey + ":" + fileRow.getValues().get(0);

				addMessage(userKey, message);

				List<String> followers = userList.getFollowers(userKey);
				for (String follower : followers) {
					addMessage(follower, message);
				}
			}

		}

	}

	/**
	 * A method that adds a message to a messages list given a user that wrote the
	 * message and the message text
	 * 
	 * @param user
	 *            The user that originated the message
	 * @param message
	 *            The message text
	 */
	private void addMessage(String user, String message) {
		List<String> userMessages = messages.get(user);

		if (userMessages == null) {
			userMessages = new ArrayList<String>();
		}

		userMessages.add(message);
		messages.put(user, userMessages);
	}

	/**
	 * A method that get a list of all messages intended for a particular user
	 * 
	 * @param user
	 *            The user that is intended to receive the set of messages
	 * @return Returns a list of messages
	 */
	public List<String> getMessages(String user) {
		return messages.get(user);
	}

	/**
	 * A getter method for userList property
	 * 
	 * @return Returns the value of the userList property
	 */
	public UserList getUserList() {
		return userList;
	}

	/**
	 * A setter method for userList property
	 * 
	 * @param userList
	 *            The UserList object instance to set the property with
	 */
	public void setUserList(UserList userList) {
		this.userList = userList;
	}

	/**
	 * A getter method for messages property
	 * 
	 * @return Returns the instance value of the messages property
	 */
	public Map<String, List<String>> getMessages() {
		return messages;
	}

	/**
	 * A setter method for messages property
	 * 
	 * @param messages
	 *            The Messages object instance to set the property with
	 */
	public void setMessages(Map<String, List<String>> messages) {
		this.messages = messages;
	}
}
