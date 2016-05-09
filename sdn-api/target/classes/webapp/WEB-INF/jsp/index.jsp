<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8" import="java.util.*"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<h2>Hello World!</h2>
<head>
<script type="text/javascript" src="/static/js/jquery.min.js" charset="utf-8"></script>

<script type="text/javascript">
var test={"id":1,"name":"xuran"};
//$.ajax({type:"POST",dataType:"json",contentType:"application/json",data:JSON.stringify(test),url:"/test/save",success:function(result){alert(JSON.stringify(result));}});
</script>
</head>
<body>
${requestScope.key}
${requestScope.value}
</body>
</html>
