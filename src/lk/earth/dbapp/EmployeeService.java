package lk.earth.dbapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	public List<Employee> getAll() {
		return (List<Employee>) this.employeeDao.findAll();
	}

	public Employee getById(Integer id) {
		return this.employeeDao.findAllById(id);
	}

	public List<Employee> getAllByName(String name) {
		return (List<Employee>) this.employeeDao.findAllByName(name);
	}

	public List<Employee> getAllByNic(String nic) {
		return (List<Employee>) this.employeeDao.findAllByNic(nic);
	}

	public List<Employee> getAllByGender(Gender gender) {
		return (List<Employee>) this.employeeDao.findAllByGender(gender);
	}

	public List<Employee> getAllByDesignation(Designation designation) {
		return (List<Employee>) this.employeeDao.findAllByDesignation(designation);
	}

	public List<Employee> getAllByNameGender(String name, Gender gender) {
		return (List<Employee>) this.employeeDao.findAllByNameAndGender(name, gender);
	}

	public List<Employee> getAllByNameDesignation(String name, Designation designation) {
		return (List<Employee>) this.employeeDao.findAllByNameAndDesignation(name, designation);
	}

	public List<Employee> getAllByGenderDesignation(Gender gender, Designation designation) {
		return (List<Employee>) this.employeeDao.findAllByGenderAndDesignation(gender, designation);
	}

	public List<Employee> getAllByNameGenderDesignation(String name, Gender gender, Designation designation) {
		return (List<Employee>) this.employeeDao.findAllByNameAndGenderAndDesignation(name, gender, designation);
	}

	public Employee add(Employee employee) {
		Employee msg = null;
		msg = this.employeeDao.save(employee);
		return msg;
	}

	public String delete(Employee employee) {
		String msg = "0";
		Employee emp = this.employeeDao.findAllById(employee.getId());
		System.out.println(emp);
		if (emp != null) {
			employeeDao.delete(employee);
			return msg = "1";
		} else {
			// throw new RuntimeException("Couldn't find employee with id " + emp);
			return msg = "Employee Deleted Failed" + emp;
		}

	}

	public Employee update(Employee employee) {
		Employee msg = null;
		msg = employeeDao.save(employee);
		return msg;
	}

}

// public String post(Employee employee) {

// // AnnotationConfigApplicationContext appContext = new
// // AnnotationConfigApplicationContext();
// // appContext.scan("lk.earth.dbapp");
// // appContext.refresh();
// // EmployeeService employeeService = (EmployeeService)
// // appContext.getBean("employeeService");

// String msg = "";
// String err = "";

// LocalDate today = LocalDate.now();
// int age = today.getYear() - employee.getDob().getYear();

// if (age < 18)
// err = err + "\nMust be 18 Years";

// List<Employee> empNic = employeeDao.findAllByNic(employee.getNic());

// if (!empNic.isEmpty())
// err = err + "\nNIC Exists";
// if (err.isEmpty()) {
// Employee dberr = employeeDao.save(employee);

// if (dberr != null)
// msg = "1";
// else
// msg = "DB error as : " + dberr;
// } else {
// msg = "Data Errors : \n" + err;
// }

// return msg;
// }

// public void delete(Employee employee) {
// this.employeeDao.delete(employee);

// }

// public void update(Employee employee){
// this.employeeDao.update(employee);

// // this.employeeDao.update(employee);
// }
