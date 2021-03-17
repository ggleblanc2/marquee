package com.ggl.marquee.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.ggl.marquee.model.MarqueeModel;

public class MarqueePanel extends JPanel {

	private static final long serialVersionUID = -1677343084333836763L;

	private static final int pixelWidth = 4;
	private static final int gapWidth = 2;
	private static final int totalWidth = pixelWidth + gapWidth;
	private static final int yStart = gapWidth + totalWidth + totalWidth;

	private MarqueeModel model;

	public MarqueePanel(MarqueeModel model) {
		this.model = model;

		int width = model.getMarqueeWidth() * totalWidth + gapWidth;
		int height = model.getMarqueeHeight() * totalWidth + yStart + yStart;
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		int x = gapWidth;
		int y = yStart;

		for (int i = 0; i < model.getMarqueeWidth(); i++) {
			for (int j = 0; j < model.getMarqueeHeight(); j++) {
				if (model.getMarqueePixel(i, j)) {
					g.setColor(Color.PINK);
				} else {
					g.setColor(Color.BLACK);
				}

				g.fillRect(x, y, pixelWidth, pixelWidth);
				y += totalWidth;
			}
			y = yStart;
			x += totalWidth;
		}
	}

}
