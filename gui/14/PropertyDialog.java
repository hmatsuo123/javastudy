package ex14;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PropertyDialog extends Dialog implements ActionListener{
	private DigitalClock owner;
	private Choice fontSizeChoice;
	private Choice fontChoice;
	private Choice fontColorChoice;
	private Choice backGroundColorChoice;

	private int bufFontSize;
	private String bufFontFamily;
	private Color bufFontColor;
	private Color bufBackgroundColor;

	//フォントサイズ
	enum FontSizeConstant {
		SMALL("SMALL", 10),
		BIG("BIG", 30),
		LARGE("LARGE", 60);

		int size;
		String text;

		FontSizeConstant(String text, int size) {
			this.text = text;
			this.size = size;
		}

		int getSize() {
			return size;
		}

		String getText() {
			return text;
		}
	}

	PropertyDialog(Frame owner, int fontSize, String fontFamily, Color fontColor, Color backGroundColor) {
		super(owner);
		this.owner = (DigitalClock)owner;
		this.bufFontSize = fontSize;
		this.bufFontFamily = fontFamily;
		this.bufFontColor = fontColor;
		this.bufBackgroundColor = backGroundColor;

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		//フォントの種類変更欄
		Label fontFamilyLabel = new Label("Font Family ");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(fontFamilyLabel, constraints);
		add(fontFamilyLabel);

		fontChoice = new Choice();
		fontChoice.addItemListener(new ChangeFontActionListener(this.owner));
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fs = ge.getAvailableFontFamilyNames();
		for (String font : fs) {
			fontChoice.add(font);
		}
		fontChoice.select(this.owner.fontFamily);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(fontChoice, constraints);
		add(fontChoice);

		//フォントサイズの変更欄
		Label fontSizeLabel = new Label("Font size ");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(fontSizeLabel, constraints);
		add(fontSizeLabel);

		fontSizeChoice = new Choice();
		fontSizeChoice.add(FontSizeConstant.SMALL.getText());
		fontSizeChoice.add(FontSizeConstant.BIG.getText());
		fontSizeChoice.add(FontSizeConstant.LARGE.getText());
		String fontSizeString = FontSizeConstant.BIG.getText();
		if (FontSizeConstant.SMALL.getSize() == this.owner.fontSize) {
			fontSizeString = FontSizeConstant.SMALL.getText();
		} else if (FontSizeConstant.BIG.getSize() == this.owner.fontSize) {
			fontSizeString = FontSizeConstant.BIG.getText();
		} else if (FontSizeConstant.LARGE.getSize() == this.owner.fontSize) {
			fontSizeString = FontSizeConstant.LARGE.getText();
		}
		fontSizeChoice.select(fontSizeString);
		fontSizeChoice.addItemListener(new ChangeFontSizeActionListener(this.owner));
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(fontSizeChoice, constraints);
		add(fontSizeChoice);

		//フォントの色変更欄
		Label fontColorLabel = new Label("Font Color ");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(fontColorLabel, constraints);
		add(fontColorLabel);

		fontColorChoice = new Choice();
		fontColorChoice.addItemListener(new ChangeFontColorActionListener(this.owner));
		for (Color color : this.owner.colorMap.keySet()) {
			fontColorChoice.add(this.owner.colorMap.get(color));
		}
		fontColorChoice.select(this.owner.colorMap.get(this.owner.fontColor));
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(fontColorChoice, constraints);
		add(fontColorChoice);

		//背景色の変更欄
		Label backGroundColorLabel = new Label("Back Ground Color ");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(backGroundColorLabel, constraints);
		add(backGroundColorLabel);

		backGroundColorChoice = new Choice();
		backGroundColorChoice.addItemListener(new ChangeBackgroundColorActionListener(this.owner));
		for (Color color : this.owner.colorMap.keySet()) {
			backGroundColorChoice.add(this.owner.colorMap.get(color));
		}
		backGroundColorChoice.select(this.owner.colorMap.get(this.owner.backgroundColor));
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(backGroundColorChoice, constraints);
		add(backGroundColorChoice);

		//実行ボタン
		Button okButton = new Button("OK");
		okButton.addActionListener(this);
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(okButton, constraints);
		add(okButton);

		//キャンセルボタン
		Button cancelButton = new Button("キャンセル");
		cancelButton.addActionListener(this);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(cancelButton, constraints);
		add(cancelButton);

		setTitle("Change Font Property");
		setSize(350, 170);
		setLayout(layout);

		//ウィンドウを閉じる
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				cancelChageProperty();
				setVisible(false);
			}
		});
	}

	public static int getFontSize(String enumName) {
		return FontSizeConstant.valueOf(enumName).getSize();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "キャンセル") {
			cancelChageProperty();
		}
		setVisible(false);
	}

	private void cancelChageProperty() {
		owner.fontSize = bufFontSize;
		owner.fontFamily = bufFontFamily;
		owner.fontColor = bufFontColor;
		owner.backgroundColor = bufBackgroundColor;
		owner.reDisplay();
	}
}
