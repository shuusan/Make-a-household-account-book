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
import dao.UpdateDAO;
import dto.SelectDTO;
import process.csvCreate;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		session.setAttribute("downlord", "　");

		//日時の取得
		String move = request.getParameter("move");
		int month = (int)session.getAttribute("month");
		int year = (int)session.getAttribute("year");
		String user = (String)session.getAttribute("user");


		//月の変更<month>
		if(!("month".equals(move))){

			if("plus".equals(move)){
				//次月への移動
				session.setAttribute("month", (month==12)?1:(month+1));
				session.setAttribute("year", (month==12)?(year+1):year);

			}else{
				//前月への移動
				session.setAttribute("month", (month==1)?12:(month-1));
				session.setAttribute("year", (month==1)?(year-1):year);
			}

			//収支・収入・支出の計算
			SelectDTO select = SelectDAO.cost(user,month, year);
			session.setAttribute("sum", (select != null)?select.getSum():0);
			session.setAttribute("income", (select != null)?select.getIncome():0);
			session.setAttribute("spending", (select != null)?select.getSpending():0);
		}

		//メインフレームへ
		String view="/WEB-INF/view/main.jsp";
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

		//パラメーターの取得
		String user = (String)session.getAttribute("user");
		String submit = (String)request.getParameter("submit");
		int sb = Integer.parseInt(submit);
		//日時の取得
		int month = (int)session.getAttribute("month");
		int year = (int)session.getAttribute("year");
		SelectDTO select;


		/*-----------実行SQL文の区分け　ここから-----------*/
		switch(sb){


		/**-----データの削除-----**/
		case 0:
			session.setAttribute("downlord", "削除完了！");

			//jspの一覧テーブルlist
			ArrayList<SelectDTO> list = (ArrayList<SelectDTO>)session.getAttribute("table");
			//削除する項目一覧list
			ArrayList<String> deleteList = new ArrayList<String>();
			for(int i=0;i<list.size();i++){
				//チェックボックスのパラメーター取得
				String a = request.getParameter("check"+String.valueOf(i));

				if(a!=null){
					deleteList.add(a);
				}

			}
			if(deleteList!=null){
				//削除SQL
				DeleteDAO.delete(user,deleteList);
			}
			break;


			/**-----データのcsvダウンロード-----**/
		case 1:
			session.setAttribute("downlord", "ダウンロード完了！");

			//収支・収入・支出の取得
			int sum, income, spending;
			sum = (int)session.getAttribute("sum");
			income = (int)session.getAttribute("income");
			spending = (int)session.getAttribute("spending");
			//jspの一覧テーブルlist
			ArrayList<SelectDTO> array = (ArrayList<SelectDTO>)session.getAttribute("table");
			//.csv出力SQL
			csvCreate.exportCsv(month, sum, income, spending, array);
			break;

			/**-----データの追加-----**/
		case 2:
			session.setAttribute("downlord", "追加完了！");

			//追加内容のパラメーター取得
			String re=request.getParameter("re");
			String content=request.getParameter("content");
			String cost=request.getParameter("cost");
			int cost2=Integer.parseInt(cost);
			String day=request.getParameter("day");
			//日時の取得
			String[] calender = day.split("-");
			year = Integer.parseInt(calender[0]);
			month = Integer.parseInt(calender[1]);
			//追加SQL
			dao.InsertDAO.table(user,re,content,cost2,day,year,month);
			break;

			/**-----データの更新-----**/
		case 3:
			session.setAttribute("downlord", "更新完了！");

			//jspの一覧テーブルlist
			ArrayList<SelectDTO> list2 = (ArrayList<SelectDTO>)session.getAttribute("table");
			//更新する項目の一覧テーブルlist
			ArrayList<String> updateList = new ArrayList<String>();
			for(int i=0;i<list2.size();i++){
				//チェックボックスのパラメーター取得
				String a = request.getParameter("check"+String.valueOf(i));
				if(a!=null){
					//更新内容のパラメーター取得
					String re2=request.getParameter("re"+String.valueOf(i));
					String content2=request.getParameter("content"+String.valueOf(i));
					String cost3=request.getParameter("cost"+String.valueOf(i));
					String id = re2 + "," + content2 + "," + cost3;
					//取得した値をlistに追加
					updateList.add(a);
					updateList.add(id);
				}
			}
			if(updateList!=null){
				//更新SQL
				UpdateDAO.update(user,updateList);
			}
			break;
		}
		/*-----ここまで-----*/


		//収支・収入・支出の取得SQL
		select = SelectDAO.cost(user,month, year);
		session.setAttribute("sum", (select != null)?select.getSum():0);
		session.setAttribute("income", (select != null)?select.getIncome():0);
		session.setAttribute("spending", (select != null)?select.getSpending():0);

		//メインフレームへ
		String view="/WEB-INF/view/main.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
