/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;

public class KdTree {
    private class Node {
        private final Point2D value;
        private Node left;
        private Node right;
        private final boolean isVertical;
        private final RectHV rect;

        Node(Point2D val, boolean isVertical, RectHV rect) {
            this.value = val;
            this.isVertical = isVertical;
            this.rect = rect;
        }
    }

    private Node root;
    private int size;
    private final RectHV unitSquare = new RectHV(0.0, 0.0, 1.0, 1.0);

    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        boolean isDuplicatePoint = false;
        Node x = root;
        if (x == null) root = new Node(p, true, unitSquare);
        else {
            while (x != null) {
                int compared = (x.isVertical) ? Double.compare(p.x(), x.value.x()) :
                               Double.compare(p.y(), x.value.y());
                if (compared > 0) {
                    if (x.right == null) {
                        x.right = new Node(p, !(x.isVertical), createRect(x, false));
                        x = null;
                    }
                    else {
                        x = x.right;
                    }
                }
                else {
                    if (p.equals(x.value)) {
                        isDuplicatePoint = true;
                        x = null;
                    }
                    else if (x.left == null) {
                        x.left = new Node(p, !(x.isVertical),
                                          createRect(x, true));
                        x = null;
                    }
                    else {
                        x = x.left;
                    }
                }
            }
        }
        if (!isDuplicatePoint) size++;
    }

    private Node insert(Node x, Point2D p) {
        if (x == null) return new Node(p, true, unitSquare);
        int compared = (x.isVertical) ? Double.compare(p.x(), x.value.x()) :
                       Double.compare(p.y(), x.value.y());
        if (compared > 0) {
            if (x.right == null) {
                x.right = new Node(p, !(x.isVertical), createRect(x, false));
            }
            else x.right = insert(x.right, p);
        }
        else {
            if (p.equals(x.value)) return x;
            if (x.left == null) {
                x.left = new Node(p, !(x.isVertical),
                                  createRect(x, true));
            }
            else x.left = insert(x.left, p);
        }
        return x;
    }

    private RectHV createRect(Node x, boolean leftOrBottom) {
        double xMin, yMin, xMax, yMax;
        if (x.isVertical) {
            if (leftOrBottom) {
                xMin = x.rect.xmin();
                yMin = x.rect.ymin();
                xMax = x.value.x();
                yMax = x.rect.ymax();
            }
            else {
                xMax = x.rect.xmax();
                yMax = x.rect.ymax();
                xMin = x.value.x();
                yMin = x.rect.ymin();
            }
        }
        else {
            if (leftOrBottom) {
                xMin = x.rect.xmin();
                yMin = x.rect.ymin();
                xMax = x.rect.xmax();
                yMax = x.value.y();

            }
            else {
                xMax = x.rect.xmax();
                yMax = x.rect.ymax();
                xMin = x.rect.xmin();
                yMin = x.value.y();
            }
        }
        return new RectHV(xMin, yMin, xMax, yMax);
    }

    public boolean contains(Point2D p) {
        Node x = root;
        while (x != null) {
            int cmp = (x.isVertical) ? (Double.compare(p.x(), x.value.x())) :
                      Double.compare(p.y(), x.value.y());
            if (cmp > 0) {
                x = x.right;
            }
            else {
                if (cmp == 0) {
                    if (p.equals(x.value)) return true;
                }
                x = x.left;
            }
        }
        return false;
    }

    public void draw() {
        /*
            Draw the KdTree to the StdDraw..
         */
    }

    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> ans = new ArrayList<>();
        range(rect, ans, root);
        return ans;
    }

    private void range(RectHV rect, List<Point2D> q, Node x) {
        if (x == null) return;
        if (rect.intersects(x.rect)) {
            range(rect, q, x.left);
            if (rect.contains(x.value)) q.add(x.value);
            range(rect, q, x.right);
        }
    }

    public Point2D nearest(Point2D p) {
        Node x = root;
        return nearest(p, x);
    }

    private Point2D nearest(Point2D p, Node x) {
        Point2D ans = x.value;
        double minDist = p.distanceSquaredTo(x.value);

        if (x.left == null && x.right == null) {
            return x.value;
        }
        else if (x.right == null) {
            if (minDist > x.left.rect.distanceSquaredTo(p)) {
                Point2D q = nearest(p, x.left);
                double temp1 = p.distanceSquaredTo(q);
                if (minDist > temp1) {
                    ans = q;
                    minDist = temp1;
                }
            }
        }
        else if (x.left == null) {
            if (minDist > x.right.rect.distanceSquaredTo(p)) {
                Point2D q = nearest(p, x.right);
                double temp2 = p.distanceSquaredTo(q);
                if (minDist > temp2) {
                    ans = q;
                    minDist = temp2;
                }
            }
        }
        else {
            boolean leftOrBottom = x.left.rect.contains(p);
            if (leftOrBottom) {
                if (minDist > x.left.rect.distanceSquaredTo(p)) {
                    Point2D q = nearest(p, x.left);
                    double temp = p.distanceSquaredTo(q);
                    if (minDist > temp) {
                        minDist = temp;
                        ans = q;
                    }
                }
                if (minDist > x.right.rect.distanceSquaredTo(p)) {
                    Point2D q = nearest(p, x.right);
                    double temp = p.distanceSquaredTo(q);
                    if (minDist > temp) {
                        minDist = temp;
                        ans = q;
                    }
                }
            }
            else {
                if (minDist > x.right.rect.distanceSquaredTo(p)) {
                    Point2D q = nearest(p, x.right);
                    double temp = p.distanceSquaredTo(q);
                    if (minDist > temp) {
                        minDist = temp;
                        ans = q;
                    }
                }
                if (minDist > x.left.rect.distanceSquaredTo(p)) {
                    Point2D q = nearest(p, x.left);
                    double temp = p.distanceSquaredTo(q);
                    if (minDist > temp) {
                        minDist = temp;
                        ans = q;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        /*
        Client Testing for KdTree.java
         */
    }
}
