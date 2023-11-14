public class TotalUserVisitor implements Visitor{

	private int totalUsers = 0;

	@Override
	public void visitUser(User user) {
		setUserTotal(getUserTotal() + 1);
	}

	@Override
	public void visitGroup(UserGroup group) {
	}

	public int getUserTotal() {
		return totalUsers;
	}

	public void setUserTotal(int total) {
		totalUsers = total;
	}
	
}
