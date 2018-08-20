package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DeleteDAO;
import dao.SelectDAO;
import dto.SelectDTO;
import process.csvCreate;

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
		//月の変更<month>
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String move = request.getParameter("move");
		int month = (int)session.getAttribute("month");
		int year = (int)session.getAttribute("year");
		session.setAttribute("downlord", "　");

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
		SelectDTO select = SelectDAO.cost(month, year);
		if (select == null) {
			session.setAttribute("sum", 0);
			session.setAttribute("income", 0);
			session.setAttribute("spending", 0);
		} else {
			session.setAttribute("sum", select.getSum());
			session.setAttribute("income", select.getIncome());
			session.setAttribute("spending", select.getSpending());
		}
		String view="/WEB-INF/j_view/j_month.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String submit = (null!=(String)request.getParameter("submit")) ? (String)request.getParameter("submit"):"0";
		int sb = Integer.parseInt(submit);
		System.out.println(sb);

		switch(sb){
		case 0:
			session.setAttribute("downlord", "　");
			ArrayList<SelectDTO> list = (ArrayList<SelectDTO>)session.getAttribute("table");
			ArrayList<String> deleteList = new ArrayList<String>();
			for(int i=0;i<list.size();i++){
				String a = request.getParameter(String.valueOf(i));
				if(a!=null){
					deleteList.add(a);
				}
			}
			if(deleteList!=null){
				DeleteDAO.delete(deleteList);
			}
			break;

		case 1:
			session.setAttribute("downlord", "csvファイルをダウンロードしました。");
			int month,year,sum, income, spending;
			month = (int)session.getAttribute("month");
			year = (int)session.getAttribute("month");
			sum = (int)session.getAttribute("sum");
			income = (int)session.getAttribute("income");
			spending = (int)session.getAttribute("spending");
			ArrayList<SelectDTO> array = (ArrayList<SelectDTO>)session.getAttribute("table");
			csvCreate.exportCsv(month, sum, income, spending, array);
			break;

		case 2:
			session.setAttribute("downlord", "　");
			String re=request.getParameter("re");
			String content=request.getParameter("content");
			String cost=request.getParameter("cost");
			int cost2=Integer.parseInt(cost);
			String day=request.getParameter("day");
			String[] calender = day.split("-");
			year = Integer.parseInt(calender[0]);
			month = Integer.parseInt(calender[1]);
			dao.InsertDAO.table(re,content,cost2,day,year,month);
			break;
		}
		String view="/WEB-INF/j_view/j_month.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
