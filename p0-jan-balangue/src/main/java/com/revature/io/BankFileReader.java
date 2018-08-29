//package com.revature.io;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//
//import org.apache.log4j.Logger;
// public class BankFileReader {
//	 
//	private static Logger log = Logger.getRootLogger();
//
//	 
// 	public BankFileReader() {
//		super();
//	}
//	
//	public String[] readLines(String path) {
//		String[] lines = new String[3];
//		File file = new File(path);
//		try (FileReader fr = new FileReader(file);
//				BufferedReader br = new BufferedReader(fr);){
//			
//			for (int i = 0; i < lines.length; i++) {
//				lines[i] = br.readLine();
//			}
//		} catch (Exception e) {
//			log.error(e);
//		}
//		return lines;
//	}
//}
