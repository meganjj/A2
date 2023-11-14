import java.util.ArrayList;

public class UserGroup implements Client {

	private String id;
	private ArrayList<Client> users;

	public UserGroup(String id) {
		this.id = id;
		users = new ArrayList<>();
	}

	@Override
	public String getId() {
		return id;
	}

	public ArrayList<Client> getUsers() {
		return users;
	}

	public void addUsers(Client newUser) {
		users.add(newUser);
	}

	public void accept(Visitor visitor) {
		visitor.visitGroup(this);
		for (Client accounts : users) {
			if (accounts instanceof User) {
				accounts.accept(visitor);
			}
			else if (accounts instanceof UserGroup) {
				accounts.accept(visitor);
			} 
		}
	}

	public User getUser(String userId) {
		for (Client accounts : users) {
			if (accounts instanceof User) {
				if (accounts.getId().equals(userId)) {
					return (User) accounts;
				}
			} else if (accounts instanceof UserGroup) {
				// iterate through all groups in group and check if the 
				//user id exist there
				if (((UserGroup)accounts).containsUser(userId)) {
					return ((UserGroup) accounts).getUser(userId);
				}
			}
		}
		return null;
	}

	public Boolean containsUser(String userId) {
		for (Client accounts : users) {
			if (accounts instanceof User) {
				if (accounts.getId().equals(userId)) {
					return true;
				} else if (accounts instanceof UserGroup) {
					if (((UserGroup)accounts).containsUser(userId)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public Boolean containsGroup(String groupId) {
		for (Client accounts : users) {
			if (accounts instanceof User) {
				continue;
			}
			else if (accounts instanceof UserGroup) {
				if (accounts.getId().equals(groupId)) {
					return true;
				}
				else {
					if(((UserGroup) accounts).containsGroup(groupId)){
                        			return true;
                    			}
				}
			}
		}
		return false;
	}

	public String toString() {
		return this.getId();
	}
}
