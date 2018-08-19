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
	int sum,income,spending;
	if(select==null){
		sum=0;
		income=0;
		spending=0;
	}else{
		sum=select.getSum();
		income=select.getIncome();
		spending=select.getSpending();
	}
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
			<a href="month.html"><u>How to use it</u></a> <select name="language"
				id="language">
				<option value="../Japanese/month.html">日本語</option>
				<option value="../English/month.html">English</option>
			</select>
		</div>
	</header>
	<div id="contents">

		<div id="cost">
			<p> 今月収支：<%=sum%>　
				今月収入：<%=income%>　
				今月支出：<%=spending%></p>
		</div>
		<div id="control">
			<select name="pulldown1" id="pulldown1">
				<option value="">並び替え</option>
				<option value="month.html">月</option>
				<option value="week.html">週</option>
			</select> <select name="pulldown2" id="pulldown2">
				<option value="month.html">生活費</option>
				<option value="test.html">食費</option>
			</select> <input type="submit" id="delete" value="チェックした項目を削除"> <input
				type="submit" id="up" value=".csv  " onclick="<%csvCreate.exportCsv(month,sum,income,spending,array);%>">
		</div>

		<form action="/MOTONOTE/Main_Japanene" method="post">
			<div id="add">
				<input type="search" list="re" id="reText1" name="re"
					placeholder="ダブルクリック" autocomplete="on" required>
				<datalist id="re">
					<option value="収入"></option>
					<option value="支出"></option>
				</datalist>
				<input type="text" id="text1" placeholder="収支内容" name="content">
				<input type="text" class="text1" placeholder="収支金額" name="cost">
				<input type="text" class="text1" placeholder="日付" name="day">
				<input type="submit" id="addsubmit" value="追加">
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
			<table class="sticky_table">
				<tbody>
					<%
						for (int i = 0; i < array.size(); i++) {
							String re = (array.get(i).getRe() == 0) ? "収入" : "支出";
					%>
					<tr>
						<td><input type="search" list="re" id="reText2" name="re"
							placeholder="ダブルクリック" autocomplete="on" value="<%=re%>" required>
							<datalist id="re">
								<option value="収入"></option>
								<option value="支出"></option>
							</datalist></td>
						<td><input type="text" id="text2" placeholder="収支内容"
							value="<%=array.get(i).getContent()%>"></td>
						<td><input type="text" class="text2" placeholder="収支金額"
							value="<%=array.get(i).getCost()%>"></td>
						<td><input type="checkbox" id="checkbox" value="<%=i%>"></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
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
