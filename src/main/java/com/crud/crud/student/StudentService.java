package com.crud.crud.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, Student studentDetails) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exist"));

        if (studentDetails.getName() != null && !studentDetails.getName().isEmpty()) {
            student.setName(studentDetails.getName());
        }

        if (studentDetails.getEmail() != null && !studentDetails.getEmail().isEmpty()) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentDetails.getEmail());
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(studentDetails.getEmail());
        }

        if (studentDetails.getDob() != null) {
            student.setDob(studentDetails.getDob());
        }
    }

}
