package com.ggl.marquee.runnable;

import javax.swing.SwingUtilities;

import com.ggl.marquee.model.MarqueeModel;
import com.ggl.marquee.view.MarqueeFrame;

public class DisplayTextPixelsRunnable implements Runnable {

	private static int textPixelPosition;

	private volatile boolean running;

	private MarqueeFrame frame;

	private MarqueeModel model;

	public DisplayTextPixelsRunnable(MarqueeFrame frame, MarqueeModel model) {
		this.frame = frame;
		this.model = model;
		textPixelPosition = 0;
	}

	@Override
	public void run() {
		this.running = true;
		while (running) {
			model.copyTextPixels(textPixelPosition);
			repaint();
			sleep();
			textPixelPosition++;
			textPixelPosition %= model.getTextPixelWidth();
		}
	}

	private void repaint() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.repaintMarqueePanel();
			}
		});
	}

	private void sleep() {
		try {
			Thread.sleep(50L);
		} catch (InterruptedException e) {

		}
	}

	public synchronized void stopDisplayTextPixelsRunnable() {
		this.running = false;
	}

}
