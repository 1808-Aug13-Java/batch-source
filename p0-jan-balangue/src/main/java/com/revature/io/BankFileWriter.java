//package com.revature.io;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//
//public class BankFileWriter {
//	
//	private static Logger log = Logger.getRootLogger();
//
//	
// 	public BankFileWriter() {
//		super();
//	}
// 	public void writeFile(List<String> t, String path) {
//		File file = new File(path);
//		try {
//			if (file.createNewFile()) {
//				createWriter(file, t);
//			}
//		} catch (IOException e) {
//			log.error(e);
//		}
//	}
// 	public void createWriter(File file, List<String> t) {
// 		try (FileWriter fw = new FileWriter(file, false);
//				BufferedWriter bw = new BufferedWriter(fw)){		
//			for (String str: t) {
//				bw.write(str + "\n");
//			}
//		} catch (IOException e) {
//			log.error(e);
//		}
// 	}
//}
