<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.comp9321.assign2.*"%>
<%@page import="java.util.*"%>

<!DOCTYPE html >
<html>
<%
	int flag = 0;
	int flagBM = 0;
	try {
		flagBM = (Integer) session.getAttribute("flagBM");
	} catch(Throwable e){
		flagBM = 0;	
	}
%>
<head>
<%@ include file="HeaderFile.jsp"%>
<script type="text/javascript">
	function check_box(){
		var objs=document.getElementsByName('adds');
		var isSel=false;
		for(var i=0;i<objs.length;i++){
			if(objs[i].checked==true){
				isSel=true;
				break;
			}
		}
		
		if(isSel==false){
			alert("Please add a least one room!");
			return false;
		}
	
		return true;
	}
</script>
<title>Search Results</title>

</head>

<body>
	<%
		if (request.getAttribute("message") != null) {
	%>
	<script type="text/javascript">
		alert('<%=request.getAttribute("message")%>')
		window.location = "Index.jsp"
	</script>
	<%
		}
	%>
	<%@ include file="Header.jsp"%>
	<div class="back_grey text-center search_BAR_FOR_RES_fixed-top">
		<br /> <br /> <br />
		<%@ include file="SearchBar_ForResults.jsp"%>
	</div>

	<%
		@SuppressWarnings("unchecked")
		ArrayList<Integer> target_roomID_List = (ArrayList<Integer>) request.getAttribute("target_room");
		@SuppressWarnings("unchecked")
		ArrayList<Float> target_room_maxprice = (ArrayList<Float>) request.getAttribute("target_room_maxprice");
		@SuppressWarnings("unchecked")
		ArrayList<String> target_room_special_List = (ArrayList<String>) request
				.getAttribute("target_room_special_List");
		@SuppressWarnings("unchecked")
		ArrayList<String> target_room_peak_List = (ArrayList<String>) request.getAttribute("target_room_peak_List");

		if (target_roomID_List != null) {
	%>
	<div class="table-location">
		<form class="form-horizontal" action="control" method="POST"
			onsubmit="return check_box()">
			<div>

				<table class="table pagination-centered table-striped">
					<!-- <table style="border-collapse: collapse;" border=0 width="80%"> -->
					<tr>
						<th>Want List</th>
						<th>Room Preview</th>
						<th>Hotel name</th>
						<th>Room Type</th>
						<th>MAX Price</th>
						<th>City</th>
						<th>Address</th>
						<th>Preview</th>
						<th>Special info</th>
						<th>Peak info</th>
						<th>Extra bed</th>
					</tr>
					<%
						for (int i = 0; i < target_roomID_List.size(); i++) {
								Room_Bean room = Factory_DAO.GetRoomDAOInstance().FindById(target_roomID_List.get(i));
								float results_maxprice = target_room_maxprice.get(i);
								String special_time = target_room_special_List.get(i);
								String peak_time = target_room_peak_List.get(i);
					%>
					<tr>
						<td><input type="checkbox" name="adds"
							value="<%=target_roomID_List.get(i)%> - <%=results_maxprice%>" />
						</td>
						<td><img
							src=<%=room.getImg_url().contains("http")
							? room.getImg_url()
							: request.getContextPath() + "/img/" + room.getImg_url()%>
							onerror="this.src='<%=request.getContextPath() + "/img/default_image.jpg"%>'"
							width="200" height="120"></td>
						<td><%=room.getHotel_name()%></td>
						<td><%=room.getRoom_type()%></td>
						<td>$ <%=results_maxprice%></td>
						<td><%=room.getCity()%></td>
						<td><%=room.getAddress()%></td>
						<td><%=room.getPreview()%></td>
						<td><%=special_time%></td>
						<td><%=peak_time%></td>
						<td>
							<%
								if (room.getRoom_type().contains("single")) {
							%>-<%
								} else {
							%> <input type="checkbox" name="extra_bed"
							value="<%=target_roomID_List.get(i)%>" /> <%
 	}
 %>
						</td>
					</tr>
					<%
						}
					%>
				</table>
				<%
					if (flagBM == 1) {
				%>
				<input
					class="button button-glow button-border button-highlight button-rounded button-pill submit_button_1"
					type="submit" name="action" value="Add to Booking" />
				<%
					} else {
				%>
				<input
					class="button button-glow button-border button-highlight button-rounded button-pill submit_button_1"
					type="submit" name="action" value="Add to Cart" />
				<%
					}
				%>
			</div>

			<!--check in  -->

			<input type="hidden" name="checkin_date" value=<%=check_in_data%>>
			<input type="hidden" name="checkout_date" value=<%=check_out_data%>>
			<%
				} else {
					flag = 1;
					String gif_path = request.getContextPath();
					if ((int) (Math.random() * (1 + 1)) == 1) {
						gif_path += "/img/no_results_1.gif";
					} else {
						gif_path += "/img/no_results_2.gif";
					}
			%>
			<div align="center">
				<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
				<br /> <br /> <br /> <img src=<%=gif_path%> alt="" width="509"
					height="251">
			</div>
			<%
				if (flagBM == 1) {
			%>
			<script>
				alert('Sorry, No results could match your search.');
				location.href = 'booking_detail.jsp'
			</script>
			<%
				} else {
			%>
			<script type="text/javascript">
				alert('Sorry, No results could match your search, please search again.');
			</script>
			<%
				}
				}
				session.setAttribute("flag", flag);
			%>
		</form>
	</div>
</body>
</html>
