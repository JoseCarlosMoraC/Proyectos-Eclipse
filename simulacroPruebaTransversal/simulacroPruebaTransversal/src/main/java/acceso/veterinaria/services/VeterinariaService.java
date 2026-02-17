package acceso.veterinaria.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import acceso.veterinaria.models.Animal;
import acceso.veterinaria.models.TipoAnimal;
import acceso.veterinaria.models.Vacuna;


@Service
public interface VeterinariaService {
	List<Animal>  findAll();
    Set<Animal> findByTipoAnimal(TipoAnimal tipoAnimal);
    public String createAnimal(Animal animal) ;
    public Animal findAnimalById(long idAnimal);
    public Animal updateNameAnimal(long idAnimal,  Animal animal);
    
    List<Vacuna>  findAllVacunas();
    Set<Vacuna> findByFarmaceutica(String farmaceutica);
    public Vacuna createVacuna(Vacuna animal) ;
    public Vacuna findVacunaById(long idVacuna);
    public Vacuna updateNameVacuna(long idVacuna,  Vacuna vacuna);
    
}
