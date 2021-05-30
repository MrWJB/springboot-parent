<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图书列表</title>
</head>
<body>
<table border="1">
    <tr>
        <td>图书编号</td>
        <td>图书名称</td>
        <td>图书作者</td>
    </tr>
<#--    第15行首先判断model中的books不为空，并且books中有数据，然后进行遍历。-->
    <#if books ??&& (books?size>0)>
        <#list books as book>
            <tr>
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.author}</td>
            </tr>
        </#list>
    </#if>
</table>
</body>
</html>