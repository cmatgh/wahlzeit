package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate{

    private static final double TWO_PI = 2 * Math.PI;

    private final static Map<Integer, SphericCoordinate> cache = new HashMap<>();

    private double radius;
    private double theta;
    private double phi;

    public double getRadius() {
        return radius;
    }

    public double getTheta() { return theta; }

    public double getPhi() {
        return phi;
    }

    public static SphericCoordinate valueOf(double radius, double theta, double phi) throws CoordinateException {
        if(radius < 0){
            throw new CoordinateException("Radius of SphericCoordinate cannot be negative");
        }

        SphericCoordinate coordinate = doValueOf(radius, theta, phi);

        assert coordinate != null;

        return coordinate;
    }

    private static SphericCoordinate doValueOf(double radius, double theta, double phi) {
        double thetaNormalized = normalizeAngle(theta, Math.PI);
        double phiNormalized = normalizeAngle(phi, TWO_PI);

        return putToCacheIfAbsentAndReturn(radius, thetaNormalized, phiNormalized);
    }

    private static SphericCoordinate putToCacheIfAbsentAndReturn(double radius, double theta, double phi){
        int hash = Objects.hash(radius, theta, phi);
        if(!cache.containsKey(hash)){
            synchronized (SphericCoordinate.class){
                if(!cache.containsKey(hash)) {
                    SphericCoordinate coordinate = new SphericCoordinate(radius, theta, phi);
                    cache.put(coordinate.hashCode(), coordinate);
                }
            }

        }

        return cache.get(hash);
    }

    private SphericCoordinate(double radius, double theta, double phi) {
        this.radius = radius;
        this.theta = theta;
        this.phi = phi;

        assertClassInvariants();
    }

    @Override
    protected CartesianCoordinate doAsCartesianCoordinate() throws CoordinateException {
        double radius = getRadius();
        double theta = getTheta();
        double phi = getPhi();

        CartesianCoordinate cartesianCoordinate = basicAsCartesianCoordinate();

        assert radius == getRadius();
        assert theta == getTheta();
        assert phi == getPhi();

        return cartesianCoordinate;
    }

    private CartesianCoordinate basicAsCartesianCoordinate() throws CoordinateException {
        double x = getRadius() * Math.sin(getPhi()) * Math.cos(getTheta());
        double y = getRadius() * Math.sin(getPhi()) * Math.sin(getTheta());
        double z = getRadius() * Math.cos(getPhi());

        return CartesianCoordinate.valueOf(x, y, z);
    }

    @Override
    protected double doGetCartesianDistance(Coordinate coordinate) throws CoordinateException {
        return asCartesianCoordinate().doGetCartesianDistance(coordinate);
   }

    @Override
    protected SphericCoordinate doAsSphericCoordinate() {
        return this;
    }

    @Override
    protected double doGetCentralAngle(Coordinate coordinate) throws CoordinateException {
        double radius = this.getRadius();
        double theta = this.getTheta();
        double phi = this.getPhi();

        double angle = basicGetCentralAngle(coordinate);

        assert radius == getRadius();
        assert theta == getTheta();
        assert phi == getPhi();

        return angle;
    }

    private double basicGetCentralAngle(Coordinate coordinate) throws CoordinateException {
        SphericCoordinate c1 = this;
        SphericCoordinate c2 = coordinate.asSphericCoordinate();

        if(c1.getRadius() <= DELTA || c2.getRadius() <= DELTA){
            return 0;
        }
        double dLong = Math.abs((c1.getTheta() - Math.PI) - (c2.getTheta() - Math.PI));
        double lat1 = c1.getPhi()- Math.PI/2;
        double lat2 = c2.getPhi()- Math.PI/2;

        double t1 = Math.cos(lat2) * Math.sin(dLong);
        double t2 = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(dLong);
        double denom = (Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(dLong));
        if(denom == 0){
            return 0;
        }

        return Math.abs(Math.atan(Math.sqrt(t1*t1 + t2*t2)/denom));
    }

    @Override
    protected boolean doIsEqual(Coordinate coordinate) throws CoordinateException {
        SphericCoordinate coord = coordinate.asSphericCoordinate();

        return coord == this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRadius(), getTheta(), getPhi());
    }

    @Override
    public String toString(){
        return "(" + getRadius() +", " + getTheta() + ", " + getPhi() + ")";
    }

    @Override
    protected void assertClassInvariants(){
        assert getRadius() >= 0;
        assert getTheta() >= 0 && getTheta() < Math.PI;
        assert getPhi() >= 0 && getPhi() < TWO_PI;
    }

    private static double normalizeAngle(double angle, double normalizationFactor){
        double normalizedAngle = angle % normalizationFactor;
        if(normalizedAngle < 0){
            normalizedAngle +=  normalizationFactor;
        }
        return normalizedAngle;
    }

}
