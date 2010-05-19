package gui;

import java.util.Collection;
import java.util.LinkedList;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import rss.RSSFeed;

public class FeedsTreeModel implements TreeModel {

	private Collection<TreeModelListener> _listeners;

	private DefaultMutableTreeNode root;

	public FeedsTreeModel(){

		setRoot( new DefaultMutableTreeNode() );
		setListeners( new LinkedList<TreeModelListener>() );
	}

	public FeedsTreeModel(DefaultMutableTreeNode feeds) {

		setRoot( feeds );
		setListeners( new LinkedList<TreeModelListener>() );
	}


//----------------| Listeners |------------------------------

	private void setListeners(LinkedList<TreeModelListener> linkedList) {

		this._listeners = linkedList;
	}

	public Collection<TreeModelListener> getListeners() {
		return this._listeners;
	}

	public void addTreeModelListener(TreeModelListener l) {

		getListeners().add(l);
	}

	public void removeTreeModelListener(TreeModelListener l) {

		getListeners().remove(l);
	}


//--------------------| Root |-------------------------------

	public void setRoot(DefaultMutableTreeNode root){

		this.root = root;
	}

	public Object getRoot(){

		return this.root;
	}


//--------------------| Children |----------------------------

	public Object getChild(Object parent, int index) {

		return ((DefaultMutableTreeNode)parent).getChildAt( index );
	}

	public int getChildCount(Object parent) {

		return ((DefaultMutableTreeNode)parent).getChildCount();
	}

	public int getIndexOfChild(Object parent, Object child) {

		return ((DefaultMutableTreeNode)parent).getIndex(
				(DefaultMutableTreeNode)child );
	}

	public boolean isLeaf(Object node) {

		return ((DefaultMutableTreeNode)node).isLeaf();
	}


//-------------------| Actions |-----------------------------

	public void remove(DefaultMutableTreeNode node){

		int[] index = { this.root.getIndex(node) };
		Object[] obj = { node };

		this.root.remove( node );

		TreeModelEvent tEvt =
			new TreeModelEvent( this, this.root.getPath(), index, obj );

		for (TreeModelListener tListener : getListeners()){

			tListener.treeNodesRemoved(tEvt);
		}
	}

	public boolean contains(RSSFeed feed){

		DefaultMutableTreeNode root = (DefaultMutableTreeNode)getRoot();

		for(int i=0; i < root.getChildCount(); i++ ){

			if( ((DefaultMutableTreeNode)root.getChildAt( i )).getUserObject().equals(feed) )

				return true;
		}

		return false;
	}


//--------------------| Other |------------------------------

	public void valueForPathChanged(TreePath path, Object newValue) {}
}
