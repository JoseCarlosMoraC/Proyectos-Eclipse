package acceso.veterinaria.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Exceptions.VeterinariaNotFoundException;
import acceso.veterinaria.models.Animal;
import acceso.veterinaria.models.TipoAnimal;
import acceso.veterinaria.models.Vacuna;
import acceso.veterinaria.repositories.AnimalRepository;
import acceso.veterinaria.repositories.VacunaRepository;

@Service
public class VeterinariaServiceIml implements VeterinariaService {

    private final AnimalRepository animalRepository;
    private final VacunaRepository vacunaRepository;


    @Autowired
    public VeterinariaServiceIml(AnimalRepository animalRepository, VacunaRepository vacunaRepository) {
        this.animalRepository = animalRepository;
        this.vacunaRepository = vacunaRepository;
    }

    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public Set<Animal> findByTipoAnimal(TipoAnimal tipoAnimal) {
        return animalRepository.findByTipo(tipoAnimal);
    }

    @Override
    public String createAnimal(Animal animal) {
    	String respuesta = "";
    	Animal animalAñadido = animalRepository.save(animal);
    	if(animalAñadido != null) {
    		respuesta = animalAñadido.getIdAnimal().toString();
    	}else {
    		respuesta = "No se ha podido añadir bebesito";
    	}
        return respuesta;
    }

    @Override
    public Animal findAnimalById(long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new VeterinariaNotFoundException(id));
    }

    @Override
    public Animal updateNameAnimal(long idAnimal, Animal animal) {
        Animal pOriginal = this.findAnimalById(idAnimal);
        pOriginal.setNombre(animal.getNombre());
        return animalRepository.save(pOriginal);
    }

    @Override
    public List<Vacuna> findAllVacunas() {
        return vacunaRepository.findAll();
    }

    @Override
    public Set<Vacuna> findByFarmaceutica(String farmaceutica) {
        return vacunaRepository.findByFarmaceutica(farmaceutica);
    }

    @Override
    public Vacuna createVacuna(Vacuna vacuna) {
        return vacunaRepository.save(vacuna);
    }

    @Override
    public Vacuna findVacunaById(long idVacuna) {
        return vacunaRepository.findById(idVacuna)
                .orElseThrow(() -> new VeterinariaNotFoundException(idVacuna));
    }

    @Override
    public Vacuna updateNameVacuna(long idVacuna, Vacuna vacuna) {
        Vacuna vOriginal = this.findVacunaById(idVacuna);
        vOriginal.setNombre(vacuna.getNombre());
        return vacunaRepository.save(vOriginal);
    }
}