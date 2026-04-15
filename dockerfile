FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY . .

# ✅ FIX: give permission to mvnw
RUN chmod +x mvnw

# build the app
RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/reg-0.0.1-SNAPSHOT.jar"]