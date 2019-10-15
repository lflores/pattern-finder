# pattern-finder

## Status

El proyecto se encuentra en desarrollo y actualemente lee un archivo predeterminado con las urls
y intenta obtener tres tipos de patrones, titulo de la página, @{texto} como un referencia a
una cuenta de twitter, y #{texto} como un hashtag en twitter

### Problemas conocidos 
 - Hay sitios que se están redireccionando que se debe mejorar la consulta para obtener la redirección
 - Se está buscando @{texto} como patrón para encontrar una cuenta de twitter pero no excluye el css embebido, que puede tener el mismo patrón para identificar fonts
 - Se está buscand #{texto} para encontrar hashtag, pero no excluye el css embebido que puede contener el mismo patrón para los colores
 - Aun no se está usando de forma externa el archivo con los sites, por lo que el único archivo que carga es el contenido en resources/sites-file.csv
 - Aun no se están guardando los tags encontrados en distintos archivos.   

## Config
La carpeta resources contiene el archivo de resources/sites-file.csv para obtener las url de los sitios a escanear
``mvn clean compile``

## Tests
La carpeta test/resources contiene ejemplos de la carga y de las pruebas de los distintos tests.
``mvn clean test``

## Run

Sin argumentos y ejecuta los archivos configurados por default.
Es necesario el parámetro cleanupDaemonThreads=false para que el plugin de maven no trate de matar los threads y puedan terminar antes que la ejecucíón trate de matarlos por timeout

``mvn clean compile exec:java -Dexec.mainClass=com.triadsoft.PatternFinderApp -Dexec.cleanupDaemonThreads=false``

Con argumentos donde el parámetro sites, es la ruta al archivo cvs que contiene las url cada una en una linea distinta

``mvn clean compile exec:java -Dexec.mainClass=com.triadsoft.PatternFinderApp -Dexec.args="sites=sites.csv"``
