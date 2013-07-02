package musicCatalog;



public class Orchestral extends Music {

	private String composer;
	private String[] instruments;
	private final String genre="orchestral";
	//Accessory Methods
	
	public String getComposer(){
		return composer;
	}
	public String[] getInstruments(){
		return instruments;
	}
	
	
	//Mutator Methods
	
	public void setComposer(String composer){
		this.composer=composer;
	}
	public void setInstruments(String[] instruments){
		this.instruments=instruments;
	}

	//Methods to do stuff
	public String toString(){// display all music data as a string
		return (getComposer()+"\n\t"+getTitle()+"\n\t"+getGenre()+
				"\n\tlength= "+getDuration()+"\n\tInstruments include: "+
				getInstruments()+"\n\tComposed in"+getYear()+"\n\t"+
				getComment()+"\n\t"+getSongId());
		

		
	}

}
