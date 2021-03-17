package com.ggl.marquee.model;

import java.security.InvalidParameterException;

public class MarqueeCharacter {
	
	private static int columnCount;
	
	private int height;
	private int width;
	
	private boolean[][] pixels;

	public MarqueeCharacter(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new boolean[width][height];
		columnCount = 0;
	}
	
	public void setColumn(Boolean[] value) {
		int height = value.length;
		if (this.height != height) {
			String s = "The number of values must equal the column height - "
					+ this.height;
			throw new InvalidParameterException(s);
		}
		
		for (int i = 0; i < height; i++) {
			pixels[columnCount][i] = value[i];
		}
		
		columnCount++;
	}

	public boolean[][] getPixels() {
		return pixels;
	}
	
	public boolean isComplete() {
		return (width == columnCount);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
}
