<html>
    <head>
        <meta charset="utf-8">
        <title>卖家后台管理系统</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row clearfix">
                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <form role="form" method="GET" action="/sell/category/save">
                                <div class="form-group">
                                    <label>类目名称</label>
                                    <input name="categoryName" type="text" class="form-control" value="${(productCategory.categoryName)!''}">
                                </div>
                                <div class="form-group">
                                    <label>类目编号</label>
                                    <input name="categoryType" type="number" class="form-control"value="${(productCategory.categoryType)!''}">
                                </div>
                                <input name="categoryId" hidden value="${(productCategory.categoryId)!''}">
                                <button type="submit" class="btn btn-default">提交</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>