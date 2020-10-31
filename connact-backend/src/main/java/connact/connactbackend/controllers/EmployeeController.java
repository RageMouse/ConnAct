package connact.connactbackend.controllers;

import connact.connactbackend.entities.Profile;
import connact.connactbackend.repositories.EmployeeRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/employee")

public class EmployeeController {
    private EmployeeRepo employeeRepo;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "this is a test";
    }

    @PostMapping("/")
    public ResponseEntity<?> createAuction(@RequestBody EmployeeCreateModel employeeCreateModel) {
        if (employeeCreateModel.getUserName() == null || employeeCreateModel.getPassword() == null) {
            return new ResponseEntity<Error>(HttpStatus.NO_CONTENT);
        }
        Employee employee = new Employee(employeeCreateModel.getUserName(), employeeCreateModel.getPassword());
        employeeRepo.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
}
