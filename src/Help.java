import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;


public class Help extends JFrame implements FocusListener {

    public Help() {
        File helpFile = new File("help.txt");
        StringBuilder sb = new StringBuilder("");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(helpFile));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line+"\n");
            }
        } catch (FileNotFoundException e) {
            new ErrorMsg("Fout!", "Fout in code: help.txt kon niet gevonden worden");
            return;
        } catch (IOException e) {
            new ErrorMsg("Fout!", "Onbekende fout in code");
            return;
        }

        JTextArea helpText = new JTextArea(sb.toString(), 5, 40);
        JScrollPane scrollPane = new JScrollPane(helpText);
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
