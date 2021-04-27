<%--
  Created by IntelliJ IDEA.
  User: 10994
  Date: 2020/8/20
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<html>
<body class="layui-layout-body ">
<div class="layui-layout layui-layout-admin  ">
    <div class="layui-header">
        <div class="layui-logo">李梦琳测试后台布局</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->


    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">历史记录</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>

                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/goAddPro">项目</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;height: 100%;">
            <iframe id="iframeLeft" scrolling="yes" style="width: 100%;height: 100%" name="iframeLeft" frameborder="0" src="<%=basePath%>/ind/index"></iframe>
        </div>
    </div>
    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © 李梦琳 - 底部固定区域
    </div>
</div>
<script src="layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>
