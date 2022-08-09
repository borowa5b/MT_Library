FROM adoptopenjdk/openjdk11
RUN set -x \
    mkdir -p /app
COPY ./build/libs/Library-0.1-all.jar /app/
EXPOSE 8080
WORKDIR /app
CMD ["java", "-jar", "Library-0.1-all.jar"]
