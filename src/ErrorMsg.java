import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by crude on 4-4-16.
 */
public class ErrorMsg extends JFrame implements FocusListener {
    public ErrorMsg(String title, String message) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(message.length()*10+10,100);
        this.setLayout(new GridLayout(2,1));
        this.addFocusListener(this);

        this.add(new JLabel(message));
        this.add(new JLabel("Klik op het bord om door te gaan"));
        this.setVisible(true);


    }

//    public class ErrorBericht {
//        public void addComponentsToPane(Container pane) {
//            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
//
//            addAButton("Button 1", pane);
//            addAButton("Button 2", pane);
//            addAButton("Button 3", pane);
//            addAButton("Long-Named Button 4", pane);
//            addAButton("5", pane);
//        }
//
//        private void addAButton(String text, Container container) {
//            JButton button = new JButton(text);
//            button.setAlignmentX(Component.CENTER_ALIGNMENT);
//            container.add(button);
//        }
//    }

    @Override
    public void focusGained(FocusEvent focusEvent) {

    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        this.dispose();
    }
}
