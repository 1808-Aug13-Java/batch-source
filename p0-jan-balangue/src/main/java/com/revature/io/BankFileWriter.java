package com.revature.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class BankFileWriter {

	private static Logger log = Logger.getRootLogger();
	
	public BankFileWriter() {
		super();
	}

	public void writeFile(ArrayList<String> t, String path) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			File file = new File(path);	
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file, false);
			bw = new BufferedWriter(fw);
			for (String str: t) {
				bw.write(str + "\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
					fw.close();
				} catch (IOException e) {
					log.error(e);
				}
				
			}
		}
	}
}
