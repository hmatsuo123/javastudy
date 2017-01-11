package puzzle;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import puzzle.DigitalClockPanel.AnimationMode;

public class AnimationSettingsDialog extends JDialog {
	private DigitalClockPanel panel;
	private final int LEFT = 0;
	private final int RIGHT = 1;
	private JRadioButton radioOn;
	private JRadioButton radioOff;
	private ButtonGroup radioOnOffGroup;
	private JRadioButton radioSlide;
	private JRadioButton radioRandom;
	private ButtonGroup radioAnimationMode;
	private JSlider slider;
	private JButton okButton;
	private JButton cancelButton;
	private Boolean isAnimationOn;
	private int animationMoveLength;

	public AnimationSettingsDialog(JFrame owner, DigitalClockPanel panel) {
		super(owner);
		this.panel = panel;

		// Animationの設定を一時的に保持する
		isAnimationOn = panel.digitalClockAnimation.isAnimationOn;
		animationMoveLength = panel.digitalClockAnimation.moveLength;

		SettingsDialogActionListener listener = new SettingsDialogActionListener();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		// Animation ON/OFF
		JLabel onOffLabel = new JLabel("Animation ON/OFF");
		constraints.gridx = LEFT;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(onOffLabel, constraints);
		add(onOffLabel);

		JPanel radioButtonPanel = new JPanel(new GridLayout(1, 2));
		radioOnOffGroup = new ButtonGroup();
		radioOn = new JRadioButton("ON");
		radioOff = new JRadioButton("OFF");
		radioOnOffGroup.add(radioOn);
		radioOnOffGroup.add(radioOff);
		if (panel.digitalClockAnimation.isAnimationOn) {
			radioOn.setSelected(true);
		} else {
			radioOff.setSelected(true);
		}
		// radioButtonのChangeListenerではradioButtonにフォーカスを当てるだけでイベントが発生し多発してしまうためActionListnerを使用
		radioOn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.digitalClockAnimation.changeAnimationOn();
			}
		});
		radioOff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.digitalClockAnimation.changeAnimationOff();
			}
		});
		radioButtonPanel.add(radioOn);
		radioButtonPanel.add(radioOff);
		constraints.gridx = RIGHT;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(radioButtonPanel, constraints);
		add(radioButtonPanel);

		// Change Animation Mode
		JLabel animationModeLabel = new JLabel("Change Animation Mode");
		constraints.gridx = LEFT;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(animationModeLabel, constraints);
		add(animationModeLabel);

		JPanel animationModePanel = new JPanel(new GridLayout(1, 2));
		radioAnimationMode = new ButtonGroup();
		radioSlide = new JRadioButton("Slide");
		radioRandom = new JRadioButton("Random");
		radioAnimationMode.add(radioSlide);
		radioAnimationMode.add(radioRandom);
		if (panel.digitalClockAnimation.mode == AnimationMode.slideMode) {
			radioSlide.setSelected(true);
		} else if (panel.digitalClockAnimation.mode == AnimationMode.randomMode){
			radioRandom.setSelected(true);
		}
		// radioButtonのChangeListenerではradioButtonにフォーカスを当てるだけでイベントが発生し多発してしまうためActionListnerを使用
		radioSlide.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.digitalClockAnimation.mode = AnimationMode.slideMode;
			}
		});
		radioRandom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.digitalClockAnimation.mode = AnimationMode.randomMode;
			}
		});
		animationModePanel.add(radioSlide);
		animationModePanel.add(radioRandom);
		constraints.gridx = RIGHT;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(animationModePanel, constraints);
		add(animationModePanel);

		// Change Animation Speed
		JLabel changeSpeedLabel = new JLabel("Change Animation Speed");
		constraints.gridx = LEFT;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(changeSpeedLabel, constraints);
		add(changeSpeedLabel);

		slider = new JSlider(0, 40);
		slider.setValue(panel.digitalClockAnimation.moveLength);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				panel.digitalClockAnimation.moveLength = slider.getValue();
			}
		});
		slider.setMajorTickSpacing(4);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		constraints.gridx = RIGHT;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(slider, constraints);
		add(slider);

		// Button Panel
		JPanel buttonPanel = new JPanel();
		GridBagLayout buttonLayout = new GridBagLayout();
		buttonPanel.setLayout(buttonLayout);
		constraints.gridx = RIGHT;
		constraints.gridy = 3;
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
		setTitle("Animation Settings");
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
				// 設定を戻して終了
				panel.digitalClockAnimation.isAnimationOn = isAnimationOn;
				panel.digitalClockAnimation.moveLength = animationMoveLength;
				setVisible(false);
			}
		}
	}
}
