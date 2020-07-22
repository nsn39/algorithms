/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private final TreeSet<Point2D> myPointSet;

    public PointSET() {
        myPointSet = new TreeSet<>();
    }
    // construct an empty set of points

    public boolean isEmpty() {
        return myPointSet.isEmpty();
    }
    // is the set empty?

    public int size() {
        return myPointSet.size();
    }
    // number of points in the set

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("The point is null.");
        }

        myPointSet.add(p);
    }
    // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("The point is null.");
        }
        return myPointSet.contains(p);
    }
    // does the set contain point p?

    public void draw() {
        for (Point2D point : myPointSet) {
            point.draw();
        }
    }
    // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("The rectangle is null.");
        }
        List<Point2D> ansPoints = new ArrayList<>();
        for (Point2D point : myPointSet) {
            if (rect.contains(point)) {
                ansPoints.add(point);
            }
        }
        return ansPoints;
    }
    // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("The point is null.");
        }
        if (myPointSet.isEmpty()) return null;
        Point2D nearestPoint = null;
        double shortestDistance = 5.00;
        for (Point2D point : myPointSet) {
            double distanceTo = p.distanceSquaredTo(point);
            if (distanceTo < shortestDistance) {
                nearestPoint = point;
                shortestDistance = distanceTo;
            }
        }
        return nearestPoint;
    }
    // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
        /*
            Test Client for PointSET.java
         */
    }
}
