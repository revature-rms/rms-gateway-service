export FARGATE_IP=$(wget -q -O - http://169.254.170.2/v2/metadata | jq -r .Containers[0].Networks[0].IPv4Addresses[0])
echo $FARGATE_IP
sh -c java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -Dspring.profiles.active=$SPRING_ENV -jar /app.jar