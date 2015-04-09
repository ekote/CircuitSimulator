package view;

import java.awt.*;

/**
 * Created by ekot on 31.03.15.
 */
public class CapacitorView extends CircuitComponent{
    public CapacitorView(int x, int y) {
        super(x, y, 140, 180);
    }

    @Override
    public void paintComponent(Graphics g) {
        setColorIfSelected(g);

        int d = 30;
        int hr = 5;

        if (getOrientation() == Orientation.Horizontal) {

            g.drawLine(x, y, x + d, y);
            g.drawLine(x + d, y, x + d, y+d);
            g.drawLine(x + d, y, x + d, y-d);

            g.drawLine(x+2*d, y, x + 3*d, y);
            g.drawLine(x +2*d, y, x + 2*d, y+d);
            g.drawLine(x +2*d, y, x + 2*d, y-d);

            g.drawArc(x-2*hr, y - hr, 2 * hr, 2 * hr, 0, 360);
            g.drawArc(x + 3*d, y - hr, 2 * hr, 2 * hr, 0, 360);



            if (name != null && name.length() > 0)
                g.drawString(name, x - 80, y+20);
        }
        else
        {

            g.drawLine(x, y, x, y+d);
            g.drawLine(x, y+d, x + d, y+d);
            g.drawLine(x, y+d, x - d, y+d);

            g.drawLine(x, y+2*d, x, y+3*d);
            g.drawLine(x, y+2*d, x + d, y+2*d);
            g.drawLine(x, y+2*d, x - d, y+2*d);


            g.drawArc(x-hr, y - 2* hr, 2 * hr, 2 * hr, 0, 360);
            g.drawArc(x-hr, y + 3*d, 2 * hr, 2 * hr, 0, 360);


            if (name != null && name.length() > 0)
                g.drawString(name, x+2, y + w/2 + g.getFontMetrics().getHeight() / 2);


        }



    }
}
