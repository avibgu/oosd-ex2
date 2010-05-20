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


public class PluginWrapper {

	private Object _plugin;
	private Method _method;
	private String _view;
	private File _file;

    private String getAttr(Attributes attrs, String key) {
        return (String) attrs.get(new Attributes.Name(key));
    }

    public PluginWrapper(File file) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {
		_file = file;
    	JarFile tJar = new JarFile(file);
		Manifest tManifest = tJar.getManifest();
		Attributes tAttrs = tManifest.getMainAttributes();
		String tClassName = getAttr(tAttrs, "Reader-Plugin-Class");
		String tMethodName = getAttr(tAttrs, "Reader-Plugin-Method");
		setView(getAttr(tAttrs, "Reader-Plugin-view"));
		URL tURL = file.toURI().toURL();
		URL[] tPath = new URL[] { tURL };
		ClassLoader tLoader = new URLClassLoader(tPath);
		Class tClass = tLoader.loadClass(tClassName);
		setPlugin(tClass.newInstance());
		Class[] tArgTypes = new Class[] { };
		setMethod(tClass.getMethod(tMethodName, tArgTypes));
		if (!Component.class.isAssignableFrom(getMethod().getReturnType())) {
			throw new NoSuchMethodException(getMethod().getName());
		}
	}

	public Component getComponent() throws InvocationTargetException, IllegalAccessException {
		Object[] tArgs = { };
		return (Component) getMethod().invoke(getPlugin(), tArgs);
	}

	public String getView() {
		return _view;
	}

	public void setView(String view) {
		_view = view;
	}

    private Object getPlugin() {
        return _plugin;
    }

    private void setPlugin(Object plugin) {
        _plugin = plugin;
    }

    private Method getMethod() {
        return _method;
    }

    private void setMethod(Method method) {
        _method = method;
    }
    
    public File getFile(){
    	return _file;
    }
}
