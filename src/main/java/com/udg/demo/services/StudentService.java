package com.udg.demo.services;

import com.udg.demo.models.StudentModel;
import com.udg.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository repository;

    //CRUD

    public StudentModel saveStudent(StudentModel student){
        //Validar que el código que recibí no exista en otro estudiante
        String searchCode = student.getCode();
        //buscar en el repository en el método findByCode si estamos usando el código
        if(repository.findByCode(searchCode).isEmpty()){  //si no existe, agregarlo
            return repository.save(student);
        }else{
            //si ya existe retorno un student con el id -1
            StudentModel studentModel = new StudentModel();
            studentModel.setId(-1L);
            return studentModel;
        }
    }

    public ArrayList<StudentModel> getAllStudents(){
        return (ArrayList<StudentModel>) repository.findAll();
    }

    public Optional<StudentModel> findStudentById(Long id){
        return repository.findById(id);
    }
    public Optional<StudentModel> findStudentByName(String name){
        return repository.findByName(name);
    }


    public StudentModel editStudent(StudentModel student){
        return repository.save(student);
    }

    public String deleteStudentById(Long id){

        if(findStudentById(id).isPresent()){
            repository.deleteById(id);
            return "student deleted successfully";
        }else {
            return "student with id="+ id+ " not foud";
        }
    }

    public Optional<StudentModel> findStudentByCode(String code){
        return repository.findByCode(code);
    }
}