package hu.fun.gameboyemulator.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JFrame{
	private static final long serialVersionUID = -1402279606705022368L;

	Canvas canvas = new Canvas();
	
	MemoryBus memoryBus;
	
	int[][] tiles;
	
	public Display(MemoryBus memoryBus) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.memoryBus = memoryBus;
	}
	
	@Override
	public void setVisible(boolean b) {
		this.setSize(256, 256);
		canvas.setSize(256, 256);
		this.add(canvas);
		int mem = 0x8000;
		tiles = new int[16][16];
		for(int i = 0; i<=15; i++) {
			for(int j = 0; j <= 15; j++) {
				tiles[i][j] = memoryBus.readMem(mem);
				mem++;
			}
		}
		
		
		super.setVisible(b);
	}
	
	private class Canvas extends JPanel{
		private static final long serialVersionUID = -4724794022546529106L;

		public Canvas() {
	        setBorder(BorderFactory.createLineBorder(Color.black));
	    }

	    public Dimension getPreferredSize() {
	        return new Dimension(250,200);
	    }

	    public void paintComponent(Graphics g) {
	    	super.paintComponent(g);
	        g.setColor(Color.BLACK);
	        int width = 0;
	        g.drawLine(15, 20, 25, 17);
			for(int i = 0; i<=15; i++) {
				for(int j= 0; j < 15; j++, j++) {
					int line = tiles[i][j] | tiles[i][j+1];
					if((line & 1) != 0) {
						g.drawLine(width, j, width, j);
					}if((line & 2) != 0) {
						g.drawLine(width+1, j, width+1, j);
					}
					if((line & 4) != 0) {
						g.drawLine(width+2, j, width+2, j);
					}
					if((line & 8) != 0) {
						g.drawLine(width+3, j, width+3, j);
					}if((line & 16) != 0) {
						g.drawLine(width+4, j, width+4, j);
					}
					if((line & 32) != 0) {
						g.drawLine(width+5, j, width+5, j);
					}
					if((line & 64) != 0) {
						g.drawLine(width+6, j, width+6, j);
					}
					if((line & 128) != 0) {
						g.drawLine(width+7, j, width+7, j);
					}
				}
				width += 10;
			}
	    }  
	}
	
	
}
