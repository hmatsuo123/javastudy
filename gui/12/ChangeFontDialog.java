package ex12;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeFontDialog  extends Dialog implements ActionListener {
	private DigitalClock12 owner;
	private TextField FontSizeTextField;

	ChangeFontDialog(Frame owner) {
        super(owner);
        this.owner = (DigitalClock12)owner;

        setLayout(new FlowLayout());
        setTitle("Change Font Property");
        setSize(200, 100);

        Label sizeLabel = new Label("Font size [px] ");
        add(sizeLabel);
        FontSizeTextField = new TextField("", 5);
        FontSizeTextField.addActionListener(this);
        add(FontSizeTextField);



        Button button = new Button("OK");
        button.addActionListener(this);
        add(button);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            int size = Integer.parseInt(FontSizeTextField.getText());
            owner.fontSize = Integer.parseInt(FontSizeTextField.getText());
    		owner.reDisplay();
        } catch (NumberFormatException nfe) {
        	//TODO:エラーを表示する
            return;
        }
        setVisible(false);
    }
}
