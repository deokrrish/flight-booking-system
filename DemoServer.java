import javax.servlet.http.*;  
import javax.servlet.*;  
import java.io.*; 
import java.sql.*;
import java.lang.*;
public class DemoServer extends HttpServlet{ 
public void doGet(HttpServletRequest req,HttpServletResponse res)
throws ServletException,IOException  
{  
res.setContentType("text/html"); 
PrintWriter pw=res.getWriter();
        try {
           
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
	    Statement selectStmt = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbs","root", "");
            pw.println("Database is connected !");

	    String un = req.getParameter("n1");
	    String paw = req.getParameter("n2");
            selectStmt = conn.createStatement();
            ResultSet rs = selectStmt.executeQuery("SELECT * from userlogin");
            while(rs.next())
            {
                String s1=rs.getString(1);

                String s2=rs.getString(2); 
    
	    if(s1.equals(un) && s2.equals(paw))
		{

		     pw.println("LOGIN SUCCESS");
}
	    	}

            conn.close();
        }
        catch(Exception e) {
            pw.println("<h1>error</h1>");
        }
req.getRequestDispatcher("dash.html").include(req,res);

	pw.close();
    }
}