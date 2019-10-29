package org.wahlzeit.model;

import java.util.Objects;

public class Coordinate{

    private double x;
    private double y;
    private double z;

    public Coordinate(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }

    /**
     * Calculates the euklidian distance between the two coordinates
     */
    public double getDistance(Coordinate coordinate){
        if(null == coordinate){
            throw new IllegalArgumentException();
        }

        double dx = this.x - coordinate.x;
        double dy = this.y - coordinate.y;
        double dz = this.z - coordinate.z;

        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    public boolean isEqual(Coordinate coordinate){
        if(null == coordinate){
            return false;
        }
        if(this == coordinate){
            return true;
        }

        return Double.compare(coordinate.x, x) == 0 &&
                Double.compare(coordinate.y, y) == 0 &&
                Double.compare(coordinate.z, z) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Coordinate)) return false;

        return isEqual((Coordinate) o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString(){
        return "(" + x +", " + y + ", " + z + ")";
    }
}