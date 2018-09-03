<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.NullPointerException"%>
<%@page import="dao.SelectDAO"%>
<%@page import="dto.SelectDTO"%>
<%@page import="process.csvCreate"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	try {
		String user = (String) session.getAttribute("user");
		int month = (int) session.getAttribute("month");
		int year = (int) session.getAttribute("year");
		ArrayList<SelectDTO> list = SelectDAO.table(user, month, year);
		session.setAttribute("table", list);
		int sum = (int) session.getAttribute("sum");
		int income = (int) session.getAttribute("income");
		int spending = (int) session.getAttribute("spending");
		String downlord = (String) session.getAttribute("downlord");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>家計簿 - MOTONOTE -</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<header>
		<div id="top">
			<a href="/MOTONOTE/Start">ログアウト</a>
		</div>
	</header>
	<div id="contents">
		<div id="control">
			<div id="cost">
				<p>
					今月収支：<%=sum%>円 今月収入：<%=income%>円 今月支出：<%=spending%>円
				</p>
			</div>
			<div id="download">
				<p><%=downlord%></p>
			</div>
		</div>

		<form action="/MOTONOTE/Main" method="post">
			<div id="add">
				<select id="reText1" name="re">
					<option value="0">収入</option>
					<option value="1">支出</option>
				</select>
				<input type="text" id="text1" placeholder="収支内容" name="content" required>
				<input type="number" class="text1" placeholder="収支金額(半角数字)" name="cost" required>
				<input type="date" class="text1" placeholder="日付(yyyy-MM-dd)" name="day" required>
				<button type="submit" id="addsubmit" name="submit" value="2">追加</button>
			</div>
		</form>

		<form action="/MOTONOTE/Main" method="get">
			<div id="month">
				<button type="submit" value="minus" id="left" name="move">◀</button>
				<button type="submit" value="month" id="monthButton" name="move"><%=month%>月</button>
				<button type="submit" value="plus" id="right" name="move">▶</button>
			</div>
		</form>

		<div id="table">
			<form action="/MOTONOTE/Main" method="post" name="form1">
				<button type="submit" id="up" value="1" name="submit">.csv</button>
				<button type="submit" id="delete" value="0" name="submit">チェックした項目を削除</button>
				<button type="submit" id="delete" value="3" name="submit">チェックした項目を更新</button>
				<table class="sticky_table">
					<tbody>
						<%
							for (int i = 0; i < list.size(); i++) {
									int re = list.get(i).getRe();
									String content = list.get(i).getContent();
									int cost = list.get(i).getCost();
									String id = re + "," + content + "," + cost;
						%>
						<tr>
							<td>
								<select id="reText2" name="re<%=i%>">
									<% if (0 == re) { %>
										<option value="0" selected>収入</option>
										<option value="1">支出</option>
									<% } else { %>
										<option value="0">収入</option>
										<option value="1" selected>支出</option>
									<% } %>
								</select>
							<td><input type="text" id="text2" placeholder="収支内容" value="<%=content%>" name="content<%=i%>" required></td>
							<td><input type="number" class="text2" placeholder="収支金額" value="<%=cost%>" name="cost<%=i%>" required></td>
							<td><input type="checkbox" id="checkbox" name="check<%=i%>" value="<%=id%>"></td>
						</tr>
						<% } %>
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
</body>
</html>
<% } catch (NullPointerException e) {
		System.out.println("main logout");
		response.sendRedirect("/MOTONOTE/Start");
	}
%>
