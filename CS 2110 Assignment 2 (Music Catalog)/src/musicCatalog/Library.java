package musicCatalog;

import java.io.IOException;

public class Library {
	
	//Declare Variables To Hold Information About the Library 
	private static int numberOfSongs = 0;
	private static int numberOfGenres = 0;
	private String libraryName;
	
	//Accessory Methods
	static int getNumberOfSongs() { //For numberOfSongs
		return numberOfSongs;
	}
	public static int getNumberOfGenres() {
		return numberOfGenres;
	}
	public String getLibraryNames() {
		return libraryName;
	}
	
	//Mutator Methods
	static void setNumberOfSongs(int number) {
		numberOfSongs = number;
	}
	public static void setNumberOfGenres(int number) {
		numberOfGenres = number;
	}
	public void setLibraryName(String name) {
		libraryName = name;
	}
	
	//For working on existing catalog
	public void importLibrary(String filepath) {
		
	}
	
	//For saving out catalog
	public void exportLibrary() {
		
	}
	
	//To add a song to the catalog
	public void addSongs() {
		Catalog.out.println("What is the name of the Artist or Composer?");
		try {
			Catalog.in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
	
	//To remove a song from the catalog
	public void deleteSongs() {
		
	}
	
	//To check the information entered by the user
	public void spellChecker() {
		
	}
	
	//Constructor Method
	public Library() {
		
	}
	
	
	
}//Close Library Class
