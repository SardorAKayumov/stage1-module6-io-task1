package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class FileReader {

    public Profile getDataFromFile(File file) {
        try (FileInputStream in = new FileInputStream(file.getPath())) {
            //Read data from the file
            int ch;
            StringBuilder text = new StringBuilder();
            while ((ch = in.read()) != -1) {
                text.append((char) ch);
            }

            //Format data into an array of strings
            String[] newLineSplitArray = text.toString().split("\\r?\\n");
            List<String> finalArray = new ArrayList<>();
            for (String tempStr : newLineSplitArray) {
                String[] tempArr = tempStr.split(": ");
                finalArray.add(tempArr[0]);
                finalArray.add(tempArr[1]);
            }

            //Format array into a map
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < finalArray.size(); i += 2) {
                map.put(finalArray.get(i), finalArray.get(i+1));
            }

            //Get values for profile fields
            String name = map.get("Name");
            Integer age = Integer.parseInt(map.get("Age"));
            String email = map.get("Email");
            Long phone = Long.parseLong(map.get("Phone"));

            return new Profile(name, age, email, phone);

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return new Profile();
    }
}
