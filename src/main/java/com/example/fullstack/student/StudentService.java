package com.example.fullstack.student;

import com.example.fullstack.student.exception.BadRequestException;
import com.example.fullstack.student.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Boolean isEmailExists = studentRepository.existsByEmail(student.getEmail());

        if(isEmailExists)
            throw new BadRequestException("Email " + student.getEmail() + " has been taken");
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id))
            throw new StudentNotFoundException("Student with id " + id + "does not exist");
        studentRepository.deleteById(id);
    }
}
