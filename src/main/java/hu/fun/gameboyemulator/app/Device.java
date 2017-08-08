package hu.fun.gameboyemulator.app;

import org.apache.log4j.Logger;

import hu.fun.gameboyemulator.core.CPU;
import hu.fun.gameboyemulator.core.Display;
import hu.fun.gameboyemulator.core.Memory;
import hu.fun.gameboyemulator.core.MemoryBus;

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
		this.memory = loader.createMemory();
		MemoryBus bus = new MemoryBus(this.memory);
		cpu = loader.createCPU(bus);
		cpu.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
