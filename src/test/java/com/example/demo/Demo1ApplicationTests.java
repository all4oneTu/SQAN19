package com.example.demo;

import com.example.demo.Controller.TeacherController;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.ui.ConcurrentModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

@SpringBootTest
class Demo1ApplicationTests {

    private final TeacherController teacherController;

    @Autowired
    Demo1ApplicationTests(TeacherController teacherController) {


        this.teacherController = teacherController;
    }

    private Connection getConnection() {
        String usernameDb = "root";
        String passwordDb = "1234";
        String serverName = "localhost";
        int portNumber = 3306;
        String dbName = "teacher";
        Connection conn = null;
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", usernameDb);
            connectionProps.put("password", passwordDb);
            conn = DriverManager
                    .getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public boolean executeCmd(Connection conn, String command) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(command); // This will throw a SQLException if it fails
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("________________Start testing________________");
    }

    @AfterAll
    public static void affterAll() {
        System.out.println("________________End testing________________");
    }

//    @Test
//    void testRegister() {
//        String expectResult = "redirect:/login";
//        String username;
//        String password;
//        Assertions.assertEquals(expectResult, teacherController.register(username, password));
//    }

    @Test
    @Rollback
    @Transactional
    void testRegisterHasUsernameAndPassword() {
        String expectResult;
        String username;
        String password;

        //username's length smaller than 3 and password in true format
        username = "ab";
        password = "12345678";
        expectResult = "username is not reach required length";
        Assertions.assertEquals(expectResult, teacherController.register(username, password));
    }

    @Test
    @Rollback
    @Transactional
    void testRegisterHasUsernameAndPassword_2() {
        String expectResult;
        String username;
        String password;
        //username's length bigger than 3 and smaller than 30 and password in true format
        username = "abdffa";
        password = "12345678";
        expectResult = "redirect:/login";
        Assertions.assertEquals(expectResult, teacherController.register(username, password));
    }

    @Test
    @Rollback
    @Transactional
    void testRegisterHasUsernameAndPassword_3() {
        String expectResult;
        String username;
        String password;
        //username's length bigger  than 30 and password in true format
        username = "dsadadasdasdsadadasdsadasdasddasad";
        password = "12345678";
        expectResult = "username is not reach required length";
        Assertions.assertEquals(expectResult, teacherController.register(username, password));
    }

    @Test
    @Rollback
    @Transactional
    void testRegisterHasUsernameAndPassword_4() {
        String expectResult;
        String username;
        String password;
        //username's null and password in true format
        username = null;
        password = "12345678";
        expectResult = "username is null";
        Assertions.assertEquals(expectResult, teacherController.register(username, password));
    }

    @Test
    @Rollback
    @Transactional
    void testRegisterHasUsernameAndPassword_5() {
        String expectResult;
        String username;
        String password;
        //username has special character
        username = "null&^%$";
        password = "12345678";
        expectResult = "redirect:/login";
        Assertions.assertEquals(expectResult, teacherController.register(username, password));

    }

    @Test
    @Rollback
    @Transactional
    void testRegisterHasUsernameAndPassword_6() {
        String expectResult;
        String username;
        String password;
        //password's length is null
        username = "dominhduc12022001@gmail.com";
        password = null;
        expectResult = "password is null";
        Assertions.assertEquals(expectResult, teacherController.register(username, password));
    }

    @Test
    @Rollback
    @Transactional
    void testRegisterHasUsernameAndPassword_7() {
        String expectResult;
        String username;
        String password;
        //password's length is bigger 10
        username = "dominhduc12022001@gmail.com";
        password = "1234567891000";
        expectResult = "password not reach required length";
        Assertions.assertEquals(expectResult, teacherController.register(username, password));

    }

    @Test
    @Rollback
    @Transactional
    void testRegisterHasUsernameAndPassword_8() {
        String expectResult;
        String username;
        String password;

        //password's length is bigger than 5 and smaller than 10
        username = "dominhduc12022001@gmail.com";
        password = "12345678";
        expectResult = "redirect:/login";
        Assertions.assertEquals(expectResult, teacherController.register(username, password));

    }

    @Test
    @Rollback
    @Transactional
    void testRegisterHasUsernameAndPassword_9() {
        String expectResult;
        String username;
        String password;

        //password's length is smaller than 6
        username = "dominhduc12022001@gmail.com";
        password = "12345";
        expectResult = "password not reach required length";
        Assertions.assertEquals(expectResult, teacherController.register(username, password));

    }

    @Test
    @Rollback
    @Transactional
    void testRegisterHasUsernameAndPassword_10() {
        String expectResult;
        String username;
        String password;
        //username is not existed
        username = "adasdda@gmail.com";
        password = "12345678";
        expectResult = "redirect:/login";
        String actualResult = teacherController.register(username, password);
        if (!isInserted(username)) {
            Assertions.assertEquals(expectResult, actualResult);
        } else {
            Assertions.fail();
        }
    }

    @Test
    @Rollback
    @Transactional
    void testRegisterHasUsernameAndPassword_11() {
        String expectResult;
        String username;
        String password;

        //username is existed
        username = "dominhduc12022001@gmail.com";
        password = "12345678";
        expectResult = "username is already exist";
        Assertions.assertEquals(expectResult, teacherController.register(username, password));
    }

    boolean isInserted(String username) {
        String cmd = "SELECT * FROM teacher.teacher WHERE teacher.teacher.username = " + "'" + username + "'";
        Connection connection = getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(cmd);
                while (resultSet.next()) {
                    String usernameSaved = resultSet.getString("username");
                    if (username.equals(usernameSaved)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }



    // test login
    @Test
    void testLogin_InvalidPassword() {
        // Arrange
        String username = "test";
        String password = "12234565";
        String expected = "Invalid username or password";
        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testLogin_RequiredLength() {
        // Arrange
        String username = "te";
        String password = "12345678";
        String expected = "username is not reach required length";
        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testLogin_RequiredLength2() {
        // Arrange
        String username = "12345678901234567890123456789";
        String password = "12345678";
        String expected = "redirect:/home";

        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testLogin_RequiredLength3(){
        // Arrange
        String username = "123456789012345678901234567890";
        String password = "12345678";
        String expected = "redirect:/home";

        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testLogin_RequiredLength4(){
        // Arrange
        String username = "1234567890123456789012345678901";
        String password = "12345678";
        String expected = "redirect:/home";

        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testLogin_RequiredLength5(){
        // Arrange
        String username= null;
        String password = "12345678";
        String expected = "redirect:/home";

        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testLogin_RequiredLength6(){
        String username ="null&^%$";
        String password= "12345678";
        String expected = "redirect:/home";
        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testLogin_RequiredLength7(){
        String username =" 123456";
        String password= "12345678";
        String expected = "redirect:/home";
        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testLogin_RequiredLength8(){
        // Arrange
        String username = "test";
        String password = "1234";
        String expected = "redirect:/home";

        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testLogin_RequiredLength9(){
        // Arrange
        String username = "test";
        String password = "123456789";
        String expected = "redirect:/home";

        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testLogin_RequiredLength10(){
        // Arrange
        String username = "test";
        String password = "1234567890";
        String expected = "redirect:/home";

        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testLogin_RequiredLength_11(){
        // Arrange
        String username = "test";
        String password = "1234567890";
        String expected = "redirect:/home";

        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testLogin_RequiredLength12(){
        // Arrange
        String username = "test";
        String password = "12345678901";
        String expected = "password not reach requirement length";

        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testLogin_RequiredLength13(){
        // Arrange
        String username = "test";
        String password = "";
        String expected = "invalid username or password";

        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testLogin_RequiredLength14(){
        // Arrange
        String username = "test";
        String password = "@!dfsfsdf";
        String expected = "redirect:/home";

        ConcurrentModel model = new ConcurrentModel();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        String actual = teacherController.welcomepage(model, username, password, request);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    // schedule registry

    @Rollback
    @Transactional
    @Test
    void testScheduleRegistry_01() {

    }
}
