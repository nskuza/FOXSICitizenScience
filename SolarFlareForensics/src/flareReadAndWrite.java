import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class flareReadAndWrite {
    public FlareList f;
    int workflowID;
    double workflowVersion;

    // Creates a of data for one workflow ID and version. These should be unique for any data collecting expenditure.
    public flareReadAndWrite(int workflowID, double workflowVersion){
        this.workflowID = workflowID;
        this.workflowVersion = workflowVersion;
        f = new FlareList();
    }

    public FlareList getMainList(){
        return f;
    }

    // Reads the csv data file and converts every flare response into a flare object.
    public boolean readCSV(String fileName){
        Scanner scan = null;

        // Here the file must be read and only stores information with lines that have the proper workflow.
        try{
            scan = new Scanner(new File(fileName));
        } catch (Exception e){
            System.out.println("Error no file found.");
            return false;
        }
        String readLine;
        int indexWID = -1, indexVersion = -1;
        readLine = scan.nextLine();
        String[] lineArray = readLine.split(",");
        for(int i = 0; i < lineArray.length; i++){
            if(lineArray[i].equals("workflow_id")){
                indexWID = i;
            }
            else if(lineArray[i].equals("workflow_version")){
                indexVersion = i;
            }
        }

        // Member variables for

        while(scan.hasNext()){
            readLine = scan.nextLine();
            int subjectID = 0;
            double[] xPoints = null;
            double[] yPoints = null;
            int arches = 0;
            lineArray = readLine.split(",");
            int testID =  Integer.parseInt(lineArray[indexWID]); // The testID is taken from the proper index
            double testVersion = Double.parseDouble(lineArray[indexVersion]); // The test version is taken from the proper index
            if(testID == workflowID && testVersion == workflowVersion) {
                subjectID = Integer.parseInt(lineArray[lineArray.length - 1]);
                lineArray = readLine.split("task");
                for (int i = 0; i < lineArray.length; i++) {
                    // This checks if a bezier curve was drawn. Only records the brightest (red curves).
                    if(lineArray[i].contains("T1") && lineArray[i+1].contains("x") && lineArray[i+1].contains("Outline the brightest flare structure.") ){
                        xPoints = extractT1XArray(lineArray[i + 1]);
                        yPoints = extractT1YArray(lineArray[i + 1]);
                    }
                    // This checks how many arhes were determined to be found.
                    if(lineArray[i].contains("T2") && lineArray[i+1].contains("value")){
                        arches = extractArches(lineArray[i+1]);
                    }
                }
                FlareArch fa = new FlareArch(subjectID, xPoints, yPoints, arches) {
                    @Override
                    public int compareTo(FlareArch o) {
                        return 0;
                    }
                };
                f.add(fa);
            }
        }
        return true;
    }

    // Writes relevant data to a txt file for plotting.
    public boolean writeDataToTxt(FlareList[] flareSubjects, String dataFile){
        PrintWriter p;
        try {
            p = new PrintWriter(new File(dataFile));
        } catch (Exception e){
            System.out.println("Could not create file.");
            return false;
        }
        for(int i=0; i < flareSubjects.length; i++) {
            p.println(flareSubjects[i].statStringData());
        }
        p.close();
        return true;
    }

    // Collects the X array data.
    public double[] extractT1XArray(String data){
       String[] seperateCurves = data.split("auto_closed");
       String[] dataArray = new String[4];
       for(int i = 0; i < seperateCurves.length; i++) {
           if(seperateCurves[i].contains("x")) {
               dataArray = seperateCurves[i].split("\"");
           }
       }
       double[] xPoints = new double[4];
       int j = 0;
       for(int i = 0; i < dataArray.length; i++){
           if(dataArray[i].equals("x") && j < 4){
               dataArray[i+2] = dataArray[i+2].replaceAll(",|:|}|]", "");
               dataArray[i+2] = dataArray[i+2].replace("{", "");
               xPoints[j] = Double.parseDouble(dataArray[i+2]);
               j++;
           }
       }
       if( j < 4){
           return xPoints;
       }
       else{ return null;}
    }

    // Collects the Y Array data.
    public double[] extractT1YArray(String data){
        String[] seperateCurves = data.split("auto_closed");
        String[] dataArray = new String[4];
        for(int i = 0; i < seperateCurves.length; i++) {
            if(seperateCurves[i].contains("y")) {
                dataArray = seperateCurves[i].split("\"");
            }
        }
        double[] yPoints = new double[4];
        int j = 0;
        for(int i = 0; i < dataArray.length; i++){
            if(dataArray[i].equals("y") && j < 4){
                dataArray[i+2] = dataArray[i+2].replaceAll(",|:|}|]", "");
                dataArray[i+2] = dataArray[i+2].replace("{", "");
                yPoints[j] = Double.parseDouble(dataArray[i+2]);
                j++;
            }
        }
        if( j < 4){
            return yPoints;
        }
        else{ return null;}
    }

    public int extractArches(String line){
        String[] parsed = line.split("value");
        if(parsed[1].contains("None") || parsed[1].contains("null")){ return 0;} // Accounts for situtations where parseInt will fail.
        if(parsed[1].contains("0")){ return 0;}
        if(parsed[1].contains("1")){ return 1;}
        if(parsed[1].contains("2")){ return 2;}
        if(parsed[1].contains("3")){ return 3;}
        if(parsed[1].contains("3")){ return 3;}
        if(parsed[1].contains("4")){ return 4;}
        return 0;
    }

    public String toString(){
        return f.toString();
    }
}
