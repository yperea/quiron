package co.net.quiron.vendor.com.apimedic;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Diagnosis{

	@JsonProperty("Issue")
	private Issue issue;

	@JsonProperty("Specialisation")
	private List<Specialisation> specialisation;

	public void setIssue(Issue issue){
		this.issue = issue;
	}

	public Issue getIssue(){
		return issue;
	}

	public void setSpecialisation(List<Specialisation> specialisation){
		this.specialisation = specialisation;
	}

	public List<Specialisation> getSpecialisation(){
		return specialisation;
	}

	@Override
 	public String toString(){
		return 
			"Diagnosis{" + 
			"issue = '" + issue + '\'' + 
			",specialisation = '" + specialisation + '\'' + 
			"}";
		}
}