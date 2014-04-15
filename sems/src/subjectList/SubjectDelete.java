package subjectList;

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

@WebServlet("/subject/delete.bit")
public class SubjectDelete extends HttpServlet{
	private static final long serialVersionUID = 1L;
	SubjectDao dao;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		DBConnectionPool dbconnectionPool = new DBConnectionPool();
		dao = new MysqlSubjectDao();
		((MysqlSubjectDao)dao).setDBConnectionPool(dbconnectionPool);
		
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		
		if(no > 0){
		try {
			dao.delete(no);
		} catch (Throwable e) {
			e.printStackTrace();
		}
			out.println("삭제 성공");
		}else{
			out.println("삭제 실패");
		}
		
	}
}
