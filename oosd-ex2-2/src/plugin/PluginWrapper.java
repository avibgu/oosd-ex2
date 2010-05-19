package plugin;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.swing.JComponent;
import javax.swing.event.ListSelectionListener;

import rss.Item;
import rss.RSSFeed;

public class PluginWrapper {

	private Object _plugin;

	private String _view;
	
	private Method _makeMethod;
	private Method _listenerMethod;
	private Method _feedMethod;
	private Method _clearMethod;
	private Method _getMethod;

    public PluginWrapper(File file)
    				throws IOException, ClassNotFoundException,
    						IllegalAccessException, InstantiationException,
    							NoSuchMethodException {
		
    	JarFile tJar = new JarFile(file);
    	
		Manifest tManifest = tJar.getManifest();
		
		Attributes tAttrs = tManifest.getMainAttributes();
		
		String tClassName = getAttr(tAttrs, "Reader-Plugin-Class");
		String tMakeMethodName = getAttr(tAttrs, "Reader-Plugin-MakeMethod");
		String tListenerMethodName = getAttr(tAttrs, "Reader-Plugin-ListenerMethod");
		String tFeedMethodName = getAttr(tAttrs, "Reader-Plugin-FeedMethod");
		String tClearMethodName = getAttr(tAttrs, "Reader-Plugin-ClearMethod");
		String tGetMethodName = getAttr(tAttrs, "Reader-Plugin-GetMethod");
		
		setView( getAttr( tAttrs, "Reader-Plugin-view" ) );
		URL tURL = file.toURI().toURL();
		URL[] tPath = new URL[] { tURL };
		ClassLoader tLoader = new URLClassLoader( tPath );
		Class tClass = tLoader.loadClass( tClassName );
		setPlugin( tClass.newInstance() );
		
		Class[] tArgTypes = new Class[]{};
		
		_makeMethod = tClass.getMethod( tMakeMethodName, tArgTypes );
		
		tArgTypes = new Class[] { JComponent.class,
				  				  ListSelectionListener.class };
		
		_listenerMethod = tClass.getMethod( tListenerMethodName, tArgTypes );

		tArgTypes = new Class[] { JComponent.class, RSSFeed.class };
		
		_feedMethod = tClass.getMethod( tFeedMethodName, tArgTypes );
		
		tArgTypes = new Class[] { JComponent.class };
		
		_clearMethod = tClass.getMethod( tClearMethodName, tArgTypes );
		
		_getMethod = tClass.getMethod( tGetMethodName, tArgTypes );

//		TODO check why it doesn't work..
//		if ( !Component.class.isAssignableFrom( _makeMethod.getReturnType() ) ) {
//			
//			throw new NoSuchMethodException( _makeMethod.getName() );
//		}
//		if ( !Component.class.isAssignableFrom( _listenerMethod.getReturnType() ) ) {
//			
//			throw new NoSuchMethodException( _listenerMethod.getName() );
//		}
//		if ( !Component.class.isAssignableFrom( _feedMethod.getReturnType() ) ) {
//			
//			throw new NoSuchMethodException( _feedMethod.getName() );
//		}
//		if ( !Component.class.isAssignableFrom( _clearMethod.getReturnType() ) ) {
//			
//			throw new NoSuchMethodException( _clearMethod.getName() );
//		}
//		if ( !Component.class.isAssignableFrom( _getMethod.getReturnType() ) ) {
//			
//			throw new NoSuchMethodException( _getMethod.getName() );
//		}
	}
    
    private String getAttr(Attributes attrs, String key) {
    	
        return (String) attrs.get(new Attributes.Name(key));
    }

	public Component getComponent()
						throws InvocationTargetException, IllegalAccessException {
		
		Object[] tArgs = { };
		
		return (Component) _makeMethod.invoke( getPlugin(), tArgs );
	}

	public String getView() { return _view; }

	public void setView(String view) { _view = view; }

    private Object getPlugin() { return _plugin; }

    private void setPlugin(Object plugin) { _plugin = plugin; }

	public void setListener( JComponent tItems, ListSelectionListener listener )
						throws IllegalArgumentException, IllegalAccessException,
													InvocationTargetException {

		Object[] tArgs = { tItems, listener };
		_listenerMethod.invoke( getPlugin(), tArgs );
	}

	public void setFeed( JComponent tItems, RSSFeed feed )
						throws IllegalArgumentException, IllegalAccessException,
													InvocationTargetException {

		Object[] tArgs = { tItems, feed };
		_feedMethod.invoke( getPlugin(), tArgs );
	}

	public void clearSelection( JComponent tItems )
						throws IllegalArgumentException, IllegalAccessException,
													InvocationTargetException {

		Object[] tArgs = { tItems };
		_clearMethod.invoke( getPlugin(), tArgs );
	}

	public Item getSelectedValue( JComponent tItems )
						throws IllegalArgumentException, IllegalAccessException,
													InvocationTargetException {

		Object[] tArgs = { tItems };
		Item item = (Item) _getMethod.invoke( getPlugin(), tArgs );
		
		return item;
	}
}
