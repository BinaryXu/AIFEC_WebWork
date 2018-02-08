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
                            <form role="form" method="POST" action="/sell/seller/product/save">
                                <div class="form-group">
                                    <label>商品名称</label>
                                    <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!''}">
                                </div>
                                <div class="form-group">
                                    <label>商品价格</label>
                                    <input name="productPrice" type="number" step="0.01" class="form-control"value="${(productInfo.productPrice)!''}">
                                </div>
                                <div class="form-group">
                                    <label>库存</label>
                                    <input name="productStock" type="number" class="form-control"value="${(productInfo.productStock)!''}">
                                </div>
                                <div class="form-group">
                                    <label>商品描述</label>
                                    <input name="productDescription" type="text" class="form-control"value="${(productInfo.productDescription)!''}">
                                </div>
                                <div class="form-group">
                                    <label>商品图片</label>
                                    <img src="${(productInfo.productIcon)!''}">
                                    <input name="productIcon" type="text" class="form-control"value="${(productInfo.productIcon)!''}">
                                </div>
                                <div class="form-group">
                                    <label>商品类目</label>
                                    <select class="form-control" name="categoryType">
                                        <#list productCategories as productCategory>
                                            <option value="${productCategory.categoryType!}"
                                                    <#if (productInfo.categoryType)?? && productInfo.categoryType == productCategory.categoryType>
                                                        selected
                                                    </#if> >
                                                ${productCategory.categoryName}
                                            </option>
                                        </#list>
                                    </select>
                                </div>
                                <input name="productId" hidden value="${(productInfo.productId)!''}">
                                </div> <button type="submit" class="btn btn-default">提交</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>