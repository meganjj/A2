public class LastUpdateTimeVisitor implements Visitor{

	String latestUser = "";
	long latestUpdateTime = 0;

	@Override
	public void visitUser(User user) {
		if (user.getLastUpdate() > latestUpdateTime) {
			latestUpdateTime = user.getLastUpdate();
			latestUser = user.getId();
		}
	}

	@Override
	public void visitGroup(UserGroup group) {
	}

	public String getLatestUser() {
		return latestUser;
	}
	
}
