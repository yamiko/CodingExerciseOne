package org.feed.twitter.business;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserListTest {

	private UserList userList;

	@Before
	public void setUp() throws Exception {
		this.userList = new UserList();
		userList.loadUsersFromTextFile("user.txt");
	}

	@Test
	public void testLoadUsersFromTextFile() {
		// Test that the user list only has three users in it
		assertEquals(userList.getUsers().size(), 3);
		assertNotEquals(userList.getUsers().size(), 7);
	}

	@Test
	public void testGetFollowers() {
		// Test that Alan is followed by only one person
		assertEquals(userList.getFollowers("Alan").size(), 1);
		assertNotEquals(userList.getFollowers("Alan").size(), 8);
	}

}
