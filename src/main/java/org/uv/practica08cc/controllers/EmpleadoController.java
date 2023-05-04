package org.uv.practica08cc.controllers;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uv.practica08cc.repository.EmpleadoRepository;
import org.uv.practica08cc.dtos.DTOEmpleado;
import org.uv.practica08cc.models.Empleado;

/**
 *
 * @author fermin
 */
@RestController
@RequestMapping(path = "/api")
public class EmpleadoController {   
    @Autowired
    EmpleadoRepository empRep;
    
    @GetMapping("/hello")
    public String hello(){
        return "Hola mundo";
    }
    
    @GetMapping("/empleado/{id}")
    public DTOEmpleado findById(@PathVariable("id") int id){
        Optional<Empleado> res= empRep.findById(id);
        DTOEmpleado emp= null;
        
        if(res.isPresent()){
            emp= new DTOEmpleado();
            emp.setClave(res.get().getClave());
            emp.setNombre(res.get().getNombre());
            emp.setDireccion(res.get().getDireccion());
            emp.setTelefono(res.get().getTelefono());
        }
        
        return emp;
    }
    
    @GetMapping("/empleado")
    public List<DTOEmpleado> findAll(){
        List<DTOEmpleado> emps= new ArrayList<>();
        
        Iterable<Empleado> res= empRep.findAll();
        for (Iterator<Empleado> iterator = res.iterator(); iterator.hasNext();) {
            Empleado emp= iterator.next();
            DTOEmpleado dtoEmp = new DTOEmpleado();
            dtoEmp.setClave(emp.getClave());
            dtoEmp.setNombre(emp.getNombre());
            dtoEmp.setDireccion(emp.getDireccion());
            dtoEmp.setTelefono(emp.getTelefono());
            emps.add(dtoEmp);
            
        }
        
        return emps;
    }
    
    @PostMapping("/empleado")
    public DTOEmpleado create(@RequestBody Empleado empleado){
        Empleado emp= new Empleado();
        emp.setClave(empleado.getClave());
        emp.setNombre(empleado.getNombre());
        emp.setDireccion(empleado.getDireccion());
        emp.setTelefono(empleado.getTelefono());
        Empleado empNew= empRep.save(emp);
        
        DTOEmpleado dtoEmpNew= new DTOEmpleado();
        dtoEmpNew.setClave(empNew.getClave());
        dtoEmpNew.setNombre(empNew.getNombre());
        dtoEmpNew.setDireccion(empNew.getDireccion());
        dtoEmpNew.setTelefono(empNew.getTelefono());
        
        return dtoEmpNew;
    }
    
    @DeleteMapping("/empleado/{id}")
    public String delete(@PathVariable int id){
        empRep.deleteById(id);
        return "Se ha borrado el registro";
    }
    
    @PutMapping("/empleado")
    public DTOEmpleado update(@RequestBody Empleado emp){
        Optional<Empleado> opt=empRep.findById(emp.getClave());
        DTOEmpleado empDto=null;
        if(opt.isPresent()){
            empDto= new DTOEmpleado();
            Empleado empNew=empRep.save(emp);
            empDto.setClave(empNew.getClave());
            empDto.setNombre(empNew.getNombre());
            empDto.setDireccion(empNew.getDireccion());
            empDto.setTelefono(empNew.getTelefono());
            return empDto;
        }
        else{
            Logger.getLogger(EmpleadoController.class.getName()).log(Logger.Level.INFO,"No se encontro el registro con el id {0}", new Object[]{emp.getClave()});
            return empDto;
        }
    }
}