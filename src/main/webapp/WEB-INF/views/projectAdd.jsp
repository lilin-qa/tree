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
    <title>增加项目</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <c:if test = "${projectinfo.isuse!=null}">
        <legend>编辑</legend>
    </c:if>
    <c:if test = "${projectinfo.isuse==null}">
        <legend>新增</legend>
    </c:if>

</fieldset>

<div class="demoTable">
    <form class="layui-form" action="/pro/savePro">

        <div class="layui-form-item">
            <label class="layui-form-label">*名称</label>
            <div class="layui-input-block">
                <input type="text" name="projectname" value="${projectinfo.projectname}"  lay-verify="required" style="width: 70%;" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
                <input type="hidden" name="id" value="${projectinfo.id}">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">开关</label>
            <div class="layui-input-block">

                <c:if test = "${projectinfo.isuse=='off' || projectinfo.isuse==null}">


                    <input type="checkbox" name="isuse"   lay-skin="switch" lay-filter="switchTest" lay-text="启用|关闭">

                </c:if>
                <c:if test = "${projectinfo.isuse=='on'}">
                    <input type="checkbox" checked="" name="isuse"   lay-skin="switch" lay-filter="switchTest" lay-text="启用|关闭">


                </c:if>

            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容"  style="width: 70%;" name="remarks" class="layui-textarea">${projectinfo.remarks}</textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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