package hu.fun.gameboyemulator.core;

public class Globals {
	
	//Interrupt addresses
	public static final int RST00 = 0x0000;
	
	public static final int RST08 = 0x0008;
	
	public static final int RST10 = 0x0010;
	
	public static final int RST18 = 0x0018;
	
	public static final int RST20 = 0x0020;
	
	public static final int RST28 = 0x0028;
	
	public static final int RST30 = 0x0030;
	
	public static final int RST38 = 0x0038;
	
	public static final int VERICAL_BLANK_INT = 0x0040;
	
	public static final int LCDC_STATUS_INT = 0x0048;
	
	public static final int TIME_OVERFLOW_INT = 0x0050;
	
	public static final int SERIAL_TX_COMP_INT = 0x0058;
	
	public static final int HTL_P10_13_INT = 0x0060;

	//game data
	public static final int NINTENDO_GRAPH = 0x0104;
	
	public static final int GAME_NAME_ADDRESS = 0x0134; //16 bytes
	
	public static final int GB_SGB_INDICATOR_ADDRESS = 0x0146; //1byte
	
	public static final int CARTRIDGE_TYPE_ADDRESS = 0x0147; //1 byte
	
	public static final int ROM_SIZE_ADDRESS = 0x0148; //1byte
	
	public static final int RAM_SIZE_ADDRESS = 0x0149; //1byte
	
	//RAM addresses
	public static final int VRAM = 0x8000;
	
	public static final int IO_PORTS_ADDRESS = 0xFF00;
	
	//RAM registers	
	public static final int LCDC_ADDRESS = 0xff40;
	
	
}
