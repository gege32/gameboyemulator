package hu.fun.gameboyemulator.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.log4j.Logger;

import hu.fun.gameboyemulator.core.CPU;
import hu.fun.gameboyemulator.core.Globals;
import hu.fun.gameboyemulator.core.Memory;
import hu.fun.gameboyemulator.core.MemoryBus;

public class Loader {
	
	Logger log = Logger.getLogger(Loader.class);
	
	int[] romImage;

	public void loadFile(String filename) {
		File file = new File(filename);
		log.debug("Reading rom from file " + filename);
		
		try {
			InputStream is = new FileInputStream(file);
			romImage = new int[(int) file.length()];
			
			try {
			for(int i = 0; i < file.length(); i++) {
				romImage[i] = is.read();
			}
			} catch (IOException e) {
				log.error("File reading error", e);
			}finally {
				try {
					is.close();
				} catch (IOException e) {}
			}
			
		} catch (FileNotFoundException e) {
			log.error("File not found", e);
		}
	}
	
	public Memory createMemory() {
		Memory ret = new Memory(romImage);
		
		return ret;
	}
	
	public CPU createCPU(MemoryBus bus) {
		CPU cpu = new CPU(bus);
		return cpu;
	}
	
	public String getGameName() {
		StringBuffer buf = new StringBuffer();
		int[] copyOfRange = Arrays.copyOfRange((romImage), Globals.GAME_NAME_ADDRESS, Globals.GAME_NAME_ADDRESS+16);
		for(int kaka : copyOfRange) {
			if(kaka != 0)buf.append((char)kaka);
		}
		
		return buf.toString();
	}
}
