<html>

<body>
<h1>Spring MVC file upload example</h1>

<form method="POST" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
    <input type="file" name="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>

<h2><a href="${pageContext.request.contextPath}/downloadFile">Click here to download the file</a></h2>

</body>
</html>
