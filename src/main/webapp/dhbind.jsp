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
  	<a class="navbar-brand"><strong>绑卡（<s>ie1-9</s>）</strong></a>
  </div>
</nav>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<% if(request.getAttribute("errorMsg") != null){%>
			<div class="alert alert-warning" role="alert"><%=request.getAttribute("errorMsg")%></div>
			<%}%>
			<form role="form" action="dhBind.do" method="post"  name="dhBind">
				<div class="form-group">
					<label for="version">接口版本号v1</label>
					<input type="text" class="form-control" name="version" id="version" value="v1" required />
				</div>
				<div class="form-group">
					<label for="userId">用户标识</label>
					<input type="text" class="form-control" name="userId" id="userId" value="" required />
				</div>
				<div class="form-group">
					<label for="cardNum">卡号</label>
					<input type="text" class="form-control" value="" name="cardNum" id="cardNum" required />
				</div>
				<div class="form-group">
					<label for="bankAgentId">联行号</label>
					<input type="text" class="form-control" value="" name="bankAgentId" id="bankAgentId" required />
				</div>
				<div class="form-group">
					<label for="userName">用户名称</label>
					<input type="text" class="form-control" value="" name="userName" id="userName" required />
				</div>
				<div class="form-group">
					<label for="certificateType">证件类型</label>
					<input type="text" class="form-control" value="ZR01" name="certificateType" id="certificateType" required />
				</div>
				<div class="form-group">
					<label for="certificateNum">证件号码</label>
					<input type="text" class="form-control" value="" name="certificateNum" id="certificateNum" required />
				</div>
				<div class="form-group">
					<label for="mobile">银行卡预留手机号</label>
					<input type="text" class="form-control" value="" name="mobile" id="mobile" required />
				</div>
				<div class="form-group">
					<label for="notifyUrl">结果通知地址</label>
					<input type="text" class="form-control" name="notifyUrl" value="http://192.168.0.105:8888/ielpm-merchant-web-demo/notify.do" id="notifyUrl" required />
				</div>
				<div class="form-group">
					<label for="returnUrl">前台通知地址</label>
					<input type="text" class="form-control" name="returnUrl" value="http://192.168.0.105:8888/ielpm-merchant-web-demo/qnotify.do" id="returnUrl" required />
				</div>
			
				<button type="submit" class="btn btn-primary">提交</button>
			</form>
		</div>
	</div>
</div>

</body>
 
</html>