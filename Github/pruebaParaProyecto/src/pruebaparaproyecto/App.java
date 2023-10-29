/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaparaproyecto;


import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

/**
 *
 * @author user
 */
public class App {
    // create an AffineTransform 
    // and a triangle centered on (0,0) and pointing downward
    // somewhere outside Swing's paint loop
    AffineTransform tx = new AffineTransform();
    Line2D.Double line = new Line2D.Double(0,0,100,100);
    Polygon arrowHead = new Polygon();  
    arrowHead.addPoint( 0,5);
    arrowHead.addPoint( -5, -5);
    arrowHead.addPoint( 5,-5);

    // [...]
    private void drawArrowHead(Graphics2D g2d) {  
        tx.setToIdentity();
        double angle = Math.atan2(line.y2-line.y1, line.x2-line.x1);
        tx.translate(line.x2, line.y2);
        tx.rotate((angle-Math.PI/2d));  

        Graphics2D g = (Graphics2D) g2d.create();
        g.setTransform(tx);   
        g.fill(arrowHead);
        g.dispose();
    }
}
