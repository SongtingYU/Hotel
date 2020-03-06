<!DOCTYPE html>
<html lang="en">
<head>
<%@page
	import="java.util.ArrayList,java.util.Random,com.comp9321.assign2.*"%>
<title>Hotel</title>
<%@ include file="HeaderFile.jsp"%>
<title>welcome</title>

<%
	String info = (String) session.getAttribute("Information");
	if (info != null) {
%><script type="text/javascript">
		alert('<%=info%>
	')
</script>
<%
	session.invalidate();
	}
%>
</head>
<body>
	<%
		ArrayList<Integer> Sydney_Rooms = Factory_DAO.GetRoomDAOInstance().FindId_ByCity("Sydney");
		ArrayList<Integer> Brisbane_Rooms = Factory_DAO.GetRoomDAOInstance().FindId_ByCity("Brisbane");
		ArrayList<Integer> Melbourne_Rooms = Factory_DAO.GetRoomDAOInstance().FindId_ByCity("Melbourne");
		ArrayList<Integer> Perth_Rooms = Factory_DAO.GetRoomDAOInstance().FindId_ByCity("Perth");
		ArrayList<Integer> Adelaide_Rooms = Factory_DAO.GetRoomDAOInstance().FindId_ByCity("Adelaide");
		ArrayList<Integer> Hobart_Rooms = Factory_DAO.GetRoomDAOInstance().FindId_ByCity("Hobart");
		ArrayList<Deal_Bean> deals = Factory_DAO.GetDealDAOInstance().SpecialDealList();
		int deals_number = deals.size() - 1;
	%>
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="Welcome_container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand">Welcome to 4th Hotel</a>
			</div>

		</div>
		<!-- /.container -->
	</nav>

	<!-- Header Carousel -->
	<header id="myCarousel" class="carousel slide">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<a href="Index.jsp">
					<div class="fill"
						style="background-image: url('http://poppenbuetteler-hof.de/img/galerie/rooms/04e_dreibettzimmer.jpg');">
					</div>
				</a>
				<div class="sepecial_deal_info">
					<h2><%=deals.get(deals_number).getDeal_name()%>!!!
						<%=deals.get(deals_number).getPercentage()%>%
					</h2>
					from
					<%=deals.get(deals_number).getStart_date()%>
					to
					<%=deals.get(deals_number).getEnd_date()%>
				</div>
			</div>
			<div class="item">

				<a href="Index.jsp">
					<div class="fill"
						style="background-image: url('https://c2.staticflickr.com/4/3757/13361820533_254c11db66_b.jpg');">
					</div>

				</a>
				<div class="sepecial_deal_info">
					<h2><%=deals.get(1).getDeal_name()%>!!!
						<%=deals.get(1).getPercentage()%>%
					</h2>
					from
					<%=deals.get(1).getStart_date()%>
					to
					<%=deals.get(1).getEnd_date()%>
				</div>

			</div>
			<div class="item">
				<a href="Index.jsp">
					<div class="fill"
						style="background-image: url('http://static1.squarespace.com/static/55812b34e4b073fcfef7a7f5/t/55cb0d8de4b03dbaa077eb6f/1439370641172/?format=2500w');">
					</div>

				</a>

			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="icon-prev"></span>
		</a> <a class="right carousel-control" href="#myCarousel"
			data-slide="next"> <span class="icon-next"></span>
		</a>
	</header>


	<!-- Page Content -->

	<div class="text-center serach_bar_top_button">
		<%@ include file="SearchBar.jsp"%>
	</div>
	<%
		if (!Sydney_Rooms.isEmpty()) {
			Random Sydney = new Random();
			int Sydney_index = Sydney.nextInt(Sydney_Rooms.size());
			Room_Bean Sydney_Room = Factory_DAO.GetRoomDAOInstance().FindById(Sydney_Rooms.get(Sydney_index));

			String Sydney_url = Sydney_Room.getImg_url();
			String Sydney_type = Sydney_Room.getRoom_type();
			float Sydney_price = Sydney_Room.getPrice();
	%>
	<!-- /.container -->

	<div class="container">

		<div class="row text-center">
			<div class="col-md-4 col-sm-6 hero-feature">
				<div class="thumbnail">
					<a href="Index.jsp"> <img
						src=<%=Sydney_url.contains("http") ? Sydney_url : request.getContextPath() + "/img/" + Sydney_url%>
						onerror="this.src='<%=request.getContextPath() + "/img/default_image.jpg"%>'"
						style="width:400px;height:230px;"/>
					</a>
					<div class="caption">
						<h3>Sydney</h3>
						<p>
							<b>Room type:</b>
							<%=Sydney_type%>
							<b>Price:</b>
							<%=Sydney_price%>
						</p>
					</div>

				</div>

			</div>

			<%
				}
			%>
			<%
				if (!Brisbane_Rooms.isEmpty()) {
					Random Brisbane = new Random();
					int Brisbane_index = Brisbane.nextInt(Brisbane_Rooms.size());
					Room_Bean Brisbane_Room = Factory_DAO.GetRoomDAOInstance().FindById(Brisbane_Rooms.get(Brisbane_index));
					String Brisbane_url = Brisbane_Room.getImg_url();
					String Brisbane_type = Brisbane_Room.getRoom_type();
					float Brisbane_price = Brisbane_Room.getPrice();
			%>
			<div class="col-md-4 col-sm-6 hero-feature">
				<div class="thumbnail">
					<a href="Index.jsp"> <img
						src=<%=Brisbane_url.contains("http")
						? Brisbane_url
						: request.getContextPath() + "/img/" + Brisbane_url%>
						onerror="this.src='<%=request.getContextPath() + "/img/default_image.jpg"%>'"
						style="width:400px;height:230px;"/>


					</a>
					<div class="caption">
						<h3>Brisbane</h3>
						<p>
							<b>Room type:</b>
							<%=Brisbane_type%>
							<b>Price:</b>
							<%=Brisbane_price%></p>
					</div>
				</div>
			</div>
			<%
				}
			%>
			<%
				if (!Melbourne_Rooms.isEmpty()) {
					Random Melbourne = new Random();
					int Melbourne_index = Melbourne.nextInt(Melbourne_Rooms.size());
					Room_Bean Melbourne_Room = Factory_DAO.GetRoomDAOInstance()
							.FindById(Melbourne_Rooms.get(Melbourne_index));
					String Melbourne_url = Melbourne_Room.getImg_url();
					String Melbourne_type = Melbourne_Room.getRoom_type();
					float Melbourne_price = Melbourne_Room.getPrice();
			%>
			<div class="col-md-4 col-sm-6 hero-feature">
				<div class="thumbnail">
					<a href="Index.jsp"> <img
						src=<%=Melbourne_url.contains("http")
						? Melbourne_url
						: request.getContextPath() + "/img/" + Melbourne_url%>
						onerror="this.src='<%=request.getContextPath() + "/img/default_image.jpg"%>'"
						style="width:400px;height:230px;"/>


					</a>
					<div class="caption">
						<h3>Melbourne</h3>
						<p>
							<b>Room type:</b>
							<%=Melbourne_type%>
							<b>Price:</b>
							<%=Melbourne_price%></p>
					</div>
				</div>
			</div>

		</div>
		<%
			}
		%>
		<%
			if (!Perth_Rooms.isEmpty()) {
				Random Perth = new Random();
				int Perth_index = Perth.nextInt(Perth_Rooms.size());
				Room_Bean Perth_Room = Factory_DAO.GetRoomDAOInstance().FindById(Perth_Rooms.get(Perth_index));
				String Perth_url = Perth_Room.getImg_url();
				String Perth_type = Perth_Room.getRoom_type();
				float Perth_price = Perth_Room.getPrice();
		%>
		<!-- Page Features -->
		<div class="row text-center">

			<div class="col-md-4 col-sm-6 hero-feature">
				<div class="thumbnail">
					<a href="Index.jsp"> <img
						src=<%=Perth_url.contains("http") ? Perth_url : request.getContextPath() + "/img/" + Perth_url%>
						onerror="this.src='<%=request.getContextPath() + "/img/default_image.jpg"%>'"
						style="width:400px;height:230px;"/>
					</a>
					<div class="caption">
						<h3>Perth</h3>
						<p>
							<b>Room type:</b>
							<%=Perth_type%>
							<b>Price:</b>
							<%=Perth_price%></p>
					</div>
				</div>
			</div>
			<%
				}
			%>
			<%
				if (!Adelaide_Rooms.isEmpty()) {
					Random Adelaide = new Random();
					int Adelaide_index = Adelaide.nextInt(Adelaide_Rooms.size());
					Room_Bean Adelaide_Room = Factory_DAO.GetRoomDAOInstance().FindById(Adelaide_Rooms.get(Adelaide_index));
					String Adelaide_url = Adelaide_Room.getImg_url();
					String Adelaide_type = Adelaide_Room.getRoom_type();
					float Adelaide_price = Adelaide_Room.getPrice();
			%>
			<div class="col-md-4 col-sm-6 hero-feature">
				<div class="thumbnail">
					<a href="Index.jsp"> <img
						src=<%=Adelaide_url.contains("http")
						? Adelaide_url
						: request.getContextPath() + "/img/" + Adelaide_url%>
						onerror="this.src='<%=request.getContextPath() + "/img/default_image.jpg"%>'"
						style="width:400px;height:230px;"/>
					</a>

					<div class="caption">
						<h3>Adelaide</h3>
						<p>
							<b>Room type:</b>
							<%=Adelaide_type%>
							<b>Price:</b>
							<%=Adelaide_price%></p>
					</div>
				</div>
			</div>
			<%
				}
			%>
			<%
				if (!Hobart_Rooms.isEmpty()) {
					Random Hobart = new Random();
					int Hobart_index = Hobart.nextInt(Hobart_Rooms.size());
					Room_Bean Hobart_Room = Factory_DAO.GetRoomDAOInstance().FindById(Hobart_Rooms.get(Hobart_index));
					String Hobart_url = Hobart_Room.getImg_url();
					String Hobart_type = Hobart_Room.getRoom_type();
					float Hobart_price = Hobart_Room.getPrice();
			%>
			<div class="col-md-4 col-sm-6 hero-feature">
				<div class="thumbnail">
					<a href="Index.jsp"> <img
						src=<%=Hobart_url.contains("http") ? Hobart_url : request.getContextPath() + "/img/" + Hobart_url%>
						onerror="this.src='<%=request.getContextPath() + "/img/default_image.jpg"%>'"
						 style="width:400px;height:230px;"/>
					</a>
					<div class="caption">
						<h3>Hobart</h3>
						<p>
							<b>Room type:</b>
							<%=Hobart_type%>
							<b>Price:</b>
							<%=Hobart_price%></p>
					</div>
				</div>
			</div>

		</div>
		<%
			}
		%>
		<!-- /.row -->




	</div>
	<%@ include file="footer.jsp"%>
	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Script to Activate the Carousel -->
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>

</body>

</html>
