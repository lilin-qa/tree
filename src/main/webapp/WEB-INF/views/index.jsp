<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home</title>
    <style>
        #iframeTop{
            width: 100%;
            height: 70px;
        }
        #iframeLeft{
            width: 15%;
            height: 700px;
            float: left;
        }
        #iframeContent{
            width: 84%;
            height: 700px;
        }
    </style>
<body>
<div>
    <iframe id="iframeTop" name="iframeTop" frameborder="0" src="https://www.baidu.com/">22222</iframe>
    <iframe id="iframeLeft" name="iframeLeft" frameborder="0" src="<%=basePath%>/index"></iframe>
    <iframe id="iframeContent" name="iframeContent" frameborder="0" src="https://www.baidu.com/"></iframe>
</div>
</body>
</html>