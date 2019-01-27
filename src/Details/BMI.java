package Details;
import DB_CONNECTION.util;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class BMI
 */
public class BMI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BMI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw=response.getWriter();
		  response.setContentType("text/html");
		  String name=request.getParameter("name");
		  int weight=Integer.parseInt(request.getParameter("weight"));
		  float height=Float.parseFloat(request.getParameter("height"));
		  float height1=height/100;
		  float bmi;
		  String msg;
		  String message;
		  try
		  {
			  bmi=(float)(weight/height1);
			  bmi=(bmi/height1);
			  if(bmi<18.5)
			  {
				  msg="underweight";
			  }
			  else if(bmi>=18.5 && bmi<=24.9)
			  {
				  msg="normalweight";
			  }
			  else if(bmi>=25 &&bmi<=29.9)
			  {
				  msg="overweight";
			  }
			  else if(bmi>=30 &&bmi<=34.9)
			  {
				  msg="class I obese";
			  }
			  else if(bmi>=35 && bmi<=39.9)
			  {
				  msg="class II obese";
			  }
			  else
			  {
				  msg="class III obese";
			  }
			  message="A BMI of "+bmi+" indicates that you are at a "+msg;
			  Connection con=util.getDBConnection();
			  PreparedStatement pi1=con.prepareStatement("insert into BMI_DETAILS values(?,?,?,?)");
			  pi1.setString(1,name);
			  pi1.setInt(2,weight);
			  pi1.setFloat(3,height);
			  pi1.setFloat(4,bmi);
			  pi1.executeUpdate();
			  pw.println("<center>");
			  pw.println("<table border=1>");
			  pw.println("<tr><td><h2>"+message+"</h2></td></tr>");
			  pw.println("</table>");
			  pw.println("<center>");
			  	  }
		  catch (Exception e)
		   {  e.printStackTrace();   
		   }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
