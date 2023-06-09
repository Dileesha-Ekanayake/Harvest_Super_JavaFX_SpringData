// package lk.earth.dbapp;

// import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// import java.util.List;

// public class DBApp {

//     public static void main(String[] args) {
// 		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
// 		appContext.scan("lk.earth.dbapp");
// 		appContext.refresh();

// 		EmployeeService employeeService = (EmployeeService) appContext.getBean("employeeService");

// 		List<Employee> employeeList = employeeService.getAll();
// 		for (Employee employee : employeeList) {
// 			System.out.print(employee.getId() + "\t");
// 			System.out.print(employee.getName() + "\t");
// 			System.out.print(employee.getDob() + "\t");
// 			System.out.print(employee.getNic() + "\t");
// 			System.out.print(employee.getMobile() + "\t");
// 			System.out.print(employee.getEmail() + "\t");
// 			System.out.print(employee.getGender().getName() + "\t");
// 			System.out.print(employee.getDesignation().getName() + "\t");
// 			System.out.print(employee.getStatusemployee().getName() + "\t");
// 			System.out.println("");

// 		}
// 		System.out.println("====================================================");

// 		Employee emp = employeeService.getById(1);
// 		System.out.print(emp.getId() + "\t");
// 		System.out.print(emp.getName() + "\t");
// 		System.out.print(emp.getDob() + "\t");
// 		System.out.print(emp.getNic() + "\t");
// 		System.out.print(emp.getMobile() + "\t");
// 		System.out.print(emp.getEmail() + "\t");
// 		System.out.print(emp.getGender().getName() + "\t");
// 		System.out.print(emp.getDesignation().getName() + "\t");
// 		System.out.print(emp.getStatusemployee().getName() + "\t");
// 		System.out.println("");

// 		System.out.println("====================================================");

// 		List<Employee> employeeList2 = employeeService.getAllByName("o");
// 		for (Employee employee : employeeList2) {
// 			System.out.print(employee.getId() + "\t");
// 			System.out.print(employee.getName() + "\t");
// 			System.out.print(employee.getDob() + "\t");
// 			System.out.print(employee.getNic() + "\t");
// 			System.out.print(employee.getMobile() + "\t");
// 			System.out.print(employee.getEmail() + "\t");
// 			System.out.print(employee.getGender().getName() + "\t");
// 			System.out.print(employee.getDesignation().getName() + "\t");
// 			System.out.print(employee.getStatusemployee().getName() + "\t");
// 			System.out.println("");

// 		}
// 		appContext.close();
// 	}

// }
