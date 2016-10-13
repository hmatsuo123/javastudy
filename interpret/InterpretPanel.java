package interpret;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InterpretPanel extends JPanel{
	private JFrame owner;
	private ObjectTreePanel objectTreePanel;
	private ControlObjectPanel controlObjPane;
	private List<ObjectInfo> objList;
	private List<ArrayInfo> arrayList;
	private List<String> objNameList;
	private List<String> arrayNameList;
	private JTextArea console;
	private ObjectInfo selectedObj;
	private ArrayInfo selectedArray;

	class ObjectInfo {
		protected final Object object;
		protected final String name;		// 現在の仕様ではユニーク値
		protected final Class<?> cls;

		public ObjectInfo(Object object, String name, Class<?> cls) {
			this.object = object;
			this.name = name;
			this.cls = cls;
		}
	}

	class ArrayInfo {
		protected  Object object;
		protected String name;		// 現在の仕様ではユニーク値
		protected final int size;
		protected String[] childNames;

		public ArrayInfo(Object object, String name, int size) {
			this.object = object;
			this.name = name;
			this.size = size;
			this.childNames = new String[size];
		}
	}

	public InterpretPanel(JFrame owner) {
		super();
		this.owner = owner;
		objList = new ArrayList<ObjectInfo>();
		arrayList = new ArrayList<ArrayInfo>();
		objNameList = new ArrayList<String>();
		arrayNameList = new ArrayList<String>();
		setLayout(new BorderLayout());

		JLabel description = new JLabel("作成したオブジェクトと配列に対する操作を行うことができます。");
		//add(description, BorderLayout.PAGE_START);

		// オブジェクトツリー欄
		objectTreePanel = new ObjectTreePanel(this);
		add(objectTreePanel, BorderLayout.WEST);

		// オブジェクト操作欄
		controlObjPane = new ControlObjectPanel(this);
		add(controlObjPane, BorderLayout.CENTER);

		// コンソール
		/*console = new JTextArea("");
		console.setForeground(Color.WHITE);
		console.setBackground(Color.BLACK);
		add(console, BorderLayout.SOUTH);
		console.setPreferredSize(new Dimension(200, 120));*/
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
		objList.add(new ObjectInfo(instance, name, cls));
		objNameList.add(name);
		objectTreePanel.addNewObject(name + "(" + cls.getName() + ")");
	}

	public void addNewObjectToArray(Class<?> cls, Object instance, String name, int arrayIdx, String targetArrayName) {
		for (ArrayInfo arrayInfo : arrayList) {
			if (arrayInfo.name.equals(targetArrayName)) {
				Object[] tmp = (Object[]) arrayInfo.object;
				tmp[arrayIdx] = instance;
				arrayInfo.childNames[arrayIdx] = name;
				//selectedArray = arrayInfo;
			}
		}
		selectedObj = new ObjectInfo(instance, name, cls);

		// フィールドを更新
		changeControlObjectPanel(cls.getName(), true, arrayIdx);
	}

	public void addNewArray(Class<?> cls, Object instance, String name, int size) {
		arrayList.add(new ArrayInfo(instance, name, size));
		arrayNameList.add(name);
		objectTreePanel.addNewObject(name + "(" + cls.getName() + "[])");
	}

	// objectもしくはarrayで同じ名前のインスタンスがないか確認する
	public Boolean isDuplicationObjectName(String name) {
		for (String _name : objNameList) {
			if (_name.equals(name)) return true;
		}
		for (String _name : arrayNameList) {
			if (_name.equals(name)) return true;
		}
		return false;
	}

	@Deprecated
	public void showMessageToConsole(String command, Boolean isSuccess, String message) {
		if (isSuccess) {
			console.setText(/*"■命令" + System.getProperty("line.separator") + "   " + command + "\n■結果" + System.getProperty("line.separator") + "   " + */"success");
		} else {
			console.setText(/*"■命令" + System.getProperty("line.separator") + "   " + command + "\n■結果" + System.getProperty("line.separator") + "   " + */"error: " + message);
		}

	}

	public void changeControlObjectPanel(String name, Boolean isArray, int arrayIdx) {
		List<String> list = new ArrayList<String>();
		if (isArray) {
			list.add(name);
		} else {
			String regex = "\\((.+?)\\)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(name);
			while (matcher.find()) {
				list.add(matcher.group(1));
			}
		}

		try {
			String targetClass = list.get(0);
			if (targetClass.indexOf("[]") != -1) {
				// 配列の場合 TODO:いずれ名前でなく内部フラグ等で判定するようにする
				Class<?> c = Class.forName(targetClass.replace("[]", ""));
				String objName = name.replace("(" + list.get(0) + ")", "");
				for (int i = 0; i < arrayNameList.size(); i++) {
					if (objName.equals(arrayNameList.get(i))) {
						selectedArray = arrayList.get(i);
						selectedObj = null;
						break;
					}
				}
				controlObjPane.showControlObjectPanelForArray(c.toString(), new ObjectInfo(selectedArray.object, objName, c), selectedArray);
			} else {
				Class<?> c = Class.forName(targetClass);
				String objName = name.replace("(" + list.get(0) + ")", "");
				for (int i = 0; i < objNameList.size(); i++) {
					if (objName.equals(objNameList.get(i))) {
						selectedObj = objList.get(i);
						selectedArray = null;
						break;
					}
				}
				controlObjPane.showControlObjectPanel(c.toString(), selectedObj);
				if (isArray)
					controlObjPane.setArrayElmToList(new ObjectInfo(selectedArray.object, selectedArray.name, c), selectedArray, arrayIdx);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setNewFieldValue(Field field, String value) {
		try {
			field.setAccessible(true);
			if(field.getType() == int.class || field.getType() == Integer.class){
				field.setInt(selectedObj.object, Integer.parseInt(value));
			} else if (field.getType() == String.class) {
				field.set(selectedObj.object, value);
			} else if (field.getType() == char.class) {
				field.set(selectedObj.object, value.toCharArray());
			} else if (field.getType() == long.class) {
				field.set(selectedObj.object, Long.parseLong(value));
			}

			// テーブルを再表示
			controlObjPane.setFieldsToList(selectedObj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			//showMessageToConsole("", false, e.toString());
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}

	// 自作したクラスが存在すればそのインスタンスを返す
	private Object getObject(String name) {
		for (ObjectInfo e : objList) {
			if (e.name.equals(name))
                return e.object;
		}

        return null;
	}

	public void invokeMethod(Method method, String parameter) {
		try {
			if (method == null) {
				JOptionPane.showMessageDialog(this, "Please select a method.");
				return;
			}
			method.setAccessible(true);
			//String[] args = argument.toString().split(",", 0);
			Class<?>[] args = method.getParameterTypes();
			Object[] paramData = new Object[args.length];
			String[] params = parameter.toString().split(",", 0);
			if (args.length == 0) {

			} else {
				for (int i = 0; i < args.length; i++) {
					if (params[i] == "") continue;
					else if (params[i].startsWith("'")) {
						// ' 'で囲まれた文字列は自作したクラス
						String regex = "\'(.+?)\'";
						List<String> list = new ArrayList<String>();
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(params[i]);
						while (matcher.find()) {
							list.add(matcher.group(1));
						}

						Object obj = getObject(list.get(0));
						if (obj == null) {
							JOptionPane.showMessageDialog(this, "Array not found: " + list.get(0));
	                        return;
	                    } else {
	                    	paramData[i] = obj;
	                    }
					} else {
						try {
							if (args[i].equals(int.class))
								paramData[i] = Integer.parseInt(params[i]);
							else if (args[i].equals(double.class))
								paramData[i] = Double.parseDouble(params[i]);
							else if (args[i].equals(float.class))
								paramData[i] = Float.parseFloat(params[i]);
							else if (args[i].equals(short.class))
								paramData[i] = Short.parseShort(params[i]);
							else if (args[i].equals(char.class))
								paramData[i] = (char) Integer.parseInt(params[i]);
							else if (args[i].equals(byte.class))
								paramData[i] = Byte.parseByte(params[i]);
							else if (args[i].equals(boolean.class))
								paramData[i] = Boolean.parseBoolean(params[i]);
							else if (args[i].equals(String.class))
								continue;
							else {
								JOptionPane.showMessageDialog(this, "Unknown type");
								return;
							}
							continue;
						} catch (Exception e) {
							JOptionPane.showMessageDialog(this, e.toString());
							return;
						}
					}
				}
			}

			Object result;
			try {
				result = method.invoke(selectedObj.object, paramData);

				if (result == null)
					result = "none";
				//controlObjPane.clearParameterText();
				// テーブルを再表示
				controlObjPane.setMethodsToList(selectedObj);
				JOptionPane.showMessageDialog(this, "Result: " + result);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.toString());
				return;
			}

		} catch (IllegalArgumentException/* | IllegalAccessException*/ e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			//showMessageToConsole("", false, e.toString());
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}

	public String GetSelectedArrayName() {
		return selectedArray.name;
	}

	public ObjectInfo GetSelectedObj(int idx) {
		Object[] tmp = (Object[]) selectedArray.object;
		String[] tmp2 = selectedArray.childNames;
		selectedObj = new ObjectInfo(tmp[idx], tmp2[idx], tmp[idx].getClass());
		return selectedObj;
	}
}
