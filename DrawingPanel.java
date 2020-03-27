package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawingPanel extends JPanel {

    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image;
    Graphics2D graphics;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    /**
     * sets the frame's background color and size
     */
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, W, H);
    }

    /**
     * The method makes sure to call the drawShape(mouseX,mouseY) function everytime the mouse is pressed
     */
    private void init() {
        setPreferredSize(new Dimension(W, H));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY());
                repaint();
            }
        });
    }

    /**
     * The method gets and sets the following characteristics: type, size, color and sides number, and then it draws the shape
     *
     * @param x mousex --the x and y where the shape is drawn
     * @param y mousey
     */
    private void drawShape(int x, int y) {

        /**
         * getting & setting the selected size
         */
        int shSize = (int) frame.configPanel.shapeSize.getValue();

        /**
         * getting & setting the selected nr of sides
         */
        int sides = (int) frame.configPanel.sidesNumber.getValue();

        /**
         * Getting & generating the selected color
         */
        Color color = Color.black;
        String col = String.valueOf(frame.configPanel.shapeColor.getSelectedItem());
        if (col.equals("Pink")) color = new Color(255, 204, 255);
        if (col.equals("Black")) color = new Color(0, 0, 0);
        if (col.equals("Blue")) color = new Color(153, 204, 255);
        if (col.equals("Green")) color = new Color(0, 255, 0);
        if (col.equals("Purple")) color = new Color(178, 102, 255);
        if (col.equals("Random")) {
            Random rand = new Random();
            int R, G, B;
            R = rand.nextInt(255);
            G = rand.nextInt(255);
            B = rand.nextInt(255);
            color = new Color(R, G, B);
        }
        graphics.setColor(color);

        /**
         * Getting and setting the shape type
         */
        String type = String.valueOf(frame.configPanel.shapeType.getSelectedItem());
        if (type.equals("Regular"))
            graphics.fill(new RegularPolygon(x, y, shSize, sides));
        else
            graphics.fill(new SpecialShape(x, y, shSize));
    }

    @Override
    public void update(Graphics g) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    /**
     * The method clears the drawing area
     */
    public void clear() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, W, H);
        updateUI();
    }
}