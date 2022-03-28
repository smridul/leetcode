package myanswers;

import java.io.*;
import java.util.*;

import static java.lang.System.exit;
import static java.lang.System.in;

import org.junit.Test;

/**
 * Created by smridul on 6/10/18.
 */
public class ReadFile {

    public static void main(String args[]) throws IOException {

        String inputFilePath = "/Users/smridul/temp/final_assessment/remaining";
        String outputFilePath = "/Users/smridul/temp/final_assessment/remaining_processed";

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
                int index = trimmed.indexOf('.');
                int index2 = trimmed.indexOf('%');

               /* if(index!=-1 && index2==-1){
                    trimmed = trimmed.substring(0, index);
                }*/

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

        String inputFilePath = "/Users/smridul/temp/last6month/targetCompanies/fb_google/fb_proc";
       String inputFilePath2 = "/Users/smridul/temp/last6month/targetCompanies/fb_google/google_proc";
        //String inputFilePath2 = "/Users/smridul/temp/last6month/targetCompanies/mediums/fb_dd_link_mediums_uniques";



        String outputFilePath = "/Users/smridul/temp/last6month/targetCompanies/fb_google/only_fb";

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


                if (map.get(trimmedInteger) == null) {
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






    @Test
    public void test22() {

        String inputFilePath = "/Users/smridul/temp/final_assessment/remaining";
        String outputFilePath = "/Users/smridul/temp/final_assessment/remaining_processed";

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
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

        String level="";
        int number=-1;
        try {
            while ((sCurrentLine = bufferedReader.readLine()) != null) {

                String trimmed = sCurrentLine.trim();
                int index = trimmed.indexOf('.');
                int index2 = trimmed.indexOf('%');

               /* if(index!=-1 && index2==-1){
                    trimmed = trimmed.substring(0, index);
                }*/

                int trimmedInteger = 0;
                try {
                    trimmedInteger = Integer.parseInt(trimmed);

                } catch (Exception e){
                    if(trimmed.contains("Hard")){
                        level = "hard";
                    }else if (trimmed.contains("Easy")){
                        level = "easy";
                    }else if(trimmed.contains("Medium")){
                        level = "medium";
                    }
                    if(!level.isEmpty()){
                        map.put(number, level);
                    }

                    level = "";
                    continue;
                }


                if (map.get(trimmedInteger) == null) {
                    map.put(trimmedInteger, "");
                    level="";
                    number = trimmedInteger;

                }

            }

            Object[] array = map.keySet().toArray();
            Arrays.sort(array);
            for (int s : map.keySet()) {
                bufferedWriter.write(Integer.toString(s));
                bufferedWriter.write(" ");
                bufferedWriter.write(map.get(s));
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

