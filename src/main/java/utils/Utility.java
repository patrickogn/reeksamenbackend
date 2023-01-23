/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import java.util.Set;
import com.google.gson.*;
import dtos.RenameMeDTO;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author tha
 */
public class Utility {
    private static Gson gson = new GsonBuilder().create();
    
    public static void printAllProperties() {
            Properties prop = System.getProperties();
            Set<Object> keySet = prop.keySet();
            for (Object obj : keySet) {
                    System.out.println("System Property: {" 
                                    + obj.toString() + "," 
                                    + System.getProperty(obj.toString()) + "}");
            }
    }
    
    public static RenameMeDTO json2DTO(String json) throws UnsupportedEncodingException{
            return gson.fromJson(new String(json.getBytes("UTF8")), RenameMeDTO.class);
    }
    
    public static String DTO2json(RenameMeDTO rmDTO){
        return gson.toJson(rmDTO, RenameMeDTO.class);
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
//        printAllProperties();
        
        //Test json2DTO and back again
        String str2 = "{'id':1, 'str1':'Dette er den første tekst', 'str2':'Her er den ANDEN'}";
        RenameMeDTO rmDTO = json2DTO(str2);
        System.out.println(rmDTO);
        
        String backAgain = DTO2json(rmDTO);
        System.out.println(backAgain);
    }

    public static String fixDiets(String badJSON)
    {
        int indexOfStartBracket = badJSON.indexOf("diets\":[")+7;
        char start = badJSON.charAt(indexOfStartBracket);
        int indexOfEndBracket = badJSON.indexOf("], \"analyzedInstructions");
        char end = badJSON.charAt(indexOfEndBracket);
        if (indexOfStartBracket+1 != indexOfEndBracket)
        {
            String diets = badJSON.substring(indexOfStartBracket+1, indexOfEndBracket);
            String arrayOfDiets[] = diets.split(", ");
            String workingDiets = "";
            for (int i = 0; i < arrayOfDiets.length; i++) {
                if (i == arrayOfDiets.length-1)
                    workingDiets += "\""+arrayOfDiets[i]+"\"";
                else
                    workingDiets += "\""+arrayOfDiets[i]+"\", ";
            }
            return badJSON.replace(diets, workingDiets);
        }
        else
            return badJSON;
    }

}
