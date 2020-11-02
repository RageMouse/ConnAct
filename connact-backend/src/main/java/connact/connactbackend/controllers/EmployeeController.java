package connact.connactbackend.controllers;

import connact.connactbackend.entities.Employee;
import connact.connactbackend.models.EmployeeCreateModel;
import connact.connactbackend.repositories.EmployeeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/employee")

public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;


    @PostMapping("/")
    public ResponseEntity<?> createAuction(@RequestBody EmployeeCreateModel employeeCreateModel) {
        System.out.println("WORDT DIE EVENT AANGEROEPEN?");
        System.out.println("raar ding");
        if(employeeCreateModel!=null){
            System.out.println("hij maakt wel?");
            System.out.println("naam  :"+ employeeCreateModel.getUserName()+"    Wachtwoord"+employeeCreateModel.getPassword());
        }
        else{
            System.out.println("hij maakt niet?");
        }
        if (employeeCreateModel.getUserName() == null || employeeCreateModel.getPassword() == null) {

            return new ResponseEntity<Error>(HttpStatus.NO_CONTENT);
        }
        System.out.println("Deze lijn start");
        Employee employee = new Employee(employeeCreateModel.getUserName(), employeeCreateModel.getPassword());
        employeeRepo.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
}
