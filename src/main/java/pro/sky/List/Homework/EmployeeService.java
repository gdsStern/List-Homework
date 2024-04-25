package pro.sky.List.Homework;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private List<Employee> employeeList = new ArrayList<>(List.of(
       new Employee("Бычко", "Илья"),
       new Employee("Дулебова", "Инна"),
       new Employee("Бессергенев", "Филипп")
    ));
    private final int MAX_EMPLOYEES = 5;

    public String addEmployees(String lastName, String firstName)
            throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        if (employeeList.size() == MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        }
        if (employeeList.contains(new Employee(lastName,firstName))) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        employeeList.add(new Employee(lastName, firstName));
        return employeeList.get(employeeList.indexOf(new Employee(lastName, firstName))).toString();
    }

    public String removeEmployees(String lastName, String firstName) throws EmployeeNotFoundException {
        int i = employeeList.indexOf(new Employee(lastName, firstName));
        if (i >= 0) {
            String json = employeeList.get(employeeList.indexOf(new Employee(lastName, firstName))).toString();
            employeeList.remove(new Employee(lastName, firstName));
            return json;
        } else {
            throw new EmployeeNotFoundException("Такого сотрудника нет");
        }
    }

    public String findEmployees(String lastName, String firstName) throws EmployeeNotFoundException {
        if(employeeList.contains(new Employee(lastName, firstName))) {
            return employeeList.get(employeeList.indexOf(new Employee(lastName, firstName))).toString();
        } else {
            throw new EmployeeNotFoundException("Такого сотрудника нет");
        }
    }

    public String printAllEmployees() {
        String allText = "";
        for (int i = 0; i < employeeList.size(); i++) {
            allText = allText + employeeList.get(i).toString();
            if (i != employeeList.size() - 1) {
                allText = allText + " , ";
            }

        }
        return allText;
    }
}

