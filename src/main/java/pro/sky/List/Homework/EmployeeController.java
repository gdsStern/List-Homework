package pro.sky.List.Homework;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/employee")
@RestController
public class EmployeeController {
    private final EmployeeService serviceEmployee;

    public EmployeeController(EmployeeService serviceEmployee) {
        this.serviceEmployee = serviceEmployee;
    }
    @GetMapping("/add")
    public String add(@RequestParam(value = "lastName", required = false) String lastName,
                    @RequestParam(value = "firstName", required = false) String firstName) {
        if (lastName == null || firstName == null || lastName.isEmpty() || firstName.isEmpty()) {
            return "неверно введены параметры";
        }
        try {
            return serviceEmployee.addEmployees(lastName, firstName);
        } catch (EmployeeStorageIsFullException | EmployeeAlreadyAddedException e) {
            return e.getMessage();
        }
    }
    @GetMapping("/find")
    public String find(@RequestParam(value = "lastName", required = false) String lastName,
                      @RequestParam(value = "firstName", required = false) String firstName) {
        if (lastName == null || firstName == null || lastName.isEmpty() || firstName.isEmpty()) {
            return "неверно введены параметры";
        }
        try {
            return serviceEmployee.findEmployees(lastName,firstName);
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }

    }
    @GetMapping("/remove")
    public String remove(@RequestParam(value = "lastName", required = false) String lastName,
                      @RequestParam(value = "firstName", required = false) String firstName) {
        if (lastName == null || firstName == null || lastName.isEmpty() || firstName.isEmpty()) {
            return "неверно введены параметры";
        }
        try {
            return serviceEmployee.removeEmployees(lastName,firstName);
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }

    }

    @GetMapping("/print")
    public String print() {
        return serviceEmployee.printAllEmployees();
    }
}
