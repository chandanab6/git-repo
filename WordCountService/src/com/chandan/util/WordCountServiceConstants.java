package com.chandan.util;

public class WordCountServiceConstants {
	
	public static final String EMPTY_STRING = "";
	public static final String TAB = "\t";
	
	public static final int MAX_CORE_THREADS = 50;
	public static final int MAX_THREADS = 100;
	public static final int DEFAULT_PORT = 8282;
	
	public enum EnumTrueFalse
	{
		TRUE("true"),
		FALSE("false"),
		INVALID("invalid");

		private String value;

		private EnumTrueFalse(String value)
		{
			this.value = value;
		}

		public static EnumTrueFalse fromString(String str)
		{
			if (str != null) 
			{
				for (EnumTrueFalse b : EnumTrueFalse.values()) 
				{
					if (b.value.equalsIgnoreCase(str)) 
					{
						return b;
					}
				}
			}
			return INVALID;
		}

		@Override
		public String toString()
		{
			return value;
		}
	}
}
