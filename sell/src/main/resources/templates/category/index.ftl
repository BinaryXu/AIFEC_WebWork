<html>
  <#include "../common/header.ftl">
    <body>
    <div class="container-fluid row">
    <#--边栏-->
        <#include "../common/leftMenu.ftl">
        <div class="col-xs-12 col-md-8">
        <#--主要内容-->
            <div class="container pull-right">
            <div class="row clearfix">
                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <form role="form" method="GET" action="/sell/seller/category/save">
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