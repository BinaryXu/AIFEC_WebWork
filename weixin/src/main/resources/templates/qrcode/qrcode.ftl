<html>
<head>
    <meta charset="utf-8">
    <title>二维码</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="jumbotron">
    <div class="row">
        <div class="col-md-3 col-md-offset-3">
            <img src="data:image/png;base64,${qrcode}" class="img-rounded">
        </div>
        <div class="col-md-3 col-md-offset-1">
            <h2><span id = "des" class="label label-success">扫描图中二维码登录</span></h2>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    var websocket = null;
    if('WebSocket' in window){
        websocket = new WebSocket('ws://127.0.0.1:8082/WebSocket');
    }else{
        alert('该浏览器不支持webSocket消息推送')
    }
    websocket.onopen = function (event) {
        console.log('连接成功！！！');
        websocket.send('${uuid}')
    }
    websocket.onclose = function (event) {
        console.log('连接关闭！！！');
    }
    websocket.onmessage = function (ev) {
        console.log('你有新的消息：'+ev.data)
        switch(ev.data){
            case '401':
                $("#des").text('扫描成功,请点击确认即可登录');
                break;
            default:
                window.location.href=ev.data;
        }
    }
    websocket.onerror = function (ev) {
        console.log('websocket通信发生异常！！！')
    }
    window.onbeforeunload = function(){
        websocket.close();
    }

</script>
</html>


