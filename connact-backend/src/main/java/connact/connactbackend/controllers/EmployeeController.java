package connact.connactbackend.controllers;

import connact.connactbackend.entities.Employee;
import connact.connactbackend.models.EmployeeCreateModel;
import connact.connactbackend.repositories.EmployeeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/employee")

public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;
    private byte[] saltG;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody EmployeeCreateModel employeeCreateModel) {
        Employee employee = employeeRepo.findByUserName(employeeCreateModel.getUserName());
        String password = hash(employeeCreateModel.getPassword(), stringToByte(employee.getSalt()));

        if (employee.getPassword().equals(password)) {
            employee.setPassword("");
            employee.setSalt("");
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body("Wrong password");
        }
    }


    @PostMapping("/")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeCreateModel employeeCreateModel) {

        if (employeeCreateModel.getUserName() == null || employeeCreateModel.getPassword() == null) {
            return new ResponseEntity<Error>(HttpStatus.NO_CONTENT);
        }

        //create salt and create hash for password
        saltG = createsalt();
        String salt = bytetoString(saltG);
        byte[] bytedSalt = stringToByte(salt);
        String finalSalt = bytetoString(bytedSalt);
        employeeCreateModel.setSalt(finalSalt);
        String generatedHashPassword = hash(employeeCreateModel.getPassword(), stringToByte(finalSalt));

        Employee employee = new Employee(employeeCreateModel.getUserName(), generatedHashPassword, employeeCreateModel.getSalt());
        employeeRepo.save(employee);

        employee.setSalt(null);
        employee.setPassword(null);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    public String hash(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            md.update(salt);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public byte[] createsalt() {
        SecureRandom random = new SecureRandom();
        saltG = new byte[16];
        random.nextBytes(saltG);
        return saltG;
    }

    public String bytetoString(byte[] input) {
        return org.apache.commons.codec.binary.Base64.encodeBase64String(input);
    }

    public byte[] stringToByte(String input) {
        if (org.apache.commons.codec.binary.Base64.isBase64(input)) {
            return org.apache.commons.codec.binary.Base64.decodeBase64(input);
        } else {
            return org.apache.commons.codec.binary.Base64.encodeBase64(input.getBytes());
        }
    }

    @PostMapping("/searchEmployee")
    public ResponseEntity<Employee> searchEmployee(@RequestBody EmployeeCreateModel employeeCreateModel) {
        Employee employee = employeeRepo.findByUserName(employeeCreateModel.getUserName());
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeRepo.findByEmployeeId(employeeId);
        employeeRepo.delete(employee);
    }
}
