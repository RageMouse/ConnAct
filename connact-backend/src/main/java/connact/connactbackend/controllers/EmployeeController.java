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

import org.apache.commons.codec.binary.Base64;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/employee")

public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;
    private byte[] saltG;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody EmployeeCreateModel employeeCreateModel){
        System.out.println(employeeCreateModel.getUserName()+"   "+employeeCreateModel.getPassword());
        System.out.println("HIJ GAAT HIER NOG WEL");
        Employee employee = employeeRepo.findByUserName(employeeCreateModel.getUserName());
        System.out.println("EMPLOYEE GEVONDEN"+employee.getUserName());
        String password = hash(employeeCreateModel.getPassword(),stringToByte(employee.getSalt()));

        if(employee.getPassword().equals(password)){
            System.out.println("WACHTWOORD KLOPT");
            employee.setPassword("");
            employee.setSalt("");
            System.out.println(employee.getEmployeeId()+ "  "+ employee.getUserName());

            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        }
        else{
            //wachtwoord klopt
            System.out.println("WACHTWOORD KLOPT NIET");
            return new ResponseEntity<Error>(HttpStatus.NO_CONTENT);
        }
    }


    @PostMapping("/")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeCreateModel employeeCreateModel) {

        if (employeeCreateModel.getUserName() == null || employeeCreateModel.getPassword() == null) {
            System.out.println(employeeCreateModel.getUserName()+"    :    "+employeeCreateModel.getPassword());
            return new ResponseEntity<Error>(HttpStatus.NO_CONTENT);
        }

        //create salt and create hash for password
        saltG = createsalt();
        String salt = bytetoString(saltG);
        byte[] bytedsalt = stringToByte(salt);
        String finalsalt = bytetoString(bytedsalt);
        employeeCreateModel.setSalt(finalsalt);
        String generatedHashPassword = hash(employeeCreateModel.getPassword(),stringToByte(finalsalt));
        System.out.println("SALTTT: "+finalsalt);

        Employee employee = new Employee(employeeCreateModel.getUserName(), generatedHashPassword,employeeCreateModel.getSalt());
        employeeRepo.save(employee);
        String password = hash(employeeCreateModel.getPassword(),stringToByte(finalsalt));
        if(password.equals(generatedHashPassword)){
            System.out.println("HIER KLOPT HET NOG WEL");
        }


        employee.setSalt(null);
        employee.setPassword(null);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
    public String hash(String passwordToHash, byte[] salt){
        String generatedPassword = null;
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            md.update(salt);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
            /*String s = bytetoString(saltG);
            System.out.println(s + "  DIT IS SSSSS");
            byte[] yikes = stringToByte(s);
            String a = bytetoString(yikes);
            System.out.println(a + "DIT IS AAAA");
            generatedPassword = generatedPassword + bytetoString(saltG);
            System.out.println(generatedPassword);
            System.out.println(generatedPassword.replace(bytetoString(saltG),""));*/
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
}
