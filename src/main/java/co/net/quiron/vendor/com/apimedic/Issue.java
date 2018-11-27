package co.net.quiron.vendor.com.apimedic;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Issue{

	@JsonProperty("Accuracy")
	private int accuracy;

	@JsonProperty("Ranking")
	private int ranking;

	@JsonProperty("ProfName")
	private String profName;

	@JsonProperty("IcdName")
	private String icdName;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("Icd")
	private String icd;

	@JsonProperty("Name")
	private String name;

	public void setAccuracy(int accuracy){
		this.accuracy = accuracy;
	}

	public int getAccuracy(){
		return accuracy;
	}

	public void setRanking(int ranking){
		this.ranking = ranking;
	}

	public int getRanking(){
		return ranking;
	}

	public void setProfName(String profName){
		this.profName = profName;
	}

	public String getProfName(){
		return profName;
	}

	public void setIcdName(String icdName){
		this.icdName = icdName;
	}

	public String getIcdName(){
		return icdName;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setIcd(String icd){
		this.icd = icd;
	}

	public String getIcd(){
		return icd;
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
			"Issue{" + 
			"accuracy = '" + accuracy + '\'' + 
			",ranking = '" + ranking + '\'' + 
			",profName = '" + profName + '\'' + 
			",icdName = '" + icdName + '\'' + 
			",iD = '" + iD + '\'' + 
			",icd = '" + icd + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}