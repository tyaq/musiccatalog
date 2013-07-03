package musicCatalog;

import java.io.IOException;
import java.lang.reflect.*;


public class Orchestral extends Music {

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
	
	//Displays all uninitialized variables
	public void askInfo() throws IllegalArgumentException, IllegalAccessException, IOException, NoSuchMethodException, SecurityException{ //ask for info not already entered
		System.out.println("<--Orchestral.askInfo()-->");//For Debugging
		for (Field f : this.getClass().getDeclaredFields()) {
		        Class t = f.getType();
		      	Object o = f.get(this);
		        /*For booleans
		        if(t == boolean.class && Boolean.FALSE.equals(v)) 
		        {// found default value		 }*/
		        if(t.isPrimitive() && ((Number) o).doubleValue() == 0)
		        {// found default value
		        	 Catalog.out.println("What is "+f.getName());
		        	 Catalog.out.println("this is still not finished");
		        	 String methodName= "set"+f.getName();
		        	 Method method = this.getClass().getMethod(methodName, String.class);
		        	 f.set(this.getClass(), Catalog.in.readLine());
		        }
		        else if(!t.isPrimitive() && o == null)
		        { // found default value
		        	Catalog.out.println("What is "+f.getName());
		        	Catalog.out.println("this is still not finished");
		        	f.set(this.getClass(), Catalog.in.readLine());
		        }//Close if
		}//Close for
	}//Close askInfoMethod
	
	public String toString(){// display all music data as a string
		return (getComposer()+"\n\t"+getTitle()+"\n\t"+getGenre()+
				"\n\tlength= "+getDuration()+"\n\tInstruments include: "+
				getInstruments()+"\n\tComposed in"+getYear()+"\n\t"+
				getComment()+"\n\t"+getSongId());
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
