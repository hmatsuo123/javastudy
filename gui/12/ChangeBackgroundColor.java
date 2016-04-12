package ex12;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class ChangeBackgroundColor  extends Dialog implements ActionListener {
	private DigitalClock12 owner;
	private Choice colorChoice;
	//TODO:共通で持つようにする
	private Map<Color, String> colorMap = new HashMap<Color, String>();
	private Map<String, Color> colorDecodeMap = new HashMap<String, Color>();

	ChangeBackgroundColor(Frame owner) {
		super(owner);
		this.owner = (DigitalClock12)owner;
		setTitle("Change Background Property");
		setSize(200, 100);
		setLayout(new GridLayout(2,1));
		//Colortest.class

		//背景色の変更欄
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

		colorChoice = new Choice();
		for (Color color : colorMap.keySet()) {
			colorChoice.add(colorMap.get(color));
		}
		colorChoice.select(colorMap.get(this.owner.backgroundColor));
		add(colorChoice);


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
		owner.backgroundColor = colorDecodeMap.get(colorChoice.getSelectedItem());
		owner.reDisplay();
		setVisible(false);
	}
}
