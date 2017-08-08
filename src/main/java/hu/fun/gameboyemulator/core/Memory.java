package hu.fun.gameboyemulator.core;

public class Memory {
		
	private int[] ram;
	
	private CartridgeType ct;
	
	public Memory(int[] rom) {
		this.ram = new int[0x10000];
		System.arraycopy(rom, 0, ram, 0, rom.length);
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
