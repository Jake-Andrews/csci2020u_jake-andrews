package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    private Canvas mainCanvas;
    @FXML
    public GraphicsContext gc;

    private static double[] dataArray = {
            1243.0, 112.0, 4564.0, 124, 1.0
    };

    @FXML
    public void initialize() {
        gc = mainCanvas.getGraphicsContext2D();
        drawGraph(300, 300, dataArray, Color.RED);
    }

    public void drawGraph(int w, int h, double[] data, Color color) {
        gc.setFill(color);

        //used for scaling, positive and negative infinity
        double maxVal = Double.NEGATIVE_INFINITY, minVal = Double.MAX_VALUE;
        for (double val : data) {
            if (val > maxVal)
                maxVal = val;
            if (val < minVal)
                minVal = val;
        }
        //width of canvas / number of bars. so the bar graph fits
        double barWidth = w / data.length;
        //starting position, 0 to x. left to right
        double x = 0;
        //iterating through data set
        for (double val : data) {
            //scaling the bar's height
            double barHeight = ((val - minVal) / (maxVal - minVal)) * h;
            //x position, height of canvas - barheight, bar width, barheight
            //y value is like that since top left is 0,0.
            gc.fillRect(x, (h - barHeight), barWidth, barHeight);
            //keep moving to the right
            x += barWidth;
        }
    }

    public void drawExample(int w, int h) {
        gc.setFill(Color.RED);
        gc.fillRect(0,0, 150, 150);
        gc.setFill(Color.BLUE);
        gc.fillRect(150,150, 150, 150);
    }
}