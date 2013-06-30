package musicCatalog;

public class Music {
	
	private int songId=1;	
	private String title;
	private static String genre;
	private String comment;
	private double duration;
	private int year;
	
	//Accessory Methods
	public int getSongId(){
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
		Library.numberOfSongs++;
		songId=numberOfSongs;
		
		
		
	}
	

}
