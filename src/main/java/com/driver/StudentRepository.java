package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String,Student> studentDb;
    HashMap<String,Teacher> teacherDb;
    HashMap<String,List<String>> studentTeacherDb;

    public StudentRepository() {
        studentDb = new HashMap<>();
        teacherDb = new HashMap<>();
        studentTeacherDb= new HashMap<>();
    }

    public void addStudent(Student student) {
        studentDb.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(!studentTeacherDb.containsKey(teacher))
            studentTeacherDb.put(teacher,new ArrayList<>());
        studentTeacherDb.get(teacher).add(student);
    }

    public Student getStudentByName(String name) {
        return studentDb.getOrDefault(name,null);
    }

    public Teacher getTeacherByName(String name) {
        return teacherDb.getOrDefault(name,null);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return studentTeacherDb.getOrDefault(teacher,null);
    }

    public List<String> getAllStudents() {
        List<String> list =  new ArrayList<>();
        for(String s:studentDb.keySet())
            list.add(s);
        return list;
    }

    public void deleteTeacherByName(String teacher) {
        if(teacherDb.containsKey(teacher)){
            if(studentTeacherDb.containsKey(teacher)){
                List<String> studentList = studentTeacherDb.get(teacher);
                for(String s:studentList){
                    if(studentDb.containsKey(s))
                        studentDb.remove(s);
                }
                studentTeacherDb.remove(teacher);
            }
            teacherDb.remove(teacher);
        }
    }

    public void deleteAllTeachers() {
        for(String teacher:teacherDb.keySet()){
            if(studentTeacherDb.containsKey(teacher)){
                List<String> studentList = studentTeacherDb.get(teacher);
                for(String s:studentList){
                    if(studentDb.containsKey(s))
                        studentDb.remove(s);
                }
                studentTeacherDb.remove(teacher);
            }
            teacherDb.remove(teacher);
        }
    }
}
