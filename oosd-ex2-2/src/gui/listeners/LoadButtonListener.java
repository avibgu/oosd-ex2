package gui.listeners;

import gui.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import plugin.PluginWrapper;

public class LoadButtonListener implements ActionListener {

	private Gui _gui;

	public LoadButtonListener(Gui gui){
		
		this._gui = gui;
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

                if( tWrap.getView().equals("Items") ){
                	
                	JComponent tItems = (JComponent)tWrap.getComponent();
                	tWrap.setListener( tItems, (ListSelectionListener)this._gui );
                	this._gui.setItems( tItems );
                	this._gui.setWrapper( tWrap );
                	this._gui.getTitlePane().setViewportView( tItems );
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
