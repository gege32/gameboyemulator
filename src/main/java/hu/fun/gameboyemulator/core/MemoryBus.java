package hu.fun.gameboyemulator.core;

public class MemoryBus {

	private Memory memory;
	
	public MemoryBus(Memory mem) {
		this.memory = mem;
	}
	
	public int readMem(int address) {
		return memory.readRAM(address);
	}
	
	public void writeMem(int address, int value) {
		memory.writeRAM(address, value);;
	}
	
	public CartridgeType getCartridgeType() {
		return memory.getCartridgeType();
	}
}
