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
	
}
