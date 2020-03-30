FROM ktis-bastion01.container.ipc.kt.com:5000/admin/openjdk-millet:8-jdk-alpine
ADD ./target-line-engine/target/*.jar app.jar

ENV TZ Asia/Seoul

ENV JAVA_OPTS="-Xms2G -Xmx4G -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=1024m"
ENV JAVA_OPTS="${JAVA_OPTS} -Djava.net.preferIPv4Stack=true -XX:+UseG1GC -XX:+UnlockDiagnosticVMOptions -XX:+G1SummarizeConcMark -XX:InitiatingHeapOccupancyPercent=35 -XX:G1ConcRefinementThreads=20"

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]