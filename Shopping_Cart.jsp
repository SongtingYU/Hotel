<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.comp9321.assign2.*"%>
<%@page import="java.util.*, java.text.SimpleDateFormat"%>

<!DOCTYPE html >
<html>
<head>
<%@ include file="HeaderFile.jsp"%>
<script type="text/javascript">
	function confirm_alert() {
		if (confirm('Are you sure about this decision?')) {
			return true
		}
		return false
	}
</script>



<title>Shopping Cart</title>
</head>
<body>
	<%@ include file="Header.jsp"%>


	<%
		@SuppressWarnings("unchecked")
		ArrayList<Booking_Bean> booking_List = session.getAttribute("BOOKING_List") != null
				? (ArrayList<Booking_Bean>) session.getAttribute("BOOKING_List") : null;

		/* ArrayList<Booking_Bean> booking_List = (ArrayList<Booking_Bean>) request.getSession()
				.getAttribute("cartList"); */
		if (booking_List != null && booking_List.size() != 0) {

			double total_price = 0;
			java.text.DecimalFormat format_double = new java.text.DecimalFormat("#0.00");

			for (Booking_Bean booking_Node : booking_List) {

				double once_price = booking_Node.getBooking_confirm_price() + booking_Node.getExtra_bed() * 35;

				program_package date_cal = new program_package();
				int day = date_cal.daysBetween(booking_Node.getCheck_in_date(), booking_Node.getCheck_out_date());

				total_price += day * once_price;
			}
	%>

	<div class="back_grey text-center search_BAR_FOR_RES_fixed-top">
		<br /> <font size="10">My Order </font>
		<p>
			<font size="5">Total price: <span style="color: red">$</span>
				<%=format_double.format(total_price)%>
			</font>
	</div>
	<div class="table-location">
		<form class="form-horizontal" action="control" method="POST"
			onsubmit="return confirm_alert()">
			<div>

				<table class="table pagination-centered table-striped">
					<tr>
						<th><span style="color: red">Remove the room</span></th>
						<th>Room Preview</th>
						<th>Hotel name</th>
						<th>Room Type</th>
						<th>MAX Price</th>
						<th>City</th>
						<th>Address</th>
						<th>Preview</th>
						<th>Extra bed</th>
					</tr>
					<%
						for (int i = 0; i < booking_List.size(); i++) {
								Booking_Bean booking_Node = booking_List.get(i);
								Room_Bean room = Factory_DAO.GetRoomDAOInstance()
										.FindById(booking_List.get(i).getBooking_room_id());
								float price = booking_Node.getBooking_confirm_price();
								if (booking_Node.getExtra_bed() == 1) {
									price += 35;
								}
					%>
					<tr>
						<td><input type="checkbox" name="remove_room"
							value="<%=booking_Node.getBooking_room_id()%>" /></td>
						<td><img
							src=<%=room.getImg_url().contains("http") ? room.getImg_url()
							: request.getContextPath() + "/img/" + room.getImg_url()%>
							onerror="this.src='<%=request.getContextPath() + "/img/default_image.jpg"%>'"
							width="200" height="120"></td>
						<td><%=room.getHotel_name()%></td>
						<td><%=room.getRoom_type()%></td>
						<td>$ <%=price%></td>
						<td><%=room.getCity()%></td>
						<td><%=room.getAddress()%></td>
						<td><%=room.getPreview()%></td>
						<td>
							<%
								if (Factory_DAO.GetRoomDAOInstance().FindById(booking_Node.getBooking_room_id()).getRoom_type()
												.contains("single")) {
							%>-<%
								} else {
							%> <input type="checkbox" name="extra_bed"
							value=<%=booking_Node.getBooking_room_id()%>
							<%if (booking_Node.getExtra_bed() == 1) {%> checked="checked"
							<%}%> /> <%
 	}
 %>
						</td>
					</tr>
					<%
						}
					%>
				</table>
				<input
					class="button button-glow button-border button-highlight button-rounded button-pill submit_button_2_1"
					type="submit" name="action" value="  Update  " />
			</div>
		</form>
		<form action="Hello.jsp">
			<input
				class="button button-glow button-border button-highlight button-rounded button-pill submit_button_2_2"
				type="submit" value="Go Search" />
		</form>
		<form action="control" method="POST">
			<input
				class="button button-glow button-border button-highlight button-rounded button-pill submit_button_3_3"
				type="submit" name="action" value="Check out" />
		</form>
		<%
			} else {
		%><div align="center">
			<br /> <br /> <br /> <font size="10">My Order</font> <br /> <br />
			<br /> <br /> <font size="5"><span style="color: red"><b>Cart
						is Empty.</b></span></font>
		</div>
		<form action="Hello.jsp">
			<input
				class="button button-glow button-border button-highlight button-rounded button-pill submit_button_3_2"
				type="submit" value="Go Search" />
		</form>
		<%
			}
		%>


	</div>
</body>
</html>