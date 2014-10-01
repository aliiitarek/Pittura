import java.io.File;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Class_Loader {

	public Constructor[] constructors;

	public Class_Loader(String path) {

		// TODO Auto-generated constructor stub
		System.out.println(path);
		String classNamee = "";
		String pathNamee = "";
		String rclassNamee = "";
		String rpathNamee = "";
		for (int i = (path.length() - 1); i > -1; i--) {
			if (path.charAt(i) == '.') {
				while (path.charAt(i) != '\\' && i > 0) {
					i--;
					if (path.charAt(i) != '\\')
						rclassNamee += path.charAt(i);
				}
				i--;
				for (; i > -1; i--) {
					rpathNamee += path.charAt(i);
				}
			}

		}
		for (int i = (rclassNamee.length() - 1); i > -1; i--) {
			classNamee += rclassNamee.charAt(i);
		}
		for (int i = (rpathNamee.length() - 1); i > -1; i--) {
			pathNamee += rpathNamee.charAt(i);
		}
		System.out.println(pathNamee + "<><><>" + classNamee);

		File file = new File(pathNamee);

		try {
			URL url = file.toURI().toURL();
			URL[] urls = new URL[] { url };
			ClassLoader cl = new URLClassLoader(urls);
			Class cls = cl.loadClass(classNamee);
			constructors = cls.getConstructors();
			for (Constructor constructor : constructors) {
				System.out.println(constructor);
			}

		} catch (MalformedURLException e) {
		} catch (ClassNotFoundException e) {
		}

	}

}
