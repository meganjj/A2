import javax.swing.*;
import java.awt.*;

public class UserView {
	
	static void openUserView(JFrame frame, User user, UserGroup root) {
		JDialog userViewArea = new JDialog(frame, user.getId(), false);
		userViewArea.setSize(350, 300);
		userViewArea.setLayout(new FlowLayout());
		userViewArea.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel username = new JLabel(user.getId());
		JTextArea userId = new JTextArea("User Id");
		JButton follow = new JButton("Follow User");
		JTextArea tweet = new JTextArea("What's happening?",5, 20);
		JButton post = new JButton("Post Tweet");
		JList followerList = new JList<>(user.getFollowings());
		JList newsFeed = new JList<>(user.getNewsFeed());

		follow.addActionListener(ae -> {
			String id = userId.getText();
			//GetUser finds if a user exists
			//User userToFollow = 
			if (userToFollow == null) {
				JOptionPane.showMessageDialog(userViewArea, "User does not exist!");
			}
			else {
				user.follow(userToFollow);
			}
		});

		post.addActionListener(ae -> {
			String message = tweet.getText();
			user.tweet(message);
			user.notifyObservers(message);
		});

		userViewArea.add(username);
		userViewArea.add(userId);
		userViewArea.add(follow);
		userViewArea.add(tweet);
		userViewArea.add(post);
		userViewArea.add(newsFeed);
		userViewArea.add(followerList);

		userViewArea.setLocationRelativeTo(frame);
		userViewArea.setVisible(true);
	}
}
