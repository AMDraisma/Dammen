import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class Help extends JFrame implements FocusListener {

    public Help() {
        JTextArea helpText = new JTextArea("Het bord en de beginstand\n" +
                "\n" +
                "Dammen wordt gespeeld op een bord met 50 donkere en 50 lichte velden. Alleen de donkere velden worden gebruikt. Links onder ligt een donker veld.\n" +
                "\n" +
                "Wit en zwart beginnen met ieder 20 schijven en doen om de beurt een zet. Wit begint.\n" +
                "\n" +
                "Op het voorbeeld hiernaast zie je een voorbeeld van de beginstand. Druk op de knop '>' om wit een van de negen mogelijk beginzetten te laten spelen.\n" +
                "\n" +
                "Schuiven\n" +
                "\n" +
                "Dammen wordt ook wel eens geringschattend schuiven genoemd. En schuiven is inderdaad de basis. Als je aan de beurt bent, mag je een schijf schuin naar voren schuiven naar het direct aangrenzende donkere veld. Je mag alleen naar voren en niet naar achter.\n" +
                "\n" +
                "Druk op de knop 'Auto' om een voorbeeld te laten zien.\n" +
                "\n" +
                "Slaan\n" +
                "\n" +
                "Als het goed is, ben je na het volgen van het vorige voorbeeld in de stand hiernaast uitgekomen. Hier kan wit een schijf van zwart slaan. Je springt schuin over de schijf en pakt hem van het bord.\n" +
                "\n" +
                "Als er achter het veld waar je uitkomt weer een vijandelijke schijf staat die je kan slaan, mag je direkt doorslaan. Op dit manier kan je in een beurt meerdere schijven slaan.\n" +
                "\n" +
                "Het is ook toegestaan achteruit te slaan. Druk in het voorbeeld hiernaast op de knop 'Auto' en zie hoe wit drie zwarte schijven in een beurt slaat.\n" +
                "\n" +
                "Dam halen\n" +
                "\n" +
                "Als je met een schijf de overkant van het bord bereikt, wordt het een dam (twee schijven op elkaar). Een dam heeft veel meer mogelijkheden dan een schijf. Hij mag zowel voor- als achteruit. Ook kan hij over een diagonaal over meerdere velden in een keer bewegen.\n" +
                "Let op: als je tijdens een slag de damlijn passeert (dus je slag eindigt er niet), krijg je geen dam!\n" +
                "\n" +
                "Slaan met de dam\n" +
                "\n" +
                "Ook bij het slaan komt de kracht van een dam naar voren. In de volgende drie voorbeelden zie je hoe je met een dam kan slaan. Je mag een losstaande schijf of dam slaan die verder op dezelfde diagonaal staat. Je mag dan zelf bepalen op welk veld achter het geslagen stuk je dam tot stilstand komt.\n" +
                "\n" +
                "Als je op een veld tot stilstand kan komen waar je weer verder zou kunnen slaan, mag je je slag in dezelfde beurt vervolgen. Hierdoor kun je in een beurt een groot aantal schijven slaan.\n" +
                "\n" +
                "Meerslag en damslag\n" +
                "\n" +
                "Soms kun je op meerdere manieren slaan. Je moet dan de slag uitvoeren waarmee je de meeste schijven kunt slaan. Soms is dat niet altijd even leuk, zoals je in het voorbeeld hiernaast ziet. Na de eerste zet van wit is zwart verplicht er vier te slaan, hoewel hij er op drie andere manieren twee of drie zou kunnen slaan.\n" +
                "\n" +
                "In tegenstelling tot wat wel eens beweerd wordt, gaat damslag niet voor. Wit hoeft na het behalen van zijn dam niet met de dam te slaan, maar mag gewoon met de schijf slaan.\n" +
                "\n" +
                "Slaan voor gevorderden\n" +
                "\n" +
                "Er zijn nog wat aanvullende regels voor het slaan, die soms van groot belang kunnen zijn:\n" +
                "1. Je mag niet twee maal over hetzelfde stuk slaan.\n" +
                "2. Je mag de geslagen stukken pas na het voltooien van de complete slag van het bord pakken.\n" +
                "\n" +
                "De consequentie van deze twee regels zie je het voorbeeld hiernaast, ook wel bekend onder de naam Turkse Slag of Coup Turc. De witte dam kan aan het eind van zijn slag niet verder omdat de ene schijf al geslagen is en hij niet over de andere mag slaan omdat de schijf die erachter staat pas na de slag van het bord genomen wordt.\n" +
                "\n" +
                "Ben jij een ster in dammen, vind je vast je weg met online poker. Als je bekend bent met poker heb je hier uiteraard voordeel. Je hebt geen pokerface nodig en speelt altijd live tegen andere mensen overal ter wereld. Wil je een overzicht van de beste pokerrooms neem dan even een kijkje in de beste gids van Nederland.\n" +
                "\n" +
                "Het einde van de partij\n" +
                "\n" +
                "De partij is voorbij als wit of zwart het opgeeft of er remise (gekijkspel) overeen gekomen wordt. Je hebt gewonnen als je tegenstander geen reglementaire zet meer kan spelen. dit is uiteraard het geval als die geen stukken meer over heeft. Maar het kan ook voorkomen dat hij vast staat. Bij het schaakspel is het dan remise (een zogenaamde pat-stelling), maar bij het dammen verlies je.\n" +
                "\n" +
                "In het voorbeeld hiernaast zie je een ingewikkeld voorbeeld. In dit werkje van H.J. van Alphen heeft zwart op het eind nog maar een zet: het weggeven van een dam, waarna hij vervolgens helemaal vast staat.", 5, 40);
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
