package interpret;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ObjectTreePanel extends JPanel implements ActionListener, ListSelectionListener{
	private InterpretPanel owner;
	private DefaultListModel<String> model;
	JList<String> list;

	enum ButtonContents {
		NEWOBJECT("New Object"),
		NEWARRAY("New Array");

		//UI上に表示する文字
		String text;

		ButtonContents(String text) {
			this.text = text;
		}

		String getText() {
			return text;
		}
	}

	public ObjectTreePanel(InterpretPanel owner) {
		super();
		this.owner = owner;
		model = new DefaultListModel<String>();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JButton newObjButton = new JButton(ButtonContents.NEWOBJECT.getText());
		newObjButton.addActionListener(this);
		buttonPanel.add(newObjButton);
		JButton newArrayButton = new JButton(ButtonContents.NEWARRAY.getText());
		newArrayButton.addActionListener(this);
		buttonPanel.add(newArrayButton);
		add(buttonPanel);

		// テストデータ
		/*String[] testData = {"AAA", "BBB", "CCC"};
		Object[] treeData = {testData, "obj2", "obj3", "obj4"};
		tree = new JTree(treeData);*/
		list = new JList<String>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.getViewport().setView(list);
		//scrollPanel.setPreferredSize(new Dimension(180, 120));
		add(scrollPanel, BorderLayout.WEST);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == ButtonContents.NEWOBJECT.getText()) {
			//System.out.println(e.getActionCommand());
			String value = JOptionPane.showInputDialog(ButtonContents.NEWOBJECT.getText(), "java.lang.Integer");
			if (value != null){
				// OKボタン押下
				try {
					//owner.createNewObject(Class.forName(value));
					Class<?> cls = Class.forName(value);
					new InitializeObjectDialog(owner, cls, value);

					//owner.showMessageToConsole("オブジェクト作成", true, null);
				} catch (ClassNotFoundException e1) {
					//owner.showMessageToConsole("オブジェクト作成", false, "ClassNotFoundException");
					JOptionPane.showMessageDialog(this, e1.toString());
				}
			}
		} else if (e.getActionCommand() == ButtonContents.NEWARRAY.getText()) {
			String value = JOptionPane.showInputDialog(ButtonContents.NEWARRAY.getText(), "java.lang.String");
			if (value != null){
				// OKボタン押下
				try {
					Class<?> cls = Class.forName(value);
					new InitializeArrayDialog(owner, cls, value);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(this, e1.toString());
				}
			}
		}
	};

	public void addNewObject(String name) {
		model.addElement(name);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource() == list){
			owner.changeControlObjectPanel(list.getSelectedValue().toString());
		}
	}
}
