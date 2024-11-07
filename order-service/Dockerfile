FROM public.ecr.aws/a0w5k8s1/ecr_repository_enlace_operativo:enlace_java

WORKDIR /app
COPY order-service/target/order-service-1.0-SNAPSHOT.jar ./
EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/app/order-service-1.0-SNAPSHOT.jar"]