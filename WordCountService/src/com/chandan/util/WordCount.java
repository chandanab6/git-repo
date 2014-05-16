package com.chandan.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chandan.configuration.WordCountConfiguration;

public class WordCount {

	private static final Logger logger = LoggerFactory.getLogger(WordCount.class);

	public static int getWordCount(String word) throws Exception{
		long startTime = System.currentTimeMillis();


		int count = 0;
		File files[];
		try {
			files = FileOperation.listFiles(WordCountConfiguration.INSTANCE.getCorpusFilePath());

			/*for(File file:files){
			//System.out.println(file.getName());
			String []words = FileOperation.readFile(file);
			for(String w:words){
				if(w.equalsIgnoreCase(word))
					count++;
			}
		}*/
			for(File file:files){
				if(file.isFile()) {
					WordCountFile wordCountFileObj = WordCountFile.getWordCountFileObj(file);
					count +=wordCountFileObj.getWordCount(word);
				}
			}

			long endTime = System.currentTimeMillis();
			long time = endTime - startTime;
			logger.debug("Total time taken for word count : " + time + " msec");
			return count;
		} catch (Exception e) {
			throw e;
		}
	}
}
