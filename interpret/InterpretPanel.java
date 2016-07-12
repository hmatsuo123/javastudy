package interpret;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InterpretPanel extends JPanel{
	JFrame owner;
	ObjectTreePanel objectTreePanel;
	ControlObjectPanel controlObjPane;
	private List<Object> objList;
	private JTextArea console;

	class ObjectInfo {
		protected final Object object;
		protected final String name;

		public ObjectInfo(Object object, String name) {
			this.object = object;
			this.name = name;
		}
	}

	public InterpretPanel(JFrame owner) {
		super();
		this.owner = owner;
		objList = new ArrayList<Object>();
		setLayout(new BorderLayout());

		JLabel description = new JLabel("作成したオブジェクトと配列に対する操作を行うことができます。");
		//add(description, BorderLayout.PAGE_START);

		// オブジェクトツリー欄
		objectTreePanel = new ObjectTreePanel(this);
		add(objectTreePanel, BorderLayout.WEST);

		// オブジェクト操作欄
		controlObjPane = new ControlObjectPanel();
		add(controlObjPane, BorderLayout.CENTER);

		// コンソール
		console = new JTextArea("");
		console.setForeground(Color.WHITE);
		console.setBackground(Color.BLACK);
		add(console, BorderLayout.SOUTH);
		console.setPreferredSize(new Dimension(200, 120));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.CYAN);
	};

	/*@Override
	public Dimension getPreferredSize() {
		return panelSize;
	}*/

	public void addNewObject(Class<?> cls, Object instance, String name) {
		objList.add(new ObjectInfo(instance, name));
		objectTreePanel.showNewObject(name + "(" + cls.getName() + ")");
	}

	public void showMessageToConsole(String command, Boolean isSuccess, String message) {
		if (isSuccess) {
			console.setText("■命令" + System.getProperty("line.separator") + "   " + command + "\n■結果" + System.getProperty("line.separator") + "   " + "success");
		} else {
			console.setText("■命令" + System.getProperty("line.separator") + "   " + command + "\n■結果" + System.getProperty("line.separator") + "   " + "error: " + message);
		}

	}

	public void changeControlObjectPanel(String name) {
		String regex = "\\((.+?)\\)";
		List<String> list = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		while (matcher.find()) {
		    list.add(matcher.group(1));
		}
		try {
			Class<?> c =Class.forName(list.get(0));
			controlObjPane.showControlObjectPanel(c.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
