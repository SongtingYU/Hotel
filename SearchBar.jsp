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
		if (Date.parse(obj.checkin_date.value) >= Date
				.parse(obj.checkout_date.value)) {
			alert("Check out time should after check in time!")
			return false;
		}
		if (Date.parse(obj.checkin_date.value) < Date.parse(todayDate)) {
			alert("Sorry, we can't server you before today!")
			return false;
		}
	}
</script>


<div id="searchbar">
	<div class="searchbar">
		<form id="searchbar-form" action="control" method="post"
			onsubmit="return check(this)">

			<!-- <div class="searchbar__input-wrapper"> -->

			<!--city bar -->
			<label class="searchbar__location"> <span
				class="screen-reader-only"> City </span> <span
				class="select select-large"> <select id="citys" name="citys">
						<option value="Sydney">Sydney</option>
						<option value="Brisbane">Brisbane</option>
						<option value="Melbourne">Melbourne</option>
						<option value="Perth">Perth</option>
						<option value="Adelaide">Adelaide</option>
						<option value="Hobart">Hobart</option>
				</select>
			</span>
			</label>

			<!--check in  -->
			<%
				SimpleDateFormat DB_format = new SimpleDateFormat("yyyy-MM-dd");
				String now_date = DB_format.format(new Date());
			%>

			<label class="searchbar__checkin"> <span
				class="screen-reader-only"> Check In </span> <input
				class="form-control" name="checkin_date" id="registration-date"
				type="date" value=<%=now_date%> />
			</label>

			<!--check out  -->
			<%
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(new Date());
				cal.add(GregorianCalendar.DATE, 1);
				String next_date = DB_format.format(cal.getTime());
			%>

			<label class="searchbar__checkout"> <span
				class="screen-reader-only"> Check Out </span> <input
				class="form-control" name="checkout_date" id="registration-date"
				type="date" value=<%=next_date%> />
			</label>

			<!--Room  -->
			<label class="searchbar__location"> <span
				class="screen-reader-only"> Number of Rooms </span> <span
				class="select select-large"> <select id="guests" name="rooms">
						<option value="1">1 Room</option>
						<option value="2">2 Rooms</option>
						<option value="3">3 Rooms</option>
						<option value="4">4 Rooms</option>
						<option value="5">5 Rooms</option>
						<option value="6">6 Rooms</option>
						<option value="7">7 Rooms</option>
						<option value="8">8 Rooms</option>
						<option value="9">8 Rooms+</option>
				</select>
			</span>
			</label>
			<!--Max price  -->
			<label class="searchbar__price"> <input type="text"
				onkeyup="value=value.replace(/[^\d\.]/g, '').replace(/(\.{1,})/g, '.').replace(/(\.\d)\./g, '$1').replace(/(\.\d{2}).*/g, '$1')"
				value="" name="maxprice" placeholder="Maxium Price $" />
			</label>
			<!--Submit  -->
			<input type="submit"
				class="searchbar__submit btn btn-primary btn-large" name="action"
				value="Search" />
		</form>
	</div>
</div>
