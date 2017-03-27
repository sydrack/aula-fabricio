<%@page import="br.univali.sisnet.controller.Controlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda de contatos</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>


        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

        <style>
            body{
                padding: 50px;
            }
        </style>

    </head>
    <body>
        <jsp:include page="WEB-INF/menu.jsp"/>

        <%
            Controlador controlador = new Controlador(request);
            controlador.processaRequisicao();
        %>

        <c:if test="${not empty contatos}">
            <h2>Lista de Contatos</h2>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Telefones</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${contatos}" var="umContato">
                        <tr>
                            <td>${umContato.nome}</td>

                            <td> <!-- telefones -->
                                <c:if test="${not empty umContato.telefones}">
                                    <ul class="list-unstyled">
                                        <c:forEach items="${umContato.telefones}" var="telefone" varStatus="status">
                                            <li>
                                                ${telefone.numero}
                                                <a class="glyphicon glyphicon-remove" style="color: red;" href="index.jsp?acao=deletarTelefone&idDoTelefone=${telefone.id}&idDoContato=${umContato.id}"></a>  
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:if>


                                <form class="form-inline" id="formTelefone${umContato.id}" action="index.jsp?acao=cadastrarTelefone" method="post">
                                    <a id="botaoAddTelefone${umContato.id}" class="glyphicon glyphicon-plus" href="#"></a>
                                    <input type="text" class="form-control" name="telefone" id="telefone" placeholder="Novo telefone">
                                    <input type="hidden" name="idDoContato" value="${umContato.id}"/>
                                </form>
                                <script>
                                    $("#formTelefone${umContato.id} input:eq(0)").hide();
                                    $("#botaoAddTelefone${umContato.id}").click(function () {
                                        $("#formTelefone${umContato.id} input:eq(0)").toggle();
                                    });
                                </script>
                            </td>

                            <!-- emails -->
                            <td>
                                <c:if test="${not empty umContato.emails}">
                                    <ul class="list-unstyled">
                                        <c:forEach items="${umContato.emails}" var="email" varStatus="status">
                                            <li>
                                                ${email.endereco}
                                                <a class="glyphicon glyphicon-remove" style="color: red;" href="index.jsp?acao=deletarEmail&idDoEmail=${email.id}&idDoContato=${umContato.id}"></a>  
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:if>

                                <form class="form-inline" id="formEmail${umContato.id}" action="index.jsp?acao=cadastrarEmail" method="post">
                                    <a id="botaoAddEmail${umContato.id}" class="glyphicon glyphicon-plus" href="#"></a>
                                    <input type="text" class="form-control" name="email" id="email" placeholder="Novo email">
                                    <input type="hidden" name="idDoContato" value="${umContato.id}"/>
                                </form>
                                <script>
                                    $("#formEmail${umContato.id} input:eq(0)").hide();
                                    $("#botaoAddEmail${umContato.id}").click(function () {
                                        $("#formEmail${umContato.id} input:eq(0)").toggle();
                                    });
                                </script>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty contatos}">
            <h4>Nenhum contato cadastrado!</h4>
        </c:if>
    </body>
</html>
