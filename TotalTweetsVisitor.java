public class TotalTweetsVisitor implements Visitor {

	private int totalTweets = 0;

	@Override
	public void visitUser(User user) {
		setTotalTweets(getTotalTweets() + user.getMyTweets().size());
	}

	@Override
	public void visitGroup(UserGroup group) {
	}

	public int getTotalTweets() {
		return totalTweets;
	}

	public void setTotalTweets(int totalMsgs) {
		totalTweets = totalMsgs; 
	}
	
}
