package helpers;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReaderClass {


    public static void readCSVFile(String filename) throws IOException {


            CSVReader csvReader = new CSVReader(new FileReader(filename));

            String[] row = null;

            while ((row = csvReader.readNext()) != null){

                System.out.println(row[0] + " -- " + row[1]);
            }
            csvReader.close();
    }


    public static String getFirstValueFromCSV(String filename) throws IOException {

        String value ;
        CSVReader csvReader = null;

            csvReader = new CSVReader(new FileReader(filename));
            String[] row = csvReader.readNext();
            value = row[0];
            csvReader.close();

        return value;
    }


    public static String getSecondValueFromCSV(String filename) throws IOException {

        String value ;
        CSVReader csvReader = null;

        csvReader = new CSVReader(new FileReader(filename));
        String[] row = csvReader.readNext();
        value = row[1];
        csvReader.close();

        return value;
    }
}
