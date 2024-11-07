FROM public.ecr.aws/a0w5k8s1/ecr_repository_enlace_operativo:enlace_java

WORKDIR /app
COPY target/product-service-1.0-SNAPSHOT.jar ./
EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/product-service-1.0-SNAPSHOT.jar"]