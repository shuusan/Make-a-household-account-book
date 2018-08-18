package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Main_Japanene
 */
@WebServlet("/Main_Japanene")
public class Main_Japanene extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main_Japanene() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String move = request.getParameter("move");
		int month = (int)session.getAttribute("month");
		int year = (int)session.getAttribute("year");

		if(!("month".equals(move))){

			if("plus".equals(move)){
				year=(month==12)?(year+1):year;
				month=(month==12)?1:(month+1);
				session.setAttribute("month", month);
				session.setAttribute("year", year);
			}else{
				year=(month==1)?(year-1):year;
				month=(month==1)?12:(month-1);
				session.setAttribute("month", month);
				session.setAttribute("year", year);
			}
		}
		String view="/WEB-INF/j_view/j_month.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String re=request.getParameter("re");
		String content=request.getParameter("content");
		String cost=request.getParameter("cost");
		int cost2=Integer.parseInt(cost);
		String day=request.getParameter("day");

		String[] calender = day.split("-");
		int year = Integer.parseInt(calender[0]);
		int month = Integer.parseInt(calender[1]);

		dao.InsertDAO.table(re,content,cost2,day,year,month);
		String view="/WEB-INF/j_view/j_month.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
