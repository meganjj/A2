public interface Subject {

	public void follow(User follower);
	public void unregisterObserver(User follower);
	public void notifyObservers(String tweet);
	
} 
