package myanswers;

import java.io.*;
import java.util.LinkedHashMap;

import static java.lang.System.exit;


public class FileProcess {

    public static void main(String args[]) throws IOException {

        String inputFilePath = "/Users/smridul/Downloads/unremovable_comms_accounts.csv";
        String outputFilePath = "/Users/smridul/Downloads/unremovable_comms_accounts";

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;


        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileWriter = new FileWriter(outputFilePath);
            bufferedWriter = new BufferedWriter(fileWriter);


            fileReader = new FileReader(inputFilePath);
            bufferedReader = new BufferedReader(fileReader);


        } catch (IOException e) {
            System.out.println("File not found");
            exit(0);
        }

        String sCurrentLine = null;
        LinkedHashMap<Integer, Boolean> map = new LinkedHashMap<>();

        try {
            while ((sCurrentLine = bufferedReader.readLine()) != null) {


                String[] input = sCurrentLine.split(",");

                bufferedWriter.write(input[1]+ " " + "REMOVE "+ input[2]);
                bufferedWriter.newLine();
            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException ioException) {

            }
        }


        exit(0);
    }
}


