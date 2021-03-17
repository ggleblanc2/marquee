package com.ggl.marquee;

import javax.swing.SwingUtilities;

import com.ggl.marquee.model.MarqueeModel;
import com.ggl.marquee.view.MarqueeFrame;

public class Marquee implements Runnable {

	@Override
	public void run() {
		new MarqueeFrame(new MarqueeModel());
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Marquee());
	}

}
