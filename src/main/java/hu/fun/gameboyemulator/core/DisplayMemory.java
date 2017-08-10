package hu.fun.gameboyemulator.core;

public class DisplayMemory {

	/**
	 * LCD control register
	 */
	public static final int LCDC = 0xff40;

	public static class LCDC {
		/**
		 * 0: no picture 1: operation
		 */
		public static final int LCD_ON = 0x80;
		/**
		 * Window tile map select:
		 * 0: 0x9800-0x9BFF
		 * 1: 0x8000-0x9FFF
		 */
		public static final int TILE_MAP_SELECT = 0x40;
		/**
		 * Windows display.
		 * 0: off
		 * 1: on
		 */
		public static final int WINDOW_DISPLAY = 0x20;
		/**
		 * BG & Window Tile Data Select
		 * 0: 0x8800-0x97FF
		 * 1: 0x8000-0x8FFF
		 */
		public static final int BG_WINDOW_TILE_DATA_DATA_SELECT = 0x10;
		/**
		 * BG Tile Map Display Select
		 * 0: $9800-$9BFF
		 * 1: $9C00-$9FFF
		 */
		public static final int BG_TILE_MAP_DISPLAY_SELECT = 0x08;
		/**
		 * OBJ (Sprite) Size
		 * 0: 8*8
		 * 1: 8*16 (width*height)
		 */
		public static final int SPRITE_SIZE = 0x04;
		/**
		 * OBJ (Sprite) Display
		 * 0: off
		 * 1: on
		 */
		public static final int SPRITE_DISPLAY = 0x02;
		/**
		 * BG & Window Display
		 * 0: off
		 * 1: on
		 */
		public static final int BG_WINDOW_DISPLAY = 0x01;
	}

}
