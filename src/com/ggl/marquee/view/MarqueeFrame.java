package com.ggl.marquee.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ggl.marquee.model.MarqueeModel;
import com.ggl.marquee.runnable.DisplayAllPixelsRunnable;

public class MarqueeFrame {

	private ControlPanel controlPanel;

	private DisplayAllPixelsRunnable dapRunnable;

	private JFrame frame;

	private MarqueeModel model;

	private MarqueePanel marqueePanel;

	public MarqueeFrame(MarqueeModel model) {
		this.model = model;
		model.setFrame(this);
		createPartControl();
	}

	private void createPartControl() {
		frame = new JFrame();
		// frame.setIconImage(getFrameImage());
		frame.setTitle("Marquee");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				exitProcedure();
			}
		});

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		marqueePanel = new MarqueePanel(model);
		mainPanel.add(marqueePanel);
		controlPanel = new ControlPanel(this, model);
		mainPanel.add(controlPanel.getPanel());

		frame.add(mainPanel);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.getRootPane().setDefaultButton(controlPanel.getSubmitButton());
		frame.setVisible(true);

		dapRunnable = new DisplayAllPixelsRunnable(this, model);
		new Thread(dapRunnable).start();
	}

	private void exitProcedure() {
		frame.dispose();
		System.exit(0);
	}

	public void repaintMarqueePanel() {
		marqueePanel.repaint();
	}

}
