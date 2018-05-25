public abstract class FlareArch implements Comparable<FlareArch> {
    private int ID;
    private int arches;
    private int length; // length is always equal to the order of the parametric polynomial + 1
    private double[] xPoints;
    private double[] yPoints;
    private double[] coefficientsX;
    private double[] coefficientsY;
    boolean valid;

    public FlareArch(int setID, double[] x, double[] y, int setArches) {
        ID = setID;
        arches = setArches;
        if( (x == null || y == null)){
            valid = false;
            length = 0;
        }
        else {
            valid = true;
            length = x.length;
        }
        if(valid && x[3] == 0.0){
            length = 3;
        }
        xPoints = new double[length];
        yPoints = new double[length];
        for (int i = 0; i < length; i++) {
            xPoints[i] = x[i];
            yPoints[i] = y[i];
        }

        // Checks to see if the points are ordered properly and rearranges them if it has to.
        if(valid) {
            double d0 = Math.sqrt(xPoints[0] * xPoints[0] + yPoints[0] * yPoints[0]);
            double dMax = Math.sqrt(xPoints[length - 1] * xPoints[length - 1] + yPoints[length - 1] * yPoints[length - 1]);
            if (d0 > dMax && length == 3) {
                double tempX = xPoints[0];
                double tempY = yPoints[0];
                xPoints[0] = xPoints[2];
                yPoints[0] = yPoints[2];
                xPoints[2] = tempX;
                yPoints[2] = tempY;
            }
            if (d0 > dMax && length == 4) {
                double tempX = xPoints[0];
                double tempY = yPoints[0];
                xPoints[0] = xPoints[3];
                yPoints[0] = yPoints[3];
                xPoints[3] = tempX;
                yPoints[3] = tempY;
                tempX = xPoints[1];
                tempY = yPoints[1];
                xPoints[1] = xPoints[2];
                yPoints[1] = yPoints[2];
                xPoints[2] = tempX;
                yPoints[2] = tempY;
            }

            // Equations that define the characteristics of the equations.
            coefficientsX = new double[length];
            coefficientsY = new double[length];
            if (length != 3 && length != 4) {
                valid = false;
            } else if (length == 3) {
                coefficientsX[0] = xPoints[0];
                coefficientsX[1] = 2 * (xPoints[1] - xPoints[0]);
                coefficientsX[2] = xPoints[2] - (2 * xPoints[1]) + xPoints[0];
                coefficientsY[0] = yPoints[0];
                coefficientsY[1] = 2 * (yPoints[1] - yPoints[0]);
                coefficientsY[2] = yPoints[2] - (2 * yPoints[1]) + yPoints[0];
                valid = true;
            } else if (length == 4) {
                coefficientsX[0] = xPoints[0];
                coefficientsX[1] = 3 * (xPoints[1] - xPoints[0]);
                coefficientsX[2] = 3 * (xPoints[2] - 2 * xPoints[1] + xPoints[0]);
                coefficientsX[3] = xPoints[3] - 3 * xPoints[2] + 3 * xPoints[1] - xPoints[0];
                coefficientsY[0] = yPoints[0];
                coefficientsY[1] = 3 * (yPoints[1] - yPoints[0]);
                coefficientsY[2] = 3 * (yPoints[2] - 2 * yPoints[1] + yPoints[0]);
                coefficientsY[3] = yPoints[3] - 3 * yPoints[2] + 3 * yPoints[1] - yPoints[0];
                valid = true;
            }

            if(arches == 0){
                valid = true;
            }
        }
    }

    public boolean isValid(){
        return valid;
    }

    public int getID() {
        return ID;
    }

    public int getArches(){
        return arches;
    }

    public double getXPoint(int index){
        if(index < length && index >= 0){
            return xPoints[index];
        }
        else{
            return 0.0;
        }
    }
    public double getYPoint(int index){
        if(index < length && index >= 0){
            return yPoints[index];
        }
        else{
            return 0.0;
        }
    }
    public double getCoefficientsX(int index){
        if(index < length && index >= 0){
            return coefficientsX[index];
        }
        else{
            return 0.0;
        }
    }
    public double getCoefficientsY(int index){
        if(index < length && index >= 0){
            return coefficientsY[index];
        }
        else{
            return 0.0;
        }
    }

    public String toString(){
        if(valid) {
            String prntString = "Subject ID: " + ID + " " + "Arches: " + arches + "\n";
            for (int i = 0; i < length; i++) {
                prntString = prntString + "x:" + xPoints[i] + " ";
                prntString = prntString + "y:" + yPoints[i] + " ";
            }
            for (int j = 0; j < length; j++) {
                prntString = prntString + "cx:" + coefficientsX[j] + " ";
                prntString = prntString + "cy:" + coefficientsY[j] + " ";
            }
            prntString = prntString + "\n";
            return prntString;
        }
        return "Subject ID: " + ID + " " + "Arches: " + arches + "\n";
    }
}
