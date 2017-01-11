package puzzle;

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

public class ClockPropertiesDialog extends JDialog {
	private DigitalClockPanel panel;
	private final int LEFT = 0;
	private final int CENTER = 1;
	private final int RIGHT = 2;
	private String selectedFont;
	private int selectedFontSize;
	private Color selectedFontColor;

	private JButton selectColorButton;
	private JButton okButton;
	private JButton cancelButton;
	private JButton defaultButton;

	private String defaultFont;
	private int defaultFontSize;
	private Color defaultFontColor;
	private Color defaultBackgroundColor;

	JComboBox<String> fontComboBox;

	public ClockPropertiesDialog(JFrame owner, DigitalClockPanel panel) {
		super(owner);
		this.panel = panel;
		defaultFont = panel.clockFont.getFontName();
		selectedFont = panel.clockFont.getFontName();
		defaultFontSize = panel.clockFontSize;
		selectedFontSize = panel.clockFontSize;
		defaultFontColor = panel.clockFontColor;
		selectedFontColor = panel.clockFontColor;
		defaultBackgroundColor = panel.clockBackgroundColor;

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
		fontComboBox = new JComboBox<String>(combodata);
		fontComboBox.setSelectedItem(selectedFont);
		fontComboBox.addItemListener(new FontItemChangeListener());
		constraints.gridx = RIGHT;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(fontComboBox, constraints);
		add(fontComboBox);

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
		constraints.gridx = CENTER;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.EAST;
		buttonLayout.setConstraints(cancelButton, constraints);
		buttonPanel.add(cancelButton);

		// Default button
		defaultButton = new JButton("Default");
		defaultButton.addActionListener(listener);
		constraints.gridx = LEFT;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.EAST;
		buttonLayout.setConstraints(defaultButton, constraints);
		buttonPanel.add(defaultButton);

		setLayout(layout);
		setTitle("Property Settings");
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
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
				selectedFontColor = JColorChooser.showDialog(panel, "Select Font Color", Color.white);
				panel.setClockFontColer(selectedFontColor);
			} else if (e.getSource().equals(defaultButton)) {
				fontComboBox.setSelectedItem(panel.defaultClockFontName);
				selectedFontColor = panel.defaultClockColor;
				panel.setClockFontColer(selectedFontColor);
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
}
