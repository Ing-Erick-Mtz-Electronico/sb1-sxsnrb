FROM public.ecr.aws/a0w5k8s1/ecr_repository_enlace_operativo:enlace_java

WORKDIR /app
COPY user-service/target/user-service-1.0-SNAPSHOT.jar ./
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/user-service-1.0-SNAPSHOT.jar"]