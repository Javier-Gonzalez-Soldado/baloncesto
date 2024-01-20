<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Baloncesto</title>
    </head>
    <body>
        <h1>Resultado de las votaciones:</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Votos</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="jugador" items="${jugadores}">
                    <tr>
                        <td>${jugador.nombre}</td>
                        <td>${jugador.votos}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br />
        <a href="index.html">Volver a la pagina principal</a>
    </body>
</html>
