package command;

import java.io.File;

public class ProvideFileOnServerCommand {
	public File execute(String fullPath){
		try {
			File file = new File(fullPath);

			return file;
		} catch (Exception e) {
			return null;
		}
	}
}