package interpret.Interpret;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewObjectDialog extends Dialog implements ActionListener{
	private TextField inputTextField;
	private Interpret owner;

	NewObjectDialog(Frame owner) {
		super(owner);
		this.owner = (Interpret)owner;

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		// 説明文
		Label newObjectLabel = new Label("生成するクラス名を入力してください。");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(newObjectLabel, constraints);
		add(newObjectLabel);

		// 入力欄
		inputTextField = new TextField("java.awt.Button");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(inputTextField, constraints);
		add(inputTextField);

		Panel buttonPanel = new Panel();
		buttonPanel.setLayout(new GridLayout(1, 2));

		//実行ボタン
		Button okButton = new Button("OK");
		okButton.addActionListener(this);
		buttonPanel.add(okButton);

		//キャンセルボタン
		Button cancelButton = new Button("キャンセル");
		cancelButton.addActionListener(this);
		buttonPanel.add(cancelButton);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(buttonPanel, constraints);
		add(buttonPanel);

		setTitle("New Object");
		setSize(300, 150);
		setLayout(layout);

		//ウィンドウを閉じる
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				setVisible(false);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "OK") {
			try {
				owner.createNewObject(Class.forName(inputTextField.getText()));
			} catch (ClassNotFoundException e1) {
				owner.showErrorDialog("ClassNotFoundException");
			}
		}
		setVisible(false);
	}
}
