package fatec.phc.api.trainingschedule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.phc.api.trainingschedule.model.Training;

public interface ITrainingRepository extends JpaRepository<Training, Long>{
	List<Training> findByType(String type);
}
