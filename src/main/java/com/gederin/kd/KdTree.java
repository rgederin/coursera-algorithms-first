package com.gederin.kd;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

/**
 * Created by rgederin on 11/3/16.
 */
public class KdTree {

    private Node root;
    private int size;

    public KdTree() {
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D point) {
        if (point == null) throw new NullPointerException();
        if (contains(point)) return;

        if (root == null) {
            root = new Node(point, true, null);
        } else {
            putDistinct(root, point, root.vertical, 0.0, 0.0, 1.0, 1.0);

        }
        size++;
    }


    private Node putDistinct(Node node, Point2D point, boolean vertical, double x0, double y0,
                             double x1, double y1) {
        if (node == null) {
            RectHV r = new RectHV(x0, y0, x1, y1);
            return new Node(point, vertical, r);
        }

        double cmp = node.vertical ? node.point.x() - point.x() : node.point.y() - point.y();

        // means that current node is "bigger" than inserted - so new node should goes left
        if (cmp > 0 && vertical) node.left = putDistinct(node.left, point, !node.vertical, x0, y0, node.point.x(), y1);
        else if (cmp > 0 && !vertical) node.left = putDistinct(node.left, point, !node.vertical, x0, y0, x1, node.point.y());

            // means that current node is "smaller" than inserted - so new node should goes right
        else if (cmp <= 0 && vertical) node.right = putDistinct(node.right, point, !node.vertical, node.point.x(), y0, x1, y1 );
        else if (cmp <= 0 && !vertical) node.right = putDistinct(node.right, point, !node.vertical, x0, node.point.y(), x1, y1);

        return node;
    }


    public boolean contains(Point2D point) {
        if (point == null) throw new NullPointerException();

        Node node = root;
        while (node != null) {
            double cmpX = node.point.x() - point.x();
            double cmpY = node.point.y() - point.y();
            double cmp = node.vertical ? cmpX : cmpY;

            if (cmp > 0) node = node.left;
            else if (cmp < 0) node = node.right;
            else if (cmpX == 0 && cmpY == 0) return true;
            else node = node.right;
        }
        return false;
    }

    public void draw() {

    }

    public Point2D nearest(Point2D point) {
        if (root == null) return null;
        return nearest(root, point, root.point, true);
    }

    public Iterable<Point2D> range(RectHV rectangle) {
        if (rectangle == null) throw new NullPointerException();
        Queue<Point2D> queue = new Queue<Point2D>();
        range(root, rectangle, queue);
        return queue;
    }

    private Point2D nearest(Node node, Point2D p, Point2D c, boolean xcmp) {
        Point2D closest = c;
        // If there are no more nodes, return the closest point found so far
        if (node == null) return closest;
        // If the current point is closer than the closest point found so far,
        // update the closest point
        if (node.point.distanceSquaredTo(p) < closest.distanceSquaredTo(p))
            closest = node.point;
        // If the current rectangle is closer to p than the closest point, check its
        // subtrees
        if (node.rectangle.distanceSquaredTo(p) < closest.distanceSquaredTo(p)) {
            // Find which subtree the p is in
            Node near;
            Node far;
            if ((xcmp && (p.x() < node.point.x())) || (!xcmp && (p.y() < node.point.y()))) {
                near = node.left;
                far = node.right;
            }
            else {
                near = node.right;
                far = node.left;
            }
            // Check subtree on the same side as p
            closest = nearest(near, p, closest, !xcmp);
            // Check the subtree on the opposite side as p
            closest = nearest(far, p, closest, !xcmp);
        }
        return closest;
    }

    private void range(Node node, RectHV rectangle, Queue<Point2D> queue) {
        if (node == null) return;
        // If the current point is in the input rectangle, enqueue that point
        if (rectangle.contains(node.point)) {
            queue.enqueue(node.point);
        }
        // Check the left and right subtrees if the input rectangle intersects
        // the current rectangle
        if (rectangle.intersects(node.rectangle)) {
            range(node.left, rectangle, queue);
            range(node.right, rectangle, queue);
        }
    }

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.0, 0.1));
        kdTree.insert(new Point2D(0.0, 0.1));
        System.out.println(kdTree.size());
    }

    private static class Node {
        private Point2D point;
        private RectHV rectangle;
        private boolean vertical;
        private Node left;
        private Node right;

        public Node(Point2D point, boolean vertical, RectHV rectangle) {
            this.point = point;
            this.vertical = vertical;
            this.rectangle = rectangle;
        }
    }
}
