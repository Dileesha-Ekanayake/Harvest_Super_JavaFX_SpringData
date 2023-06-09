// package lk.earth.dbapp;

// import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// import java.util.List;

// public class StatusemployeeController {

//     public static List<Statusemployee> get() {

//         AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
//         appContext.scan("lk.earth.dbapp");
//         appContext.refresh();
//         StatusemployeeService statusemployeeService = (StatusemployeeService) appContext.getBean("statusemployeeService");

//         List<Statusemployee> statusemployees = statusemployeeService.getAll();

//         return statusemployees;

//     }

// }