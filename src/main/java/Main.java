import entity.Address;
import entity.Employee;
import org.apache.commons.beanutils.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        Employee employee=new Employee();
        employee.setFirstName("FirstName");
        employee.setLastName("LastName");

        try {
            String firstName=(String)PropertyUtils.getSimpleProperty(employee,"firstName");
            String lastName=(String)PropertyUtils.getSimpleProperty(employee,"lastName");
            System.out.println(firstName+" "+lastName);

            PropertyUtils.setSimpleProperty(employee,"firstName","New"+firstName);
            PropertyUtils.setSimpleProperty(employee,"lastName","New"+lastName);

            firstName=(String)PropertyUtils.getSimpleProperty(employee,"firstName");
            lastName=(String)PropertyUtils.getSimpleProperty(employee,"lastName");
            System.out.println(firstName+" "+lastName);

            for(int i=0;i<3;i++){
                Address tempAddress=new Address("TempAddress"+i);

                Employee tempEmployee=new Employee();
                tempEmployee.setFirstName(String.valueOf(i));
                tempEmployee.setAddress("tempAddress",tempAddress);

                employee.setSubordinate(i,tempEmployee);
            }

            int index=0;
            String indexStr="subordinate["+index+"]";
            Employee subordinate=(Employee)PropertyUtils.getIndexedProperty(employee,indexStr);

            index++;
            Employee subordinate1=(Employee)PropertyUtils.getIndexedProperty(employee,"subordinate",index);

            Address homeAddress=new Address("HomeAddress");
            employee.setAddress("home",homeAddress);

            Address getAddress=(Address)PropertyUtils.getMappedProperty(employee,"address","home");

            Address newHomeAddress=new Address("NewHomeAddress");
            PropertyUtils.setMappedProperty(employee,"address","home",newHomeAddress);

            String addressStr=(String)PropertyUtils.getNestedProperty(employee,"address(home).addressStr");
            System.out.println(addressStr);

            String tempAddress=(String)PropertyUtils.getProperty(employee,"subordinate[0].address(tempAddress).addressStr");
            System.out.println(tempAddress);

            DynaProperty[] props=new DynaProperty[]{
                    new DynaProperty("address",java.util.Map.class),
                    new DynaProperty("subordinate",Employee.class),
                    new DynaProperty("firstName",String.class)
            };
            BasicDynaClass dynaClass=new BasicDynaClass("employee",null,props);

            DynaBean dynaBean=dynaClass.newInstance();
            dynaBean.set("address",new HashMap<>());
            dynaBean.set("subordinate",new Employee());
            dynaBean.set("firstName","DynaFistName");

            System.out.println(dynaBean.get("firstName"));

            DynaBean wrapDynaBean=new WrapDynaBean(employee);
            System.out.println(wrapDynaBean.get("firstName"));

            DynaBean lazyDynaBean=new LazyDynaBean();
            lazyDynaBean.set("age",11);
            lazyDynaBean.set("customer","firstName","CustomerFirstName");
            lazyDynaBean.set("customer","lastName","CustomerLastName");
            lazyDynaBean.set("address",0,"address0");
            lazyDynaBean.set("address",1,"address1");

            System.out.println(lazyDynaBean.get("age"));

            DynaBean lazyDynaMap=new LazyDynaMap();
            lazyDynaMap.set("age",11);
            lazyDynaMap.set("customer","firstName","CustomerFirstName");
            lazyDynaMap.set("address",0,"address0");

            Map resultMap=((LazyDynaMap) lazyDynaMap).getMap();
            System.out.println(resultMap);

            Map<String,String> dataMap=new HashMap<>();
            dataMap.put("firstName","FirstNameData");
            dataMap.put("lastName","LastNameData");
            dataMap.put("age","12");
            Employee newEmployee=new Employee();
            BeanUtils.populate(newEmployee,dataMap);

            System.out.println();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
