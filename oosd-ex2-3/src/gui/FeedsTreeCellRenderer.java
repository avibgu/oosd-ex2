package gui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class FeedsTreeCellRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 216029601162006965L;

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus){

		super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);

		setIcon( new ImageIcon() );

		setText( ((DefaultMutableTreeNode)value).getUserObject().toString() );
		
		return this;
	}
}
