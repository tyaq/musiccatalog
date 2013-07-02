package musicCatalog;

import java.lang.reflect.Field;

public class Music {
	
	private static int songId=1;
	private static String genre;
	private String title;
	private String comment;
	private double duration;
	private int year;
	
	//Accessory Methods
	public static int getSongId(){
		return songId;
	}
	public String getTitle(){
		return title;
	}
	public String getGenre(){
		return genre;
	}
	public String getComment(){
		return comment;
	}
	public double getDuration(){
		return duration;
	}
	public int getYear(){
		return year;
	}
	
	//Mutator Methods
	public void setSongId(int number){
		songId= number;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setGenre(String type){
		genre = type;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}
	
	public void setDuration(double time){
		duration=time;
	}
	
	public void setYear(int year){
		this.year=year;
	}
	
	//Default Constructors
	
	public Music(){	
		Library.setNumberOfSongs(Library.getNumberOfSongs()+1);
		songId++;
				
	} 
	
	//Methods to do stuff
	
	//Displays all uninitialized variables
	public void askInfo() throws IllegalArgumentException, IllegalAccessException{ //ask for info not already entered
		System.out.println("<--Orchestral.askInfo()-->");//For Debugging
		for (Field f : this.getClass().getDeclaredFields()) {
		        Class t = f.getType();
		      	Object o = f.get(this);
		        /*For booleans
		        if(t == boolean.class && Boolean.FALSE.equals(v)) 
		        {// found default value		 }*/
		        if(t.isPrimitive() && ((Number) o).doubleValue() == 0)
		        {// found default value
		        	 Catalog.out.println("What is "+f);
		        	 Catalog.out.println("this is still not finished");
		        }
		        else if(!t.isPrimitive() && o == null)
		        { // found default value
		        	Catalog.out.println("What is "+f);
		        	Catalog.out.println("this is still not finished");
		        }//Close if
		}//Close for
	}//Close askInfoMethod
	
	public String toString(){// display all music data as a string
		return ("\n\t"+getTitle()+"\n\t"+getGenre()+
				"\n\tlength= "+getDuration()+
				"\n\tComposed in"+getYear()+"\n\t"+
				getComment()+"\n\t"+getSongId());

	
	}
	
	/*public Ar get(String dad){
		String data = info.get(dad);
		switch (data)
		case title:
			return title.get(dad)
		
		
	}*/

}//Close music class
