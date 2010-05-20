package plugin.items;


import java.awt.Component;
import java.io.File;
import javax.swing.JList;

public class ItemsPlugin {

	public Component make(File file) {

		JList tList = new JList();

		tList.setCellRenderer( new ItemsPluginCellRenderer() );
		
		return tList;

	}
}
