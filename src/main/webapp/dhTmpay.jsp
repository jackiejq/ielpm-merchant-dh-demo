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
  	<a class="navbar-brand"><strong>同名代还交易（<s>ie1-9</s>）</strong></a>
  </div>
</nav>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<% if(request.getAttribute("errorMsg") != null){%>
			<div class="alert alert-warning" role="alert"><%=request.getAttribute("errorMsg")%></div>
			<%}%>
			<form role="form" action="dhTmpay.do" method="post"  name="dhTmpayForm">
				<div class="form-group">
                    <label for="version">接口版本号v1</label>
                    <input type="text" class="form-control" name="version" id="version" value="v1" required />
                </div>
				<div class="form-group">
					<label for="deductBindId">扣款绑卡ID</label>
					<input type="text" class="form-control" name="deductBindId" id="deductBindId" value="" required />
				</div>
				<div class="form-group">
					<label for="rePayBindId">还款绑卡ID</label>
					<input type="text" class="form-control" name="rePayBindId" id="rePayBindId" value="" required />
				</div>
				<div class="form-group">
					<label for="amount">交易金额</label>
					<input type="text" class="form-control" name="amount" value="" id="amount" required />
				</div>
				<div class="form-group">
					<label for="rePayType">还款周期</label>
					<select name="rePayType">
						<option value="D0">D0</option>
						<option value="T1">T1</option>
					</select>
				</div>
				<div class="form-group">
					<label for="deductUrl">扣款通知地址</label>
					<input type="text" class="form-control" name="deductUrl" value="http://192.168.0.105:8888/ielpm-merchant-web-demo/notify.do" id="deductUrl" required />
				</div>
				<div class="form-group">
					<label for="rePayUrl">还款通知地址</label>
					<input type="text" class="form-control" name="rePayUrl" value="http://192.168.0.105:8888/ielpm-merchant-web-demo/notify.do" id="rePayUrl" required />
				</div>
				
				<div class="form-group">
					<label for="userId">用户标识</label>
					<input type="text" class="form-control" value="" name="userId" id="userId" required />
				</div>
			
				<button type="submit" class="btn btn-primary">提交</button>
			</form>
		</div>
	</div>
</div>

</body>
 
</html>
