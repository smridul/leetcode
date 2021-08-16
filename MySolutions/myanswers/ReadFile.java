package myanswers;

import java.io.*;
import java.util.*;

import static java.lang.System.exit;

import org.junit.Test;

/**
 * Created by smridul on 6/10/18.
 */
public class ReadFile {

    public static void main(String args[]) throws IOException {

        String inputFilePath = "/Users/smridul/temp/easy_all";
        String outputFilePath = "/Users/smridul/temp/easy_all_processed";

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


                String trimmed = sCurrentLine.trim();

                int trimmedInteger = 0;
                try {
                    trimmedInteger = Integer.parseInt(trimmed);

                } catch (Exception e){
                    continue;
                }


                if (map.get(trimmedInteger) == null) {
                    map.put(trimmedInteger, true);
                }

            }

            Object[] array = map.keySet().toArray();
            Arrays.sort(array);
            for (int s : map.keySet()) {
                bufferedWriter.write(Integer.toString(s));
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

    @Test
    public  void test() throws IOException {

        String inputFilePath = "/Users/smridul/temp/easy_all_processed";
        String inputFilePath2 = "/Users/smridul/temp/last6month/easy_processed";


        String outputFilePath = "/Users/smridul/temp/last6month/easy_processed_sorted";

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;


        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        FileReader fileReader2 = null;
        BufferedReader bufferedReader2 = null;

        try {
            fileWriter = new FileWriter(outputFilePath);
            bufferedWriter = new BufferedWriter(fileWriter);

            fileReader = new FileReader(inputFilePath);
            bufferedReader = new BufferedReader(fileReader);

            fileReader2 = new FileReader(inputFilePath2);
            bufferedReader2 = new BufferedReader(fileReader2);


        } catch (IOException e) {
            System.out.println("File not found");
            exit(0);
        }

        String sCurrentLine = null;
        LinkedHashMap<Integer, Boolean> map = new LinkedHashMap<>();
        while ((sCurrentLine = bufferedReader2.readLine()) != null) {
            String trimmed = sCurrentLine.trim();
            int trimmedInteger = 0;
            try {
                trimmedInteger = Integer.parseInt(trimmed);
            } catch (Exception e){
                continue;
            }
            if (map.get(trimmedInteger) == null) {
                map.put(trimmedInteger, true);
            }
        }



        LinkedHashMap<Integer, Boolean> map2 = new LinkedHashMap<>();
        try {
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                String trimmed = sCurrentLine.trim();
                int trimmedInteger = 0;
                try {
                    trimmedInteger = Integer.parseInt(trimmed);
                } catch (Exception e){
                    continue;
                }


                if (map.get(trimmedInteger) != null) {
                    map2.put(trimmedInteger, true);
                }
            }

            Object[] array = map2.keySet().toArray();
            Arrays.sort(array);
            for (int s : map2.keySet()) {
                bufferedWriter.write(Integer.toString(s));
                bufferedWriter.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
                bufferedReader2.close();
                fileReader2.close();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException ioException) {

            }
        }


        exit(0);
    }
}

