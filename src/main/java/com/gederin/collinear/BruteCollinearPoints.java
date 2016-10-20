package com.gederin.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rgederin on 10/13/2016.
 */
public class BruteCollinearPoints {

    //  private com.gederin.collinear.LineSegment[] segments;

    private List<LineSegment> collinearPoints = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) {
        inputChecking(points);
        bruteForse(points);
    }


    public int numberOfSegments() {
        return this.collinearPoints.size();
    }


    public LineSegment[] segments() {
        Object[] arr = this.collinearPoints.toArray();
        LineSegment[] lineArr = Arrays.copyOf(arr, arr.length, LineSegment[].class);
        return lineArr;
    }


    private void bruteForse(Point[] points) {
        int size = points.length;
        for (int p = 0; p < size; p++) {
            for (int q = p + 1; q < size; q++) {
                for (int r = q + 1; r < size; r++) {
                    for (int s = r + 1; s < size; s++) {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r])) {
                            if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])) {
                                if (points[p].slopeTo(points[r]) == points[p].slopeTo(points[s])) {
                                    Point[] collinear = new Point[4];
                                    collinear[0] = points[p];
                                    collinear[1] = points[q];
                                    collinear[2] = points[r];
                                    collinear[3] = points[s];
                                    Arrays.sort(collinear);
                                    collinearPoints.add(new LineSegment(collinear[0], collinear[3]));
                                }
                            }
                        }
                    }
                }
            }
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
