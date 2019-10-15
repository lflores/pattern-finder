# pattern-finder


## Config
``mvn clean compile``

## Tests

``mvn clean test``

## Run

Sin argumentos y ejecuta los archivos configurados por default.
Es necesario el parámetro cleanupDaemonThreads=false para que el plugin de maven no trate de matar los threads y puedan terminar antes que la ejecucíón trate de matarlos por timeout

``mvn clean compile exec:java -Dexec.mainClass=com.triadsoft.PatternFinderApp -Dexec.cleanupDaemonThreads=false``

Con argumentos donde el parámetro sites, es la ruta al archivo cvs que contiene las url cada una en una linea distinta

``mvn clean compile exec:java -Dexec.mainClass=com.triadsoft.PatternFinderApp -Dexec.args="sites=sites.csv"``
