package com.application.springRest;

import com.application.springConfig.MailSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@ComponentScan("com.application")
//@RestController
public class StudentController {

    /*@GetMapping(path = "/student")
    public String studentString() {
        return "Krutika";
    }*/

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    /*@GetMapping(path="/student/{name}")
    public Student studentPathVariable(@PathVariable String name)
    {
        return new Student(String.format("%s", name)); //%s replace the name
    }*/

    @GetMapping(path = "/student-bean")
    public Student studentBean() {
        return new Student(12, "Krutika", "19-06-20");
    }

    @GetMapping("/students")
    public List<Student> retriveAllStudents() {
        return service.findAll();
    }

    @GetMapping("/student/{id}")
    public Student retriveStudent(@PathVariable int id) {
        Optional<Student> student = service.findOne(id);
        if (student.isEmpty()) {
            throw new StudentNotFoundException("id:" + id);
        }
        return student.get();
    }

    @PostMapping("/student")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        Student savedStudent = service.save(student);
        // takes the current path, appends id that we got from the object
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.getId()).toUri();
        // this sets the status as 201 - created
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable int id) {
        Optional<Student> deletedStudent = service.deleteById(id);
        if (deletedStudent.isEmpty()) {
            throw new StudentNotFoundException("id: " + id);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("student/{id}")
    public void updateById(@PathVariable int id, @RequestBody Student student) {
        Optional<Student> updatedStudent = service.updateById(id, student);
        if (updatedStudent.isEmpty()) {
            throw new StudentNotFoundException("id: " + id);
        }
    }

    // getting value from properties file
  /*  @Value("${server.port}")
    private String serverPort;

    @Value("${url}")
    private String url;*/

    @Value("${port.url}")
    private String portUrl;

    @GetMapping("/properties")
    public String getProperty() {
        return portUrl;
    }

    // how to add a default value ?
    @Value("${random:default value}")
    private String random;

    @GetMapping("/defaultProperties")
    public String getDefault() {
        return random;
    }

    // how to access a list ?
    @Value("${list.of.numbers}")
    private List<Integer> listOfValues;

    @GetMapping("/listProperties")
    public List<Integer> getList() {
        return listOfValues;
    }

    // in yml we can use nested map only through ConfigurationProperties
    // # treats the elements inside {} as a spring expression language
    /*@Value("#{${dbValues}}")
    private Map<String,String> databaseValues;

    @GetMapping("/mapProperties")
    public Map<String,String> getMap(){
        return databaseValues;
    }

    // access 1st element from a mao
    @Value("#{${dbValues}.connectionString}")
    private String connectionString;

    @GetMapping("/keyMapProperties")
    public String getConnectionString(){
        return connectionString;
    }*/

    @Autowired
    private MailSettings mailSettings;

    @GetMapping("/configurationProperties")
    public String getMailSerrings() {
        return mailSettings.getHost() + " " + mailSettings.getPort() + " " + mailSettings.getFrom() + " " +
                mailSettings.getDefaultRecipients() + " " + mailSettings.getAdditionalHeaders() + " " +
                mailSettings.getCredentials();
    }

    @Value("${spring.messages}")
    private String springMessage;

    @GetMapping("/springProfile")
    public String getSpringMessage() {
        return springMessage;
    }

}
