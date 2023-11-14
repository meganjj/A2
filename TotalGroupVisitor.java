public class TotalGroupVisitor implements Visitor{

	private int totalGroups = 0;

	@Override
	public void visitUser(User user) {
	}

	@Override
	public void visitGroup(UserGroup group) {
		setGroupTotal(getTotalGroups() + 1);
	}

	public int getTotalGroups() {
		return totalGroups;
	}

	public void setGroupTotal(int total) {
		totalGroups = total;
	}
	
}
