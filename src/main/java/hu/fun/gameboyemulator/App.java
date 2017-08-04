package hu.fun.gameboyemulator;

import hu.fun.gameboyemulator.app.Device;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        app.start();
    }
    
    private void start() {
    	Device device = new Device("data/tetris.gb");
    	device.init();
    }
}
