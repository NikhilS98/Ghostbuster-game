package ghostCave;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class FilePath {

	private static Path currentDir = Paths.get("").toAbsolutePath();
	
	public static BufferedImage readImage(String path) {
		try {
			return ImageIO.read(new File(currentDir + path));
		} catch (IOException e) {
		}
		return null;
	}
	
	public static File readFile(String path) {
		return new File(currentDir + path);
	}
}
