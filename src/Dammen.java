import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Dammen extends JFrame {

    // het dambord
    Bord bord;
    // oorsprongspunt wanneer een gebruiker sleept met de muis
    Point sleepOorsprong;

    public static void main (String[] args) {
        new Dammen();
    }

    public Dammen () {
        this.bord = new Bord(this);
        this.sleepOorsprong = new Point(-1,-1);

        this.setSize(800, 550);
        setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setContentPane(bord);

        // voegt zichzelf als een luisteraar voor muisbeweging toe
        this.addMouseListener(new MListener());
    }

    class MListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
        }

        // deze methode wordt uitgevoerd op het moment dat een muisknop wordt ingedrukt.
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            // Een point is een locatie op het scherm, uitgedrukt in pixels van links (x) en pixels van boven (y)
            // in dit geval wordt vanaf het scherm van het programma gerekend. Er kan ook vanaf het scherm worden gerekend
            // met mouseEvent.getLocationOnScreen();
            Point p = mouseEvent.getPoint();

            if (bord.isMuisBinnenBord(p)) {
                // als er een damsteen op de beginlocatie van het slepen is, kunnen we verder
                if (bord.isDamsteenOpLocatie(bord.schermCoordNaarGrid(p))) {
                    sleepOorsprong = p;
                }
            }
        }

        // deze methode wordt uitgevoerd wanneer een muisknop wordt losgelaten
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            Point sleepEindpunt;
            // als een beginpunt van het slepen is vastgesteld, kunnen we een eindpunt vaststellen en de damsteen bewegen
            if (sleepOorsprong.getX() != -1 & sleepOorsprong.getY() != -1) {
                sleepEindpunt = mouseEvent.getPoint();
                int[] begincoordinaten = bord.schermCoordNaarGrid(sleepOorsprong);
                int[] eindcoordinaten = bord.schermCoordNaarGrid(sleepEindpunt);
                bord.beweeg(begincoordinaten[0], begincoordinaten[1], eindcoordinaten[0], eindcoordinaten[1]);
                bord.repaint();
                sleepOorsprong.setLocation(new Point(-1,-1));
            }
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

    public void restart(){
        new Dammen();
        dispose();

    }
}
