public class FlareList {
    private ArrayList<FlareArch> flares = new ArrayList<FlareArch>();

    public FlareList(){}

    public void add(FlareArch fa){
        flares.add(fa);
    }

    public boolean isEmpty(){
        return flares.isEmpty();
    }

    public int getSize(){
        return flares.size();
    }

    public int getID(int index){
        return flares.get(index).getID();
    }

    private double mean(double[] data){
        double sum = 0;
        int j = 0;
        for(int i = 0; i < data.length; i++){
            if(data[i] != 0) {
                sum += data[i];
                j++;
            }
        }
        return sum/j;
    }

    private double stanDev(double[] data){
        double mean = mean(data);
        double sum = 0;
        int j = 0;
        for(int i = 0; i < data.length; i++){
            if(data[i] != 0) {
                sum += ((mean - data[i]) * (mean - data[i]));
                j++;
            }
        }
        return Math.sqrt(sum/j);
    }

    // Returns a String for printing the data to the screen. Most useful for quick read of the data.
    public String statStringPrint(){
        double r = 100.0;
        String results = "";
        results += "ID: " + flares.get(0).getID() + "\n" + "\n";
        int setSize = flares.size();
        double x[] = new double[setSize], y[] = new double[setSize], cx[] = new double[setSize], cy[] = new double[setSize];
        for(int j = 0; j < 3; j++){
            for(int i = 0; i < flares.size(); i++){
                x[i] = flares.get(i).getXPoint(j);
                y[i] = flares.get(i).getYPoint(j);
                cx[i] = flares.get(i).getCoefficientsX(j);
                cy[i] = flares.get(i).getCoefficientsY(j);
            }
            results += "x" + j + ": " + Math.round(r*mean(x))/r + " +/- " + Math.round(r*stanDev(x))/r + "\n";
            results += "y" + j + ": " + Math.round(r*mean(y))/r + " +/- " + Math.round(r*stanDev(y))/r + "\n";
            results += "cx" + j + ": " + Math.round(r*mean(cx))/r + " +/- " + Math.round(r*stanDev(cx))/r + "\n";
            results += "cy" + j + ": " + Math.round(r*mean(cy))/r + " +/- " + Math.round(r*stanDev(cy))/r + "\n";
        }
        return results;
    }

    // Returns a String for writing the data in line to a file.
    public String statStringData(){
        double r = 100.0;
        String results = "";
        results += flares.get(0).getID() + " ";
        int setSize = flares.size();
        double x[] = new double[setSize], y[] = new double[setSize], cx[] = new double[setSize], cy[] = new double[setSize];
        for(int j = 0; j < 3; j++){
            for(int i = 0; i < flares.size(); i++){
                x[i] = flares.get(i).getXPoint(j);
                y[i] = flares.get(i).getYPoint(j);
             //   cx[i] = flares.get(i).getCoefficientsX(j);
             //   cy[i] = flares.get(i).getCoefficientsY(j);
            }
            results += Math.round(r*mean(x))/r + " ";
            results += Math.round(r*mean(y))/r + " ";
           // results += Math.round(r*mean(cx))/r + " ";
           // results += Math.round(r*mean(cy))/r + " ";
        }
        return results;
    }

    // Returns a list of Flares with common ID.
    public FlareList findID(int ID){
        FlareList singleID = new FlareList();
        for(int i = 0; i < flares.size(); i++){
            if(flares.get(i).getID() == ID){
                singleID.add(flares.remove(i));
                i--;
            }
        }
        return singleID;
    }



    public String toString(){
        String listString = "";
        for(int i = 0; i < flares.size(); i++){
            listString = listString + flares.get(i).toString() + "\n" ;
        }
        return listString;
    }
}
