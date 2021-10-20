# BitcoinCalc

> "La experiencia es algo maravilloso, nos permite reconocer un error cada vez que lo volvemos a cometer"
> - Franklin P. Jones

**Presentacion:**
BitcoinCalc es una API Rest capaz de tomar el valor del bitcoin en dolares y calcular el promedio, valor maximo y direrencia porsentual.

**Solucion y tecnologias:**
BitcoinCalc utiliza WebClient para hacer peticiones cada 10 segundo a la API cex.io que proporciona el valor actual del Bitcoin.
Se genera un flujo de peticiones infinitas sobre el cual se extrae el valor maximo y se almacena en memoria sobre H2. Para ello se utiliza JPA(Para mapear una unica tabla que contiene el valor maximo) y JDBC para generar dicha conexion. Este valor es actualizado cada vez que el se encuenta un valor mayor en el flujo. Sobre este mismo flujo obtenemos el valor promedio entre dos timestamps, para finalmente entregarlo junto con el valor maximo y la diferencia porsentual entre ellos (en el caso que sea negativo).
Se utilizan herramientas como Lombock y Gson (Gson es implementado para recibir el valor string que trae la API y convertirlo a json para una mejor utiizacion). Todas las dependencias son actualizadas por Gradle.

Contamos con 2 endpoints de peticiones GET, sin parametros y como un evento de stream (Esto ultimo nos permite obtener secuencialmente cada elemento resuelto en el flujo).

* *getBitcoin* nos proporciona el valor actual del Bitcion actualizandose cada 10 segundo (Solo por diversion).

* *getCalc* nos proporciona el promedio del ultimo timestamp, el valor maximo registrado en memoria, la diferencia porsentual entre ellos y el momento en el que esta peticion fue actualizada.

**Intrucciones de ejecucion local**

* Una vez clonado el repositorio, se debe actualizar las dependencias Gradle.

* Una vez levantado el proyecto, podemos observar su comportamiendo a travez de una ventana de comandos.

* Ejecucion de *getBitcoin* :
    * Utilizamos el comando:
      `curl http://localhost:8080/bitcoin`
      Este nos devuelve el valor actual del Bitcoin cada 10 segundos.

* Ejecucion de *getCalc*:
    * Utilizamos el comando:
      `curl http://localhost:8080/bitcoin/calc`
      Este nos devuelve el el valor maximo registrado en memoria, la diferencia porsentual entre ellos y el momento en el que esta peticion fue actualizada.