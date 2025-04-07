package myanswers;


import org.junit.Test;

import java.util.*;

public class FileStructure {
    @Test
    public void test1() {
        FileStructure1 fs = new FileStructure1();
        fs.mkdir("/a");
        fs.mkdir("/a/b");
        fs.mkdir("/a/b/c");
        fs.mkdir("/a/d");
        fs.createFile("/a/file", "cc");


        System.out.println(fs.list("/a"));
        System.out.println(fs.list("/a/b"));

    }
}

class FileStructure1 {

    Map<String, List<String>> map = new HashMap<>();

    Map<String, String> files = new HashMap<>();

    public void mkdir(String path) {


        int index = path.lastIndexOf('/');

        String queryPath = "";
        if (index != -1) {
            queryPath = path.substring(0, index);

            if (map.containsKey(queryPath)) {

                // a/b/c
                //a/b exists
                String directory = path.substring(index + 1, path.length());

                List<String> existing = map.get(queryPath);
                existing.add(directory);
            } else {

                //first creation of directory in this path
                // a/b/c
                // and a/b does not exist

                String directory = path.substring(index + 1, path.length());
                map.put(queryPath, new ArrayList<>());

                map.get(queryPath).add(directory);

            }
        }

    }

    // a/b/c
    public void createFile(String path, String content) {


        int index = path.lastIndexOf('/');

        String queryPath = "";

        if (index != -1) {
            queryPath = path.substring(0, index);
            String fileName = path.substring(index+1, path.length());

            mkdir(queryPath);

            files.put(path, content);

            map.get(queryPath).add(fileName);
        }
    }

    List<String> list(String path) {


        if (files.containsKey(path)) {
            return Arrays.asList(path);
        }


        if (map.containsKey(path)) {
            return map.get(path);
        }


        return new ArrayList<>();

    }

}