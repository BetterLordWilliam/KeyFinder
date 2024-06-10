package src.ui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.InputStream;
import java.io.IOException;

import src.main.Main;

public class FontSupplier {
	
	// FONT REFERENCES
	public static Font MENU_FONT;
	public static Font MENU_FONT_LARGE;

	/**
	 * loadFont:		retrieve font
	 * @throws 			IOException 
	 * @throws 			FontFormatException 
	 */
	private static Font loadFont(String path, int FontType, float size) throws FontFormatException, IOException {
		if (path == null)
			return null;
		InputStream is = Main.class.getResourceAsStream(path);
		return Font.createFont(FontType, is).deriveFont(size);
	}
	
	/**
	 * loadFonts:		read the fonts from the system
	 */
	public static void loadFonts() {
		try {
			MENU_FONT_LARGE = loadFont("/res/UI/Text/FFFFORWA.TTF", Font.TRUETYPE_FONT, 36f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
