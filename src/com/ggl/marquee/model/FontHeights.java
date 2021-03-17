package com.ggl.marquee.model;

public class FontHeights {
	
	private final int ascender;
	private final int baseline;
	private final int descender;
	
	public FontHeights(int ascender, int baseline, int descender) {
		this.ascender = ascender;
		this.baseline = baseline;
		this.descender = descender;
	}

	public int getCharacterHeight() {
		return descender - ascender + 1;
	}
	
	public int getCharacterHeight2() {
		return baseline - ascender + 1;
	}

	public int getAscender() {
		return ascender;
	}

	public int getBaseline() {
		return baseline;
	}

	public int getDescender() {
		return descender;
	}

}
