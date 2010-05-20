package gui.workers;

import exception.AbortException;
import frames.ErrorFrame;
import gui.FeedsTreeModel;
import gui.ItemComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.tree.DefaultMutableTreeNode;

import main.SimpleXMLReader;
import rss.RSSFeed;
import rss.RssHandler;
import config.Feed;

public class AddButtonWorker extends SwingWorker<Void, Void>{
	
	private JTextField _url;
	private JTree _tree;
	private JComboBox _refresh;
	private int[] timeInts = { 5000, 10000, 30000, 60000, 600000 };
	private HashMap<DefaultMutableTreeNode,Timer> _nodeToTimerMap;
	private ItemComponent _items;

	public AddButtonWorker(JTextField url, JTree tree, JComboBox refresh,
			HashMap<DefaultMutableTreeNode,Timer> nodeToTimerMap, ItemComponent items){
		
		this._url = url;
		this._tree = tree;
		this._refresh = refresh;
		this._nodeToTimerMap = nodeToTimerMap;
		this._items = items;
	}
	
	protected Void doInBackground() throws Exception {

		RssHandler rssHandler = new RssHandler();

		try{

			Feed feed = new Feed( this._url.getText() );

			if(feed.getAddress() != null){

				SimpleXMLReader reader = new SimpleXMLReader(feed, rssHandler);
				reader.read();

				FeedsTreeModel model = ((FeedsTreeModel)_tree.getModel());

				RSSFeed newFeed = rssHandler.getRssFeed();

				if(!model.contains( newFeed ) ){

					final DefaultMutableTreeNode node = new DefaultMutableTreeNode( newFeed );

					node.add( new DefaultMutableTreeNode(
							newFeed.getChannels().get(0).getDescription() ) );

					node.add( new DefaultMutableTreeNode(
							newFeed.getChannels().get(0).getLink() ) );

					((DefaultMutableTreeNode)model.getRoot()).add( node );

					// adds a refresh timer
					int refreshTime = timeInts[ this._refresh.getSelectedIndex() ];
					
					ActionListener tUpdate = new ActionListener() {
					
						public void actionPerformed(ActionEvent e) {
							
							new RefreshButtonWorker( node, _tree, _items ).execute();
						}
					};
					
					Timer timer = new Timer(refreshTime, tUpdate);
					timer.start();
					
					_nodeToTimerMap.put(node, timer);
				}
				else{

					ErrorFrame errorFrame = new ErrorFrame("The URL is already exist");
					errorFrame.setBounds(500, 200, 275, 180);
					errorFrame.setVisible(true);
				}
			}
		}
		catch (MalformedURLException ex) {

			ErrorFrame errorFrame = new ErrorFrame("Make sure that the URL is valid");
			errorFrame.setBounds(500, 200, 275, 180);
			errorFrame.setVisible(true);
		}
		catch (AbortException ex) {
			
			ErrorFrame errorFrame = new ErrorFrame("Not a RSS link!");
			errorFrame.setBounds(500, 200, 275, 180);
			errorFrame.setVisible(true);
		}
		
		return null;
	}
	
	protected void done(){
		
		_tree.updateUI();
		_url.setText("");
	}
}
