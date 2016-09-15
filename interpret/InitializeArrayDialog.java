package interpret;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InitializeArrayDialog extends JDialog{
	InterpretPanel owner;
	Class<?> cls;
	JTextField nameText;
	JTextField sizeText;

	public InitializeArrayDialog(InterpretPanel owner, Class<?> cls, String name) {
		super();
		this.setModal(true);
		this.owner = owner;
		this.cls = cls;
		//model = new DefaultListModel<String>();
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new GridLayout(9, 1));
		setSize(500, 550);
		setTitle("Initialize Array");

		// ターゲットオブジェクト情報
		JLabel objLabel = new JLabel("■Target Array");
		add(objLabel);
		JLabel objName = new JLabel(name);
		add(objName);

		// オブジェクト名
		JLabel nameLabel = new JLabel("■Array Name");
		add(nameLabel);
		nameText = new JTextField();
		add(nameText);

		// コンストラクタ
		JLabel listLabel = new JLabel("■Array Size");
		add(listLabel);
		sizeText = new JTextField();
		add(sizeText);


		// ボタン
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		JButton okButton = new JButton("OK");
		okButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						createArray();
					}
				});
		buttonPanel.add(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						setVisible(false);
					}
				});
		buttonPanel.add(cancelButton);
		add(buttonPanel);

		this.setVisible(true);
	}

	private void createArray() {
		String name = nameText.getText();
		String sizeStr = sizeText.getText();
		if (name == null || name.length() == 0) {
			JOptionPane.showMessageDialog(this, "Please input Object Name.");
			return;
		} else if (owner.isDuplicationObjectName(name)) {
			JOptionPane.showMessageDialog(this, "Duplication Object Name.");
			return;
		} else if (sizeStr == null || sizeStr.length() == 0) {
			JOptionPane.showMessageDialog(this, "Please input Array Size.");
			return;
		}

		try {
			int size = Integer.parseInt(sizeStr);
			owner.addNewArray(cls, Array.newInstance(cls, size), name, size);
			setVisible(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.toString());
			return;
		}
	}
}
