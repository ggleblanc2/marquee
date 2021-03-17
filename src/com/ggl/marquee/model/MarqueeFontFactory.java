package com.ggl.marquee.model;

import java.awt.Font;

import javax.swing.DefaultListModel;

public class MarqueeFontFactory {

	private DefaultListModel<MarqueeFont> fontList;

	private MarqueeFont defaultFont;

	public MarqueeFontFactory() {
		this.fontList = new DefaultListModel<MarqueeFont>();
		addElements();
	}

	private void addElements() {
		this.defaultFont = new MarqueeFont(new Font("Arial", Font.BOLD, 16));
		fontList.addElement(defaultFont);
		fontList.addElement(new MarqueeFont(new Font("Cambria", Font.BOLD, 16)));
		fontList.addElement(new MarqueeFont(new Font("Courier New", Font.BOLD,
				16)));
		fontList.addElement(new MarqueeFont(new Font("Georgia", Font.BOLD, 16)));
		fontList.addElement(new MarqueeFont(new Font("Lucida Calligraphy",
				Font.BOLD, 16)));
		fontList.addElement(new MarqueeFont(new Font("Times New Roman",
				Font.BOLD, 16)));
		fontList.addElement(new MarqueeFont(new Font("Verdana", Font.BOLD, 16)));
	}

	public DefaultListModel<MarqueeFont> getFontList() {
		return fontList;
	}

	public void setDefaultFont(MarqueeFont defaultFont) {
		this.defaultFont = defaultFont;
	}

	public MarqueeFont getDefaultFont() {
		return defaultFont;
	}

	public int getCharacterHeight() {
		int maxHeight = 0;
		for (int i = 0; i < fontList.getSize(); i++) {
			MarqueeFont font = fontList.get(i);
			int height = font.getFontHeight();
			maxHeight = Math.max(height, maxHeight);
		}
		return maxHeight;
	}
}
