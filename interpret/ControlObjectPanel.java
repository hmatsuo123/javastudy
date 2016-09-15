package interpret;

import interpret.InterpretPanel.ArrayInfo;
import interpret.InterpretPanel.ObjectInfo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class ControlObjectPanel extends JPanel{
	private InterpretPanel owner;
	private JLabel selectedObjectNameLabel;
	private JTable fieldTable;
	private DefaultTableModel fieldTableModel;
	private List<Field> fieldTableList;
	private Field targetField;
	private JTable methodTable;
	private DefaultTableModel methodTableModel;
	private List<Method> methodTableList;
	private Method targetMethod;
	private JTextField newValueText;
	private JTextField parameterText;
	private Boolean isArray = false;
	private JLabel isArrayLabel;
	private DefaultTableModel arrayTableModel;
	private JTable arrayTable;
	private List<Integer> arrayTableList;
	private Integer targetArrayElm;
	private JTextField newArrayValueText;

	public ControlObjectPanel(InterpretPanel owner) {
		super();
		this.owner = owner;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setSize(500, 500);
		//setBackground(Color.WHITE);
		setBorder(new EmptyBorder(2, 2, 2, 2));

		JLabel description = new JLabel("選択したオブジェクト・配列を操作できます。");
		description.setHorizontalAlignment(JLabel.LEFT);
		//add(description);
		JPanel objectInfoPanel = new JPanel();
		//objectInfoPanel.setBackground(Color.WHITE);
		JPanel tmp = new JPanel();
		//tmp.setBackground(Color.WHITE);
		tmp.setBorder(new TitledBorder("Object Info"));
		JPanel objectInfoDetailPanel = new JPanel();
		objectInfoDetailPanel.setLayout(new BoxLayout(objectInfoDetailPanel, BoxLayout.Y_AXIS));
		selectedObjectNameLabel = new JLabel("                                                           ");
		objectInfoDetailPanel.add(selectedObjectNameLabel);

		String[] arrayColumnNames = {"Index", "Value"};
		arrayTableModel = new DefaultTableModel(arrayColumnNames, 0);
		arrayTable = new JTable(arrayTableModel);
		arrayTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		arrayTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//選択行の行番号を取得します
				int idx = arrayTable.getSelectedRow();
				//選択行の1番目のカラムの内容を取得します。
				//String value = (String)arrayTable.getValueAt( idx, 1 );
				//newValueText.setText(value);
				//targetField = fieldTableList.get(idx);
			}
		});
		JScrollPane jsp = new JScrollPane(arrayTable);
		jsp.setPreferredSize(new Dimension(450, 100));
		objectInfoDetailPanel.add(jsp);

		JLabel newArrayValueLabel = new JLabel("New Value");
		newArrayValueLabel.setHorizontalAlignment(JLabel.LEFT);
		objectInfoDetailPanel.add(newArrayValueLabel);
		newArrayValueText = new JTextField();
		objectInfoDetailPanel.add(newArrayValueText);
		JButton upDateArrayButton = new JButton("UpDate");
		upDateArrayButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						if (newArrayValueLabel.getText() != null || newArrayValueLabel.getText() != "") {
							//owner.setNewFieldValue(targetArrayElm, newArrayValueLabel.getText());
						}
					}
				});
		objectInfoDetailPanel.add(upDateArrayButton);



		//isArrayLabel = new JLabel("");
		//objectInfoDetailPanel.add(isArrayLabel);
		tmp.add(objectInfoDetailPanel);
		objectInfoPanel.add(tmp);
		add(objectInfoPanel);

		JPanel controllPanel = new JPanel();
		controllPanel.setLayout(new BoxLayout(controllPanel, BoxLayout.Y_AXIS));
		controllPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		//controllPanel.setBackground(Color.WHITE);
		JTabbedPane tabPanel = new JTabbedPane();
		//tabPanel.setSize(550, 400);
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		fieldTableList = new ArrayList<Field>();
		String[] columnNames = {"Modifiers", "Name", "Value"};
		fieldTableModel = new DefaultTableModel(columnNames, 0);
		fieldTable = new JTable(fieldTableModel);
		fieldTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ダブルクリックによるセル情報の編集を禁止
		/*fieldTable.addMouseListener(new MouseAdapter() {
			  @Override public void mouseClicked(MouseEvent me) {
			    if (me.getClickCount() == 2) {
			      Point pt = me.getPoint();
			      int idx = fieldTable.rowAtPoint(pt);
			      if (idx >= 0) {
			        int row = fieldTable.convertRowIndexToModel(idx);
			        String str = String.format(
			          "%s (%s)", fieldTableModel.getValueAt(row, 1), fieldTableModel.getValueAt(row, 2));
			        JOptionPane.showMessageDialog(
			        		fieldTable, str, "title", JOptionPane.INFORMATION_MESSAGE);
			      }
			    }
			  }
			});*/
		fieldTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//選択行の行番号を取得します
				int idx = fieldTable.getSelectedRow();
				//選択行の1番目のカラムの内容を取得します。
				String value = (String)fieldTable.getValueAt( idx, 2 );
				newValueText.setText(value);
				targetField = fieldTableList.get(idx);
			}
		});
		// テストデータ
		/*String str[] = {"1", "11", "111"};
		fieldTableModel.addRow(str);
		String str2[] = {"2", "22", "222"};
		fieldTableModel.addRow(str2);
		String str3[] = {"1", "11", "111"};
		fieldTableModel.addRow(str3);*/
		JScrollPane sp = new JScrollPane(fieldTable);
	    //sp.setPreferredSize(new Dimension(250, 90));
		fieldPanel.add(sp);
		JLabel newValueLabel = new JLabel("New Value");
		newValueLabel.setHorizontalAlignment(JLabel.LEFT);
		fieldPanel.add(newValueLabel);
		newValueText = new JTextField();
		fieldPanel.add(newValueText);
		JButton upDateButton = new JButton("UpDate");
		upDateButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						if (newValueText.getText() != null || newValueText.getText() != "") {
							owner.setNewFieldValue(targetField, newValueText.getText());
						}
					}
				});
		fieldPanel.add(upDateButton);

		/*JLabel buf = new JLabel("                         ");
		fieldPanel.add(buf);*/
		tabPanel.addTab("Field", fieldPanel);
		JPanel methodPanel = new JPanel();
		methodPanel.setLayout(new BoxLayout(methodPanel, BoxLayout.Y_AXIS));
		methodTableList = new ArrayList<Method>();
		String[] methodColumnNames = {"Modifiers", "Name", "Arguments"};
		methodTableModel = new DefaultTableModel(methodColumnNames, 0);
		methodTable = new JTable(methodTableModel);
		methodTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		methodTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//選択行の行番号を取得します
				int idx = methodTable.getSelectedRow();
				targetMethod = methodTableList.get(idx);
			}
		});
		JScrollPane sp2 = new JScrollPane(methodTable);
		methodPanel.add(sp2);
		JLabel parameterLabel = new JLabel("Parameter");
		methodPanel.add(parameterLabel);
		parameterText = new JTextField();
		methodPanel.add(parameterText);
		JButton invokeButton = new JButton("Invoke");
		invokeButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						owner.invokeMethod(targetMethod, parameterText.getText());
					}
				});
		methodPanel.add(invokeButton);
		tabPanel.addTab("Method", methodPanel);
		controllPanel.add(tabPanel);
		add(controllPanel);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public void showControlObjectPanel(String objName, ObjectInfo targetObj) {
		selectedObjectNameLabel.setText(objName);
		isArrayLabel.setText("Object Class");
		isArray = false;
		setFieldsToList(targetObj);
		setMethodsToList(targetObj);
	}

	public void showControlObjectPanelForArray(String objName, ObjectInfo targetObj, ArrayInfo arrayInfo) {
		selectedObjectNameLabel.setText(objName);
		isArrayLabel.setText("Array Class");
		isArray = true;
		setFieldsToList(targetObj);
		setMethodsToList(targetObj);

	}

	public void setFieldsToList(ObjectInfo obj) {
		// テーブルのデータをすべて削除
		if (fieldTableModel.getRowCount() > 0) {
		    for (int i = fieldTableModel.getRowCount() - 1; i > -1; i--) {
		    	fieldTableModel.removeRow(i);
		    }
		}
		fieldTableList.clear();
		targetField = null;
		newValueText.setText("");

		Class<?> c = obj.object.getClass();
		//Field[] fields   = c.getDeclaredFields();
		Set<Field> fieldSet = new HashSet<Field>();
		for (Field m : c.getFields())
			fieldSet.add(m);
		for (Field m : c.getDeclaredFields())
			fieldSet.add(m);
		List<Field> fieldList = new ArrayList<Field>(fieldSet);
		for( int i=0; i<fieldList.size(); i++ ){
			try {
				Field field = fieldList.get(i);
				field.setAccessible(true);
				String name = field.getName();
				String modifiers = "";
				String[] strs = field.toString().split(" ", 0);
				for (String str : strs) {
					if (str.indexOf(name) == -1) modifiers += str + " ";
				}

				String value = field.get(obj.object).toString();
				String str[] = {modifiers, name, value};
				fieldTableModel.addRow(str);
				fieldTableList.add(field);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setMethodsToList(ObjectInfo obj) {
		// テーブルのデータをすべて削除
		if (methodTableModel.getRowCount() > 0) {
		    for (int i = methodTableModel.getRowCount() - 1; i > -1; i--) {
		    	methodTableModel.removeRow(i);
		    }
		}
		methodTableList.clear();
		targetMethod = null;
		parameterText.setText("");

		Class<?> c = obj.object.getClass();
		Set<Method> methodSet = new HashSet<Method>();
		for (Method m : c.getMethods())
			methodSet.add(m);
		for (Method m : c.getDeclaredMethods())
			methodSet.add(m);
		List<Method> methodList = new ArrayList<>(methodSet);
		//Collections.sort(methodList);

		for( int i=0; i<methodList.size(); i++ ){
			try {
				Method method = methodList.get(i);
				method.setAccessible(true);
				String name = method.getName();
				String modifiers = "";
				String[] strs = method.toString().split(" ", 0);
				String argument = "";
				for (String str : strs) {
					if (str.indexOf(name) == -1)
						modifiers += str + " ";
					else {
						String regex = "\\((.+?)\\)";
						List<String> list = new ArrayList<String>();
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(str);
						while (matcher.find()) {
							list.add(matcher.group(1));
						}
						if (list.size() != 0) argument = list.get(0);
					}
				}

				String str[] = {modifiers, name, argument};
				methodTableModel.addRow(str);
				methodTableList.add(method);
			}
			catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	public void setArrayElmToList(ObjectInfo obj, ArrayInfo array) {
		// テーブルのデータをすべて削除
		if (arrayTableModel.getRowCount() > 0) {
		    for (int i = arrayTableModel.getRowCount() - 1; i > -1; i--) {
		    	arrayTableModel.removeRow(i);
		    }
		}
		arrayTableList.clear();
		targetArrayElm = null;
		newArrayValueText.setText("");

		/*for (int i = 0; i < array.size; i++) {
			fieldTableModel.addRow(array.object[i]);
			fieldTableList.add(array.object[i]);
			;
		}*/

		Class<?> c = obj.object.getClass();


		//Field[] fields   = c.getDeclaredFields();
		Set<Field> fieldSet = new HashSet<Field>();
		for (Field m : c.getFields())
			fieldSet.add(m);
		for (Field m : c.getDeclaredFields())
			fieldSet.add(m);
		List<Field> fieldList = new ArrayList<Field>(fieldSet);
		for( int i=0; i<fieldList.size(); i++ ){
			try {
				Field field = fieldList.get(i);
				field.setAccessible(true);
				String name = field.getName();
				String modifiers = "";
				String[] strs = field.toString().split(" ", 0);
				for (String str : strs) {
					if (str.indexOf(name) == -1) modifiers += str + " ";
				}

				String value = field.get(obj.object).toString();
				String str[] = {modifiers, name, value};
				fieldTableModel.addRow(str);
				fieldTableList.add(field);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
