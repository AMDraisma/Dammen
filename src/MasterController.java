import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aj on 29-3-16.
 */

public class MasterController extends JPanel {
    private JTextField invoerVakSelecterenX, invoerVakSelecterenY, invoerVakZetX, invoerVakZetY, tellerWit, tellerZwart;
    private JLabel invoerBeginPosLabel, invoerEindPosLabel, geslagenLabel;
    private JButton verzetKnop, restartKnop;
    private boolean eindeBeurt;
    private JLabel beurt;
    private int stenenWit, stenenZwart;
    Bord bord;
    Dammen dammen;


    public MasterController( Bord bord, Dammen dammen ) {
        this.bord = bord;
        this.dammen = dammen;
        this.beurt = new JLabel("");
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();



        invoerBeginPosLabel = new JLabel( "Begin Positie Steen"); //label invoer vakken begin positie steen
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        this.add(invoerBeginPosLabel, c);

        invoerEindPosLabel = new JLabel( "Eind Positie Steen"); //label invoervak eindpositie steen
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 2;
        c.gridx = 2;
        c.gridy = 0;
        this.add(invoerEindPosLabel, c);

        invoerVakSelecterenX = new JTextField( 5 ); //invoervak begin positie x coordinaat
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.add(invoerVakSelecterenX, c);

        invoerVakSelecterenY = new JTextField( 5 ); //invoervak begin positie y coordinaat
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        this.add(invoerVakSelecterenY, c);

        invoerVakZetX = new JTextField( 5 ); //invoervak eind positie x coordinaat
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        this.add(invoerVakZetX, c);

        invoerVakZetY = new JTextField( 5 ); //invoervak eind positie y coordinaat
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 1;
        this.add(invoerVakZetY, c);

        tellerWit = new JTextField( 5 ); //geslagen stenenteller v/d witte stenen FIXME: 6-4-2016
        tellerWit.setEditable(false);
        tellerWit.setText( "Wit: 0" );
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        this.add(tellerWit, c);

        tellerZwart = new JTextField( 5 ); //geslagen stenenteller v/d zwarte stenen FIXME: 6-4-2016
        tellerZwart.setEditable(false);
        tellerZwart.setText( "Zwart: 0" );
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 4;
        this.add(tellerZwart, c);

        verzetKnop = new JButton("Verzet Steen"); //verzet steen knop
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 5;
        c.gridx = 0;
        c.gridy = 2;
        this.add(verzetKnop, c);
        verzetKnop.addActionListener( new KnopHandlerVerzetKnop() );

        geslagenLabel = new JLabel("Geslagen Stenen"); //Geslagen Stenen label
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 3;
        this.add(geslagenLabel, c);
        

        verzetKnop = new JButton("Help"); //help knopjes
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 4;
        this.add(verzetKnop, c);
        verzetKnop.addActionListener( new KnopHandlerHelpKnop() );

        restartKnop = new JButton("Restart"); //restart knopjes
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 2;
        c.gridx = 2;
        c.gridy = 5;
        this.add(restartKnop, c);
        restartKnop.addActionListener( new KnopHandlerRestartKnop() );

    }

    class KnopHandlerHelpKnop implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Help();

        }
    }

    class KnopHandlerRestartKnop implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dammen.restart();

        }
    }

    class KnopHandlerVerzetKnop implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            String invoerBeginX = invoerVakSelecterenX.getText();
            char invXChar = invoerBeginX.charAt(0);
            int invXInt = (int) invXChar;
            if ( invXInt >= 65 && invXInt <= 74 )
                invXInt -= 65;
            else if ( invXInt >= 97 && invXInt <= 106 )
                invXInt -= 97;
            else
                invoerVakSelecterenX.setText( "You dun goofed!" ) ;

            String invoerBeginY = invoerVakSelecterenY.getText();
            int invBY = 10 - Integer.parseInt( invoerBeginY );

            String invoerEindX = invoerVakZetX.getText();
            char invXC = invoerEindX.charAt(0);
            int invX = (int) invXC;
            if ( invX >= 65 && invX <= 74 )
                invX -= 65;
            else if ( invX >= 97 && invX <= 106 )
                invX -= 97;
            else
                invoerVakSelecterenX.setText( "You dun goofed!" ) ;

            String invoerEindY = invoerVakZetY.getText();
            int invEY = 10 - Integer.parseInt( invoerEindY );

            bord.beweeg(invXInt, invBY, invX, invEY);

            invoerVakSelecterenX.setText("");
            invoerVakSelecterenY.setText("");
            invoerVakZetX.setText("");
            invoerVakZetY.setText("");

            bord.repaint();
        }
    }


    public void updateBeurt(Color beurtkleur) {
        if (beurtkleur == Color.BLACK) {
            beurt.setText("Zwart is aan zet");
        }else{
            beurt.setText("Wit is aan zet");
        }
        repaint();
    }

    public  void tellerUpdate( int wit, int zwart){
        tellerZwart.setText( "Zwart: " + zwart );
        tellerWit.setText( "Wit: " + wit );
    }

//    class KnopHandlerEinigBeurtKnop implements ActionListener {
//        public void actionPerformed( ActionEvent e ) {
//
//            eindeBeurt = true;
//
//        }
//    }
}