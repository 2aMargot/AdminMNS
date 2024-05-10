package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.EmployeeDao;
import com.project.adminmns.dao.UserRoleDao;
import com.project.adminmns.model.Employee;
import com.project.adminmns.model.UserRole;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.view.EmployeeView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
public class EmployeeController {

    EmployeeDao employeeDao;
    UserRoleDao userRoleDao;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/employee/{id}")
    @AdminPermission
    @JsonView(EmployeeView.class)
    public ResponseEntity<Employee> get(@PathVariable int id) {

        Optional<Employee> employeeOptional = employeeDao.findById(id);

        if (employeeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employeeOptional.get(), HttpStatus.OK);

    }

    @GetMapping("/employee/list")
    @JsonView(EmployeeView.class)
    @AdminPermission
    public List<Employee> list() {

        return employeeDao.findAll();
    }

    @GetMapping("/role/list")
    @JsonView(EmployeeView.class)
    @AdminPermission
    public List<UserRole> listRole(){

        return userRoleDao.findAll();
    }

    @PostMapping("/employee")
    @AdminPermission
    @JsonView(EmployeeView.class)
    public ResponseEntity<Employee> add(@Valid @RequestBody Employee newUser) {

        if (newUser.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        employeeDao.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/employee/{id}")
    @AdminPermission
    @JsonView(EmployeeView.class)
    public ResponseEntity<Employee> modified(@Valid @RequestBody Employee user, @PathVariable int id) {
        user.setId(id);

        Optional<Employee> employeeOptional = employeeDao.findById(user.getId());

        if (employeeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setPassword(employeeOptional.get().getPassword());

        employeeDao.save(user);
        return new ResponseEntity<>(employeeOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    @AdminPermission
    @JsonView(EmployeeView.class)
    public ResponseEntity<Employee> delete(@PathVariable int id) {

        Optional<Employee> employeeOptional = employeeDao.findById(id);

        if (employeeOptional.isPresent()) {

            employeeDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
