package fatec.phc.api.trainingschedule.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String type;
	@NotNull
	private LocalDate date;
	@NotBlank
	private String muscularGroup;
	
	private int duration;
	
	private String gym;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public String getMuscularGroup() {
		return muscularGroup;
	}


	public void setMuscularGroup(String muscularGroup) {
		this.muscularGroup = muscularGroup;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getGym() {
		return gym;
	}


	public void setGym(String gym) {
		this.gym = gym;
	}

	@Override
	public String toString() {
		return "Treino" +
				"\n [id=" + id +
				"\n type=" + type +
				"\n date=" + date +
				"\n muscularGroup=" + muscularGroup +
				"\n duration=" + duration +
				"\n gym=" + gym +
				"\n]";
	}

}
