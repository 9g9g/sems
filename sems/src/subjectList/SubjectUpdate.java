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
import subject.SubjectVo;

@WebServlet("/subject/update.bit")
public class SubjectUpdate extends HttpServlet{
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
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		
		if(no > 0 && title != "null" && description != "null"){
		try {
			dao.update(new SubjectVo().setNo(no).setTitle(title).setDescription(description).setNo(no));
		} catch (Throwable e) {
			e.printStackTrace();
		}
			out.println("변경성공");
		}else{
			out.println("변경실패");
		}
		
		
	}
}
