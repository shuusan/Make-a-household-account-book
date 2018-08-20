package servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SelectDAO;
import dto.SelectDTO;

/**
 * Servlet implementation class start
 */
@WebServlet("/Start")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Calendar a = Calendar.getInstance();
		int month = a.get(Calendar.MONTH);
		int year = a.get(Calendar.YEAR);
		session.setAttribute("month", month+1);
		session.setAttribute("year", year);
		session.setAttribute("downlord", "　");
		SelectDTO select = SelectDAO.cost(month+1, year);
		if (select == null) {
			session.setAttribute("sum", 0);
			session.setAttribute("income", 0);
			session.setAttribute("spending", 0);
		} else {
			session.setAttribute("sum", select.getSum());
			session.setAttribute("income", select.getIncome());
			session.setAttribute("spending", select.getSpending());
		}
		String view="/WEB-INF/view/main.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
