//  package lk.earth.dbapp;

//  import java.util.HashMap;
//  import java.util.List;
//  import javafx.collections.FXCollections;
//  import javafx.collections.ObservableList;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

//  public class CommonDao {

//      public static String insert(Object object) {
//          LocalEntityManagerFactoryBean session = AppConfig.entityManagerFactory();
         
//          String msg = "0";

//          try {
//              transaction = session.createTransaction();
//              session.save(object);
//              transaction.commit();
//              if (session != null) {
//                  msg = "1";
//              }
//          } catch (HibernateException e) {
//              if (transaction != null) {
//                  transaction.rollback();
//              }
//              System.out.println(e.getMessage());
//              System.out.println("Cann't Insert");
//              msg = "Database Error";
//          }
//          return msg;
//      }

//      public static ObservableList select(String nameQuery) {

//          ObservableList obList = FXCollections.observableArrayList();
//          List list = null;
//          Session session = HibernateUtil.getSessionFactory().openSession();
//          Transaction transaction = null;

//          try {
//              transaction = session.beginTransaction();
//              Query query = session.getNamedQuery(nameQuery);
//              list = (List) query.list();

//              for (Object element : list) {
//                  obList.add(element);
//              }
//          } catch (HibernateException e) {
//              System.out.println(e.getMessage());
//              if (transaction != null) {
//                  transaction.rollback();
//              }
//          } finally {
//              session.close();
//          }
//          return obList;
//      }

//      public static ObservableList select(String nameQuery, HashMap properties) {

//          ObservableList obList = FXCollections.observableArrayList();
//          List list = null;
//          Session session = HibernateUtil.getSessionFactory().openSession();
//          Transaction transaction = null;

//          try {

//              transaction = session.beginTransaction();
//              Query query = session.getNamedQuery(nameQuery).setProperties(properties);

//              list = (List) query.list();
//              for (Object element : list) {
//                  obList.add(element);
//              }

//          } catch (HibernateException e) {
//              System.out.println(e.getMessage());

//              if (transaction != null) {
//                  transaction.rollback();
//              }
//          } finally {
//              session.close();
//          }
//          return obList;
//      }

//      public static String update(Object object) {

//          Session session = HibernateUtil.getSessionFactory().openSession();
//          Transaction transaction = null;
//          String msg = "0";

//          try {
//              transaction = session.beginTransaction();
//              session.update(object);
//              transaction.commit();
//              if (session != null) {
//                  msg = "1";
//              }
//          } catch (HibernateException e) {
//              System.out.println(e.getMessage());
//              if (transaction != null) {
//                  transaction.rollback();
//                  msg = "Database Error";
//              }
//          }
//          return msg;
//      }

//      public static String delete(Object object) {
//          Session session = HibernateUtil.getSessionFactory().openSession();
//          Transaction transaction = null;
//          String msg = "0";

//          try {
//              transaction = session.beginTransaction();
//              session.delete(object);
//              if (session != null) {
//                  msg = "1";
//              }
//              transaction.commit();

//          } catch (HibernateException e) {
//              System.out.println(e.getMessage());
//              if (transaction != null) {
//                  transaction.rollback();
//                  msg = "Database Error";
//              }
//          }
//          return msg;
//      }

//  }
