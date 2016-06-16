package interpret.Interpret;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;

public class ChangeFieldDialog extends Dialog implements ActionListener{
	private Interpret owner;
	private TextField inputTextField;
	private Object newObject;
	private Field field;

	public ChangeFieldDialog(Frame owner, Object obj, Field field) {
		super(owner);
		this.owner = (Interpret)owner;
		this.newObject = obj;
		this.field = field;

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		// 説明文
		Label messageLabel = new Label("■フィールド値を修正します。");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(messageLabel, constraints);
		add(messageLabel);

		// インスタンス名
		Label targetObjectLabel = new Label("対象インスタンス名： ");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(targetObjectLabel, constraints);
		add(targetObjectLabel);

		Label targetObject = new Label(obj.getClass().getName());
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(targetObject, constraints);
		add(targetObject);

		// フィールド名
		Label targetFieldLabel = new Label("対象フィールド名： ");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(targetFieldLabel, constraints);
		add(targetFieldLabel);

		Label targetField = new Label(field.getClass().getName());
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(targetField, constraints);
		add(targetField);

		// フィールド値
		Label targetFieldValueLabel = new Label("対象フィールド値[型, 値]： ");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(targetFieldValueLabel, constraints);
		add(targetFieldValueLabel);

		Label targetFieldValue = new Label();
		try {
			targetFieldValue.setText("[ " + field.getType().toString() + ", " + field.get(obj).toString() + " ]");
		} catch (IllegalArgumentException e) {
			this.owner.showErrorDialog("IllegalArgumentException");
			return;
		} catch (IllegalAccessException e) {
			this.owner.showErrorDialog("IllegalAccessException");
			return;
		}
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(targetFieldValue, constraints);
		add(targetFieldValue);

		// 修正値
		Label changeFieldLabel = new Label("修正後のフィールド値： ");
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(changeFieldLabel, constraints);
		add(changeFieldLabel);

		inputTextField = new TextField("                            ");
		inputTextField.setText("");
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(inputTextField, constraints);
		add(inputTextField);

		//OKボタン
		Button okButton = new Button("OK");
		okButton.addActionListener(this);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(okButton, constraints);
		add(okButton);

		//閉じるボタン
		Button closeButton = new Button("閉じる");
		closeButton.addActionListener(this);
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(closeButton, constraints);
		add(closeButton);

		setTitle("Change Field");
		setSize(500, 250);
		setLayout(layout);
		setVisible(true);

		//ウィンドウを閉じる
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				setVisible(false);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "OK") {
			if (inputTextField.getText() == null) {
				return;
			}
			owner.changeFieldValue(newObject, field, field.getType(), inputTextField.getText());
		}
		setVisible(false);
	}
}
