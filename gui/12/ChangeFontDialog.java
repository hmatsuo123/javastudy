package ex12;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class ChangeFontDialog extends Dialog implements ActionListener {
	private DigitalClock12 owner;
	private TextField FontSizeTextField;
	private Choice fontChoice;
	private Choice colorChoice;
	//TODO:共通で持つようにする
	private Map<Color, String> colorMap = new HashMap<Color, String>();
	private Map<String, Color> colorDecodeMap = new HashMap<String, Color>();

	ChangeFontDialog(Frame owner) {
		super(owner);
		this.owner = (DigitalClock12)owner;
		setTitle("Change Font Property");
		setSize(230, 150);
		setLayout(new GridLayout(4,1));

		//フォントサイズの変更欄
		Panel changeFontSizePanel = new Panel();
		changeFontSizePanel.setLayout(new GridLayout(1, 2));
		Label sizeLabel = new Label("Font size [px] ");
		changeFontSizePanel.add(sizeLabel);
		FontSizeTextField = new TextField(String.valueOf(this.owner.fontSize), 5);
		FontSizeTextField.addActionListener(this);
		changeFontSizePanel.add(FontSizeTextField);
		add(changeFontSizePanel);

		//フォントの種類変更欄
		Panel changeFontFamilyPanel = new Panel();
		changeFontFamilyPanel.setLayout(new GridLayout(1, 2));
		Label familyLabel = new Label("Font Family ");
		changeFontFamilyPanel.add(familyLabel);
		fontChoice = new Choice();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fs = ge.getAvailableFontFamilyNames();
		for (String font : fs) {
			fontChoice.add(font);
		}
		fontChoice.select(this.owner.fontFamily);
		changeFontFamilyPanel.add(fontChoice);
		add(changeFontFamilyPanel);

		//フォントの色変更欄
		colorMap.put(Color.BLACK, "BLACK");
		colorMap.put(Color.BLUE, "BLUE");
		colorMap.put(Color.CYAN, "CYAN");
		colorMap.put(Color.DARK_GRAY, "DARK_GRAY");
		colorMap.put(Color.GRAY, "GRAY");
		colorMap.put(Color.GREEN, "GREEN");
		colorMap.put(Color.LIGHT_GRAY, "LIGHT_GRAY");
		colorMap.put(Color.MAGENTA, "MAGENTA");
		colorMap.put(Color.ORANGE, "ORANGE");
		colorMap.put(Color.PINK, "PINK");
		colorMap.put(Color.RED, "RED");
		colorMap.put(Color.WHITE, "WHITE");
		colorMap.put(Color.YELLOW, "YELLOW");

		colorDecodeMap.put("BLACK", Color.BLACK);
		colorDecodeMap.put("BLUE", Color.BLUE);
		colorDecodeMap.put("CYAN", Color.CYAN);
		colorDecodeMap.put("DARK_GRAY", Color.DARK_GRAY);
		colorDecodeMap.put("GRAY", Color.GRAY);
		colorDecodeMap.put("GREEN", Color.GREEN);
		colorDecodeMap.put("LIGHT_GRAY", Color.LIGHT_GRAY);
		colorDecodeMap.put("MAGENTA", Color.MAGENTA);
		colorDecodeMap.put("ORANGE", Color.ORANGE);
		colorDecodeMap.put("PINK", Color.PINK);
		colorDecodeMap.put("RED", Color.RED);
		colorDecodeMap.put("WHITE", Color.WHITE);
		colorDecodeMap.put("YELLOW", Color.YELLOW);

		Panel changeFontColorPanel = new Panel();
		changeFontColorPanel.setLayout(new GridLayout(1, 2));
		Label colorLabel = new Label("Font Color ");
		changeFontColorPanel.add(colorLabel);
		colorChoice = new Choice();
		for (Color color : colorMap.keySet()) {
			colorChoice.add(colorMap.get(color));
		}
		colorChoice.select(colorMap.get(this.owner.fontColor));
		changeFontColorPanel.add(colorChoice);
		add(changeFontColorPanel);

		//実行ボタン
		Button button = new Button("OK");
		button.addActionListener(this);
		add(button);

		//ウィンドウを閉じる
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				setVisible(false);
			}
		});
	}
	public void actionPerformed(ActionEvent e) {
		try {
			int size = Integer.parseInt(FontSizeTextField.getText());
			owner.fontSize = Integer.parseInt(FontSizeTextField.getText());
			owner.fontFamily = fontChoice.getSelectedItem();
			owner.fontColor = colorDecodeMap.get(colorChoice.getSelectedItem());
			owner.reDisplay();
		} catch (NumberFormatException nfe) {
			//TODO:エラーを表示する
			return;
		}
		setVisible(false);
	}
}
