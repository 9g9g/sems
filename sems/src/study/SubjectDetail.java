package subjectServlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import subject.DBConnectionPool;
import subject.MysqlSubjectDao;
import subject.SubjectDao;
import subject.SubjectVo;

@WebServlet("/subject/detail.bit")
public class SubjectDetail extends HttpServlet{
  private static final long serialVersionUID = 1L;
  SubjectDao dao;
  SubjectVo subject;
  
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  	
  	response.setContentType("text/html;charset=UTF-8");
  	PrintWriter out = response.getWriter();
  	
  	DBConnectionPool dbconnectionPool = new DBConnectionPool();
  	dao = new MysqlSubjectDao();
  	((MysqlSubjectDao)dao).setDBConnectionPool(dbconnectionPool);

  	
  	
  	int no = Integer.parseInt(request.getParameter("no"));
 	
  	
  	
  	
  	try {
	    subject = dao.detail(no);
    } catch (Throwable e) {
	    e.printStackTrace();
    }
  	
  	
  	
  
  	out.println("<html><body><table border='1'>");
  	out.println("<tr><th>과목상세정보</th></tr>");
  	out.println("<tr><td>번호</td><td>" + subject.getNo() + "</td></tr>");
  	out.println("<tr><td>과목명</td><td>" + subject.getTitle() + "</td></tr>");
  	out.println("<tr><td>설명</td><td><input type='textarea'>" + subject.getDescription() + "</td></tr>");
  	out.println("</table></body></html>");
   }
}
