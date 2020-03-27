package sample;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    DrawingPanel canvas;
    ControlPanel controlPanel;

    /**
     * creates and adds the panels
     * sets the layout
     */
    public MainFrame() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);
        getContentPane().setLayout(new BorderLayout());
        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();

    }
}