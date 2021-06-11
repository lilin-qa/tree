

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
<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上地述js路径需要改成你本的 -->
<script src="js/jquery-3.5.1.js" charset="utf-8"></script>
<script type="text/javascript">
    console.log(1)

    // function getbus() {
    //     alert(2)
    // }


    // var doms = document.getElementsByClassName('.selects')[0];
    //
    // console.log(doms)
    //
    // $(' #toolbarDemo .selects').change(function(){
    //     alert(1)
    // })
    console.log( $("#proids"))

</script>

<table class="layui-hide" id="test" lay-filter="test"></table>


<script type="text/html" id="toolbarDemo">
    <form class="layui-form batchinput-form"  action="" id="box-form">
    <div class="demoTable">

        <div class="layui-form-item ">
           <label class="layui-form-label">所属服务端：  </label>
            <div class="layui-input-block">
                <select  id="proids" class="selects" name="proid"  lay-filter="one_cate">
                    <option value="0" class="changeItem">请选择服务端名称</option>
                    <c:forEach items="${prolist}" var="plist"  varStatus="p">
                        <option value="${plist.id}">${plist.projectname}</option>
                    </c:forEach>
                </select>


            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">所属业务线：  </label>
            <div class="layui-input-block">
                <select  id="busi"  lay-filter="aihao" lay-search="">
<%--                    <option value="">请选择业务线名称</option>--%>
<%--                    <c:forEach items="${prolist}" var="plist"  varStatus="p">--%>
<%--                        <option value="${plist.id}">${plist.projectname}</option>--%>
<%--                    </c:forEach>--%>


                </select>
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-sm" data-type="reload" lay-filter="demo1"><i class="layui-icon">&#xe615;</i>搜索 </button>
                <button class="layui-btn layui-btn-sm" lay-event="getCheckData" onclick=window.location.href='/inte/addInte'>
                    <i class="layui-icon">&#xe608;</i>新增

                </button>
            </div>
        </div>


    </div>



</form>



</script>

<script type="text/html" id="barDemo">

    <a class="layui-btn layui-btn-xs" lay-event="edit" ><i class="layui-icon">&#xe642;</i>编辑</a>


    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"> <i class="layui-icon">&#xe640;</i>删除</a>
</script>

<script>
    layui.use('table', function(){

        var table = layui.table;

        table.render({

            elem: '#test'
            ,url:'/inte/getinteList'
            ,toolbar: '#toolbarDemo' //开启头具栏，并为其绑定左侧模板
            ,title: '业务线表'
            ,page: true
            ,  height : 480
            ,cols: [[
                ,{type: 'checkbox',  fixed: 'left'}
                ,{field:'interfaceid', title:'ID', width:80,  sort: true}
                // ,{field:'varid', title:'ID',  width:80, sort: true}
                ,{field:'proname', title:'服务端', width:350, edit: 'text'}
                ,{field:'businame', title:'业务线', width:350, edit: 'text'}
                ,{field:'interfacename', title:'接口名称', width:350, edit: 'text'}
                ,{field:'interfacename', title:'接口名称', width:350, edit: 'text'}
                ,{ title:'操作',  toolbar: '#barDemo' ,width:230}
            ]]
                ,parseData: function (res) {

                    if(res.count ==  0 || res  == null)
                    {
                        return {
                            'code': 201, //接口状态
                            'msg': '暂无数据', //提示文本
                            'count': 0, //数据长度
                            'data': [] //数据列表，是直接填充进表格中的数组
                        }
                    }
                }
            ,done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);
                //得到当前页码
                console.log(curr);
                //得到数据总量
                console.log(count);
                $(".layui-table-box").find("[data-field='busid']").css("display","none");




                pageCurr=curr;
            }
            ,id: 'testReload'
        });



        var $ = layui.$, active = {
            reload: function(){

                var varname=$('#varname')
console.log(varname.val())
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {

                        varname:varname.val()
                    }
                }, 'data');
            }
        };

        $('body').on('click', '.demoTable  .layui-form-item .layui-btn',function() {
            console.log(this)
            var id = $(this).data('type');
            console.log(id)
            active[id] ? active[id].call(this) : '';
        });
        //根据服务联动业务线
        layui.form.on('select(one_cate)', function(data){
            console.log(data.value)
            //data.value 得到被选中的值
            var url = '/inte/getbusiByproid?proid=' + data.value;
            $.get(url,function(data){
                $("#busi").empty();

                $.each(data,function(index,item){
                    console.log(item)
                    $("#busi").append(new Option(item.busname,item.busid));
                });
                layui.form.render("select");
            });
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    location.href='/vari/delVari?varid='+data.varid
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                console.log("varid:"+data.varid);
                location.href='/inte/addInte?varid='+data.varid

            }
        });
    });


</script>

<style>
    .layui-form-label{
        width: 100px;
        padding: 9px 0px;
    }
    .layui-form-item{
        margin-top: 10px;

    }
</style>
</body>
</html>