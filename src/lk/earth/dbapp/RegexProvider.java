package lk.earth.dbapp;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;

public class RegexProvider{

    public static Hashtable<String,String> get(){

         Employee emp = new Employee();

            Hashtable<String,String> htRegexes = new Hashtable<String,String>();


                Field[] fs = emp.getClass().getFields();
                System.out.println(fs.length);

                for(Field f : fs){

                    Patern a = f.getAnnotation(Patern.class);
                    System.out.println(f);
                    if(a != null){
                        // htRegexes.put(f.getName().substring(3).toLowerCase(), a.regexp());
                        htRegexes.put(f.getName(), a.regexp());
                    }

                }
             
                return htRegexes;
            }

        }

    


