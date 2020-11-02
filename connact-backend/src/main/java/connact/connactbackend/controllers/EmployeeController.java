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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/employee")

public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;
    private byte[] saltG;


    @PostMapping("/")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeCreateModel employeeCreateModel) {

        if (employeeCreateModel.getUserName() == null || employeeCreateModel.getPassword() == null) {
            return new ResponseEntity<Error>(HttpStatus.NO_CONTENT);
        }

        //create salt and create has for password
        saltG = createsalt();
        employeeCreateModel.setSalt(saltG);
        String generatedHashPassword = hash(employeeCreateModel.getPassword(),saltG);

        Employee employee = new Employee(employeeCreateModel.getUserName(), generatedHashPassword,employeeCreateModel.getSalt());
        employeeRepo.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
    public String hash(String passwordToHash, byte[] salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

    /*public boolean checkPassword(String hash, byte[] salt){
        String generatedHash = hash(hash, salt);
        return hashh.equals(generatedHash);
    }*/
    public byte[] createsalt(){
        SecureRandom random = new SecureRandom();
        saltG = new byte[16];
        random.nextBytes(saltG);
        return saltG;
    }
}
