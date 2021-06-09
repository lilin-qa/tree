<%--
  Created by IntelliJ IDEA.
  User: 10994
  Date: 2020/9/16
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>增加业务线</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <c:if test = "${business.busid!=null}">
        <legend>修改</legend>
    </c:if>
    <c:if test = "${business.busid==null}">
        <legend>新增</legend>
    </c:if>

</fieldset>

<div class="demoTable">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <label class="layui-form-label"  style="background-color: #e2e2e2;">变量名</label>
            <div class="layui-input-block">
                <input type="text" name="title" lay-verify="required"  style="width: 70%;" autocomplete="off" placeholder="请输入变量名" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"  style="background-color: #e2e2e2;">描述信息</label>
            <div class="layui-input-block">
                <input type="text" name="title" lay-verify="required"  style="width: 70%;" autocomplete="off" placeholder="请输入描述信息" class="layui-input">
            </div>
        </div>














    </form>
</div>


<script src="/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        // //监听提交
        // form.on('submit(demo1)', function(data){
        //     layer.alert(JSON.stringify(data.field), {
        //         title: '最终的提交信息'
        //     })
        //     return false;
        // });




    });
</script>

</body>
</html>