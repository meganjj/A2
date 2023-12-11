import javax.swing.DefaultListModel;

public class User implements Client, Follower, Subject {

	private String id;
	private long creationTime;
	private long lastUpdateTime;
	private DefaultListModel<User> followings = new DefaultListModel<>();
	private DefaultListModel<String> myTweets = new DefaultListModel<>();
	private DefaultListModel<String> newsFeed = new DefaultListModel<>();

	public User(String id) {
		this.id = id;
		this.creationTime = System.currentTimeMillis();
		followings = new DefaultListModel<>();
		myTweets = new DefaultListModel<>();
		newsFeed = new DefaultListModel<>();
	}

	@Override
	public String getId() {
		return id;
	}

	public Long getCreationTime() {
		return creationTime;
	}

	public Long getLastUpdate() {
		return lastUpdateTime;
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
		lastUpdateTime = System.currentTimeMillis();
	}

	public void accept(Visitor visitor) {
		visitor.visitUser(this);
	}

	@Override
	public void follow(User follower) {
		followings.addElement(follower);
	}

	@Override
	public void unregisterObserver(User follower) {
		followings.removeElement(followings.indexOf(follower));
	}

	@Override
	public void notifyObservers(String tweet) {
		for (int i = 0; i < followings.size(); i++)
        	{
            		User follower = followings.get(i);
			System.out.println(follower);
           	 	follower.update(tweet);
		}
		lastUpdateTime = System.currentTimeMillis();
	}

	@Override
	public void update(String tweet) {
		newsFeed.addElement(tweet);
	}

	public String toString() {
		return this.getId();
	}
	 
	
}