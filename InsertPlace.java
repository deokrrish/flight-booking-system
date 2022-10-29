import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.*;
public class InsertPlace extends HttpServlet {  
    protected void doPost(HttpServletRequest req, HttpServletResponse res)  
                    throws ServletException, IOException {
        res.setContentType("text/html");  
        PrintWriter out=res.getWriter();  
          try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
	Statement selectStmt = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbs","root","");
            selectStmt = conn.createStatement();
		PreparedStatement st = conn.prepareStatement("insert into bookticket values(?,?,?,?,?,?,?)");
		st.setString(1,req.getParameter("Name"));
		st.setString(2,req.getParameter("MobileNumber"));
		st.setString(3,req.getParameter("Gender"));
		st.setString(4,req.getParameter("DestinationFrom"));
st.setString(5,req.getParameter("DestinationTo"));
st.setString(6,req.getParameter("FlightName"));
		st.setString(7,req.getParameter("Date"));
		int k = st.executeUpdate();
		if(k!=0)
		out.print("Inserted Successfully");
		st.close();
            conn.close();
        }
        catch(Exception e) {
            out.print("Do not connect to DB - Error:"+e);
        }
	req.getRequestDispatcher("dash.html").include(req, res);
 out.close();  
        }  
       
    }