package com.productoescalar;

public class Vector {
    private double x;
    private double y;
    private double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }

    public double productorEscalar(Vector other) {
        if (this.x == 0 || this.y == 0 || this.z == 0 || other.x == 0 || other.y == 0 || other.z == 0) {
            System.out.println("Uno de los vectores tiene un componente cero.");
        }
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }
}
