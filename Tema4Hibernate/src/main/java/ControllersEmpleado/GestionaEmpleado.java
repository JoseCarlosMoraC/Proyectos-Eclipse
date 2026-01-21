package ControllersEmpleado;

import java.time.LocalDateTime;
import java.util.List;

import ModelsEmpleado.Acta;
import ModelsEmpleado.Departamento;
import ModelsEmpleado.Empleado;
import ModelsEmpleado.Reunion;
import ModelsEmpleado.Sala;
import RepositoriesEmpleado.ActaDao;
import RepositoriesEmpleado.DepartamentoDao;
import RepositoriesEmpleado.EmpleadoDao;
import RepositoriesEmpleado.ReunionDao;
import RepositoriesEmpleado.SalaDao;

public class GestionaEmpleado {

    public static void main(String[] args) {


        DepartamentoDao departamentoDao = new DepartamentoDao();
        EmpleadoDao empleadoDao = new EmpleadoDao();
        SalaDao salaDao = new SalaDao();
        ReunionDao reunionDao = new ReunionDao();
        ActaDao actaDao = new ActaDao();

   
        Departamento it = new Departamento("Informática");
        departamentoDao.create(it); 

  
        Empleado e1 = new Empleado("Juan", 2000.0, it);
        Empleado e2 = new Empleado("Ana", 2200.0, it);
        empleadoDao.create(e1);
        empleadoDao.create(e2);


        Sala sala1 = new Sala("Sala A", 10);
        salaDao.create(sala1); 

      
        Reunion reunion1 = new Reunion(LocalDateTime.now(), "Reunión Proyecto X", sala1);
        reunionDao.create(reunion1);

 
        Acta acta1 = new Acta();
        acta1.setContenido("Acta de la reunión X");
        acta1.setReunion(reunion1);
        actaDao.create(acta1);

    
        e1.setSalario(2500.0);
        empleadoDao.update(e1);


        sala1.setCapacidad(12);
        salaDao.update(sala1);


        reunion1.setAsunto("Reunión Proyecto X - Actualizada");
        reunionDao.update(reunion1);


        acta1.setContenido("Acta actualizada de la reunión X");
        actaDao.update(acta1);

    
        Empleado empleadoRecuperado = empleadoDao.get(e1.getId());
        System.out.println("Empleado recuperado: " + empleadoRecuperado);

        
        Sala salaRecuperada = salaDao.get(sala1.getIdSala());
        System.out.println("Sala recuperada: " + salaRecuperada);


        Reunion reunionRecuperada = reunionDao.get(reunion1.getId().intValue());
        System.out.println("Reunión recuperada: " + reunionRecuperada);


        Acta actaRecuperada = actaDao.get(acta1.getIdActa());
        System.out.println("Acta recuperada: " + actaRecuperada);

  
        List<Empleado> empleados = empleadoDao.getAll();
        System.out.println("\nLista de empleados:");
        for (Empleado e : empleados) {
            System.out.println(e);
        }

  
        List<Departamento> departamentos = departamentoDao.getAll();
        System.out.println("\nLista de departamentos:");
        for (Departamento d : departamentos) {
            System.out.println(d);
        }

    
        List<Sala> salas = salaDao.getAll();
        System.out.println("\nLista de salas:");
        for (Sala s : salas) {
            System.out.println(s);
        }

       
        List<Reunion> reuniones = reunionDao.getAll();
        System.out.println("\nLista de reuniones:");
        for (Reunion r : reuniones) {
            System.out.println(r);
        }


        List<Acta> actas = actaDao.getAll();
        System.out.println("\nLista de actas:");
        for (Acta a : actas) {
            System.out.println(a);
        }
    }
}
