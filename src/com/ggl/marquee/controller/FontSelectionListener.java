package com.ggl.marquee.controller;

import javax.swing.DefaultListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ggl.marquee.model.MarqueeFont;
import com.ggl.marquee.model.MarqueeModel;

public class FontSelectionListener implements ListSelectionListener {

	private MarqueeModel model;

	public FontSelectionListener(MarqueeModel model) {
		this.model = model;
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		DefaultListSelectionModel selectionModel = (DefaultListSelectionModel) event
				.getSource();
		if (!event.getValueIsAdjusting()) {
			int index = selectionModel.getMinSelectionIndex();
			if (index >= 0) {
				MarqueeFont font = model.getDefaultListModel().get(index);
				model.setDefaultFont(font);
			}
		}
	}

}
