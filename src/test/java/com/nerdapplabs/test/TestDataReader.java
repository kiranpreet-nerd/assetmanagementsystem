package com.nerdapplabs.test;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import au.com.bytecode.opencsv.CSVReader;
public class TestDataReader {

// to read list of string arrays
public static List<String[]> readData(String fileName ) throws IOException{
CSVReader reader;
try {
	 reader = new CSVReader(new FileReader("datasource/" + fileName));
	 //Read all rows at once
	 List<String[]> allRows = reader.readAll();
	 //close the csvReader file
	 reader.close();
	 return  allRows;
    }
	  catch (IOException e) {
	   e.printStackTrace();
		}
         return null;
		}
	}

	
