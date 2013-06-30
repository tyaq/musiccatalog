package musicCatalog;

public class Library {
	
	//Declare Variables To Hold Information About the Library 
	private int numberOfSongs;
	private int numberOfGenres;
	private String libraryName;
	
	//Accessory Methods
	public int getNumberOfSongs() { //For numberOfSongs
		return numberOfSongs;
	}
	public int getNumberOfGenres() {
		return numberOfGenres;
	}
	public String getLibraryNames() {
		return libraryName;
	}
	
	//Mutator Methods
	public void setNumberOfSongs(int number) {
		numberOfSongs = number;
	}
	public void setNumberOfGenres(int number) {
		numberOfGenres = number;
	}
	public void setLibraryName(String name) {
		libraryName = name;
	}
	
	
	
}//Close Library Class
