1. Build core project
- Connect MySQL
- Create DB with name : ecard
- Open command and cd to eCard-Parent/eCard-core folder
- Run command : mvn clean install
	
2. Build and run API webapp:
- Open command and cd to ecard-api
- Run command: mvn clean install -Pstart-jetty -Djetty.port=9090
    -Pstart-jetty : start with jetty server
	-Djetty.port=9090 : run with port 9090 (you can change port if you want)
	
Note: You can run with tomcat server