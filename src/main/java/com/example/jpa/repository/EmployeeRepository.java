package com.example.jpa.repository;

import com.example.jpa.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{


    /*
    CREATE FUNCTION get_employee_task_count(v_imie text)
    RETURNS int
    AS $$
    BEGIN
        SELECT COUNT(*)
        FROM employee e
        LEFT JOIN task t ON
            e.id = t.employee_id
        WHERE e.imie = v_imie;
    END
    $$
    LANGUAGE plpgsql;
     */

//    @Procedure("get_employee_task_count")
    @Procedure(Employee.TASKS_COUNT) // nazwa procedury wyciagnieta do stalej
    int getTasksCount(String name);

}
