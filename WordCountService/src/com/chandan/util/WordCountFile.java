package com.chandan.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordCountFile {

	//Map which stores object for each file
	private static Map<File, WordCountFile> wordCountFileMap = new HashMap<File, WordCountFile>();

	private File filename;
	private long lastModifiedTime;
	//Used for caching the word count
	private Map<String, Integer> countmap;

	private WordCountFile(File filename) {
		super();
		this.filename = filename;
		lastModifiedTime = filename.lastModified();
		countmap = new HashMap<String, Integer>();
	}

	//Static method which returns an object for a file 
	public static WordCountFile getWordCountFileObj(File filename) {
		if(wordCountFileMap.containsKey(filename)) {
			return wordCountFileMap.get(filename);
		}
		else {
			WordCountFile wordCountFile = new WordCountFile(filename);
			wordCountFileMap.put(filename, wordCountFile);
			return wordCountFile;
		}
	}

	public int getWordCount(String word) {
		if(lastModifiedTime == filename.lastModified()) {
			if(countmap.containsKey(word)) {
				return countmap.get(word);
			}
			else {
				int count = countWords(word);
				
				countmap.put(word, count);
				return countmap.get(word);
			}
		}
		else{
			lastModifiedTime = filename.lastModified();
			int count = countWords(word);

			countmap.put(word, count);
			return countmap.get(word);
		}
	}


	private int countWords(String word){
		int count = 0;

		String []words = FileOperation.readFile(filename);
		if(words != null) {
			for(String w:words){
				if(w.equalsIgnoreCase(word))
					count++;
			}
		}
		return count;
	}

}
