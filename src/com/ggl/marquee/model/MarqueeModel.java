package com.ggl.marquee.model;

import javax.swing.DefaultListModel;

import com.ggl.marquee.runnable.DisplayTextPixelsRunnable;
import com.ggl.marquee.view.MarqueeFrame;

public class MarqueeModel {

	private static final int marqueeWidth = 120;

	private boolean[][] marqueePixels;
	private boolean[][] textPixels;

	private DisplayTextPixelsRunnable dtpRunnable;

	private MarqueeFontFactory fonts;

	private MarqueeFrame frame;

	public MarqueeModel() {
		this.fonts = new MarqueeFontFactory();
		this.marqueePixels = new boolean[marqueeWidth][getMarqueeHeight()];
	}

	public void setFrame(MarqueeFrame frame) {
		this.frame = frame;
	}

	public MarqueeFontFactory getFonts() {
		return fonts;
	}

	public DefaultListModel<MarqueeFont> getDefaultListModel() {
		return fonts.getFontList();
	}

	public MarqueeFont getDefaultFont() {
		return fonts.getDefaultFont();
	}

	public void setDefaultFont(MarqueeFont defaultFont) {
		fonts.setDefaultFont(defaultFont);
	}

	public boolean[][] getMarqueePixels() {
		return marqueePixels;
	}

	public boolean getMarqueePixel(int width, int height) {
		return marqueePixels[width][height];
	}

	public int getMarqueeWidth() {
		return marqueeWidth;
	}

	public int getMarqueeHeight() {
		return fonts.getCharacterHeight();
	}

	public boolean[][] getTextPixels() {
		return textPixels;
	}

	public int getTextPixelWidth() {
		return textPixels.length;
	}

	private void startDtpRunnable() {
		dtpRunnable = new DisplayTextPixelsRunnable(frame, this);
		new Thread(dtpRunnable).start();
	}

	public void stopDtpRunnable() {
		if (dtpRunnable != null) {
			dtpRunnable.stopDisplayTextPixelsRunnable();
			dtpRunnable = null;
		}
	}

	public void setTextPixels(boolean[][] textPixels) {
		this.textPixels = textPixels;
		if (textPixels.length < getMarqueeWidth()) {
			this.marqueePixels = copyCharacterPixels(0, textPixels,
					marqueePixels);
		} else {
			startDtpRunnable();
		}
	}

	public void resetPixels() {
		for (int i = 0; i < getMarqueeWidth(); i++) {
			for (int j = 0; j < getMarqueeHeight(); j++) {
				marqueePixels[i][j] = false;
			}
		}
	}

	public void setAllPixels() {
		for (int i = 0; i < getMarqueeWidth(); i++) {
			for (int j = 0; j < getMarqueeHeight(); j++) {
				marqueePixels[i][j] = true;
			}
		}
	}

	public boolean[][] copyCharacterPixels(int position,
			boolean[][] characterPixels, boolean[][] textPixels) {
		for (int i = 0; i < characterPixels.length; i++) {
			for (int j = 0; j < characterPixels[i].length; j++) {
				textPixels[i + position][j] = characterPixels[i][j];
			}
		}

		return textPixels;
	}

	public void copyTextPixels(int position) {
		for (int i = 0; i < marqueePixels.length; i++) {
			int k = i + position;
			k %= textPixels.length;
			for (int j = 0; j < textPixels[i].length; j++) {
				marqueePixels[i][j] = textPixels[k][j];
			}
		}
	}

}
