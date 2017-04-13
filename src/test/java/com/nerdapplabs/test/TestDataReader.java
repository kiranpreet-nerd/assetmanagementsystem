package com.nerdapplabs.test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
	public class TestDataReader {
				public static List<String[]> readData(String fileName ) throws IOException{
					CSVReader reader;
					try {
						reader = new CSVReader(new FileReader("datasource/" + fileName));
						
						//Read all rows at once
					    List<String[]> allRows = reader.readAll();
						reader.close();
					    return  allRows;
//						for(String[] row : allRows){
//					        System.out.println(Arrays.toString(row));
//					        for (int i = 0; i < row.length; i++) {
//					        	 System.out.println("value: " + row[i]);
//							}
//					     }
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
	}

	
