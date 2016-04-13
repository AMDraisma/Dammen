import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class ErrorMsg extends JFrame implements FocusListener {
    public ErrorMsg(String title, String message) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(message.length()*10+10,100);
        this.setLayout(new GridLayout(2,1));
        this.addFocusListener(this);
        this.setLocationRelativeTo(null);
        this.add(new JLabel(message));
        this.add(new JLabel("Klik op het bord om door te gaan"));
        this.setVisible(true);


    }

    @Override
    public void focusGained(FocusEvent focusEvent) {

    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        this.dispose();
    }
}
