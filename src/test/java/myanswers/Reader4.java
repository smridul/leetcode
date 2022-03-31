package myanswers;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by smridul on 3/3/19.
 */
public class Reader4 {


    String file = new String("YEiOwYgGGpazYrplFQwkDXCUXHNjTziyCfytblUsRTKuElgBbHbetJZkDAJqQbRHOxTTudiNtBjsyZAmxFkeFBWbFUxsHOcYMtGGIZzwfyeBMvvtAgluXAveQFwHbBzfHjcSaysZgepMqOIuInlKzKMBrWVzYfkZheSFCNakYaQgsWpWkptxLlDFFYjmWFIJgWXxKyISNlbaLKJOWqkHAKnVxAjviPBIStqxkaPZeLAjqBGQldOrmXPoYLjkXSwEZLKoyYIkpGwaDzHknWAwvnKLZZLSwrAmXpUZJvOuAMVcYYxoWWsiKWlpLBiByhqTCJpZwCpvxlBmqYSAiQdCMhABbdBFMEwaqeFsNuHnmNMlXHWyajnBfruZyyhLaYdmELZ");
    int index = 0;


    char[] readBuf = new char[4];
    int extraCharacters = 0;
    int readIndex=0;

    private int buffPtr = 4;
    private int buffCnt = 4;

    @Test
    public void test() {


       // file="abc";
        char[] buf = new char[500];
        int n = read(buf, 374);
        System.out.println(n);
        print(buf, n);

        buf = new char[100];
        n = read(buf, 12);
        System.out.println(n);
        print(buf, n);

        buf = new char[100];
        n = read(buf, 1);
        System.out.println(n);
        print(buf, n);
    }


    public void print(char[] buf, int n){
        System.out.print("array : ");

        for (int i=0; i<n; i++){
            System.out.print(buf[i] + " ");
        }
        System.out.println();
    }



    // wrong. ate away all my time
    public int read1(char[] buf, int n) {

        int readAll = 0;
        int bufferPointer = 0;
        while (n > 0) {
            int expectedReadInThisIteration = Math.min(4, n);

            // read first always from tempbuffer
            if (extraCharacters > 0) {
                // we read from tempbuffer the extra characters
                // so now expected read is less
                // how many characters we can read
                int locallyAvailable = Math.min(expectedReadInThisIteration, extraCharacters);
                //expectedReadInThisIteration = expectedReadInThisIteration - locallyAvailable;

                // fulfilled from here itself
                System.arraycopy(readBuf, readIndex, buf, 0, locallyAvailable);
                readIndex = readIndex + locallyAvailable;
                bufferPointer+=locallyAvailable;
                extraCharacters = extraCharacters - locallyAvailable;
                readAll+=locallyAvailable;
                if(expectedReadInThisIteration == locallyAvailable){
                    return locallyAvailable;
                }
            }

            int read = read4(readBuf);
            if (read < expectedReadInThisIteration) {
                // finish it now
                System.arraycopy(readBuf, 0, buf, bufferPointer, read);
                readAll += read;
                return readAll;
            } else if (read > expectedReadInThisIteration) {

                // we have read some extra characters so we have to store some

                readAll += expectedReadInThisIteration;
                //index = index - (read - expectedRead);
                //pointer = file.charAt(index);

                // we will store all read chars in tempBuff;
                readIndex = 0;
                System.arraycopy(readBuf, 0, buf, bufferPointer, expectedReadInThisIteration);
                readIndex = readIndex + expectedReadInThisIteration;
                extraCharacters = read - expectedReadInThisIteration;
                return readAll;
            }

            System.arraycopy(readBuf, 0, buf, bufferPointer, read);
            bufferPointer +=read;
            readAll += read;
            n = n - read;
        }
        return readAll;
    }

    private int read4(char[] buf) {
        int nextindex = index + 4;
        if (nextindex <= file.length() - 1) {
            System.arraycopy(file.toCharArray(), index, buf, 0, 4);
            index = nextindex;
            return 4;
        } else {
            int read = file.length() - index;
            System.arraycopy(file.toCharArray(), index, buf, 0, read);
            index = file.length();
            return read;
        }
    }

    public int read(char[] buf, int n) {
        int ptr=0;
        while (ptr < n ) {
            //try reading from buffer
            while (buffPtr < buffCnt) {

                buf[ptr++] = readBuf[buffPtr++];

                if (ptr >= n) {
                    return ptr;
                   // break;
                }
            }
            if (buffPtr == 4 || buffPtr>= buffCnt) {// second condition only to terminate the loop
                buffCnt = read4(readBuf);
                buffPtr = 0;
            }

            // no element left in file to be read
            if (buffCnt == 0) {
                return ptr;
               // break;
            }
        }
        return ptr;
    }
}
