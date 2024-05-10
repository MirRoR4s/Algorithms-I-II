public class BruteCollinearPoints {
    private int numberOfSegments;
    private LineSegment[] lineSegments;
    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        if (points.equals(null)) {
            throw new IllegalArgumentException("The Points can't be null");
        }
        for (Point p: points) {
            if (p.equals(null)) {
                throw  new IllegalArgumentException("The Point can't be null");
            }
        }
        // 检查是否出现重复的点
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                Point p = points[i];
                Point q = points[j];
                if (p.compareTo(q) == 0) {
                    throw  new IllegalArgumentException("The Point can't be repeated");
                }
            }
        }
        lineSegments = new LineSegment[points.length]; // 线段数组的长度如何确定？
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {
                for (int k = j + 1; k < points.length - 2; k++) {
                    for (int l = k + 1; l < points.length - 3; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];

                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
                            LineSegment lineSegment = new LineSegment(p, s);
                            lineSegments[numberOfSegments] = lineSegment;
                            numberOfSegments += 1;
                        }
                    }
                }
            }
        }

    }
    public int numberOfSegments() { // the number of line segments
        return numberOfSegments;
    }
    
    public LineSegment[] segments() {  // the line segments
        return lineSegments;
    }
}
