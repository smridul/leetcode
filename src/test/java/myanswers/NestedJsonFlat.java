package myanswers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.*;

public class NestedJsonFlat {

    ObjectMapper mapper = new ObjectMapper();
    @Test
    public void testIt() throws  Exception{

        String s = "{\n" +
                "     \"a\" : \"av\",\n" +
                "     \"b\" : {\n" +
                "             \"b1\" : \"b1v\",\n" +
                "             \"b2\" : {\n" +
                "                      \"b21\": \"b21v\"\n" +
                "                    }\n" +
                "           },\n" +
                "     \"c\" : \"cv\"\n" +
                "   }";


        Map<String, Object> input = mapper.readValue(s, Map.class);

        Map<String, String> output  = flatten(input);

        for(String k : output.keySet()){
            System.out.println(k +  " " + output.get(k));
        }
    }



    Map<String, String> flatten(Map<String, Object> input){

        Map<String, String> output = new HashMap<>();
        for(String key : input.keySet()){

            Object val = input.get(key);

            if(val instanceof String){
                output.put(key, (String)val);
            }else{
                //this is nested json

                Map<String, String> flattened = flatten((Map<String, Object>) val);

                for(String key1: flattened.keySet()) {
                    output.put(key + "."+key1, flattened.get(key1));
                }

            }
        }

        return output;
    }

}

