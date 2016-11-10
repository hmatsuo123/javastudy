package ex24;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewSettingsDialog extends JDialog {
	private DigitalClockPanel panel;
	private final int LEFT = 0;
	private final int RIGHT = 1;
	private String selectedFont;
	private int selectedFontSize;
	private Color selectedFontColor;
	private Color selectedBackgroundColor;

	private JButton selectColorButton;
	private JButton selectBackgroundColorButton;
	private JButton okButton;
	private JButton cancelButton;

	private String defaultFont;
	private int defaultFontSize;
	private Color defaultFontColor;
	private Color defaultBackgroundColor;

	public ViewSettingsDialog(JFrame owner, DigitalClockPanel panel) {
		super(owner);
		this.panel = panel;
		defaultFont = panel.clockFont.getFontName();
		selectedFont = panel.clockFont.getFontName();
		defaultFontSize = panel.clockFontSize;
		selectedFontSize = panel.clockFontSize;
		defaultFontColor = panel.clockFontColor;
		selectedFontColor = panel.clockFontColor;
		defaultBackgroundColor = panel.clockBackgroundColor;
		selectedBackgroundColor = panel.clockBackgroundColor;

		SettingsDialogActionListener listener = new SettingsDialogActionListener();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		// Font
		JLabel fontLabel = new JLabel("Font");
		constraints.gridx = LEFT;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(fontLabel, constraints);
		add(fontLabel);

		Vector<String> combodata = new Vector<String>();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		for (Font font : ge.getAllFonts()) {
			combodata.add(font.getName());
		}
		JComboBox<String> fontComboBox = new JComboBox(combodata);
		fontComboBox.setSelectedItem(selectedFont);
		fontComboBox.addItemListener(new FontItemChangeListener());
		constraints.gridx = RIGHT;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(fontComboBox, constraints);
		add(fontComboBox);

		// Font Size
		JLabel fontSizeLabel = new JLabel("Font Size");
		constraints.gridx = LEFT;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(fontSizeLabel, constraints);
		add(fontSizeLabel);

		Vector<String> combodata2 = new Vector<String>();
		combodata2.add("100");
		combodata2.add("200");
		combodata2.add("300");
		JComboBox<String> fontSizeComboBox = new JComboBox(combodata2);
		fontSizeComboBox.setSelectedItem(String.valueOf(selectedFontSize));
		fontSizeComboBox.addItemListener(new FontSizeItemChangeListener());
		constraints.gridx = RIGHT;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(fontSizeComboBox, constraints);
		add(fontSizeComboBox);

		// Font Color
		JLabel fontColorLabel = new JLabel("Font Color");
		constraints.gridx = LEFT;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(fontColorLabel, constraints);
		add(fontColorLabel);

		selectColorButton = new JButton("Select");
		selectColorButton.addActionListener(listener);
		constraints.gridx = RIGHT;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(selectColorButton, constraints);
		add(selectColorButton);

		// Background Color
		JLabel backgroundColorLabel = new JLabel("Background Color");
		constraints.gridx = LEFT;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(backgroundColorLabel, constraints);
		add(backgroundColorLabel);

		selectBackgroundColorButton = new JButton("Select");
		selectBackgroundColorButton.addActionListener(listener);
		constraints.gridx = RIGHT;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(selectBackgroundColorButton, constraints);
		add(selectBackgroundColorButton);

		// Button Panel
		JPanel buttonPanel = new JPanel();
		GridBagLayout buttonLayout = new GridBagLayout();
		buttonPanel.setLayout(buttonLayout);
		constraints.gridx = RIGHT;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(buttonPanel, constraints);
		add(buttonPanel);

		// OK button
		okButton = new JButton("OK");
		okButton.addActionListener(listener);
		constraints.gridx = RIGHT;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.EAST;
		buttonLayout.setConstraints(okButton, constraints);
		buttonPanel.add(okButton);

		// Cancel button
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(listener);
		constraints.gridx = LEFT;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.EAST;
		buttonLayout.setConstraints(cancelButton, constraints);
		buttonPanel.add(cancelButton);

		setLayout(layout);
		setTitle("Property Settings");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private class SettingsDialogActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(okButton)) {
				setVisible(false);
			} else if (e.getSource().equals(cancelButton)) {
				panel.setClockFont(defaultFont, defaultFontSize);
				panel.setClockFontColer(defaultFontColor);
				panel.setBackgroundColor(defaultBackgroundColor);
				setVisible(false);
			} else if (e.getSource().equals(selectColorButton)) {
				JColorChooser colorchooser = new JColorChooser();
				selectedFontColor = JColorChooser.showDialog(panel, "Select Font Color", Color.white);
				panel.setClockFontColer(selectedFontColor);
			} else if (e.getSource().equals(selectBackgroundColorButton)) {
				JColorChooser colorchooser = new JColorChooser();
				selectedBackgroundColor = JColorChooser.showDialog(panel, "Select Background Color", Color.white);
				panel.setBackgroundColor(selectedBackgroundColor);
			}
		}
	}

	private class FontItemChangeListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				selectedFont = event.getItem().toString();
				panel.setClockFont(selectedFont, selectedFontSize);
			}
		}
	}

	private class FontSizeItemChangeListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				selectedFontSize = Integer.parseInt(event.getItem().toString());
				panel.setClockFont(selectedFont, selectedFontSize);
			}
		}
	}
}
