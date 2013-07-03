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
	out.println("Welcome to Omish Music Cataloger. Right now your Library is empty.");
		boolean run=true;
		while (run){
		out.println("Would You like to \"Add\" or \"Delete\""
				+ " a Song?\nAlso, you can choose to \"show\" your library and \"exit\" the program.");
		String choose = in.readLine().toUpperCase();
		if(choose.equalsIgnoreCase("add")){
			lib.addSong();
		}else if (choose.equalsIgnoreCase("edit")){
			//lib.editSong();
			run=false;
		}else if (choose.equalsIgnoreCase("delete")){
			lib.deleteSong();
		}else if (choose.equalsIgnoreCase("show")){
			Catalog.out.println(lib.songObj.toString());
		}else if (choose.equalsIgnoreCase("exit")){
			run=false;
		}
		}
		out.println("Goodbye!");
	}//Close main
	
}
