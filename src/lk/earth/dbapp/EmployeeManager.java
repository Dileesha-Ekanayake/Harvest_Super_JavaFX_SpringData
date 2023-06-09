package lk.earth.dbapp;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class EmployeeManager {

    public static List<Employee> get(Hashtable<String, Object> ht) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("lk.earth.dbapp");
        appContext.refresh();
        EmployeeService employeeService = (EmployeeService) appContext.getBean("employeeService");

        List<Employee> employees = new ArrayList<>();

        if (ht == null) {
            employees = employeeService.getAll();
        } else {

            String name = (String) ht.get("name");
            Gender gender = (Gender) ht.get("gender");
            Designation designation = (Designation) ht.get("designation");

            if (name.equals("") && gender == null && designation == null)
                employees = employeeService.getAll();

            if (!name.equals("") && gender == null && designation == null)
                employees = employeeService.getAllByName(name);

            if (name.equals("") && gender != null && designation == null)
                employees = employeeService.getAllByGender(gender);

            if (name.equals("") && gender == null && designation != null)
                employees = employeeService.getAllByDesignation(designation);

            if (!name.equals("") && gender != null && designation == null)
                employees = employeeService.getAllByNameGender(name, gender);

            if (name.equals("") && gender != null && designation != null)
                employees = employeeService.getAllByGenderDesignation(gender, designation);

            if (!name.equals("") && gender == null && designation != null)
                employees = employeeService.getAllByNameDesignation(name, designation);

            if (!name.equals("") && gender != null && designation != null)
                employees = employeeService.getAllByNameGenderDesignation(name, gender, designation);

        }

        return employees;

    }

    public static String post(Employee employee) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("lk.earth.dbapp");
        appContext.refresh();
        EmployeeService employeeService = (EmployeeService) appContext.getBean("employeeService");

        String msg = "";
        String err = "";

        LocalDate today = LocalDate.now();
        int age = today.getYear() - employee.getDob().getYear();

        if (age < 18)
            err = err + "\nMust be 18 Years";

        List<Employee> empNic = employeeService.getAllByNic(employee.getNic());

        if (!empNic.isEmpty())
            err = err + "\nNIC Exists";
        if (err.isEmpty()) {
            Employee dberr = employeeService.add(employee);

            if (dberr != null)
                msg = "1";
            else
                msg = "DB error as : " + dberr;
        } else {
            msg = "Data Errors : \n" + err;
        }

        return msg;
    }

    public static String put(Employee employee) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("lk.earth.dbapp");
        appContext.refresh();
        EmployeeService employeeService = (EmployeeService) appContext.getBean("employeeService");

        String msg = "";
        String err = "";
        int id = 0;

        LocalDate today = LocalDate.now();
        int age = today.getYear() - employee.getDob().getYear();
        // System.out.println(age);

        if (age < 18)
            err = err + "\nMust be 18 Years";

        List<Employee> empNic = employeeService.getAllByNic(employee.getNic());
        for (Employee emp : empNic) {
            id = emp.getId();
        }

        if (!empNic.isEmpty() && id != employee.getId())
            err = err + "\nNIC Exists";

        if (err.isEmpty()) {
            // Strimg dberr = employeeService.update(employee);
            Employee dberr = employeeService.update(employee);
            if (dberr != null)
                msg = "1";
            else
                msg = "DB error as : " + dberr;
        } else {
            msg = "Validation Data Error: \n" + err;
        }

        return msg;
    }

    public static String delete(Employee employee) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("lk.earth.dbapp");
        appContext.refresh();
        EmployeeService employeeService = (EmployeeService) appContext.getBean("employeeService");
        String msg = "";
        String dberr = employeeService.delete(employee);
        if (dberr != null) {
            msg = "1";
        } else {
            msg = "DB Error As : " + dberr;
        }

        return msg;

    }

}
