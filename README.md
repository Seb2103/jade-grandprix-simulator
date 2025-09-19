javac -cp "lib/*;." -d bin src/DriverAgent.java

java -cp "bin;lib/*" jade.Boot -gui -agents "driver:DriverAgent"