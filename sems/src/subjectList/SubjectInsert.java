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

@WebServlet("/subject/insert.bit")
public class SubjectInsert extends HttpServlet{
	private static final long serialVersionUID = 1L;

	SubjectDao dao;
	SubjectVo subject;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{


		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		DBConnectionPool dbconnectionPool = new DBConnectionPool();
		dao = new MysqlSubjectDao();
		((MysqlSubjectDao)dao).setDBConnectionPool(dbconnectionPool);



		String title = request.getParameter("title");
		String description = request.getParameter("description");



		if(title != "null" && description != "null"){
		try {
			dao.insert(new SubjectVo().setTitle(title).setDescription(description));
		} catch (Throwable e) {
			e.printStackTrace();
		}
			out.println("등록성공");
		}else{
			out.println("등록실패");
		}

		



	}
}















