package musicCatalog;

public class Music {
	
	private int songId;	
	private String title;
	private String genre;
	private String comment;
	private double duration;
	
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

}
