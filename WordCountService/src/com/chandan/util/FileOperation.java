package com.chandan.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileOperation {


	public static File[] listFiles(String corpusPath) throws Exception{
		File corpusFolder = new File(corpusPath);
		if(corpusFolder.exists() && corpusFolder.isDirectory()){
			return corpusFolder.listFiles();
		}
		else {
			throw new Exception("Corpus Path is invalid");
		}
		
	}
	public static String[] readFile(File file){
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			StringBuffer sb = new StringBuffer();
			String line;
			while((line = bufferedReader.readLine()) != null){
				sb.append(line+" ");
			}
			return sb.toString().split("\\s+");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			try {
				if(bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
