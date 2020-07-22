/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        checkNull(points);
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        checkRepeated(sortedPoints);

        final List<LineSegment> list = new LinkedList<>();
        final int num = sortedPoints.length;

        for (int i = 0; i < num; i++) {
            Point p = sortedPoints[i];
            Point[] pointsBySlope = sortedPoints.clone();
            Arrays.sort(pointsBySlope, p.slopeOrder());

            int x = 1;
            while (x < num) {
                double SLOPE_REF = p.slopeTo(pointsBySlope[x]);
                LinkedList<Point> segmentPoints = new LinkedList<>();
                do {
                    segmentPoints.add(pointsBySlope[x++]);
                } while (x < num && p.slopeTo(pointsBySlope[x]) == SLOPE_REF);

                if (segmentPoints.size() >= 3 && p.compareTo(segmentPoints.peek()) < 0) {
                    LineSegment line = new LineSegment(p, segmentPoints.getLast());
                    list.add(line);
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

