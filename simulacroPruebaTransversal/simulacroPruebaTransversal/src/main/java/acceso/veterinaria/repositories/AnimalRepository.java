package acceso.veterinaria.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acceso.veterinaria.models.Animal;
import acceso.veterinaria.models.TipoAnimal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
	  List<Animal> findAll();
	  Set<Animal> findByTipo(TipoAnimal tipo);;
	  Animal findAnimalByIdAnimal(TipoAnimal tipoAnimal); 
}



 
  


  