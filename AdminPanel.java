import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.*;

public class AdminPanel {

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
		JTextArea userId = new JTextArea("Enter user ID");
		JTextArea groupId = new JTextArea("Enter group ID");

		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();

		left.setLayout(new BorderLayout());
		right.setLayout(new GridLayout(2, 0));
		bottom.setLayout(new GridLayout(0,2));

		//Tree view for users/groups
		UserGroup root = new UserGroup("Root");
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
		rootNode.setUserObject(root);
		DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
		JTree tree = new JTree(treeModel);
		JScrollPane scrlp = new JScrollPane();
		scrlp.add(tree);
		left.add(scrlp);

		top.add(userId);
		top.add(groupId);
		top.add(addUser);
		top.add(addGroup);

		bottom.add(userTotal);
		bottom.add(groupTotal);
		bottom.add(msgsTotal);
		bottom.add(positivePercent);

		right.add(top);
		right.add(bottom);

		frame.add(left);
		frame.add(right);
	}
}
