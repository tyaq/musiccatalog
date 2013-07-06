/**
 * Class for orchestral music types
 */
package musicCatalog;


public class Orchestral extends Music {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8728548250496604436L;
	private String composer;
	private String[] instruments;
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
	
	public String toString(){// display all music data as a string. this can be improved
		return (getComposer()+"\n\t"+getTitle()+"\n\t"+getGenre()+
				"\n\tlength "+getDuration()+"s\n\tInstruments include: "+
				getInstruments()+"\n\tComposed in "+getYear()+"\n\t"+ "Note: " +
				getComment()+"\n\tSongId: "+getSongId())+"\n\n";
	}
	
	public String toString (String[] instraments) {
		String save="";
		for (int i=0;i<instraments.length;i++) {
			save += instraments[i];
		}
		return (save);
	}
	
	//Constructors
	public Orchestral(String title,String composer){
		super();
		setGenre("orchestral");
		setTitle(title);
		this.composer=composer;
	}
	
	public Orchestral(String title){
		super();
		setGenre("orchestral");
		setTitle(title);
	}

}
