package com.chandan.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class Result {

	private int count;
	public Result() {

	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
