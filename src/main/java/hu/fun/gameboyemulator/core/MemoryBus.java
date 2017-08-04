package hu.fun.gameboyemulator.core;

public class MemoryBus {

	private Memory memory;
	
	public MemoryBus(Memory mem) {
		this.memory = mem;
	}
	
	public int readROM(int address) {
		return memory.readROM(address);
	}
	
	public int readRAM(int address) {
		return memory.readRAM(address);
	}
	
	public void writeRAM(int address, int value) {
		memory.writeRAM(address, value);;
	}
	
	public CartridgeType getCartridgeType() {
		return memory.getCartridgeType();
	}
}
