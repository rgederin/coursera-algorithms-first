import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> collinearPoints = new ArrayList<LineSegment>();
    private List<Double> slopes = new ArrayList<Double>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        if (points.length < 4) {
            return;
        }

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }


        fast(points);

    }


    private void fast(Point[] points) {
        Point[] internalPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            internalPoints[i] = points[i];
        }

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Arrays.sort(internalPoints, p.slopeOrder());
            double slope1 = p.slopeTo(internalPoints[1]);
            double slope2 = p.slopeTo(internalPoints[2]);
            double slope3 = p.slopeTo(internalPoints[3]);

            if (slope1 == slope2 && slope1 == slope3 && slope2 == slope3) {

                Point[] segment = new Point[]{p, internalPoints[1], internalPoints[2], internalPoints[3]};
                Arrays.sort(segment);
                this.collinearPoints.add(new LineSegment(segment[0], segment[3]));

            }
        }
    }


    private void sortBase(Point[] points) {
        Point[] internalPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            internalPoints[i] = points[i];
        }
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Arrays.sort(points, p.slopeOrder());
            for (int j = i + 1; j < points.length - 1; j += 3) {
               /* if (p != points[i] && p.slopeTo(points[j]) == p.slopeTo(points[j + 1]) ||
                        p.slopeTo(points[j]) == p.slopeTo(points[j + 2]) ||
                        p.slopeTo(points[j + 1]) == p.slopeTo(points[j + 2])) {
                    Point[] coll = new Point[]{p, points[j], points[j + 1], points[j + 2]};
                    Arrays.sort(coll);
                    this.collinearPoints.add(new LineSegment(coll[0], coll[3]));
                }*/

                /*if (p.slopeTo(points[j]) == p.slopeTo(points[j + 1])){
                    if (p.slopeTo(points[j]) == p.slopeTo(points[j + 2])){
                        if (p.slopeTo(points[j+1]) == p.slopeTo(points[j + 2])){
                            Point[] coll = new Point[]{p, points[j], points[j + 1], points[j + 2]};
                            Arrays.sort(coll);
                            this.collinearPoints.add(new LineSegment(coll[0], coll[3]));

                        }
                    }
                }*/
            }

        }
    }

    public int numberOfSegments() {
        return collinearPoints.size();
    }


    public LineSegment[] segments() {

        Object[] objects = this.collinearPoints.toArray();
        LineSegment[] lineSegments = Arrays.copyOf(objects, objects.length,
                LineSegment[].class);
        return lineSegments;
    }
}
