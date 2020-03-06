<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>

<script type="text/javascript">
	function check(obj) {
		var todayDate = "";
		var theDate = new Date();
		todayDate += theDate.getFullYear() + "-";
		todayDate += (theDate.getMonth() + 1) + "-";
		todayDate += theDate.getDate();

		if (obj.checkin_date.value == "") {
			alert("Check in date is needed!")
			return false;
		}
		if (obj.checkout_date.value == "") {
			alert("Check out date is needed!")
			return false;
		}
		if (Date.parse(obj.checkin_date.value) >= Date.parse(obj.checkout_date.value)) {
			alert("Check out time should after check in time!")
			return false;
		}
		if (Date.parse(obj.checkin_date.value) < Date.parse(todayDate)) {
			alert("Sorry, we can't server you before today!")
			return false;
		}
	}
</script>


<%
	String city = request.getParameter("citys");
	String check_in_data = request.getParameter("checkin_date");
	String check_out_data = request.getParameter("checkout_date");
	String room_number = request.getParameter("rooms");
	float max_price;
	try {
		max_price = Float.parseFloat(request.getParameter("maxprice"));
	} catch (Exception e) {
		max_price = -1;
	}
%>

<div id="searchbar">
	<div class="searchbar">
		<form id="searchbar-form" action="control" method="post"
			onsubmit="return check(this)">


			<!-- <div class="searchbar__input-wrapper"> -->

			<!--city bar -->
			<label class="searchbar__location"> <span
				class="screen-reader-only"> City </span> <span
				class="select select-large"> <select id="citys" name="citys">
						<option value="Sydney" <%if (city.contains("Sydney")) {%>
							selected="selected" <%}%>>Sydney</option>
						<option value="Brisbane" <%if (city.contains("Brisbane")) {%>
							selected="selected" <%}%>>Brisbane</option>
						<option value="Melbourne" <%if (city.contains("Melbourne")) {%>
							selected="selected" <%}%>>Melbourne</option>
						<option value="Perth" <%if (city.contains("Perth")) {%>
							selected="selected" <%}%>>Perth</option>
						<option value="Adelaide" <%if (city.contains("Adelaide")) {%>
							selected="selected" <%}%>>Adelaide</option>
						<option value="Hobart" <%if (city.contains("Hobart")) {%>
							selected="selected" <%}%>>Hobart</option>
				</select>
			</span>
			</label>

			<!--check in  -->

			<label class="searchbar__checkin"> <span
				class="screen-reader-only"> Check In </span> <input
				class="form-control" name="checkin_date" id="registration-date"
				type="date" value=<%=check_in_data%> />
			</label>

			<!--check out  -->

			<label class="searchbar__checkout"> <span
				class="screen-reader-only"> Check Out </span> <input
				class="form-control" name="checkout_date" id="registration-date"
				type="date" value=<%=check_out_data%> />
			</label>

			<!--Room  -->
			<label class="searchbar__location"> <span
				class="screen-reader-only"> Number of Rooms </span> <span
				class="select select-large"> <select id="guests" name="rooms">
						<option value="1" <%if (room_number.contains("1")) {%>
							selected="selected" <%}%>>1 Room</option>
						<option value="2" <%if (room_number.contains("2")) {%>
							selected="selected" <%}%>>2 Rooms</option>
						<option value="3" <%if (room_number.contains("3")) {%>
							selected="selected" <%}%>>3 Rooms</option>
						<option value="4" <%if (room_number.contains("4")) {%>
							selected="selected" <%}%>>4 Rooms</option>
						<option value="5" <%if (room_number.contains("5")) {%>
							selected="selected" <%}%>>5 Rooms</option>
						<option value="6" <%if (room_number.contains("6")) {%>
							selected="selected" <%}%>>6 Rooms</option>
						<option value="7" <%if (room_number.contains("7")) {%>
							selected="selected" <%}%>>7 Rooms</option>
						<option value="8" <%if (room_number.contains("8")) {%>
							selected="selected" <%}%>>8 Rooms</option>
						<option value="9" <%if (room_number.contains("9")) {%>
							selected="selected" <%}%>>8 Rooms+</option>
				</select>
			</span>
			</label>
			<!--Max price  -->
			<label class="searchbar__price"> <input type="text"
				onkeyup="value=value.replace(/[^\d\.]/g, '').replace(/(\.{1,})/g, '.').replace(/(\.\d)\./g, '$1').replace(/(\.\d{2}).*/g, '$1')"
				<%if (max_price < 0) {%> value="" <%} else {%> value=<%=max_price%>
				<%}%> name="maxprice" placeholder="Maxium Price $" />
			</label>
			<!--Submit  -->
			<input type="submit"
				class="searchbar__submit btn btn-primary btn-large" name="action"
				value="Search" />
		</form>
	</div>
</div>
