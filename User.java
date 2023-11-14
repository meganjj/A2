import javax.swing.DefaultListModel;

public class User implements Client{

	private String id;
	private DefaultListModel<User> followings = new DefaultListModel<>();
	private DefaultListModel<String> myTweets = new DefaultListModel<>();
	private DefaultListModel<String> newsFeed = new DefaultListModel<>();

	public User(String id) {
		this.id = id;
		followings = new DefaultListModel<>();
		myTweets = new DefaultListModel<>();
		newsFeed = new DefaultListModel<>();
	}

	@Override
	public String getId() {
		return id;
	}

	public DefaultListModel<User> getFollowings() {
		return followings;
	}

	public DefaultListModel<String> getMyTweets() {
		return myTweets;
	}

	public DefaultListModel<String> getNewsFeed() {
		return newsFeed;
	}

	public void tweet(String tweet) {
		newsFeed.addElement(tweet);
	}

	public void accept(Visitor visitor) {
		visitor.visitUser(this);
	}
	 
	
}