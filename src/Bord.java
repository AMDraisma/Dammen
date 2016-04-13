import javax.swing.*;
import java.awt.*;

public class Bord extends JPanel {
    int hoeveelvakkenbreed = 10;
    int hoeveelvakkenhoog = 10;
    int vakbreedte = 20*2;
    int vakhoogte = 20*2;
    int offsetx = 25*2;
    int offsety = 25*2;
    int stenenGeslagenWit, stenenGeslagenZwart;
    // controlepaneel voor het dambord
    MasterController mc;

    Color beurtkleur;

    Damsteen[][] grid;

    public Bord ( Dammen dammen ) {
        grid = new Damsteen[hoeveelvakkenbreed][hoeveelvakkenhoog];

        this.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridwidth = 1;
        c.weightx = 0.05;
        this.mc = new MasterController(this, dammen);
        this.add(mc, c);


        JLabel leegLabel1 = new JLabel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        this.add(leegLabel1, c);

        beurtkleur = Color.WHITE;
        mc.updateBeurt(beurtkleur);

        this.setSize(new Dimension(400, 400));

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (y < 4)
                    if ((x + y) % 2 == 0) {
                        grid[x][y] = new Damsteen(0);
                        grid[x][y].dam = false;
                    }
                if (y > hoeveelvakkenhoog - 5) {
                    if ((x + y) % 2 == 0) {
                        grid[x][y] = new Damsteen(1);
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < hoeveelvakkenbreed; x++) {
            for (int y = 0; y < hoeveelvakkenhoog; y++) {
                if ((x + y) % 2 == 0)
                    g.setColor(Color.BLACK);
                else
                    g.setColor(Color.WHITE);
                g.fillRect(offsetx+x*vakbreedte, offsety+y*vakhoogte, vakbreedte, vakhoogte);
            }
        }

        for (int x = 0; x < hoeveelvakkenbreed; x++) {
            for (int y = 0; y < hoeveelvakkenhoog; y++) {
                if (null != grid[x][y]) {
                    g.setColor(grid[x][y].kleur);
                    g.fillOval(offsetx+Damsteen.RADIUS+x*20*2, offsety+Damsteen.RADIUS+y*20*2, Damsteen.DIAMETER, Damsteen.DIAMETER);
                    g.setColor(Color.gray);
                    g.drawOval(offsetx+Damsteen.RADIUS+x*20*2, offsety+Damsteen.RADIUS+y*20*2, Damsteen.DIAMETER, Damsteen.DIAMETER);
                    g.setColor(grid[x][y].kleur);
                    g.fillOval(offsetx+Damsteen.RADIUS+x*20*2, offsety+Damsteen.RADIUS+y*20*2-3, Damsteen.DIAMETER, Damsteen.DIAMETER);
                    g.setColor(Color.gray);
                    g.drawOval(offsetx+Damsteen.RADIUS+x*20*2, offsety+Damsteen.RADIUS+y*20*2-3, Damsteen.DIAMETER, Damsteen.DIAMETER);
                    if (grid[x][y].dam) {
                        g.setColor(grid[x][y].kleur);
                        g.fillOval(offsetx+Damsteen.RADIUS+x*20*2, offsety+Damsteen.RADIUS+y*20*2-6, Damsteen.DIAMETER, Damsteen.DIAMETER);
                        g.setColor(Color.gray);
                        g.drawOval(offsetx+Damsteen.RADIUS+x*20*2, offsety+Damsteen.RADIUS+y*20*2-6, Damsteen.DIAMETER, Damsteen.DIAMETER);
                    }
                }
            }
        }

        g.setColor(Color.BLACK);

        for(int x = 1; x <= hoeveelvakkenbreed; x++) {
            g.drawString( coordsLetters(x) , x*vakbreedte+offsetx-vakbreedte+vakbreedte/2, offsety-vakhoogte/4); //coords boven bord
            g.drawString( coordsLetters(x) , x*vakbreedte+offsetx-vakbreedte+vakbreedte/2, offsety+vakhoogte*hoeveelvakkenhoog+vakhoogte/2); //coords onder bord
        }

        for(int x = 1; x <= hoeveelvakkenhoog; x++) {
            g.drawString( hoeveelvakkenhoog + 1 - x + "" , offsetx-vakbreedte/2, x*vakhoogte+offsety-vakhoogte/2+5); //coords links van bord
            g.drawString( hoeveelvakkenhoog + 1 - x + "" , offsetx+hoeveelvakkenbreed*vakbreedte+vakbreedte/4, x*vakhoogte+offsety-vakhoogte/2+5); //coords rechts van bord
        }

//        repaint();
    }

    public String coordsLetters(int coordsXInvoer){
        if(coordsXInvoer > 0 && coordsXInvoer < 27)
            return String.valueOf((char)(coordsXInvoer + 64));
        else
            return null;

    }

    public void veranderBeurt () {
        beurtkleur = beurtkleur == Color.BLACK ? Color.WHITE : Color.BLACK;
        mc.updateBeurt(beurtkleur);
    }

    public boolean checkSteenUpgrade(int y, Color c) {
        if (c == Color.BLACK) {
            if (y == hoeveelvakkenhoog -1) {
                return true;
            }
        }else{
            if (y == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean beweeg(int xvan, int yvan, int xnaar, int ynaar) {
        // delta van beweging. Als damsteen twee plaatsen naar links verschuivt is delta-x (dx) 2 en delta-y (dy) 0
        int dx = xnaar - xvan;
        int dy = ynaar - yvan;
        // als er bewogen wordt van een vak naar hetzelfde vak, gebeurt er niks.
        if (dx == 0 && dy == 0) {
            return false;
        }

        // als een steen wordt bewogen van de kleur welke niet aan beurt is, wordt een foutmelding weergegeven
        if (grid[xvan][yvan].kleur != beurtkleur) {
            if (beurtkleur == Color.WHITE) {
                new ErrorMsg("Illegale zet", "Zwart is niet aan zet");
            }else{
                new ErrorMsg("Illegale zet", "Wit is niet aan zet");
            }
            return false;
        }

        if (Math.abs(dx) != Math.abs(dy)) {
            new ErrorMsg("Illegale zet", "Er mag alleen diagonaal bewogen worden");
            return false;
        }

        // als een positie in een grid null is, is deze leeg.
        // de locatie waar de damsteen vandaan komt kan niet leeg zijn.
        if (null == grid[xvan][yvan]) {
            return false;
        }


        // De locatie waar de damsteen heen moet, moet leeg zijn
        if (null == grid[xnaar][ynaar]) {

            // damstenen mogen alleen diagonaal bewegen. In dit geval beweegt een damsteen een plaats diagonaal.
            // dit houdt in dat de delta-x 1 of -1 is, en de delta-y 1 of -1 is. Math.abs() kan worden gebruikt
            // om de absolute waarde van delta-x of delta-y te berekenen.
            if (Math.abs(dx) == 1 && Math.abs(dy) == 1) {
                // Zwart mag alleen naar onderen, dus dy moet positief zijn
                // Wit mag alleen naar boven, dus dy moet negatief zijn
                // wanneer de steen een dam is, deze naar boven en beneden.
                if (grid[xvan][yvan].dam || (grid[xvan][yvan].kleur == Color.BLACK && dy > 0) || (grid[xvan][yvan].kleur == Color.WHITE && dy < 0)) {
                    // Plaats damsteen in nieuwe positie
                    grid[xnaar][ynaar] = grid[xvan][yvan];
                    // Verwijder damsteen in oude positie
                    grid[xvan][yvan] = null;
                    if (!grid[xnaar][ynaar].dam) {
                        grid[xnaar][ynaar].dam = checkSteenUpgrade(ynaar, grid[xnaar][ynaar].kleur);
                    }
                    veranderBeurt();
                    return true;
                }else{
                    if (grid[xvan][yvan].kleur == Color.BLACK) {
                        new ErrorMsg("Illegale zet", "Zwart mag alleen naar onderen bewegen");
                    }else{
                        new ErrorMsg("Illegale zet", "Wit mag alleen naar boven bewegen");
                    }
                    return false;
                }
            }
            // als absolute waarde van dx en dy 2 zijn, zijn we aan het slaan
            if (!grid[xvan][yvan].dam && Math.abs(dx) == 2 && Math.abs(dy) == 2) {
                // slaan kan alleen als er een steen tussen de dam en twee plaatsen diagonaal zit
                if (null != grid[xvan+dx/2][yvan+dy/2]) {
                    // slaan mag in alle diagonale richtingen, maar mag alleen over dammen van de andere kleur
                    // dx / 2 en dy / 2 zijn de helft van de richting naar waar de dam wil verplaatsen; de dam welke wordt
                    // geslagen.

                    if (grid[xvan][yvan].kleur != grid[xvan + dx / 2][yvan + dy / 2].kleur) {
                        if ( grid[xvan + dx / 2][yvan + dy / 2].kleur == Color.white ){
                                stenenGeslagenWit++;
                            }

                            else {
                                stenenGeslagenZwart++;
                            }
                        mc.tellerUpdate(stenenGeslagenWit, stenenGeslagenZwart);

                        // verwijder geslagen damsteen
                        grid[xvan + dx / 2][yvan + dy / 2] = null;
                        // Plaats damsteen in nieuwe positie
                        grid[xnaar][ynaar] = grid[xvan][yvan];
                        // Verwijder damsteen in oude positie
                        grid[xvan][yvan] = null;
                        if (!grid[xnaar][ynaar].dam) {
                            grid[xnaar][ynaar].dam = checkSteenUpgrade(ynaar, grid[xnaar][ynaar].kleur);
                        }
                        return true;
                    }else{
                        new ErrorMsg("Illegale zet", "Alleen dammen van de tegenovergestelde kleur kunnen worden geslagen");
                        return false;
                    }
                }else{
                    new ErrorMsg("Illegale zet", "Diagonaal twee plaatsen bewegen kan alleen als wordt geslagen");
                    return false;
                }
            }
            if (Math.abs(dx) > 1 && Math.abs(dy) > 1 && grid[xvan][yvan].dam) {
                // voor het berekenen van de richting waarin de dam zich beweegt kunnen we de delta's delen door de
                // absolute waarden van de delta's. hierdoor krijgen we 1 of -1. Bijvoorbeeld:
                // dx = 3: 3 / abs(3) = 1
                // dx = -4 / abs(-4) = -1
                int rx = dx / Math.abs(dx);
                int ry = dy / Math.abs(dy);

                // coordinaten van een geslagen steen. Een dam kan enkel een steen slaan in een zet
                int[] geslagensteen = null;
                for (int i = 1; i < Math.abs(dx); i++) {
                    if (null != grid[xvan + rx * i][yvan + ry * i]) {
                        // als er nog geen geslagen steen is opgenomen, kan er een worden opgenomen
                        if (null == geslagensteen) {
                            geslagensteen = new int[2];
                            geslagensteen[0] = xvan + rx * i;
                            geslagensteen[1] = yvan + ry * i;
                        } else {
                            new ErrorMsg("Illegale zet", "Een dam kan slechts een damsteen slaan per zet");
                            return false;
                        }
                    }
                }

                if (null != geslagensteen) {

                    if ( grid[geslagensteen[0]][geslagensteen[1]].kleur == Color.white ){
                        stenenGeslagenWit++;
                    }

                    else {
                        stenenGeslagenZwart++;
                    }
                    mc.tellerUpdate(stenenGeslagenWit, stenenGeslagenZwart);

                    // verwijder geslagen damsteen
                    grid[geslagensteen[0]][geslagensteen[1]] = null;

                }else{
                    veranderBeurt();
                }
                // Plaats damsteen in nieuwe positie
                grid[xnaar][ynaar] = grid[xvan][yvan];
                // Verwijder damsteen in oude positie
                grid[xvan][yvan] = null;
                return true;
            }
        }else {
            new ErrorMsg("Illegale zet", "De bestemmingsvak is niet leeg");
            return false;
        }
        new ErrorMsg("Illegale zet", "Deze zet is niet toegestaan");
        return false;
    }

    /** Converteert schermcoordinaten naar coordinaten van grid
     *
     * @param p schermcoordinaten
     * @return een reeks integer waarbij de schermcoordinaten zijn afgerond naar beneden.
     * de teruggegeven reeks is een reeks waarbij het eerste getal de x-coordinaat is, en het tweede getal een y-coordinaat
     * wanneer coordinaten buiten de grid vallen
     */
    public int[] schermCoordNaarGrid(Point p) {
        // de
        int[] gridcoord = new int[2];

        // de x coordinaten worden berekend door de afzet van het bord van de rand af te trekken van de
        // gegeven x-coordinaten. dit wordt dan gedeeld door de breedte van de vakken.
        gridcoord[0] = (int)Math.floor((p.getX() - offsetx)/vakbreedte);

        // van de y coordinaten worden nog eens 30 pixels afgetrokken, omdat het menubalk van het scherm om een of andere
        // reden ook meetelt
        gridcoord[1] =  (int)Math.floor((p.getY() - offsety-30)/vakhoogte);

        // als de coordinaten onder nul liggen of hoger zijn dan er vakken in de breedte zijn, geven we 0,0
        // coordinaten terug
        if (gridcoord[0] < 0 || gridcoord[0] > hoeveelvakkenbreed || gridcoord[1] < 0 || gridcoord[1] > hoeveelvakkenhoog) {
            gridcoord[0] = 0;
            gridcoord[1] = 0;
        }
        System.out.println(gridcoord[0] + "-" + gridcoord[1]);
        return gridcoord;
    }

    public boolean isDamsteenOpLocatie(int x, int y) {
        if (null == grid[x][y]) {
            return false;
        }else{
            return true;
        }
    }

    public boolean isDamsteenOpLocatie(int[] c) {
        return isDamsteenOpLocatie(c[0], c[1]);
    }

    public boolean isMuisBinnenBord(Point p) {
        int bordlinks = offsetx;
        int bordrechts = offsetx + vakbreedte * (hoeveelvakkenbreed);
        int bordboven = offsety;
        int bordonder = offsety + 30 + vakhoogte * (hoeveelvakkenhoog);
        if (p.getX() > bordlinks
                && p.getX() < bordrechts
                && p.getY() > bordboven
                && p.getY() < bordonder) {
            return true;
        }else {
            return false;
        }
    }
}
