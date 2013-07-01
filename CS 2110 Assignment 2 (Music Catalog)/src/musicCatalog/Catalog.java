package musicCatalog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Catalog {
	
	//Create IO objects that can be used within other classes
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader in = new BufferedReader(isr);
	static PrintWriter out = new PrintWriter(System.out,true);
	
	public static void main(String[] args) {

		
		String test="Another test";
		String test2="Hello Ish";

		System.out.println(test());
	}
	
	public static String test(){
		HashMap<String, String> map1 = new  HashMap<String, String>();
		String album = "a: ";
		String song = "b: ";
		
		
		map1.put(a+"adele", "21");
		map1.put(b+"adele", "21");
		
		if (map1.containsKey(a+"adele"))	
		map1.put(a+"adele", album.add(song))
		
		return map1.get("adele");
		
		if(map1.containsKey(b+"adele"))
			return map1.get()
	}

}
