package hu.fun.gameboyemulator.core;

public class Memory {

	private int[] rom;
	
	private int[] ram;
	
	private CartridgeType ct;
	
	public Memory(int[] rom) {
		this.rom = rom;
//		int ct = rom[Globals.CARTRIDGE_TYPE_ADDRESS];
	}
	
	public int readROM(int address) {
		return rom[address];
	}
	
	public int readRAM(int address) {
		return ram[address];
	}
	
	public void writeRAM(int address, int value) {
		ram[address] = value;
	}
	
	public CartridgeType getCartridgeType() {
		return this.ct;
	}
}
