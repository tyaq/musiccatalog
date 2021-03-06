/**
 * Class with all the methods used to mutate user data and store it
 */

package musicCatalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.*;


public class Library implements Serializable{
	
	/**
	 * 
	 */
	//Declare Variables To Hold Information About the Library
	private static final long serialVersionUID = -5504868398542305775L;
	private static int numberOfSongs = 0;
	private static int numberOfGenres = 0;
	private String libraryName;
	HashMap<String, String> songs;
	ArrayList<String> spellDict;
	HashMap<Integer, Object> songObj;
	
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
	/**
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void importLibrary() throws IOException, ClassNotFoundException {
		//Ask user for either a file path of existing library
					Catalog.out.println("Give the path of a file to import your library or leave it empty.");
					String inputFilePath=Catalog.in.readLine();
					
				//Checks if file exists	
					File file = new File(inputFilePath);
					if (file.exists() && file.canRead()) {
							FileInputStream fis = new FileInputStream(inputFilePath);
					        ObjectInputStream ois = new ObjectInputStream(fis);
					        
					        songs = (HashMap<String, String>) (ois.readObject());
					        spellDict=(ArrayList<String>) (ois.readObject());
					        songObj =(HashMap<Integer, Object>) ois.readObject();
					        fis.close();
					        Catalog.out.println("Your library has been loaded.\n");
						}
		
	}
	
	//For saving out catalog
	/**
	 * 
	 * @throws IOException
	 */
	public void exportLibrary() throws IOException {
		Catalog.out.println("Do you want to save this library? (\"yes\" or \"no\")");
		String response=Catalog.in.readLine();
		if(response.equals("no")) {
			System.out.println("OK. Thank you for using Omish Music Cataloger.");
		
		} else if (response.equalsIgnoreCase("yes")){
			Catalog.out.println("Write File path to save your library.");
			String outputFileName=Catalog.in.readLine();
			File file = new File(outputFileName);
			file.createNewFile();
			FileOutputStream fos = null;
		    ObjectOutputStream oos = null;
				
				try {
			        fos = new FileOutputStream(outputFileName,false);
			        oos = new ObjectOutputStream(fos);
			        oos.writeObject(songs);
			        oos.writeObject(spellDict);
			        oos.writeObject(songObj);
			        oos.close();
			        System.out.println("Library saved. Thank you for using Omish Music Cataloger");
			    } catch (IOException ex) {
			        ex.printStackTrace();
			    }
				System.out.println("You can find it encryped at " + outputFileName + ". You will need it to import your library later.");
		} else {
			Catalog.out.println("Sorry i didn't understand you, but thank you" +
					" for using Omish Music Cataloger.");
		}
	}
	
	//To add a song to the catalog
	/**
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void addSong() throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		System.out.println("<--addSong()-->");
		
		//Ask for and Artist as a reference and the Name of the song
		Catalog.out.println("What is the name of the Song?");
		String songName = cap(Catalog.in.readLine());
		songName=spellChecker(songName,spellDict);
		
		//Check to see if a song by that name already exists and if it does ask to edit, and if not create new object
		checkIfContained(songName);
		
		Catalog.out.println("What is the name of the Artist or Composer?");
		String songArtist = cap(Catalog.in.readLine());
		songArtist=spellChecker(songArtist,spellDict);
		
		//Try to pull up information on what type of genre they may be
		tryHarvest(songArtist,songName);
		
		//Both checks flow to newSong() to make a new object.
	}//Close addSong method
	
	//Make new song object with title, artist and genre telling what class to use
	/**
	 * 
	 * @param songClass
	 * @param title
	 * @param artist
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	public void newSong(String songClass,String title, String artist) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
//		System.out.println("<--Library.newSong(2args+genre)-->");
		Class cl = Class.forName("musicCatalog."+songClass);
//		System.out.println(cl);//For Debugging
		Constructor con = cl.getConstructor(String.class,String.class);
		Object newSong = con.newInstance(title, artist);
		
		//Fill in data for new song
		askInfo(songClass,newSong);
	}//Close newSong Method
	
	//Make new song object with title, artist
	/**
	 * 
	 * @param title
	 * @param artist
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	public void newSong(String title, String artist) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
//		System.out.println("<--Library.newSong(2args)-->");
		Catalog.out.println("What is the genre? Choose Orchestral.");
		String songClass=cap(Catalog.in.readLine());
		Class cl = Class.forName("musicCatalog."+songClass);
//		System.out.println(cl);//For Debugging
		Constructor con = cl.getConstructor(String.class,String.class);
		Object newSong = con.newInstance(title, artist);
		
		//Fill in data for new song
		askInfo(songClass,newSong);
	}//Close newSong Method
	
	//Make new song object with only title
	/**
	 * 
	 * @param title
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
		public void newSong(String title) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
//			System.out.println("<--Library.newSong(1args)-->");
			Catalog.out.println("What is the genre? Choose Orchestral.");
			String songClass=cap(Catalog.in.readLine());
			Class cl = Class.forName("musicCatalog."+songClass);
			Constructor con = cl.getConstructor(String.class,String.class);
			Object newSong = con.newInstance(title);
			
			//Fill in data for new song
			askInfo(songClass,newSong);
		}//Close newSong Method
	
	//To remove a song/songs or really any genre or artist from the catalog
		/**
		 * 
		 * @throws IOException
		 */
	public void deleteSong() throws IOException {
//		System.out.println("<--deleteSong()-->");
		
		//Ask for keyword to use to delete songs
		Catalog.out.println("What keyword do you want to use to delete data?"
				+ "\nNote Keywords must be exact to work. For example if you"
				+ " wanted to remove all songs of the genre orchestral you must"
				+ " enter \"Orchestral\".");
		String search = cap(Catalog.in.readLine());
		search =spellChecker(search,spellDict);
		if (songs.containsKey(search)){
			for(int i=0; i<(parseSongIds(songs.get(search))).length;i++) {
			songObj.remove((parseSongIds(songs.get(search)))[i]);
			}//close for
			songs.remove(search);
		}//close if
		else {
			Catalog.out.println("Nothing resembling "+search+" was found in your library.");
		}
	}
	
	//For future
	/**
	 * 
	 * @param song
	 */
	public void editSong(Object song) {
		
	}
	
	/**
     * Returns the number of letters of the user's typed string that are common
     * to a string already in spelldict
     * @param String a
     * @param String b
     * @return integer value
     */
	public static int similarLetters(String a, String b){
		String c=a.toUpperCase();
		String d=b.toUpperCase();
		int temp=0;
		int i=0;
		while (i<d.length()){
			if (c.indexOf(d.charAt(i))!=-1){
				temp++;}
			i++;}
		return temp;			
	}
	/**Basically a spell-checker based on the differences in lengths between 
	 * the user's typed string and a string already in spelldict, and differences
	 * in letters b/w them
	 * 
	 * @param a
	 * @param spelldict
	 * @throws IOException 
	 */
	public String spellChecker(String a, ArrayList<String> spelldict) {
		for(int i=0; i<spelldict.size();i++){
			if (
					(Math.abs(spelldict.get(i).length()-a.length())<4)&&
					(similarLetters(a,spelldict.get(i))>=spelldict.get(i).length()-2)){
				Catalog.out.println("Did you mean to type "+spelldict.get(i)+"\nIf so, type \"yes\"");
				String input;
				try {
					input = Catalog.in.readLine();
				} catch (IOException e) {
					input="no";
				}
				if (input.equalsIgnoreCase("yes")) {
					a=spelldict.get(i);
				}//Close if
			}//Close if
		}//close if
		return a;
	}//Close method
		
	
	
	
	//To check if song title is in database
	/**
	 * 
	 * @param songName
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void checkIfContained(String songName) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		System.out.println("<--checkIfContained-->");
		if (songs.containsKey(songName)){
			Catalog.out.println("Hmm... I think you already added this song. Is this it?");
			Object songObject = songObj.get(parseFirstNumber(songs.get(songName)));
			Catalog.out.println(songObject.toString());
			String response = cap(Catalog.in.readLine());
			checkIfSameSong(response,songObject,songName);
		}//Close check is already added if
	}
	
	//To check if song is already in database
	/**
	 * 
	 * @param response
	 * @param song
	 * @param songTitle
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void checkIfSameSong(String response,Object song, String songTitle) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		System.out.println("<--checkIfSameSong-->");//For Debugging
		if (response.equalsIgnoreCase("yes")){
			checkIfEdit(song);
		} else if (response.equalsIgnoreCase("no")) {
			Catalog.out.println("So, this is a new song.");
			newSong(songTitle);//Call method that asks user for input of song info not knowing genre
		} else {
			notUnderstandable(response);
		}//Close check if same song
	}//Close checkIfSameSong method
	
	/**
	 * 
	 * @param song
	 * @throws IOException
	 */
	//To Check if user wants to edit a song in database
	public void checkIfEdit(Object song) throws IOException{
//		System.out.println("<--Library.checkIfEdit()-->");//For Debugging
		Catalog.out.println("Would you like to edit this?");
		String response;
			response = Catalog.in.readLine();
		if (response.equalsIgnoreCase("yes")){//Yes? then go to edit method
			editSong(song);
		} else if (response.equalsIgnoreCase("no")){//No? then go main menu
			
		} else {//If neither then take them back to main menu
			notUnderstandable(response);
		}//Close check if want to edit
	}//Close checkIfEdit Method
	
	/**
	 * 
	 * @param songArtist
	 * @param songTitle
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	//To try and harvest genre an artist makes
	public void tryHarvest(String songArtist,String songTitle) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
//		System.out.println("<--tryHarvest()-->");
		if (songs.containsKey(songArtist)&&songs.get(songArtist)!=null) {
			//Get a song of theirs and check the genre
			Object sObject=songObj.get(parseFirstNumber(songs.get(songArtist)));
			Music songObject = (Music) (sObject);
			String genre=cap(songObject.getGenre());
			newSong(genre,songTitle, songArtist);//Make new song object with title, artist and genre telling what class to use
		}else{
			newSong(songTitle,songArtist);//Make new song with title, artist not knowing genre
		}//close if to harvest genre
	}//Close tryHarvest
	
	/**
	 * 
	 * @param songClass
	 * @param newSong
	 * @throws IOException
	 */
	//Ask info to fill out song info
	public void askInfo(String songClass,Object newSong) throws IOException{
		//Check if object is an orchestra object
		if (songClass.equals("Orchestral")) {
			askInfoIfOrchestral(newSong);
		}//end if AskInfo Orchestra
	}//Close method
	
	/**
	 * 
	 * @param newSong
	 * @throws IOException
	 */
	public void askInfoIfOrchestral(Object newSong) throws IOException{
//		System.out.println("<--askInfoIfOrchestral-->");//For Debugging
		Orchestral orch = (Orchestral) newSong;
		//Add Known Info to song
		songs.put(orch.getTitle(),songs.get(orch.getTitle())+ " " + Orchestral.getSongId());//Take current value and add this objects SongId value
		songs.put(orch.getComposer(),songs.get(orch.getComposer())+ " " + Orchestral.getSongId());
		songs.put(orch.getGenre(),songs.get(orch.getGenre())+ " " + Orchestral.getSongId());
		//Add title and composer to spellcheck
		spellDict.add(orch.getTitle());
		spellDict.add(orch.getComposer());
		//Ask and add unknown info for this object
		Catalog.out.println("What year was "+orch.getTitle() + " written?");
		String response = Catalog.in.readLine();
		if (!(response.equalsIgnoreCase("") || response.equalsIgnoreCase("\\s+"))) {
			orch.setYear(Integer.parseInt(response));
			songs.put(response,songs.get(orch.getYear())+ " " + Orchestral.getSongId());
		}//Close if
		Catalog.out.println("What is the duration of the song in seconds?");
		response = Catalog.in.readLine();
		if (!(response.equalsIgnoreCase("") || response.equalsIgnoreCase("\\s+"))) {
			orch.setDuration(Double.parseDouble(response));
			songs.put(response,songs.get(orch.getDuration())+ " " + Orchestral.getSongId());
		}
		Catalog.out.println("What are the instruments used?"
				+ " Enter them on seperate lines and when your done enter an empty line.");
		boolean enteringData=true;
		ArrayList<String> temp= new ArrayList<String>(); 
		while (enteringData) {
			response = Catalog.in.readLine().toUpperCase();
			enteringData = !(response.equalsIgnoreCase("") || response.equalsIgnoreCase("\\s+"));
			if (enteringData) {
				temp.add(response);
			}//Close if
		}//Close for
		if (!(temp.isEmpty())) { //Only parse if necessary
		String[] ttemp = temp.toArray(new String[temp.size()]);
		orch.setInstruments(ttemp);
		}
		Catalog.out.println("Enter any notes you would like to attach to this song, or leave it blank.");
		response = Catalog.in.readLine();
		if (!(response.equalsIgnoreCase("") || response.equalsIgnoreCase("\\s+"))) {
			orch.setComment(response);
		}//Close if
		//Add id to song reference
		songObj.put(Orchestral.getSongId(), orch);
	}//Close method askInfoIfOrchestra
	
	/**
	 * 
	 * @param numbers
	 * @return
	 */
	//for getting a song id
	public int parseFirstNumber(String numbers) {
		numbers.trim();
		String[] songIdString = numbers.split("\\s+");
		int songIdGetter=Integer.parseInt(songIdString[1]);
//		System.out.println(songIdGetter);
		return songIdGetter;
	}
	
	/**
	 * 
	 * @param numbers
	 * @return
	 */
	//For getting song ids
	public int[] parseSongIds(String numbers) {
		numbers.trim();
		String[] songIdString = numbers.split("\\s+");
		int[] songIds=new int[songIdString.length];
		for(int i=1;i<songIdString.length;i++){	
		int temp=Integer.parseInt(songIdString[i]);
		songIds[i]=temp;
		}
		return songIds;
	}
	
	/**
	 * 
	 * @param response
	 */
	//For returning an exception back to main menu
	public void notUnderstandable(String response) {
//		System.out.println("<--notUnderstandable-->");//For Debugging
		Catalog.out.println("I'm sorry I didn't understand: " + response + 
				"\nI was looking for \"Yes\" or \"No\".");
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	//Capitalizes a String
	public String cap (String s){
		if (s.length() == 0) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
	
	//Constructor Method
	public Library() {
		songs = new  HashMap<String, String>();
		spellDict = new ArrayList<String>();
		songObj = new HashMap<Integer, Object>();
	}
	
	
	
}//Close Library Class
