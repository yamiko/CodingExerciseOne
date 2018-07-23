package org.feed.twitter.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.feed.twitter.business.MessageList;
import org.feed.twitter.business.UserList;

/**
 * This is the driver class for printing twitter-like feeds to console
 * 
 * @author Yamiko Joseph Msosa
 *
 */
public class TwitterFeedOutput {

	/**
	 * Properties of the class
	 */
	private UserList userList;
	private MessageList messageList;

	/**
	 * Constructor that creates a TwitterFeederOutput object using a users text file
	 * name and a messages text file name
	 * 
	 * @param usersTextFile
	 *            The users file name to be used when constructing a
	 *            TwitterFeedOutput object.
	 * @param messagesTextFile
	 *            The messages file name to be used when constructing a
	 *            TwitterFeedOutput object.
	 */
	public TwitterFeedOutput(String usersTextFile, String messagesTextFile) {
		this.userList = new UserList();
		userList.loadUsersFromTextFile(usersTextFile);
		this.messageList = new MessageList();
		messageList.setUserList(userList);
		messageList.loadMessagesFromTextFile(messagesTextFile);
	}

	/**
	 * A method that prints messages from the application's messages list
	 * 
	 */
	public void printMessages() {
		Map<String, List<String>> users = userList.getUsers();

		Set<Entry<String, List<String>>> set = users.entrySet();
		Iterator<Entry<String, List<String>>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, List<String>> mapEntry = (Map.Entry<String, List<String>>) iterator.next();
			String user = mapEntry.getKey();
			System.out.println(user);
			List<String> messages = messageList.getMessages(user);

			if (messages != null) {
				for (String message : messages) {
					System.out.println(message);
				}
			}
		}

	}

	/**
	 * Driver method for the class
	 */
	public static void main(String[] args) {
		TwitterFeedOutput feedOutput;
		// Run from command-line arguments if provided, otherwise use default file names
		if (args.length < 2) {
			feedOutput = new TwitterFeedOutput("user.txt", "tweet.txt");
		} else {
			feedOutput = new TwitterFeedOutput(args[0], args[1]);
		}
		feedOutput.printMessages();
	}

}
