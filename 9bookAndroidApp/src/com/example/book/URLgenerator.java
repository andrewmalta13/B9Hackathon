package com.example.book;

public class URLgenerator {

	public static String generateRecUrl(int ociNum, int dateNum)
	{
		return "https://faculty.yale.edu/viewevals/Search/Summary?crn="+ociNum+"&tC="+dateNum;
	}
	
	public static String generateEvalUrl1(int ociNum, int dateNum)
	{
		return "https://faculty.yale.edu/viewevals/Search/BarChart?qid=YC005&crn="+ociNum+"&tC="+dateNum;
	}
	
	public static String generateEvalUrl2(int ociNum, int dateNum)
	{
		return "https://faculty.yale.edu/viewevals/Search/BarChart?qid=YC006&crn="+ociNum+"&tC="+dateNum;
	}
}
