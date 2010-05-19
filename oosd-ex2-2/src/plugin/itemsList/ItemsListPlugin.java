package plugin.itemsList;


import java.awt.Component;
import java.io.File;
import javax.swing.JList;

public class ItemsListPlugin {

	public Component make(File file) {

		JList tList = new JList();

		tList.setCellRenderer( new ItemsListPluginCellRenderer() );
		
		return tList;

	}
}
