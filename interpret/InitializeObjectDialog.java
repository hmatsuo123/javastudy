package interpret;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class InitializeObjectDialog extends JDialog {
	InterpretPanel owner;
	Class<?> cls;
	JTextField nameText;
	JTextField parameterText;
	//private DefaultListModel<String> model;
	JList<String> list;
	private Set<Constructor<?>> constructors;

	public InitializeObjectDialog(InterpretPanel owner, Class<?> cls, String name) {
		super();
		this.setModal(true);
		this.owner = owner;
		this.cls = cls;
		//model = new DefaultListModel<String>();
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new GridLayout(9, 1));
		setSize(500, 550);
		setTitle("Initialize Object");

		// ターゲットオブジェクト情報
		JLabel objLabel = new JLabel("■Target Object");
		add(objLabel);
		JLabel objName = new JLabel(name);
		add(objName);

		// オブジェクト名
		JLabel nameLabel = new JLabel("■Object Name");
		add(nameLabel);
		nameText = new JTextField();
		add(nameText);

		// コンストラクタ
		JLabel listLabel = new JLabel("■Constructors");
		add(listLabel);
		constructors = new HashSet<>();
		for (Constructor<?> c : cls.getConstructors()) {
			constructors.add(c);
		}
		Set<String> constructorNames = new HashSet<>();
		for (Constructor<?> c : constructors) {
			constructorNames.add(c.toString());
		}
		list = new JList<>(constructorNames.toArray(new String[constructors.size()]));
		list.setSelectedIndex(0);
		//list = new JList<String>(model);
		//list.setPreferredSize(new Dimension(200, 700));
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.getViewport().setView(list);
		add(scrollPanel);

		// 引数
		JLabel parameterLabel = new JLabel("■Parameter");
		add(parameterLabel);
		parameterText = new JTextField();
		add(parameterText);

		// ボタン
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		JButton okButton = new JButton("OK");
		okButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						createObject();
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

	private void createObject() {
		String name = nameText.getText();
		if (name == null || name.length() == 0) {
			JOptionPane.showMessageDialog(this, "Please input Object Name.");
			return;
		}
		// コンストラクタを取得
		Constructor<?> selectedCons = null;
		String selectedName = list.getSelectedValue();
		for (Constructor<?> c : constructors) {
			if (c.toString().equals(selectedName)) {
				selectedCons = c;
			}
		}
		Class<?>[] params = selectedCons.getParameterTypes();
		if (params == null || params.length == 0) {
			// 引数なしのコンストラクタの場合
			try {
				owner.addNewObject(cls, selectedCons.newInstance(), name);
				setVisible(false);
			} catch (InstantiationException e1) {
				JOptionPane.showMessageDialog(this, "InstantiationException");
				return;
			} catch (IllegalAccessException e1) {
				JOptionPane.showMessageDialog(this, "IllegalAccessException");
				return;
			} catch (IllegalArgumentException e1) {
				JOptionPane.showMessageDialog(this, "IllegalArgumentException");
				return;
			} catch (InvocationTargetException e1) {
				JOptionPane.showMessageDialog(this, "InvocationTargetException");
				return;
			}
		} else {
			if (parameterText.getText() == null) {
				JOptionPane.showMessageDialog(this, "Please input Parameter.");
				return;
			}
			List<String> inputParams = new ArrayList<>();
			StringTokenizer token = new StringTokenizer(parameterText.getText(), ",");
			while (token.hasMoreTokens()) {
				inputParams.add(token.nextToken());
			}
			Object[] paramData = new Object[params.length];
			for (int i = 0; i < params.length; i++) {
				String inputParam;
				try {
					inputParam = inputParams.get(i);
				} catch (IndexOutOfBoundsException ioobe) {
					paramData[i] = null;
					System.out.println("paramData[" + i + "]=(null)");
					continue;
				}
				if (inputParam.equals("null")) {
					paramData[i] = null;
					continue;
				}
				// parameter is primitive type
				if (params[i].isPrimitive()) {
					try {
						if (params[i].equals(int.class))
							paramData[i] = Integer.parseInt(inputParam);
						else if (params[i].equals(double.class))
							paramData[i] = Double.parseDouble(inputParam);
						else if (params[i].equals(float.class))
							paramData[i] = Float.parseFloat(inputParam);
						else if (params[i].equals(short.class))
							paramData[i] = Short.parseShort(inputParam);
						else if (params[i].equals(char.class))
							paramData[i] = (char) Integer
							.parseInt(inputParam);
						else if (params[i].equals(byte.class))
							paramData[i] = Byte.parseByte(inputParam);
						else if (params[i].equals(boolean.class))
							paramData[i] = Boolean.parseBoolean(inputParam);
						else {
							System.out.println("Unknown type");
							return;
						}
						System.out.println("paramData[" + i + "]="
								+ paramData[i]);
						continue;
					} catch (NumberFormatException e1) {
						System.out.println("NumberFormatException");
						return;
					}
				} else {
					// parameter has string constructor
					try {
						Object p = params[i].getConstructor(String.class)
								.newInstance(inputParam);
						paramData[i] = p;
						continue;
					} catch (ReflectiveOperationException e1) {
						System.err
						.println("Parameter #"
								+ (i + 1)
								+ " hasn't string constructor. Inserting null.");
					} catch (SecurityException e1) {
						System.out.println("SecurityException");
						return;
					}
					// insert null
					System.out.println("paramData[" + i + "]=(null)");
					paramData[i] = null;
				}
			}
			// Instantiate with parameter(s)
			try {
					owner.addNewObject(cls, selectedCons.newInstance(paramData), name);
				setVisible(false);
			} catch (InstantiationException e1) {
				JOptionPane.showMessageDialog(this, "InstantiationException");
				return;
			} catch (IllegalAccessException e1) {
				JOptionPane.showMessageDialog(this, "IllegalAccessException");
				return;
			} catch (IllegalArgumentException e1) {
				JOptionPane.showMessageDialog(this, "IllegalArgumentException");
				return;
			} catch (InvocationTargetException e1) {
				JOptionPane.showMessageDialog(this, "InvocationTargetException");
				return;
			} catch (OutOfMemoryError e1) {
				JOptionPane.showMessageDialog(this, "OutOfMemoryError");
				return;
			} catch (VirtualMachineError e1) {
				JOptionPane.showMessageDialog(this, "VirtualMachineError");
				return;
			} catch (Error e1) {
				JOptionPane.showMessageDialog(this, "Error");
				return;
			} catch (RuntimeException e1) {
				JOptionPane.showMessageDialog(this, "RuntimeException");
				return;
			}
		}
	}
}
