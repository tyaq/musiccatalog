package musicCatalog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class Catalog {
	
	//Create IO objects that can be used within other classes
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader in = new BufferedReader(isr);
	static PrintWriter out = new PrintWriter(System.out,true);
	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {

	Library lib = new Library();
		boolean run=true;
		while (run){
		out.println("Would You like to Add, Edit or Delete a Song?");
		String choose = in.readLine().toUpperCase();
		if(choose.equalsIgnoreCase("add")){
			lib.addSong();
		}else if (choose.equalsIgnoreCase("edit")){
			//lib.editSong();
		}else if (choose.equalsIgnoreCase("delete")){
			lib.deleteSong();
		}else if (choose.equalsIgnoreCase("show")){
			Catalog.out.println(lib.songObj.toString());
		}else if (choose.equalsIgnoreCase("exit")){
			run=false;
		}
		}
	}//Close main
	/*public static String test(){
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
	}*/

}
