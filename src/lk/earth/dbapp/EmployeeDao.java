package lk.earth.dbapp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.id = :id")
    Employee findAllById(@Param("id") Integer id);

    @Query("SELECT e FROM Employee e WHERE e.name like %:name%")
    List<Employee> findAllByName(@Param("name") String name);

    @Query("SELECT e FROM Employee e WHERE e.nic like :nic")
    List<Employee> findAllByNic(@Param("nic") String nic);

    @Query("SELECT e FROM Employee e WHERE e.gender = :gender")
    List<Employee> findAllByGender(@Param("gender") Gender gender);

    @Query("SELECT e FROM Employee e WHERE e.designation = :designation")
    List<Employee> findAllByDesignation(@Param("designation") Designation designation);

    @Query("SELECT e FROM Employee e WHERE e.gender = :gender and e.name like %:name%")
    List<Employee> findAllByNameAndGender(@Param("name") String name, @Param("gender") Gender gender);

    @Query("SELECT e FROM Employee e WHERE e.designation = :designation and e.name like %:name%")
    List<Employee> findAllByNameAndDesignation(@Param("name") String name,
            @Param("designation") Designation designation);

    @Query("SELECT e FROM Employee e WHERE e.gender = :gender and e.designation = :designation")
    List<Employee> findAllByGenderAndDesignation(@Param("gender") Gender gender,
            @Param("designation") Designation designation);

    @Query("SELECT e FROM Employee e WHERE e.gender = :gender and e.designation = :designation and e.name like %:name%")
    List<Employee> findAllByNameAndGenderAndDesignation(@Param("name") String name, @Param("gender") Gender gender,
            @Param("designation") Designation designation);

    @Transactional
    @Modifying
    @Query("DELETE Employee e WHERE e.id = :id")
    Employee Delete(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update Employee e set e.name = name , e.nic = nic , e.email = email, e.dob = dob, e.mobile = mobile , e.gender = gender, e.designation = designation, e.statusemployee = statusemployee")
    // void update(@Param("name") String name, @Param("nic") String nic,
    // @Param("dob") LocalDate dob,
    // @Param("email") String email, @Param("mobile") String mobile,
    // @Param("gender") Gender gender,
    // @Param("designation") Designation designation, @Param("statusemployee")
    // Statusemployee statusemployee);
    // @Query("Insert into employee
    void update(Employee employee);
}
