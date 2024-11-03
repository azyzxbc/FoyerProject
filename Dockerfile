FROM openjdk:17-jdk-alpine

# Installer curl
RUN apk add --no-cache curl

# Expose le port de l'application Spring Boot
EXPOSE 8089

# Téléchargez le fichier JAR depuis Nexus
# Remplacez localhost par l'adresse IP de votre machine ou le nom de domaine
RUN curl -u ${NEXUS_USER}:${NEXUS_PASSWORD} -o /tp-foyer-5.0.0.jar \
    http://192.168.50.4:8081/repository/maven-releases/tn/esprit/tp-foyer/5.0.0/tp-foyer-5.0.0.jar

# Commande d'exécution de l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/tp-foyer-5.0.0.jar"]
