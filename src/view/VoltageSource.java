package view;

import java.awt.*;

/**
 * Created by ekot on 09.04.15.
 */
public class VoltageSource extends CircuitComponent {
    public VoltageSource(int x, int y) {
        super(x, y, 140, 180);
    }

    @Override
    public void paintComponent(Graphics g) {
        setColorIfSelected(g);

        int d = 30;
        int hr = 5;

        if (getOrientation() == Orientation.Horizontal) {

            g.drawLine(x, y, x + d, y);
            g.drawLine(x+2*d, y, x + 3*d, y);

            g.drawLine(x+d+5, y, x + d+ 20, y);
            g.drawLine(x+d+10, y-5, x + d+ 20, y);
            g.drawLine(x+d+10, y+5, x + d+ 20, y);

            g.drawArc(x+ 6* hr, y - 3*hr , 6 * hr, 6 * hr, 0, 360);
            g.drawArc(x-2*hr, y - hr, 2 * hr, 2 * hr, 0, 360);
            g.drawArc(x + 3*d, y - hr, 2 * hr, 2 * hr, 0, 360);



            if (name != null && name.length() > 0)
                g.drawString(name, x - 80, y+20);
        }
        else
        {
            g.drawArc(x-hr, y - 2* hr, 2 * hr, 2 * hr, 0, 360);
            g.drawArc(x- 3* hr, y + d, 6 * hr, 6 * hr, 0, 360);

            g.drawLine(x, y, x, y+d);
            g.drawLine(x, y+d+5, x, y+d+25 );
            g.drawLine(x, y+2*d, x, y+3*d);

            g.drawLine(x-5, y+d+15, x, y+d+25 );
            g.drawLine(x+5, y+d+15, x, y+d+25 );

            g.drawArc(x-hr, y + 3*d, 2 * hr, 2 * hr, 0, 360);


            if (name != null && name.length() > 0)
                g.drawString(name, x+2, y + w/2 + g.getFontMetrics().getHeight() / 2);


        }



    }
    
}
