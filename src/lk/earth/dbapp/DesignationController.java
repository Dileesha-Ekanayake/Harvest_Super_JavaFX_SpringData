// package lk.earth.dbapp;

// import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// import java.util.List;

// public class DesignationController {

//     public static List<Designation> get() {

//         AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
//         appContext.scan("lk.earth.dbapp");
//         appContext.refresh();
//         DesignationService designationService = (DesignationService) appContext.getBean("designationService");

//         List<Designation> designations = designationService.getAll();

//         return designations;

//     }

// }