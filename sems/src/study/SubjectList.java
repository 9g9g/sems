package subjectServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import subject.DBConnectionPool;
import subject.MysqlSubjectDao;
import subject.SubjectDao;
import subject.SubjectVo;

@WebServlet("/subject/list.bit")
public class SubjectList extends HttpServlet{
  private static final long serialVersionUID = 1L;
	SubjectDao dao;
	List<SubjectVo> list;
	
  
	
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {  
  	
  	response.setContentType("text/html;charset=UTF-8");
  	PrintWriter out = response.getWriter();  	

  	DBConnectionPool dbconnectionPool = new DBConnectionPool();
  	dao = new MysqlSubjectDao();
  	((MysqlSubjectDao)dao).setDBConnectionPool(dbconnectionPool);	 

  	
  	
  	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
  	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
  	  	

  	
  	
  	if(pageNo > 0){
    try {
    	list = dao.list(pageNo, pageSize);
    } catch (Throwable e) {
	    e.printStackTrace();
    }
    out.println("<html><body><h1>과목정보</h1><table border='1'>");  
    out.println("<tr><th>번호</th><th>과목명</th></tr>"); 
		for (SubjectVo subject : list) {
			out.println("<tr><td>" + subject.getNo() + "</td>");
			out.println("<td>" + subject.getTitle() + "</td></tr>");
		}
		out.println("</table></body></html>");
  	
  	}else{
  		out.println("0이상 입력해주세요");
  	}
		
  	
  
  	
  	
  	
  	
  	
  	
  
  	
  }
  	
   
}
