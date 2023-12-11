import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserView {
	
	static void openUserView(JFrame frame, User user, UserGroup root) {
		JDialog userViewArea = new JDialog(frame, user.getId(), false);
		userViewArea.setSize(350, 300);
		userViewArea.setLayout(new GridBagLayout());
		userViewArea.setBackground(Color.CYAN);
		userViewArea.setForeground(Color.CYAN);
		userViewArea.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JTextArea userId = new JTextArea("User Id");
		JButton follow = new JButton("Follow User");
		JTextArea tweet = new JTextArea("What's happening?",5, 20);
		JButton post = new JButton("Post Tweet");
		JList followerList = new JList<>(user.getFollowings());
		JList newsFeed = new JList<>(user.getNewsFeed());
		JLabel creationTime = new JLabel("Created: " + user.getTimeStamp(user.getCreationTime()));
		

		follow.addActionListener(ae -> {
			String id = userId.getText();
			User userToFollow = root.getUser(id);
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



		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; 
		c.gridx = 0;		//1st column
		c.gridy = 0;		//1st row
		c.weightx = 0.5;
		c.weighty = 1;
		userViewArea.add(userId, c);

		c.gridx = 1;
		c.gridy = 0;
		userViewArea.add(follow, c);

		c.ipady = 30;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		c.gridwidth = 2;
		userViewArea.add(followerList, c);

		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.5;
		c.ipady = 30;
		userViewArea.add(tweet, c);

		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0.5;
		userViewArea.add(post, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		userViewArea.add(newsFeed, c);

		c.gridx = 0;
		c.gridy = 4;
		userViewArea.add(creationTime, c);

		userViewArea.setLocationRelativeTo(frame);
		userViewArea.setVisible(true);
	}
}
