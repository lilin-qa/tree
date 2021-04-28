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
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<html>
<body>


<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
    <form class="layui-form" action="">
        <div class="layui-form-item" style="margin-bottom:0px">
            <label class="layui-form-label">服务端名称：   </label>
            <div class="layui-input-block">
                <select name="interest" lay-filter="aihao" lay-search>

                    <option value=""></option>
                    <option value="0">写作</option>
                    <option value="1" >阅读</option>
                    <option value="2">游戏</option>
                    <option value="3">音乐</option>
                    <option value="4">旅行</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">业务线名称：  </label>
            <div class="layui-input-block">
                <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入业务线名称" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1"><i class="layui-icon">&#xe615;</i>搜索 </button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>






    </div>


</script>

<script type="text/html" id="barDemo">

    <a class="layui-btn layui-btn-xs" lay-event="edit" ><i class="layui-icon">&#xe642;</i>编辑111111111</a>


    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"> <i class="layui-icon">&#xe640;</i>删除</a>
</script>


<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    layui.use('table', function(){

        var table = layui.table;

        table.render({

            elem: '#test'
            ,url:'/pro/getList'
            ,toolbar: '#toolbarDemo' //开启头具栏，并为其绑定左侧模板
            // ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
            //     title: '提示'
            //     ,layEvent: 'LAYTABLE_TIPS'
            //     ,icon: 'layui-icon-tips'
            // }]
            ,title: '用户数据表'
            ,page: true
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field:'username', title:'名称', width:450, edit: 'text', sort: true}
                ,{field:'isuser', title:'是否启用', width:300, edit: 'text', sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,id: 'testReload'
        });



        var $ = layui.$, active = {
            reload: function(){
                var demoReload = $('#demoReload');

                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        key: {
                            id: demoReload.val()
                        }
                    }
                }, 'data');
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    location.href='/pro/delPro?id='+data.id
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                location.href='/pro/addPro?id='+data.id

            }
        });
    });
</script>

</body>
</html>