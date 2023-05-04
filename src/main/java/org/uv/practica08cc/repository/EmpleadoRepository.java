/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.practica08cc.repository;

import org.springframework.data.repository.CrudRepository;
import org.uv.practica08cc.models.Empleado;


/**
 *
 * @author fermin
 */
public interface EmpleadoRepository extends CrudRepository<Empleado, Integer>{
    
}
