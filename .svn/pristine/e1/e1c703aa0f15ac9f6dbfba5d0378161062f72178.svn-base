package org.powerstone.sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UserDAO {
	private List users;

	public UserDAO() {
		users = new ArrayList();
		int i = 0;
		for (; i < 108; i++) {
			User user = new User();
			user.setId(new Integer(i + 1).toString());
			user.setFirstName("a_firstname" + user.getId());
			user.setLastName("a_lastname" + user.getId());
			users.add(user);
		}
		for (; i < 108 + 66; i++) {
			User user = new User();
			user.setId(new Integer(i + 1).toString());
			user.setFirstName("b_firstname" + user.getId());
			user.setLastName("b_lastname" + user.getId());
			users.add(user);
		}
	}

	public List findUsers(Map criteria, int beginNo, int pageSize) {
		if(beginNo<0){
			return new ArrayList();
		}
		List allResult = findUsers(criteria);
		List result = new ArrayList();
		for (int i = beginNo; i < beginNo+pageSize; i++) {
			if (i < allResult.size()) {
				result.add(allResult.get(i));
			} else {
				break;
			}
		}
		return result;
	}

	public int countUsers(Map criteria) {
		return findUsers(criteria).size();
	}

	private List findUsers(Map criteria) {
		String firstName = (String) criteria.get("firstName");
		List result = new ArrayList();
		for (Iterator it = users.iterator(); it.hasNext();) {
			User user = (User) it.next();
			if (firstName == null
					|| user.getFirstName().indexOf(firstName) >= 0) {
				result.add(user);
			}
		}
		return result;
	}
}
