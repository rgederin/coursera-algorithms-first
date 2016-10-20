package com.gederin.collinear;

import java.util.*;

public class FastCollinearPoints {
    private List<LineSegment> lineSegments = new ArrayList<LineSegment>();
    private HashMap<Double, List<Point>> foundSegments = new HashMap<Double, List<Point>>();

    public FastCollinearPoints(Point[] points) {
        inputChecking(points);
        computeLineSegments(points);
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {

        Object[] objects = this.lineSegments.toArray();
        LineSegment[] lineSegments = Arrays.copyOf(objects, objects.length,
                LineSegment[].class);
        return lineSegments;
    }


    private void computeLineSegments(Point[] points) {
        //create copy of initial array for using it in slope sorting
        Point[] internalPoints = Arrays.copyOf(points, points.length);

        //go through all point in initial points array
        for (int i = 0; i < points.length; i++) {
            //take point as origin
            Point origin = points[i];
            //sort internal array according to slopes against origin
            Arrays.sort(internalPoints, origin.slopeOrder());

            List<Point> collinearPoints = new ArrayList<Point>();
            double slope = 0;
            double previousSlope = Double.NEGATIVE_INFINITY;

            //go over sorted points and check if 3 or more adjacent points are collinear
            for (int j = 1; j < internalPoints.length; j++) {
                slope = origin.slopeTo(internalPoints[j]);
                if (slope == previousSlope){
                    collinearPoints.add(internalPoints[j]);
                }else {
                    if (collinearPoints.size() >= 3){
                        collinearPoints.add(origin);
                        addLineSegmentIfNotExists(collinearPoints, previousSlope);
                    }
                    collinearPoints.clear();
                    collinearPoints.add(internalPoints[j]);
                }
                previousSlope = slope;
            }

            if (collinearPoints.size() >= 3){
                collinearPoints.add(origin);
                addLineSegmentIfNotExists(collinearPoints, slope);
            }
        }
    }


    private void addLineSegmentIfNotExists (List<Point> slopePoints, double slope) {
        List<Point> endPoints = foundSegments.get(slope);
        Collections.sort(slopePoints);

        Point startPoint = slopePoints.get(0);
        Point endPoint = slopePoints.get(slopePoints.size() - 1);

        if (endPoints == null) {
            endPoints = new ArrayList<Point>();
            endPoints.add(endPoint);
            foundSegments.put(slope, endPoints);
            lineSegments.add(new LineSegment(startPoint, endPoint));
        } else {
            for (Point currentEndPoint : endPoints) {
                if (currentEndPoint.compareTo(endPoint) == 0) {
                    return;
                }
            }
            endPoints.add(endPoint);
            lineSegments.add(new LineSegment(startPoint, endPoint));
        }
    }


    private void inputChecking(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
}
