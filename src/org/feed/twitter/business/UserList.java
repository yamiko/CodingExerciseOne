package org.feed.twitter.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.feed.twitter.data.FileRow;
import org.feed.twitter.data.TextFileReader;

/**
 * This class reads allows an application or service to read user/follower
 * details from a text file into a Map of <user, followers list> object pairs
 * 
 * @author Yamiko Joseph Msosa
 *
 */
public class UserList {

	/**
	 * Private member of the class
	 */
	private Map<String, List<String>> users;

	/**
	 * A method that loads users from a well-formed text file
	 * 
	 * @param fileName
	 */
	public void loadUsersFromTextFile(String fileName) {
		TextFileReader usersTextFileReader = new TextFileReader(fileName, "follows", ",");
		List<FileRow> rawUsers = usersTextFileReader.readTextFile();

		users = new TreeMap<String, List<String>>();

		Iterator<FileRow> iterator = rawUsers.iterator();
		while (iterator.hasNext()) {
			FileRow fileRow = iterator.next();
			String userKey = fileRow.getKey();

			List<String> follows = fileRow.getValues();

			addUser(userKey);

			for (String follow : follows) {
				addFollower(follow, userKey);
			}
		}

	}

	/**
	 * A method that adds a user to a users list given a user key
	 * 
	 * @param user
	 *            The user that is to be added to the users list
	 */
	private void addUser(String user) {
		List<String> userFollowers = users.get(user);
		if (userFollowers == null) {
			userFollowers = new ArrayList<String>();
			users.put(user, userFollowers);
		}
	}

	/**
	 * A method that adds a follower to a followers list of a given a user
	 * 
	 * @param user
	 *            An existing user
	 * @param follower
	 *            The follower that is to be added to a followers list of the given
	 *            user
	 */
	private void addFollower(String user, String follower) {
		List<String> userFollowers = users.get(user);

		boolean found = false;
		if (userFollowers == null) {
			userFollowers = new ArrayList<String>();
		} else {
			for (String userFollower : userFollowers) {
				if (follower.equals(userFollower)) {
					found = true;
					break;
				}
			}
		}

		if (!found) {
			userFollowers.add(follower);
			users.put(user, userFollowers);
		}
	}

	/**
	 * A method that returns a list of all followers of a given user
	 * 
	 * @return Returns the list of users from the <user, followers list> map of
	 *         users
	 */
	public List<String> getFollowers(String user) {
		return users.get(user);
	}

	/**
	 * A getter method for users property
	 * 
	 * @return Returns the instance value of the users property
	 */
	public Map<String, List<String>> getUsers() {
		return users;
	}

	/**
	 * A setter method for users property
	 * 
	 * @param users
	 *            The Map<String, List<String>> object instance to set the property
	 *            with
	 */
	public void setUsers(Map<String, List<String>> users) {
		this.users = users;
	}

}
