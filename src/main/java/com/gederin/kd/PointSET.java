package com.gederin.kd;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rgederin on 11/3/16.
 */
public class PointSET {
    private SET<Point2D> tree;

    public PointSET() {
        tree = new SET<Point2D>();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }

    public boolean contains(Point2D point) {
        if (point == null) throw new NullPointerException();
        return isEmpty() ? false : tree.contains(point);
    }

    public void insert(Point2D point) {
        if (point == null) throw new NullPointerException();
        if (!contains(point)) tree.add(point);
    }

    public void draw() {
        for (Point2D point : tree) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException();
        List<Point2D> range = new ArrayList<Point2D>();
        for (Point2D point : tree) {
            if (rect.contains(point)) range.add(point);

        }
        return range;
    }

    public Point2D nearest(Point2D point) {
        if (point == null) throw new NullPointerException();
        double distance = Double.POSITIVE_INFINITY;
        Point2D nearest = null;
        for (Point2D loopPoint : tree) {
            if (point.distanceTo(loopPoint) < distance) {
                nearest = loopPoint;
                distance = point.distanceTo(loopPoint);
            }
        }

        return nearest;
    }
}
