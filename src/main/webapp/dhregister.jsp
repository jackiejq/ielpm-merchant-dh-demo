<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head> 
<meta http-equiv="Content-Type"	content="text/html; charset=utf-8" />
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>易势支付-商户demo</title> 
<style type="text/css">
	body { padding-top: 70px; }
</style>

</head> 
<body> 
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container">
  	<a class="navbar-brand" href="index.html"><strong>首页</strong></a>
  	<a class="navbar-brand"><strong>注册（<s>ie1-9</s>）</strong></a>
  </div>
</nav>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<% if(request.getAttribute("errorMsg") != null){%>
			<div class="alert alert-warning" role="alert"><%=request.getAttribute("errorMsg")%></div>
			<%}%>
			<form role="form" action="dhRegister.do" method="post"  name="dhRegisterForm">
				<div class="form-group">
					<label for="version">接口版本号v1</label>
					<input type="text" class="form-control" name="version" id="version" value="v1" required />
				</div>
				<div class="form-group">
					<label for="userName">用户名称</label>
					<input type="text" class="form-control" name="userName" id="userName" value="" required />
				</div>
				<div class="form-group">
					<label for="certificateType">证件类型</label>
					<input type="text" class="form-control" value="ZR01" name="certificateType" id="certificateType" required />
				</div>
				<div class="form-group">
					<label for="certificateNum">证件号码</label>
					<input type="text" class="form-control" value="" name="certificateNum" id="certificateNum" required />
				</div>
			
				<button type="submit" class="btn btn-primary">提交</button>
			</form>
		</div>
	</div>
</div>

</body>
 
</html>