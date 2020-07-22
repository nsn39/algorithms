import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;


    public BruteCollinearPoints(Point[] points) {
        checkNull(points);
        Point[] pArr = points.clone();
        Arrays.sort(pArr);
        checkRepeated(pArr);

        int len = points.length;
        List<LineSegment> list = new LinkedList<>();

        for (int i = 0; i < len - 3; i++) {
            Point pZ = pArr[i];
            for (int j = i + 1; j < len - 2; j++) {
                Point pA = pArr[j];
                double slopeZA = pZ.slopeTo(pA);

                for (int k = j + 1; k < len - 1; k++) {
                    Point pB = pArr[k];
                    double slopeZB = pZ.slopeTo(pB);
                    if (slopeZA == slopeZB) {

                        for (int m = k + 1; m < len; m++) {
                            Point pC = pArr[m];
                            double slopeZC = pZ.slopeTo(pC);
                            if (slopeZA == slopeZC) {
                                LineSegment newLine = new LineSegment(pZ, pC);
                                list.add(newLine);
                            }
                        }
                    }
                }
            }
        }
        lineSegments = list.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }

    private void checkNull(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException("Seems like you have passed NULL as your argument.");
        }
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException(
                        "Seems like you have passed NULL as your argument.");
            }
        }
    }

    private void checkRepeated(Point[] points) {
        boolean isRepeated = false;
        Point[] sortedPoints = points.clone();

        for (int i = 0; i < sortedPoints.length - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i + 1]) == 0) {
                isRepeated = true;
                break;
            }
        }
        if (isRepeated) {
            throw new IllegalArgumentException("So you have repeated some of the points.");
        }
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
