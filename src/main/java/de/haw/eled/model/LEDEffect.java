package de.haw.eled.model;

/**
 * Created by Tim on 05.05.2017.
 */
public class LEDEffect {
    private int r;
    private int g;
    private int b;
    private int w;

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    private int time;

    public LEDEffect(int r, int g, int b, int w, int time) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.time = time;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
