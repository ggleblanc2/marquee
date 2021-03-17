package com.ggl.marquee.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FontGenerator {

	private static final boolean DEBUG = false;

	private Font font;

	private FontHeights fontHeights;

	private Map<Character, MarqueeCharacter> characterMap;

	public FontGenerator(Font font) {
		this.font = font;
		this.characterMap = new HashMap<Character, MarqueeCharacter>();
	}

	public void execute() {
		int width = 50;
		BufferedImage bi = generateCharacterImage(width, "B");
		int[] result1 = getCharacterHeight(bi);
		bi = generateCharacterImage(width, "g");
		int[] result2 = getCharacterHeight(bi);
		fontHeights = new FontHeights(result1[0], result1[1], result2[1]);

		if (DEBUG) System.out.println(fontHeights.getAscender() + ", "
				+ fontHeights.getBaseline() + ", "
				+ fontHeights.getDescender());

		for (int x = 32; x < 127; x++) {
			char c = (char) x;

			StringBuilder builder = new StringBuilder(3);
			builder.append('H');
			builder.append(c);
			builder.append('H');

			bi = generateCharacterImage(width, builder.toString());
			int[][] pixels = convertTo2D(bi);
			MarqueeCharacter mc = getCharacterPixels(pixels);

			if (DEBUG) {
				System.out.println(builder.toString() + " " +
						mc.getWidth() + "x" + mc.getHeight());
			}

			characterMap.put(c, mc);
		}

	}

	private BufferedImage generateCharacterImage(int width, String string) {
		BufferedImage bi = new BufferedImage(
				width, width, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		g.setFont(font);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, width);

		g.setColor(Color.BLACK);
		g.drawString(string, 0, width / 2);

		return bi;
	}

	private int[] getCharacterHeight(BufferedImage bi) {
		int[][] pixels = convertTo2D(bi);
		int minHeight = bi.getHeight();
		int maxHeight = 0;

		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				if (pixels[i][j] < -1) {
					minHeight = Math.min(i, minHeight);
					maxHeight = Math.max(i, maxHeight);
				}
			}
		}

		int[] result = new int[2];
		result[0] = minHeight;
		result[1] = maxHeight;
		return result;
	}

	private MarqueeCharacter getCharacterPixels(int[][] pixels) {
		List<Boolean[]> list = new ArrayList<Boolean[]>();
		int startRow = fontHeights.getAscender();
		int endRow = fontHeights.getDescender();
		int height = fontHeights.getCharacterHeight();
		int startColumn = getCharacterColumnStart(pixels);
		int endColumn = getCharacterColumnEnd(pixels);

		for (int i = startColumn; i <= endColumn; i++) {
			Boolean[] characterColumn = new Boolean[height];
			int k = 0;
			for (int j = startRow; j <= endRow; j++) {
				if (pixels[j][i] < -1) characterColumn[k] = true;
				else characterColumn[k] = false;
				k++;
			}
			list.add(characterColumn);
		}

		MarqueeCharacter mc = new MarqueeCharacter(list.size(), height);

		for (int i = 0; i < list.size(); i++) {
			Boolean[] characterColumn = list.get(i);
			mc.setColumn(characterColumn);
		}

		return mc;
	}

	private int getCharacterColumnStart(int[][] pixels) {
		int start = fontHeights.getAscender();
		int end = fontHeights.getBaseline();
		int letterEndFlag = 0;
		int column = 1;

		while (letterEndFlag < 1) {
			boolean pixelDetected = false;
			for (int i = start; i <= end; i++) {
				if (pixels[i][column] < -1) {
					pixelDetected = true;
				}
			}

			column++;

			// End of first letter
			if ((letterEndFlag == 0) && !pixelDetected) letterEndFlag = 1;
		}

		return column;
	}

	private int getCharacterColumnEnd(int[][] pixels) {
		int start = fontHeights.getAscender();
		int end = fontHeights.getBaseline();
		int height = fontHeights.getCharacterHeight2();
		int letterEndFlag = 0;
		int column = pixels.length - 1;

		while (letterEndFlag < 4) {
			int pixelCount = 0;
			for (int i = start; i <= end; i++) {
				if (pixels[i][column] < -1) {
					pixelCount++;
				}
			}

			column--;

			// End of first letter
			if (pixelCount >= height) letterEndFlag++;
			// Start of first letter
//			if ((letterEndFlag == 0) && (pixelCount > 0)) letterEndFlag = 1;
		}

		return column;
	}

	private int[][] convertTo2D(BufferedImage image) {
		final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
				.getData();
		final int width = image.getWidth();
		final int height = image.getHeight();

		int[][] result = new int[height][width];

		for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel++) {
			result[row][col] = pixels[pixel];
			col++;
			if (col == width) {
				col = 0;
				row++;
			}
		}

		return result;
	}

	public MarqueeCharacter getCharacter(Character c) {
		MarqueeCharacter mc = characterMap.get(c);
		return (mc == null) ? characterMap.get('?') : mc;
	}

	public int getCharacterHeight() {
		return fontHeights.getCharacterHeight();
	}
}
