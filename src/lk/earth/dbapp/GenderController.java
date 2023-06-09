// package lk.earth.dbapp;

// import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// import java.util.List;

// public class GenderController {

//     public static List<Gender> get() {

//         AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
//         appContext.scan("lk.earth.dbapp");
//         appContext.refresh();
//         GenderService genderService = (GenderService) appContext.getBean("genderService");

//         List<Gender> genders = genderService.getAll();

//         return genders;

//     }

// }
