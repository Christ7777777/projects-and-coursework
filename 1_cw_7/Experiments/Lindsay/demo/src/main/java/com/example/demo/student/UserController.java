package com.example.demo.student;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class UserController {
    private HashMap<String, Users> studentList = new HashMap<>();

    @GetMapping({"/users"})
    @ResponseBody
    public HashMap<String, Users> getStudents() {
        return studentList;
    }

    @PostMapping({"/user"})
    @ResponseBody
    public String createStudent(@RequestBody Users s) {
        System.out.println(s);
        this.studentList.put(s.getFirstName(), s);
        return "Successfully saved new student " + s.getFirstName() + "!";
    }

    @GetMapping({"/user/{email}"})
    @ResponseBody
    public Users getStudentInfo(@PathVariable String email) {
        return (Users)this.studentList.get(email);
    }

    @PutMapping({"/user/{firstName}"})
    @ResponseBody
    public String updateStudentInfo(@PathVariable String firstName, @RequestBody Users s) {
        this.studentList.replace(firstName, s);
        return "User " + s.getEmail() + " successfully updated!";
    }

    @DeleteMapping({"/user/{email}"})
    @ResponseBody
    public String deleteStudent(@PathVariable String firstName) {
        this.studentList.remove(firstName);
        return "Student " + firstName + " successfully deleted.";
    }
}
