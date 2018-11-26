package co.net.quiron.vendor.com.apimedic;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Symptom {

	@JsonProperty("Synonyms")
	private List<Object> synonyms;

	@JsonProperty("ProfName")
	private String profName;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("HasRedFlag")
	private boolean hasRedFlag;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("HealthSymptomLocationIDs")
	private List<Integer> healthSymptomLocationIDs;

	public void setSynonyms(List<Object> synonyms){
		this.synonyms = synonyms;
	}

	public List<Object> getSynonyms(){
		return synonyms;
	}

	public void setProfName(String profName){
		this.profName = profName;
	}

	public String getProfName(){
		return profName;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setHasRedFlag(boolean hasRedFlag){
		this.hasRedFlag = hasRedFlag;
	}

	public boolean isHasRedFlag(){
		return hasRedFlag;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setHealthSymptomLocationIDs(List<Integer> healthSymptomLocationIDs){
		this.healthSymptomLocationIDs = healthSymptomLocationIDs;
	}

	public List<Integer> getHealthSymptomLocationIDs(){
		return healthSymptomLocationIDs;
	}

	@Override
 	public String toString(){
		return 
			"Symptom{" +
			"synonyms = '" + synonyms + '\'' + 
			",profName = '" + profName + '\'' + 
			",iD = '" + iD + '\'' +
			",hasRedFlag = '" + hasRedFlag + '\'' + 
			",name = '" + name + '\'' + 
			",healthSymptomLocationIDs = '" + healthSymptomLocationIDs + '\'' + 
			"}";
		}
}