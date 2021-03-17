package com.ggl.marquee.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import com.ggl.marquee.model.MarqueeModel;
import com.ggl.marquee.view.MarqueeFrame;

public class CreateMarqueeActionListener implements ActionListener {

	private JTextField field;

	private MarqueeFrame frame;

	private MarqueeModel model;

	public CreateMarqueeActionListener(MarqueeFrame frame, MarqueeModel model,
			JTextField field) {
		this.frame = frame;
		this.model = model;
		this.field = field;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		model.stopDtpRunnable();
		model.resetPixels();

		String s = field.getText().trim();
		if (s.equals("")) {
			frame.repaintMarqueePanel();
			return;
		}

		s = " " + s + "    ";
		model.setTextPixels(model.getDefaultFont().getTextPixels(s));
		frame.repaintMarqueePanel();
	}

}
