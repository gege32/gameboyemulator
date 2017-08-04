package hu.fun.gameboyemulator.core;

public enum CartridgeType {
	
	ROM_ONLY(0x0),
	ROM_MBC1(0x01);
	
    private final int value;

	CartridgeType(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
