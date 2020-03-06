<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<div class="register">
		<p></p>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-6 col-sm-6 col-md-1"></div>
				<div class="col-xs-6 col-sm-6 col-md-6">

					<div id="myCarousel" class="carousel mycarousel slide"
						data-ride="carousel">
						<!-- Indicators -->
						<ol class="carousel-indicators">
							<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
							<li data-target="#myCarousel" data-slide-to="1"></li>
							<li data-target="#myCarousel" data-slide-to="2"></li>
							<li data-target="#myCarousel" data-slide-to="3"></li>
						</ol>

						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<div class="item active">
								<div class="fill">
									<img
										src="http://rack.1.mshcdn.com/media/ZgkyMDE1LzA2LzA0LzFhL2FsbC5uZXcuYWwuZTk3MjguanBnCnAJdGh1bWIJOTUweDUzNCMKZQlqcGc/0f2ed701/9f6/all.new_.all_.different.marvel.thm_.jpg"
										alt="Chania" width="800" height="375">
								</div>
								<div class="carousel-caption">
									<h2>
										<a href="">Melbourne</a>
									</h2>
								</div>
							</div>

							<div class="item">
								<div class="fill">
									<img src="img/Melbourne.jpg" alt="Chania" width="800"
										height="375">
								</div>
								<div class="carousel-caption">
									<h2>
										<a href="">Melbourne</a>
									</h2>
								</div>
							</div>

							<div class="item">
								<div class="fill">
									<img src="img/sydney.png" alt="Sydney" width="800" height="375">
								</div>
								<div class="carousel-caption">
									<h2>
										<a href="">sydney</a>
									</h2>
								</div>
							</div>

							<div class="item">
								<div class="fill">
									<img src="img/brisbane.jpg" alt="Flower" width="800"
										height="375">
								</div>
								<div class="carousel-caption">
									<h2>
										<a href="">brisbane</a>
									</h2>
								</div>
								-
							</div>
						</div>

						<!-- Left and right controls -->
						<a class="left carousel-control" href="#myCarousel" role="button"
							data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#myCarousel"
							role="button" data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>

				<div class="col-xs-6 col-sm-6 col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								Please sign up for <small>It's free!</small>
							</h3>
						</div>
						<div class="panel-body">
							<form name="reigster" onsubmit="return validateResigester(this)"
								action="control" method="POST">
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<div class="text-danger">${exist_username}</div>
											<input type="text" class="form-control input-sm"
												placeholder="Username" name="user_name" required
												pattern="[a-zA-Z][0-9a-zA-Z_]{3,}"
												title="four or more characters" />
										</div>
									</div>
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<input type="text" class="form-control input-sm"
												placeholder="Nickname" name="nick_name"
												pattern="[a-zA-Z]{1,}" title="one or more characters" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<input type="text" class="form-control input-sm"
												placeholder="First Name" name="first_name"
												pattern="[a-zA-Z]{1,}" title="one or more characters" />
										</div>
									</div>
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<input type="text" class="form-control input-sm"
												placeholder="Last Name" name="last_name"
												pattern="[a-zA-Z]{1,}" title="one or more characters" />
										</div>
									</div>
								</div>


								<div class="form-group">
									<input type="text" class="form-control input-sm"
										placeholder="Email Address" name="email" required
										pattern="[a-z0-9._+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
										title="Invalid Email Format" />
								</div>
								<div class="form-group">
									<input type="text" class="form-control input-sm"
										placeholder="Address" name="full_address" pattern=".{5,}"
										title="Invlid input" />
								</div>
								<div class="form-group">
									<input type="number" class="form-control input-sm"
										placeholder="Credit card" name="credit_card"
										pattern="[0-9]{16}" title="Invalid input" />
								</div>

								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<div class="text-danger">${password_different}</div>
											<input type="password" class="form-control input-sm"
												placeholder="Password" name="password" required
												pattern=".{4,}" title="4 at least" />
										</div>
									</div>
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<input type="password" class="form-control input-sm"
												placeholder="Confirm Password" name="password_confirmation "
												required pattern=".{4,}" title="4 at least" />
										</div>
									</div>
								</div>
								<input type="submit" class="btn btn-primary btn-block"
									name="action" value="Register" />
							</form>
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-sm-6 col-md-1">
					<!-- right 1 width -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>