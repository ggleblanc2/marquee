package com.ggl.marquee.view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.ggl.marquee.controller.CreateMarqueeActionListener;
import com.ggl.marquee.controller.FontSelectionListener;
import com.ggl.marquee.model.MarqueeFont;
import com.ggl.marquee.model.MarqueeModel;

public class ControlPanel {

	private JButton submitButton;

	private JPanel panel;

	private MarqueeFrame frame;

	private MarqueeModel model;

	public ControlPanel(MarqueeFrame frame, MarqueeModel model) {
		this.frame = frame;
		this.model = model;
		createPartControl();
	}

	private void createPartControl() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JPanel fontPanel = new JPanel();
		fontPanel.setLayout(new BorderLayout());

		JLabel fontLabel = new JLabel("Fonts");
		fontPanel.add(fontLabel, BorderLayout.NORTH);

		JList<MarqueeFont> fontList = new JList<MarqueeFont>(
				model.getDefaultListModel());
		fontList.setSelectedValue(model.getDefaultFont(), true);
		fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontList.setVisibleRowCount(3);

		ListSelectionModel listSelectionModel = fontList.getSelectionModel();
		listSelectionModel.addListSelectionListener(new FontSelectionListener(
				model));

		JScrollPane fontScrollPane = new JScrollPane(fontList);

		fontPanel.add(fontScrollPane, BorderLayout.CENTER);

		panel.add(fontPanel);

		JPanel fieldPanel = new JPanel();

		JLabel fieldLabel = new JLabel("Marquee Text: ");
		fieldPanel.add(fieldLabel);

		JTextField field = new JTextField(30);
		fieldPanel.add(field);

		panel.add(fieldPanel);

		JPanel buttonPanel = new JPanel();

		submitButton = new JButton("Submit");
		submitButton.addActionListener(new CreateMarqueeActionListener(frame,
				model, field));
		buttonPanel.add(submitButton);

		panel.add(buttonPanel);
	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getSubmitButton() {
		return submitButton;
	}

}
