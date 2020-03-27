package sample;

import java.awt.geom.Ellipse2D;

public class SpecialShape extends Ellipse2D.Double {
    /**
     * Creates an ellipse
     */
    public SpecialShape(double x0, double y0, double radius) {
        super(x0 - radius / 2, y0 - radius / 2, radius, radius);
    }
}
