<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.*;"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet' href='css/style.css' media='screen' />
<link rel='stylesheet' href='css/bootstrap.min.css' media='screen' />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script src="blocksit/blocksit.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/myJS.js"></script>

<script type="text/javascript">
	var like = function(id) {

		//alert($("#"+id).text());
		$.ajax({

			url : 'like',
			type : 'POST',
			dataType : 'json',
			data : {
				id : id,
				isLike : $("#" + id).text()
			},
			success : function(data) {
				if (data.isLike) {
					// alert("like successfully");
					$("#" + data.id).text("unlike");
				} else {
					// alert("unlike successfully");
					$("#" + data.id).text("like");
				}

			},
			error : function() {
				alert("error");
			}
		});

		return false;
	};

	var save = function() {

		// alert("save");
		$.ajax({
			url : 'like',
			type : 'POST',
			dataType : 'json',
			data : {
				"save" : "save"
			},
			success : function(data) {
				alert(data.success);
			},
			error : function() {
				alert("error");
			}
		});
		return false;
	};

	var prev = function() {
		//alert("prev");
		var current = $('#currentPage').text();

		if (current == 1) {
			$('#prev').addClass('disabled');
			return false;
		}

	};

	var next = function() {
		//alert("next");
		var current = $('#currentPage').text();
		var pages = $('#pages').text();
		//alert("current page is "+current + ", pages ="+ pages);
		if (current == pages) {
			//alert(" disabled the button");
			$('#next').addClass('disabled');
			$('#saveNext').addClass('disabled');
			return false;
		}
	};

</script>

<link rel="shortcut icon"
	href="http://www.inwebson.com/wp-content/themes/inwebson2/favicon.ico" />
<link rel="canonical"
	href="http://www.inwebson.com/demo/blocksit-js/demo2/" />
</head>


<body>
	<!-- Content -->
	<section id="wrapper"> <hgroup>
	<h2>Picture Review</h2>
	<h3>Picture Review</h3>
	</hgroup> <%
 	List<PictureBean> pictures = (List<PictureBean>) session
 			.getAttribute("pictures");
	String group = request.getParameter("group");
 	String interest = "";
 	String currentPage = request.getParameter("currentPage");
 	String pages = request.getParameter("pages");
 	//System.out.println("currentpage --->" + currentPage);
 	//System.out.println("pictures size--->" + pictures.size());
 %>

	<div id="container">
		<div>
			<ul class="pager">
				<li><a href="#" onclick="save()">save</a></li>
				<li id="prev"><a href="show?operation=prev"
					onclick="return prev()">Previous</a></li>
				<li><span> <label id="currentPage"><%=currentPage%></label>
						of <label id="pages"><%=pages%></label>
				</span></li>
				<li id="next"><a href="show?operation=next"
					onclick="return next()">Next</a></li>
				<li id="saveNext"><a href="show?operation=saveNext"
					onclick="return next()">Save&Next</a></li>
				<li><div class="btn-group">
						<button type="button" class="btn btn-info dropdown-toggle"
							data-toggle="dropdown">
							<%=group %> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="show?group=1">Group 1</a></li>
							<li><a href="show?group=2">Group 2</a></li>
							<li><a href="show?group=3">Group 3</a></li>
						</ul>
					</div></li>
			</ul>
		</div>

		<%
			if (pictures != null) {
				for (int i = 0; i < pictures.size(); i++) {
					if (pictures.get(i).getInteresting() == 0) {
						interest = "like";
					} else {
						interest = "unlike";
					}
		%>
		<div class="grid">
			<div class="imgholder">
				<img src="<%=pictures.get(i).getUrl()%>" />
			</div>
			<strong><%=pictures.get(i).getTitle()%></strong>
			<!-- title  -->
			<p><%=pictures.get(i).getAlt()%></p>
			<!-- description  -->
			<div>
				<a id="<%=pictures.get(i).getId()%>"
					onclick="like('<%=pictures.get(i).getId()%>')"><%=interest%></a>
			</div>
			<!--  <div class="meta">by j osborn</div>-->
		</div>
		<%
			}
			} else {
				response.sendRedirect("index.jsp");
			}
		%>
	</div>

	<!--  
	<div>
		<a id="first" href="#">First</a>  	
		<a id="prev" href="#">Prev</a> 
		<label id="page" >1 of 100</label>
		<a id="next" href="#">Next</a> 
		<a id="last" href="#">Last</a>
	</div>
	--> </section>

</body>

</html>