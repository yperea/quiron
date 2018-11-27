package co.net.quiron.vendor.com.apimedic;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Specialisation {

	@JsonProperty("SpecialistID")
	private int specialistID;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("Name")
	private String name;

	public void setSpecialistID(int specialistID){
		this.specialistID = specialistID;
	}

	public int getSpecialistID(){
		return specialistID;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"Specialisation{" +
			"specialistID = '" + specialistID + '\'' + 
			",iD = '" + iD + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}