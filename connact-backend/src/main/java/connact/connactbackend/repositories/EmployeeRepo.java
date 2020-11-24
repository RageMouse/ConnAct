package connact.connactbackend.repositories;

import connact.connactbackend.entities.Employee;
import connact.connactbackend.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByUserName(String userName);
    Employee findByEmployeeId(Long employeeId);
}
