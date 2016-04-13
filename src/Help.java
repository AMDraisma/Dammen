import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by crude on 4-4-16.
 */
public class Help extends JFrame implements FocusListener {

    private JTextArea helpText;
    private JScrollPane scrollPane;

    public Help() {
        helpText = new JTextArea("Sleep met de muis of voer de coördinaten in om stenen te verzetten.", 5, 40);
        scrollPane = new JScrollPane(helpText);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setEditable(false);
        this.setTitle("Instructies");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300,400);
        this.add(scrollPane);
        this.addFocusListener(this);
        this.setVisible(true);
        setResizable(false);


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
