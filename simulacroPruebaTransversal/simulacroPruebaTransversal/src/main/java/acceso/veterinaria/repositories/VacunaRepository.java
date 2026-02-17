package acceso.veterinaria.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import acceso.veterinaria.models.Vacuna;



@Repository
public interface VacunaRepository extends JpaRepository<Vacuna, Long> {
	  List<Vacuna> findAll();
	  Set<Vacuna> findByFarmaceutica(String farmaceutica);
	  Vacuna findVacunaByIdVacuna(long idVacuna);
}
