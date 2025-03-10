package Plan;
/**
 * Plan composé de formes / points
 */

import java.util.HashSet;

import Formes.Formes;
import Point.Point;

public class Plan {
    private HashSet<Formes> formes;
    private HashSet<Point> points;

    public HashSet<Formes> getFormes() {
        return formes;
    }

    public HashSet<Point> getPoints() {
        return points;
    }
}
