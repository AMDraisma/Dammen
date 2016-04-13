import java.awt.*;

public class Damsteen {
    Color kleur;
    public static int DIAMETER = 10*2;
    public static int RADIUS = DIAMETER/2;
    boolean dam;

    public Damsteen(int k) {
        if (k == 0)
            kleur = Color.BLACK;
        else
            kleur = Color.WHITE;
        dam = false;
    }
}
