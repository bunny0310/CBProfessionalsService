FROM openjdk

EXPOSE 8080
EXPOSE 8081

COPY ProfessionalsService.jar .
COPY config_prod.yml .
CMD ["java","-jar","ProfessionalsService.jar","server","config_prod.yml"]


