<%@page import="java.util.ArrayList"%>
<%@page import="dao.SelectDAO"%>
<%@page import="dto.SelectDTO"%>
<%@page import="process.csvCreate"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<%
	int month = (int) session.getAttribute("month");
	int year = (int) session.getAttribute("year");
	ArrayList<SelectDTO> array = SelectDAO.table(month, year);
	session.setAttribute("table", array);
	SelectDTO select = SelectDAO.cost(month, year);
	int sum = (int)session.getAttribute("sum");
	int income = (int)session.getAttribute("income");
	int spending = (int)session.getAttribute("spending");

%>
<head>
<meta charset="UTF-8">
<title>month</title>
<link rel="stylesheet" href="css/j_css.css">
</head>

<body>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<header>
		<div id="top">
			<a href="month.html">How to use it</a> <select name="language"
				id="language">
				<option value="../Japanese/month.html">日本語</option>
				<option value="../English/month.html">English</option>
			</select>
		</div>
	</header>
	<div id="contents">

		<div id="cost">
			<p>
				今月収支：<%=sum%>円　
				今月収入：<%=income%>円　
				今月支出：<%=spending%>円</p>
		</div>
		<div id="control">
			<select name="pulldown1" id="pulldown1">
				<option value="">並び替え</option>
				<option value="month.html">月</option>
				<option value="week.html">週</option>
			</select> <select name="pulldown2" id="pulldown2">
				<option value="month.html">生活費</option>
				<option value="test.html">食費</option>
			</select>

			<a href="javascript:form1.submit()"><button type="submit" id="delete">チェックした項目を削除</button></a>

			<form action="/MOTONOTE/Main_Japanene" method="post">
				<button type="submit" id="up" value="0">.csv　</button>
			</form>
		</div>

		<form action="/MOTONOTE/Main_Japanene" method="post">
			<div id="add">
				<select id="reText1" name="re">
					<option value="収入">収入</option>
					<option value="支出">支出</option>
				</select> <input type="text" id="text1" placeholder="収支内容" name="content" required>
				<input type="text" class="text1" placeholder="収支金額" name="cost" required>
				<input type="text" class="text1" placeholder="日付" name="day"
					pattern="\d{4}-\d{1,2}-\d{1,2}" required> <button type="submit"
					id="addsubmit" name="submit" value="1">追加</button>
			</div>
		</form>

		<form action="/MOTONOTE/Main_Japanene" method="get">
			<div id="month">
				<button type="submit" value="minus" id="left" name="move">◀</button>
				<button type="submit" value="month" id="monthButton" name="move"><%=month%>月
				</button>
				<button type="submit" value="plus" id="right" name="move">▶</button>
			</div>
		</form>

		<div id="table">
			<form action="/MOTONOTE/Main_Japanene" method="post" name="form1">
				<table class="sticky_table">
					<tbody>
						<%
							for (int i = 0; i < array.size(); i++) {
								int re = array.get(i).getRe();
								String content = array.get(i).getContent();
								int cost = array.get(i).getCost();
								String id = re + "," + content + "," + cost;
						%>
						<tr>
							<td><select id="reText2" name="re">
									<%
										if (0 == re) {
									%>
									<option value="収入" selected>収入</option>
									<option value="支出">支出</option>
									<%
										} else {
									%>
									<option value="収入">収入</option>
									<option value="支出" selected>支出</option>
									<%
										}
									%>
							</select>
							<td><input type="text" id="text2" placeholder="収支内容"
								value="<%=content%>"></td>
							<td><input type="text" class="text2" placeholder="収支金額"
								value="<%=cost%>"></td>
							<td><input type="checkbox" id="checkbox" name="<%=i%>"
								value="<%=id%>"></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<footer>
		<div id="copylight">
			<small>© 2018 MOTONOTE</small>
		</div>
	</footer>
	<script type="text/javascript" src="../js/pulldown.js"></script>
</body>

</html>
