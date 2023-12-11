import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.*;

public class AdminPanel {

	private static AdminPanel instance = null;

	public AdminPanel() {
		JFrame frame = new JFrame("MiniTwitter");
		frame.setSize(800, 600);
		frame.setBackground(Color.CYAN);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(0, 2));

		//Add all components for admin panel
		JButton addUser = new JButton("Add User");
		JButton addGroup = new JButton("Add Group");
		JButton userView = new JButton("Open User View");
		JButton userTotal = new JButton("Show User Total");
		JButton groupTotal = new JButton("Show Group Total");
		JButton msgsTotal = new JButton("Show Message Total");
		JButton positivePercent = new JButton("Show Positive Percentage");
		JButton validateId = new JButton("Check IDs");
		JButton latestUser = new JButton("Last updated user");
		JTextArea userId = new JTextArea();
		JTextArea groupId = new JTextArea();

		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();

		left.setLayout(new BorderLayout());
		right.setLayout(new GridLayout(2, 0));
		bottom.setLayout(new GridLayout(0,2));
		top.setLayout(new GridBagLayout());

		bottom.setBackground(Color.CYAN);
		top.setBackground(Color.CYAN);

		//Tree view for users/groups
		UserGroup root = new UserGroup("Root");
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
		rootNode.setUserObject(root);
		DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
		JTree tree = new JTree(treeModel);
		JScrollPane scrlp = new JScrollPane(tree);
		left.add(scrlp, BorderLayout.CENTER);

		//Listener to add user when button is pressed
		addUser.addActionListener(ae -> {
			DefaultMutableTreeNode selectedUser = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (selectedUser == null) {
				selectedUser = rootNode;
			}
			User newUser = new User(userId.getText());
			DefaultMutableTreeNode newUserInput = new DefaultMutableTreeNode(newUser);
			if (selectedUser.getUserObject() instanceof User) {
				JOptionPane.showMessageDialog(frame, "Cannot add user to user!");
			} 
			else if (root.containsUser(userId.getText())) {
				JOptionPane.showMessageDialog(frame, "User already exists!");
			} 
			else {
				selectedUser.add(newUserInput);
				((UserGroup) selectedUser.getUserObject()).addUsers(newUser);
			}
			
			treeModel.reload();
		});

		//Listener to add group when button is pressed
		addGroup.addActionListener(ae -> {
			DefaultMutableTreeNode selectedUser = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (selectedUser == null) {
				selectedUser = rootNode;
			}
			UserGroup newGroup = new UserGroup(groupId.getText());
			DefaultMutableTreeNode newGroupInput = new DefaultMutableTreeNode(newGroup);
			if (selectedUser.getUserObject() instanceof User) {
				JOptionPane.showMessageDialog(frame, "Cannot add group to user!");
			} else if (root.containsGroup(groupId.getText())) {
				JOptionPane.showMessageDialog(frame, "Group already exists!");
			} else {
				selectedUser.add(newGroupInput);
				((UserGroup) selectedUser.getUserObject()).addUsers(newGroup);
			}
			treeModel.reload();
		});

		userView.addActionListener(ae -> {
			DefaultMutableTreeNode selectedUser = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (selectedUser.getUserObject() instanceof User) {
				User userViewUser = (User) selectedUser.getUserObject();
				UserView.openUserView(frame, userViewUser, root);
			}
			 
		});

		//Listener to display stats for buttons
		userTotal.addActionListener(ae -> {
			TotalUserVisitor userVis = new TotalUserVisitor();
			root.accept(userVis);
			JOptionPane.showMessageDialog(frame, "Total Users: " + userVis.getUserTotal() + " users total", "Total Users", 0);
		});

		groupTotal.addActionListener(ae -> {
			TotalGroupVisitor groupVis = new TotalGroupVisitor();
			root.accept(groupVis);
			JOptionPane.showMessageDialog(frame, "Total Groups: " + groupVis.getTotalGroups(), "Total Groups", 0);
		});

		msgsTotal.addActionListener(ae -> {
			TotalTweetsVisitor twtsVis = new TotalTweetsVisitor();
			root.accept(twtsVis);
			JOptionPane.showMessageDialog(frame, "Total Tweets: " + twtsVis.getTotalTweets(), "Total Tweets", 0);
		});

		positivePercent.addActionListener(ae -> {
			PositiveTweetsVisitor posVis = new PositiveTweetsVisitor();
			root.accept(posVis);
			JOptionPane.showMessageDialog(frame, "Percentage of Positive Tweets: " + posVis.getPositivePercentage(), "Positive Percentage", 0);
		});

		validateId.addActionListener(ae -> {
			String newUser = userId.getText();
			String newGroup = groupId.getText();
			if (root.containsUser(newUser) || root.containsGroup(newGroup) ) {
				JOptionPane.showMessageDialog(frame, "Cannot have duplicate users or groups!");
			} else if (newUser.contains(" ") || newGroup.contains(" ")) {
				JOptionPane.showMessageDialog(frame, "IDs cannot contain spaces!");
			} else if (!root.containsUser(newUser) || !root.containsGroup(newGroup)) {
				JOptionPane.showMessageDialog(frame, "Valid ID!");
			}
		});

		latestUser.addActionListener(ae -> {
			LastUpdateTimeVisitor latestVisitor = new LastUpdateTimeVisitor();
			root.accept(latestVisitor);
			JOptionPane.showMessageDialog(frame, "Last updated user: " + latestVisitor.getLatestUser());
		});

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; 

		JLabel username = new JLabel("Enter User ID: ");
		c.gridx = 0;		//1st column
		c.gridy = 0;		//1st row
		c.ipady = 20;
		top.add(username, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;		//2nd column
		c.gridy = 0;		//1st row
		top.add(userId, c);

		JLabel groupLabel = new JLabel("Enter Group ID: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;		//3rd column
		c.gridy = 0;		//1st row
		c.weighty = 1;
		top.add(groupLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;		//4th column
		c.gridy = 0;		//1st row
		c.weighty = 1;
		top.add(groupId, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;	//2 columns wide
		c.gridx = 0;		//1st column
		c.gridy = 1;		//2nd row
		c.ipady = 20;
		c.ipady = 25;
		c.weighty = 0.25;		
		top.add(addUser, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;		//2nd column
		c.gridy = 1;		//2nd row
		c.gridwidth = 2;	//2 columns wide
		c.ipady = 25;
		c.weighty = 0.25;
		top.add(addGroup, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weighty = 0.25;
		c.gridwidth = 4;	
		c.gridx = 0;		//1st column
		c.gridy = 2;		//3rd row
		top.add(userView, c);

		bottom.add(userTotal);
		bottom.add(groupTotal);
		bottom.add(msgsTotal);
		bottom.add(positivePercent);
		bottom.add(validateId);
		bottom.add(latestUser);

		right.add(top);
		right.add(bottom);

		frame.add(left);
		frame.add(right);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static AdminPanel getInstance() {
		if (instance == null ) {
			synchronized (AdminPanel.class) {
				if (instance == null) {
					instance = new AdminPanel();
				}
			}
		}
		return instance;
	}

}
