package com.ggl.marquee.runnable;

import javax.swing.SwingUtilities;

import com.ggl.marquee.model.MarqueeModel;
import com.ggl.marquee.view.MarqueeFrame;

public class DisplayAllPixelsRunnable implements Runnable {

	private MarqueeFrame frame;

	private MarqueeModel model;

	public DisplayAllPixelsRunnable(MarqueeFrame frame, MarqueeModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void run() {
		model.setAllPixels();
		repaint();

		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {

		}

		model.resetPixels();
		repaint();
	}

	private void repaint() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.repaintMarqueePanel();
			}
		});
	}

}
