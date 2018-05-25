public class mainFlares {
    public static int subjects = 60;

    public static void main(String[] args){
        flareReadAndWrite dataSet = new flareReadAndWrite(4619, 53.169); //Creates a place for data of a particular sort to be set.
        dataSet.readCSV( "Alpha_raw_data.csv"); // This should be the most updated data file.
        FlareList dataList = dataSet.getMainList(); // Creates a list of Flares from the dataset.
        FlareList[] flareSubjects = new FlareList[subjects];
        int i = 0;
        while(!dataList.isEmpty()) {
            flareSubjects[i] = dataList.findID(dataList.getID(0));
            System.out.println(flareSubjects[i].toString());
         //   System.out.println(flareSubjects[i].statStringPrint());
            i++;
        }
        dataSet.writeDataToTxt(flareSubjects, "alphaDat.txt");
    }
}
