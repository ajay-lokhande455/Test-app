package ServletClass;

import Dao.StudentDao;
import Model.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/addDetail")
public class ServletClass extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name = req.getParameter("name");
       String phone =  req.getParameter("phone");
       int age = Integer.parseInt( req.getParameter("age"));
       Student student = new Student();
       student.setName(name);
       student.setPhone(phone);
       student.setAge(age);

        StudentDao studentDao = new StudentDao();
        try {
            studentDao.addStudent(student);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("text/html");
        Writer out = resp.getWriter();
        out.write("<script>alert('registration successful');</script>");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("questions.html");
        requestDispatcher.include(req, resp);
    }

}
