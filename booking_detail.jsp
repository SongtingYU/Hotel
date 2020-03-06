<%@page import="com.comp9321.assign2.Factory_DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.*" import="hotel.*" import="java.util.*"
	import="util.*" import="com.comp9321.assign2.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Row> rows = (ArrayList<Row>) session.getAttribute("rows");
	int flag = (Integer) session.getAttribute("flag");
	String username = (String) session.getAttribute("username1");
	float price = 0;
%>
<head>
<%@ include file="Header.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Booking Management</title>
</head>
<body>
	<center>
		<br> <br> <br> <br> <br>
		<h2>Booking Management</h2>
		<%
			if (rows == null || rows.size() == 0) {
				String jpg_path = request.getContextPath();
				jpg_path += "/img/sad1.jpg";
		%>
		<br> <br>
		<div align="center">
			<br /> <img src=<%=jpg_path%> alt="" width="320" height="250"><br />
		</div>
		<h3>
			<font color="red">The booking is Empty!</font>
		</h3>

		<form action='Welcome.jsp' method='POST'>
			<input type="submit" value="Return" width="100">
		</form>
		<%
			} else {
		%>
		<br> <br>
		<form class="form-horizontal" action="control" method="POST">
			<div align="center">
				<table align="center" style="border-collapse: collapse;" border=3
					width="80%">
					<tr>
						<td><div align="center">
								<b>City</b>
							</div></td>
						<td><div align="center">
								<b>Room Type</b>
							</div></td>
						<td><div align="center">
								<b>Check in date</b>
							</div></td>
						<td><div align="center">
								<b>Check out date</b>
							</div></td>
						<td><div align="center">
								<b>Extra bed</b>
							</div></td>
						<td><div align="center">
								<b>Booking time</b>
							</div></td>
						<td><div align="center">
								<b>Price</b>
							</div></td>
					</tr>

					<%
						int i = 0;
							for (i = 0; i < rows.size(); i++) {
								Row publ = rows.get(i);
					%>

					<tr>
						<td><div align="center">
								<%=publ.getcity()%>
							</div></td>
						<td><div align="center">
								<%=publ.getroom_type()%>
							</div></td>
						<td><div align="center">
								<%=publ.getCheck_in_date()%>
							</div></td>
						<td><div align="center">
								<%=publ.getCheck_out_date()%>
							</div></td>

						<td><div align="center">
								<%
									if (publ.getExtra_bed() == 1) {
								%>
								Yes
								<%
									} else {
								%>
								No
								<%
									}
								%>
							</div></td>
						<td><div align="center">
								<%=publ.getBooking_time()%>
							</div></td>
						<td><div align="center">
								<%
									price = price + publ.getBooking_confirm_price();
								%>
								<%=publ.getBooking_confirm_price()%>
							</div></td>

					</tr>
					<%
						}
						}
					%>
				</table>
				<br>
				<%
					if (rows != null && rows.size() != 0) {
				%>
				<h4>
					Total Price : <font color="blue"><%=price%></font>
				</h4>
				<br>
				<%
					}
				%>
				<%
					if (flag == 1) {
						String jpg_path1 = request.getContextPath();
						jpg_path1 += "/img/alert.jpg";
				%><br>
				<div align="center">
					<br /> <img src=<%=jpg_path1%> alt="" width="100" height="70"><br />
				</div>
				<h3>
					<font color="red">This room is not available!</font>
				</h3>
				<%
					}
					if (rows != null && rows.size() != 0 && (flag == 0 || flag == 3)) {
				%>

				<input type="submit" name="action" value="Confirm" width="100" height="70">
				<%
					if (flag == 3) {
				%>
				<input type="submit" name="action" value="Cancel" width="100" height="70">

			</div>
			<%
				}
				}
			%>
			<br>

		</form>
		<table>
			<%
				if (rows != null && rows.size() != 0) {
					if (flag == 0 || flag == 1 || flag == 2) {
			%>

			<form action='control' method='POST'>
				<input type="hidden" name="action" value="add_room" width="100" height="70"> <input
					type="hidden" name="user_name" value=<%=username%>> <input
					type="submit" value="Add Room">
			</form>
			<%
				}
					if (flag == 1) {
			%>
			<form action='control' method='POST'>
				<input type="hidden" name="action" value="delete"> <input
					type="submit" value="Delete">
			</form>
			<%
				}
				}
			%>
		</table>
		<%
			String gif = request.getContextPath();
			gif += "/img/light1.gif";
		%><br><br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<img src=<%=gif%> alt="" width="1200" height="40">	
			<br>
			<br>
			<br>
			<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; 4TH Hotel 2016</p>
				</div>
			</div>
			</footer>
	</center>
</body>
</html>