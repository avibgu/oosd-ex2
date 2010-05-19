package gui;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NONE;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import plugin.PluginWrapper;

import gui.listeners.LoadButtonListener;
import gui.workers.AddButtonWorker;

import gui.workers.RefreshButtonWorker;

import rss.Channel;
import rss.Item;
import rss.RSSFeed;

public class Gui extends JPanel
				   implements TreeSelectionListener, ListSelectionListener {

	private static final long serialVersionUID = 1048997770789816933L;


	private JTree _tree;

	private JTextArea _content;
	
	private JComponent _items;

	private RSSFeed _emptyFeed;

	private DefaultMutableTreeNode _selectedNode;

	private HashMap<DefaultMutableTreeNode,Timer> _nodeToTimerMap;

	private Map<String, PluginWrapper> _pluginsMap;
	
	private JScrollPane _titlePane;

	private PluginWrapper _wrap;
	
	public Gui() {

		super( new GridBagLayout() );

		this._selectedNode = null;

		// Creates an empty feed
		this._emptyFeed = new RSSFeed("");
		Channel channel = new Channel();
		Item item = new Item();
		channel.getItems().add(item);
		this._emptyFeed.getChannels().add(channel);

		// Creates an empty map
		_nodeToTimerMap = new HashMap<DefaultMutableTreeNode,Timer>();

		// Tree
		DefaultMutableTreeNode feeds =
			new DefaultMutableTreeNode( new String("Feeds") );
		setTree( new JTree( new FeedsTreeModel( feeds ) ) );
		getTree().setCellRenderer( new FeedsTreeCellRenderer() );
		getTree().addTreeSelectionListener( this );
		getTree().setEditable( true );

		// Items list
		JComponent tItems = null;
		
		try {
			
			setWrapper( new PluginWrapper( new File("src/plugin/defaultItems/default.jar") ) );
	    	tItems = (JComponent)getWrapper().getComponent();
	    	getWrapper().setListener( tItems, this );

		} catch (Exception e) { e.printStackTrace(); }
        
		setItems( tItems );

		// Text area
		setContent(new JTextArea(10, 30));
		getContent().setEditable(false);
		getContent().setTabSize(4);


		setBackground(Color.decode("5462640"));

		GridBagConstraints tProto = new GridBagConstraints();
		tProto.insets = new Insets(5,2,5,2);

		GridBagConstraints tLabelConst = (GridBagConstraints)tProto.clone();
		tLabelConst.anchor = EAST;
		tLabelConst.fill = NONE;
		tLabelConst.weightx = 1.0;

		GridBagConstraints tTextConst = (GridBagConstraints)tProto.clone();
		tTextConst.anchor = EAST;
		tTextConst.fill = HORIZONTAL;
		tTextConst.weightx = 5.0;

		GridBagConstraints tButtonsConst = (GridBagConstraints)tProto.clone();
		tButtonsConst.anchor = CENTER;
		tButtonsConst.fill = HORIZONTAL;
		tButtonsConst.weightx = 5.0;

		GridBagConstraints tButtonConst = (GridBagConstraints)tProto.clone();
		tButtonConst.anchor = CENTER;
		tButtonConst.fill = NONE;
		tButtonConst.weightx = 0.0;


		// (0,0) "Add New Feed"
		GridBagConstraints tConst = (GridBagConstraints)tLabelConst.clone();
		tConst.gridx = 0; tConst.gridy = 0;
		JLabel text = new JLabel("Add New Feed:\t");
		text.setFont(new Font("Time New Roman",Font.BOLD,16));
		text.setForeground(Color.WHITE);
		add(text, tConst);

		// (1,0) "URL"
		tConst =  (GridBagConstraints)tLabelConst.clone();
		tConst.gridx = 1; tConst.gridy = 0;
		text = new JLabel("URL");
		text.setFont(new Font("Time New Roman",Font.ITALIC,14));
		text.setForeground(Color.WHITE);
		add(text, tConst);

		// (2,0) [textField]
		tConst = (GridBagConstraints)tTextConst.clone();
		tConst.gridx = 2; tConst.gridy = 0;
		final JTextField url = new JTextField(30);
		add(url, tConst);

		// (3,0) "Refresh Rate"
		tConst = (GridBagConstraints)tLabelConst.clone();
		tConst.gridx = 3; tConst.gridy = 0;
		text = new JLabel("Refresh Rate");
		text.setFont(new Font("Time New Roman",Font.ITALIC,14));
		text.setForeground(Color.WHITE);
		add(text, tConst);

		// (4,0) [textField]
		tConst = (GridBagConstraints)tTextConst.clone();
		tConst.gridx = 4; tConst.gridy = 0;
		String[] timeStrings = { " 5 sec", " 10 sec", " 30 sec", " 1 min", " 10 min" };
		final JComboBox refresh = new JComboBox( timeStrings );
		refresh.setSelectedIndex(0);
		add(refresh, tConst);

		// (5,0) "Add" button
		tConst = (GridBagConstraints)tButtonConst.clone();
		tConst.gridx = 5; tConst.gridy = 0;
		JButton addButton = new JButton("Add");
		add(addButton, tConst);

		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new AddButtonWorker( url, getTree(), refresh,
						_nodeToTimerMap, (JComponent) getItems() ).execute();
			}
		});

		GridBagConstraints tListConst = (GridBagConstraints)tProto.clone();
		tListConst.anchor = CENTER;
		tListConst.weightx = 1.0;
		tListConst.weighty = 1.0;
		tListConst.insets = new Insets(2, 2, 2, 2);


		// (0-1,2-5) List of feeds
	    getTree().setBorder( BorderFactory.createEtchedBorder() );

		tConst = (GridBagConstraints)tListConst.clone();
		tConst.fill = BOTH;
		tConst.gridx = 0; tConst.gridy = 2;
		tConst.gridwidth = 2; tConst.gridheight = 4;

		JScrollPane feedsPane = new JScrollPane( getTree() );
		feedsPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		feedsPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	    add( feedsPane, tConst );


		// (2-5,2-3) List of Items
	    tConst = (GridBagConstraints)tListConst.clone();
		tConst.anchor = CENTER;
		tConst.fill = BOTH;
		tConst.gridx = 2; tConst.gridy = 2;
		tConst.gridwidth = 4; tConst.gridheight = 2;

		_titlePane = new JScrollPane( (Component) getItems() );
		_titlePane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		_titlePane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	    add( _titlePane, tConst );


		// (2-5,4-5)Text Area
		getContent().setBorder( BorderFactory.createEtchedBorder() );

		tConst = new GridBagConstraints();
		tConst.insets = new Insets(5, 2, 5, 2);
		tConst.fill = BOTH;
		tConst.anchor = CENTER;
		tConst.weighty = 1.0; tConst.weightx = 1.0;
		tConst.gridx = 2; tConst.gridy = 4;
		tConst.gridwidth = 4; tConst.gridheight = 2;

		JScrollPane TextPane = new JScrollPane( getContent() );
		TextPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		TextPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		add( TextPane, tConst);


		// (0,6) "Remove" button
		tConst = (GridBagConstraints)tButtonConst.clone();
		tConst.gridx = 0; tConst.gridy = 6;
		tConst.gridwidth = 1; tConst.gridheight = 1;

		JButton removeButton = new JButton("Remove");

		removeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if ( _selectedNode != null){

					Timer timer = _nodeToTimerMap.remove( _selectedNode );

					if ( timer !=null ) timer.stop();

					((FeedsTreeModel)getTree().getModel()).remove(_selectedNode);
					
					try {
						getWrapper().setFeed( getItems(), _emptyFeed );
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					getContent().setText("");
				}
			}
		});

		add(removeButton, tConst);

		// (1,6) "Refresh" button
		tConst = (GridBagConstraints)tButtonConst.clone();
		tConst.gridx = 1; tConst.gridy = 6;
		tConst.gridwidth = 1; tConst.gridheight = 1;

		JButton refreshButton = new JButton("Refresh");

		refreshButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new RefreshButtonWorker( _selectedNode,
						getTree(), (JComponent) getItems() ).execute();
			}
		});

		add(refreshButton, tConst);

		// (5,6) "Load Plugin" button
		tConst = (GridBagConstraints)tButtonConst.clone();
		tConst.gridx = 5; tConst.gridy = 6;
		tConst.gridwidth = 1; tConst.gridheight = 1;

		JButton loadButton = new JButton("Load Plugin");
		
		LoadButtonListener buttonListener = new LoadButtonListener( this );
		
		loadButton.addActionListener( buttonListener);
		
		add(loadButton, tConst);
	}

	public void valueChanged(TreeSelectionEvent e) {

		DefaultMutableTreeNode tNode =
			(DefaultMutableTreeNode) e.getPath().getLastPathComponent();

		if (tNode.getUserObject() instanceof RSSFeed){

			try {
				getWrapper().setFeed( getItems(), (RSSFeed)tNode.getUserObject() );
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			this._selectedNode = tNode;
		}
		else{

			String str = (String)tNode.getUserObject();

			if ( str.equals("Feeds") ){

				try {
					getWrapper().setFeed( getItems(), _emptyFeed );
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else{

				try {
					getWrapper().setFeed( getItems(),
							(RSSFeed)((DefaultMutableTreeNode)
									tNode.getParent()).getUserObject() );
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			this._selectedNode = null;
		}

		// clear item selection and content pane
		try {
			getWrapper().clearSelection( getItems() );
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getContent().setText("");
	}

	public void valueChanged(ListSelectionEvent e) {

		Item tItem = null;
		try {
			tItem = (Item) getWrapper().getSelectedValue( getItems() );
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (tItem == null) return;

		getContent().setText( tItem.getDescription() );
	}

	private void setTree(JTree jTree) { this._tree = jTree; }

	private JTree getTree() { return this._tree; }

	public void setItems(JComponent items) { this._items = items; }

	public JComponent getItems() { return this._items; }

	private void setContent(JTextArea jTextArea) { this._content = jTextArea; }

	private JTextArea getContent() { return this._content; }

	public Map<String, PluginWrapper> getPluginsMap() { return _pluginsMap; }
	
	public JScrollPane getTitlePane(){ return this._titlePane;}

	public void setWrapper(PluginWrapper tWrap) { this._wrap = tWrap; }

	public PluginWrapper getWrapper() { return _wrap; }
}
