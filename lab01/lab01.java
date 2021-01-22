package lab01;

public abstract class lab01 {
    private String colour = "";

    public lab01(String colour) {
        this.colour = colour; 
    }

    public String getColour() {
        return this.colour;
    }

    public abstract float getArea();
    public abstract float getPerimeter();
    public abstract String toString();
}