<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <title>week</title>
    <link rel="stylesheet" href="css/e_style.css">
</head>

<body>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <header>
        <div id="top">
            <a href="/Main_English"><u>How to use it</u></a>
            <select name="language" id="language">
                <option value="../English/month.html">English</option>
                <option value="../Japanese/month.html">日本語</option>
            </select>
        </div>
    </header>
    <div id="contents">

        <div id="control">
            <select name="pulldown1" id="pulldown1">
                <option value="">sort</option>
                <option value="month.html">month</option>
                <option value="week.html">week</option>
            </select>
            <select name="pulldown2" id="pulldown2">
                <option value="month.html">Cost of life</option>
                <option value="test.html">Cost of food</option>
            </select>
            <input type="submit" id="delete" value="delete checked items">
            <input type="submit" id="up" value=".csv  ">
        </div>

        <div id="add">
            <input type="search" list="re" id="reText1" name="re" placeholder="Double click" autocomplete="on" required>
            <datalist id="re">
					           <option value="revenue"></option>
					           <option value="expenditure"></option>
				            </datalist>
            <input type="text" id="text1" placeholder="content">
            <input type="text" class="text1" placeholder="money">
            <input type="text" class="text1" placeholder="day">
            <input type="submit" id="addsubmit" value="add">
        </div>

        <div id="month">
            <input type="button" value="◀" id="left">
            <input type="button" value="July week 1" id="monthButton">
            <input type="button" value="▶" id="right">
        </div>

        <div id="table">
            <table class="sticky_table">
                <tbody>
                    <tr>
                        <td>
                            <input type="search" list="re" id="reText2" name="re" placeholder="Double click" autocomplete="on" value="expenditure" required>
                            <datalist id="re">
					           <option value="revenue"></option>
					           <option value="expenditure"></option>
				            </datalist>
                        </td>
                        <td><input type="text" id="text2" placeholder="content" value="water"></td>
                        <td><input type="text" class="text2" placeholder="money" value="100"></td>
                        <td><input type="checkbox" id="checkbox"></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <footer>
        <div id="copylight">
            <small>© 2018 MOTONOTE</small>
        </div>
    </footer>
    <script type="text/javascript" src="js/e_pulldown.js"></script>
</body>

</html>
