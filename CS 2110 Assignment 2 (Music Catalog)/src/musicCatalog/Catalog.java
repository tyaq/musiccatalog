/**
 * Ishtyaq Habib & Omer Syed
 * CS 2110, Summer 2013
 * Assignment 2, July 2, 2013
 * 
 * This program allows a user to create a Library of music and its respective information.
 * 
 * Highlight Features: Easily can create new Genre type, however due to inexperience in java
 * we could not skirt around having to create a custom method for each class to prompt user
 * to insert song info.
 * & spellChecker, Import and Export capabilities.
 * 
 * Readme: Unfortunately we did not have time to create more genre types, we were also
 *  trying to make the programming of new genre types completely autonomous but gave
 *  up mid way resulting in the complicated parsing and casting in methods then 
 *  Eventually a long method to prompt user to enter undeclared variables.
 *  We were able to prompt user to enter info for songs dynamically for undeclared variables
 *  but we could not set the info into the private variables.
 *  
 *  In future clean up user interface.
 * 
 * 
 */
package musicCatalog;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
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
		lib.importLibrary();
		//Continually ask user what to do.
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
		lib.exportLibrary();
		out.println("Goodbye!");
	}//Close main
	
}
