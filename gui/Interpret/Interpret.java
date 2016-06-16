package interpret.Interpret;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;

public class Interpret extends Frame implements ActionListener{
	private Object newObj;
	Label newObjectName;
	Choice newObjectFieldChoice;

	enum MenuContents {
		FILE("File"),
		NEWOBJECT("New Object"),
		EXIT("Exit");

		//UI上に表示する文字
		String text;

		MenuContents(String text) {
			this.text = text;
		}

		String getText() {
			return text;
		}
	}

	Interpret(String frameTitle) {
		super(frameTitle);

		MenuBar menuBar = new MenuBar();
		setMenuBar(menuBar);
		// [File]
		Menu menuFile = new Menu(MenuContents.FILE.getText());
		menuFile.addActionListener(this);
		menuBar.add(menuFile);
		// [File]-[Property]
		MenuItem menuProperty = new MenuItem(MenuContents.NEWOBJECT.getText());
		menuProperty.addActionListener(this);
		menuFile.add(menuProperty);
		// [File]-[----]
		menuFile.addSeparator();
		// [File]-[Exit]
		MenuItem menuExit = new MenuItem("Exit");
		menuFile.add(menuExit);


		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		/* オブジェクト情報欄 */
		// インスタンス名
		Label newObjectNameLabel = new Label("生成したインスタンス： ");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(newObjectNameLabel, constraints);
		add(newObjectNameLabel);

		newObjectName = new Label("                                                         ");
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(newObjectName, constraints);
		add(newObjectName);

		// フィールド情報
		Label newObjectFieldLabel = new Label("フィールド： ");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(newObjectFieldLabel, constraints);
		add(newObjectFieldLabel);

		newObjectFieldChoice = new Choice();
		newObjectFieldChoice.add("                                                           ");
		newObjectFieldChoice.addItemListener(new SelectObjectFieldActionListener(this));
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(newObjectFieldChoice, constraints);
		add(newObjectFieldChoice);

		Button changeFieldButton = new Button("フィールド値変更");
		changeFieldButton.addActionListener(this);
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(changeFieldButton, constraints);
		add(changeFieldButton);

		setSize(700, 500);
		setLayout(layout);
		setVisible(true);

		//ウィンドウを閉じる
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				dispose();
				System.exit(0);
			}
		});
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		Font font = new Font("Arial", Font.BOLD, 30);
		g2d.setFont(font);

	}

	public static void main(String [] args) {
		new Interpret("Interpret");
	}

	//メニュー選択時の操作
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == MenuContents.NEWOBJECT.getText()) {
			System.out.println(e.getActionCommand());
			new NewObjectDialog(this).setVisible(true);
		} else if (e.getActionCommand() == MenuContents.EXIT.getText()) {
			dispose();
			System.exit(0);
		} else if (e.getActionCommand() == "フィールド値変更") {
			try {
				Field field = newObj.getClass().getField(newObjectFieldChoice.getSelectedItem());
				new ChangeFieldDialog(this, newObj, field).setVisible(true);
			} catch (NoSuchFieldException e1) {
				showErrorDialog("NoSuchFieldException");
			} catch (SecurityException e2) {
				showErrorDialog("SecurityException");
			}
		}
	}

	public class SelectObjectFieldActionListener implements ItemListener {
		private Interpret owner;

		public SelectObjectFieldActionListener(Interpret owner) {
			this.owner = owner;
		}
		@Override
		public void itemStateChanged(ItemEvent e) {


		}
	}

	public void showErrorDialog(String message) {
		new ErrorDialog(this, message);
	}

	public void createNewObject(Class<?> cls) {
		try {
			newObj = cls.newInstance();
			newObjectName.setText(cls.getName());

			newObjectFieldChoice.removeAll();
			for (Field field : cls.getFields()) {
				newObjectFieldChoice.add(field.getName());
			}
		} catch (InstantiationException e) {
			setVisible(false);
			showErrorDialog("InstantiationException");
		} catch (IllegalAccessException e) {
			setVisible(false);
			showErrorDialog("IllegalAccessException");
		}
	}

	public void changeFieldValue(Object obj, Field field, Class<?> valueCls, String value) {
		try {
			//field.set(newObj, valueCls.cast(value));
			field.set(newObj, Integer.parseInt(value));
		}/* catch (NoSuchFieldException e1) {
			showErrorDialog("NoSuchFieldException");
		}*/ catch (SecurityException e2) {
			showErrorDialog("SecurityException");
		} catch (IllegalArgumentException e2) {
			showErrorDialog("IllegalArgumentException");
		} catch (IllegalAccessException e2) {
			showErrorDialog("IllegalAccessException");
		}
	}
}
