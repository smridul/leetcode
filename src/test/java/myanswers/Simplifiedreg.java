package myanswers;

import static java.lang.System.exit;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;


public class Simplifiedreg {

    public static void main(String args[]) throws IOException {

        String visitfilepath = "/Users/smridul/code/temp/visitsetcontrol";
        String successpath = "/Users/smridul/code/temp/successsetcontrol";
        String filteredsuccesspath = "/Users/smridul/code/temp/successsetcontrol_filtered";

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;


        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        FileReader fileReader2 = null;
        BufferedReader bufferedReader2 = null;

        try {
            fileWriter = new FileWriter(filteredsuccesspath);
            bufferedWriter = new BufferedWriter(fileWriter);


            fileReader = new FileReader(visitfilepath);
            bufferedReader = new BufferedReader(fileReader);

            fileReader2 = new FileReader(successpath);
            bufferedReader2 = new BufferedReader(fileReader2);


        } catch (IOException e) {
            System.out.println("File not found");
            exit(0);
        }

        String sCurrentLine = null;
        LinkedHashMap<Integer, Boolean> map = new LinkedHashMap<>();

        Set<String> visitSet = new HashSet<>();

        Set<String> successSet = new HashSet<>();


        try {
            while ((sCurrentLine = bufferedReader.readLine()) != null) {


                String trimmed = sCurrentLine.trim();

                try {

                    visitSet.add(trimmed);
                } catch (Exception e){
                    continue;
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        String sCurrentLine2 = null;

        try {
            while ((sCurrentLine2 = bufferedReader2.readLine()) != null) {


                String trimmed = sCurrentLine2.trim();

                try {

                    successSet.add(trimmed);
                } catch (Exception e){
                    continue;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Set<String> successSetFiltered = new HashSet<>(successSet);
        successSetFiltered.retainAll(visitSet);


        try {

            Iterator iterator = successSetFiltered.stream().iterator();
            while (iterator.hasNext()) {
                bufferedWriter.write(iterator.next().toString());
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
