package view;

import gui.ElectricalComponentDialog;
import gui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by GR5 on 24.03.15.
 */
public class CircuitPanel extends JPanel implements ActionListener {

    java.util.List<CircuitComponent> components = new ArrayList<CircuitComponent>();
    java.util.List<CircuitConnection> connections = new ArrayList<CircuitConnection>();

    public CircuitPanel() {
        buildTestComponents();
        addActions();
    }

    private void addActions() {
        MouseAdapter mouseAdapter = new MouseAdapter() {

            int oldx, oldy;

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() % 2 == 0 && !mouseEvent.isConsumed()) {
                    CircuitComponent cc = findComponent(mouseEvent.getX(),mouseEvent.getY());

                    if (cc != null) {
                        ElectricalComponentDialog dlg = new ElectricalComponentDialog(cc);
                        dlg.addActionListener( CircuitPanel.this );
                        dlg.pack();
                        dlg.setVisible(true);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                oldx = e.getX();
                oldy = e.getY();

                for (CircuitComponent cc: components) {
                    if (!e.isShiftDown())
                        cc.setSelected(false);
                    if (cc.isInside(e.getX(), e.getY()))
                        if (e.isShiftDown())
                            cc.setSelected(!cc.isSelected());
                        else
                            cc.setSelected(true);
                }

                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                int dx = e.getX() - oldx;
                int dy = e.getY() - oldy;

                for (CircuitComponent cc: components) {
                    if (cc.isSelected()) {
                        cc.x += dx;
                        cc.y += dy;
                    }
                }

                oldx = e.getX();
                oldy = e.getY();

                repaint();
            }
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    private CircuitComponent findComponent(int x, int y) {
        for (CircuitComponent cc: components) {
            if (cc.isInside(x,y)) {
                return cc;
            }
        }
        return null;
    }

    private void buildTestComponents() {
        CircuitComponent c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;
        components.add(c1 = new CircuitComponent(20, 20, 30, 40));
        components.add(c2 = new CircuitComponent(200,20,60,40));
        components.add(c3 = new CircuitComponent(20, 80, 10, 40));
        components.add(c4 = new CircuitComponent(200, 140, 50, 40));
        c4.name = "Prostokącik";
        components.add(c5 = new ResistorView(150, 200));
        components.add(c6 = new ResistorView(280, 350));
        c6.setOrientation(CircuitComponent.Orientation.Vertical);

        c5.name = "R5";
        c6.name = "R6";

        components.add(c7 = new CapacitorView(80, 350));
        c7.setOrientation(CircuitComponent.Orientation.Vertical);
        c7.name = "Kondensator";

        components.add(c8 = new InductorView(350, 350));
        c8.setOrientation(CircuitComponent.Orientation.Vertical);
        c8.name = "cewka";

        components.add(c9 = new VoltageSource( 220 , 250));
        c9.setOrientation(CircuitComponent.Orientation.Horizontal);
        c9.name = "źródło napięciowe";

        components.add(c10 = new ElectricitySource( 20 , 250));
        c10.setOrientation(CircuitComponent.Orientation.Vertical);
        c10.name = "źródło prądowe";

        connections.add(new CircuitConnection(c1, c2));
        connections.add(new CircuitConnection(c3, c2));
        connections.add(new CircuitConnection(c3, c4));
        connections.add(new CircuitConnection(c1, c4));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawString("Circuits Simulator " + Main.VERSION, 5, 20);

        for (CircuitComponent cc: components) {
            cc.paintComponent(graphics);
        }

        graphics.setColor(Color.BLUE);
        for (CircuitConnection con: connections) {
            graphics.drawLine(
                    con.src.getMidX(), con.src.getMidY(),
                    con.dest.getMidX(), con.dest.getMidY()
                    );
        }
    }

    public void addCircuitComponent(ResistorView rv) {
        components.add(rv);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("repaint")) {
            repaint();
        }
    }
}
