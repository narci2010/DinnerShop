FROM gcr.io/google_appengine/openjdk
ADD dinner-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["/docker-entrypoint.bash"]
CMD ["java","-jar","/app/dinner-0.0.1-SNAPSHOT.jar"]