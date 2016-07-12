package interpret;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ControlObjectPanel extends JPanel{
	private JLabel selectedObjectNameLabel;
	JList<String> fieldList;
	JList<String> methodList;

	public ControlObjectPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setSize(500, 500);
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(2, 2, 2, 2));

		JLabel description = new JLabel("選択したオブジェクト・配列を操作できます。");
		description.setHorizontalAlignment(JLabel.LEFT);
		//add(description);
		JPanel objectInfoPanel = new JPanel();
		objectInfoPanel.setBackground(Color.WHITE);
		JPanel tmp = new JPanel();
		tmp.setBackground(Color.WHITE);
		tmp.setBorder(new TitledBorder("オブジェクト情報"));
		//selectedObjectNameLabel = new JLabel("   public class ObjectControllPanel extends JPanel");
		selectedObjectNameLabel = new JLabel("                                                           ");
		tmp.add(selectedObjectNameLabel);
		objectInfoPanel.add(tmp);
		add(objectInfoPanel);

		JPanel controllPanel = new JPanel(new GridLayout(2, 1));
		controllPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		controllPanel.setBackground(Color.WHITE);
		controllPanel.add(new JLabel("                                                                                                          "));
		JTabbedPane tabPanel = new JTabbedPane();
		tabPanel.setSize(490, 490);
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(2, 1));
		// テストデータ
		String[] initData = {"test", "test", "test"};
		fieldList = new JList<String>(initData);
		fieldPanel.add(fieldList);
		JLabel buf = new JLabel("                         ");
		fieldPanel.add(buf);
		tabPanel.addTab("Field", fieldPanel);
		JPanel methodPanel = new JPanel();
		methodPanel.setLayout(new GridLayout(4, 1));
		methodList = new JList<String>(initData);
		methodPanel.add(methodList);
		JLabel parameterLabel = new JLabel("Parameter");
		methodPanel.add(parameterLabel);
		JTextField parameterText = new JTextField();
		methodPanel.add(parameterText);
		JButton invokeButton = new JButton("Invoke");
		methodPanel.add(invokeButton);
		tabPanel.addTab("Method", methodPanel);
		controllPanel.add(tabPanel);
		add(controllPanel);
	}

	@Override
	public void paintComponent(Graphics g) {
	}

	public void showControlObjectPanel(String objName) {
		selectedObjectNameLabel.setText(objName);
	}
}
