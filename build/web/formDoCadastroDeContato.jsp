<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Agenda de contatos</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous"/>

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous"/>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
        
        <style>
            body{
                padding: 50px;
            }
        </style>
        
    </head>

    <body>
        <h3>Cadastro de contato</h3>
        <form action="index.jsp?acao=cadastraContato" method="post" >
            <fieldset class="form-group">
                <label for="nome">Nome:</label>
                <input class="form-control" type="text" name="nome" id="nome" size="32" maxlength="128" />
            </fieldset>

            <fieldset class="form-group">
                <label for="email">Email:</label>
                <input class="form-control" type="text" name="email" id="email" size="32" maxlength="128" />
            </fieldset>

            <fieldset class="form-group">
                <label for="telefone">Telefone:</label>
                <input class="form-control"type="text" name="telefone" id="telefone" size="32" maxlength="128" />
            </fieldset>

            <button type="submit" class="btn btn-primary">Cadastrar...</button>
            <a class="btn btn-primary" href="index.jsp">Voltar</a> 
        </form>
    </body>
</html>
