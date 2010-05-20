package gui.listeners;

import gui.Gui;
import gui.ItemComponent;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import plugin.PluginWrapper;
import plugin.items.ItemsPluginCellRenderer;

public class LoadButtonListener implements ActionListener {

	private ListSelectionListener m_Gui;

	public LoadButtonListener(ListSelectionListener gui, Component content){
		
		this.m_Gui = gui;
	}

	public void actionPerformed(ActionEvent e) {

		JFileChooser tDlg = new JFileChooser(".");

        tDlg.setFileFilter( new FileFilter() {

            public boolean accept(File f) {

                return f.isDirectory() || f.getName().endsWith(".jar");
            }

            public String getDescription() { return "Jar files"; }
        });

        int tRes = tDlg.showOpenDialog( new JFrame() );

        if (tRes == JFileChooser.APPROVE_OPTION) {

            File tFile = tDlg.getSelectedFile();

            try{

                PluginWrapper tWrap = new PluginWrapper(tFile);
                
                if( tWrap.getView().equals("Items")){
                	
                	JList newItems = (JList)tWrap.getComponent();
                	ItemsPluginCellRenderer pluginCellRenderer = (ItemsPluginCellRenderer) newItems.getCellRenderer();
                	((JList) ((Gui)this.m_Gui).getItems()).setCellRenderer(pluginCellRenderer);
                }
                if( tWrap.getView().equals("ItemsTable") ){

                	((Gui)this.m_Gui).getItems().setVisible(false);
                	ItemComponent newItems = (ItemComponent)tWrap.getComponent();
                	((Gui)this.m_Gui).setItems( newItems );
                	newItems.setListener( this.m_Gui );
                	((Gui)this.m_Gui).getItems().setVisible(true);
                	((Gui)this.m_Gui).updateUI();
                }                
            }
            catch (Exception e1) {
            	
            	e1.printStackTrace();

            	JOptionPane.showMessageDialog(
            			new JFrame(), e1.getMessage(), "Cannot load plugin", JOptionPane.ERROR_MESSAGE);
            }
        }
	}
}
