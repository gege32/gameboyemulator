package hu.fun.gameboyemulator.app;

import org.apache.log4j.Logger;

import hu.fun.gameboyemulator.core.CPU;
import hu.fun.gameboyemulator.core.Display;
import hu.fun.gameboyemulator.core.Memory;

public class Device {
	
	private Logger log = Logger.getLogger(Device.class);
	
	private String gameFileName;
	
	private CPU cpu;
	
	private Memory memory;
	
	private Loader loader;
	
	private Display display;

	public Device(String gameFileName) {
		this.gameFileName = gameFileName;
	}
	
	public void init() {
		log.info("Initializing virtual device...");
		loader = new Loader();
		loader.loadFile(gameFileName);
		log.info("Game name: " + loader.getGameName());
	}
	
	
	
}
