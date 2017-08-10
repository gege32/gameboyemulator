package hu.fun.gameboyemulator.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JFrame {
	private static final long serialVersionUID = -1402279606705022368L;

	Canvas canvas = new Canvas();

	MemoryBus memoryBus;

	int[][] tiles = new int[0x1000][16];

	int[][] buffer = new int[32][32];

	public Display(MemoryBus memoryBus) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.memoryBus = memoryBus;
	}

	@Override
	public void setVisible(boolean b) {
		this.setSize(320, 288);
		canvas.setSize(320, 288);
		System.out.println(Integer.toBinaryString(memoryBus.readMem(DisplayMemory.LCDC)));

		this.add(canvas);
		
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				canvas.repaint();
			}
			
		}, 2, 1, TimeUnit.SECONDS);

		super.setVisible(b);
	}

	private class Canvas extends JPanel {
		private static final long serialVersionUID = -4724794022546529106L;

		public Canvas() {
			setBorder(BorderFactory.createLineBorder(Color.black));
		}

		public Dimension getPreferredSize() {
			return new Dimension(250, 200);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int tileaddress = 0x8800;
			for (int i = 0; i < 0x0100; i++) {
				for (int j = 0; j <= 15; j++) {
					tiles[i][j] = memoryBus.readMem(tileaddress);
					tileaddress++;
				}
			}
			int tilemap = 0x9800;
			for (int i = 0; i < 0x10; i++) {
				for (int j = 0; j <= 0x10; j++) {
					buffer[i][j] = memoryBus.readMem(tilemap);
					tilemap++;
				}
			}
			
			g.setColor(Color.BLACK);
			int voffset = 0;
			int hoffset = 0;
			for (int i = 0; i < 0x10; i++) {
				for (int j = 0; j <= 0x10; j++) {
					int add = (buffer[i][j] + 128) & 0xff;
					
					int[] tile = tiles[buffer[i][j]];
					for (int k = 0; k <= 14; k++, k++) {
						int line = tile[k] | tile[k + 1];
						if ((line & 1) != 0) {
							g.drawLine(voffset, k+hoffset, voffset, k+hoffset);
						}
						if ((line & 2) != 0) {
							g.drawLine(voffset + 1, k+hoffset, voffset + 1, k+hoffset);
						}
						if ((line & 4) != 0) {
							g.drawLine(voffset + 2, k+hoffset, voffset + 2, k+hoffset);
						}
						if ((line & 8) != 0) {
							g.drawLine(voffset + 3, k+hoffset, voffset + 3, k+hoffset);
						}
						if ((line & 16) != 0) {
							g.drawLine(voffset + 4, k+hoffset, voffset + 4, k+hoffset);
						}
						if ((line & 32) != 0) {
							g.drawLine(voffset + 5, k+hoffset, voffset + 5, k+hoffset);
						}
						if ((line & 64) != 0) {
							g.drawLine(voffset + 6, k+hoffset, voffset + 6, k+hoffset);
						}
						if ((line & 128) != 0) {
							g.drawLine(voffset + 7, k+hoffset, voffset + 7, k+hoffset);
						}
					}
					voffset += 8;
				}
				hoffset += 8;
				voffset = 0;
			}
			
		}
	}

}
