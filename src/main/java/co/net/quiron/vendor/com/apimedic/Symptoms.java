package co.net.quiron.vendor.com.apimedic;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Symptoms{

	@JsonProperty("Symptoms")
	private List<Symptom> symptoms;

	public void setSymptoms(List<Symptom> symptoms){
		this.symptoms = symptoms;
	}

	public List<Symptom> getSymptoms(){
		return symptoms;
	}

	@Override
 	public String toString(){
		return 
			"Symptoms{" + 
			"symptoms = '" + symptoms + '\'' + 
			"}";
		}
}