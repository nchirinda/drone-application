# Drone Application
REST API application that allows clients to communicate with the drones for functions such as delivery of small items that are (urgently) needed in locations with difficult access.

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org)

# Getting Started

### Cloning the project

clone repository from gitub using this command:

```
git clone https://github.com/nchirinda/drone-application
```

### Running the application locally

There are several ways to run the Drone Application on your local machine. 
1. One way is to execute the `main` method in the `com.assesment.droneapplication.DroneApplication` class from your IDE.

2. Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:
`mvn spring-boot:run`

 - The application will be running on port 8080, make sure the port is not occupied before running the application otherwise the application will fail to start 
 - To view the database tables enter the following link on your web browser
```
    http://localhost:8080/dronedb
```

 - Change the JDBC URL value to: `jdbc:h2:mem:dronedb` and User Name to `drone_admin`. And leave the Password field empty

### Testing the application

Initially there is preloaded data that is loaded when you run the application. You can view the data by running the curl commands or using an HTTP client application such as Postman;
        
- GET All Drones - `curl --location --request GET 'http://localhost:8080/api/v1/drones' \
  --header 'Content-Type: application/json'`
- GET All Medication - `curl --location --request GET 'http://localhost:8080/api/v1/medication' \
  --header 'Content-Type: application/json'`

The application can perform the following tasks;

1. Register a Drone - 
   ``` 
   curl --location --request POST 'http://localhost:8080/api/v1/drones/register' \
      --header 'Content-Type: application/json' \
      --data-raw '{
      "serialNumber": "ALKDFKJB",
      "weightLimit": 450,
      "batteryCapacity": 96
      }'

2. Loading a drone with medication items -
    ``` 
   curl --location --request POST 'http://localhost:8080/api/v1/drones/${droneId}/load_medication' \
    --header 'Content-Type: application/json' \
    --data-raw '[
    {
        "id": "02b0b316-214e-43a2-8cb0-4a6f0f4d911c",
        "createdDateTime": "2023-02-01T16:54:34.665399",
        "name": "Meizol",
        "weight": 100.0,
        "code": "MD0029",
        "image": {
            "name": "meizol_image",
            "type": "png",
            "bytes": "lkjndfs8o7tvlbjf="
        }
    }
    ]'

3. Checking loaded medication items for a given drone -
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/drones/${droneId}/medication_items' \
    --header 'Content-Type: application/json'
    ```

4. Check available drone for loading - 
    ```
   curl --location --request GET 'http://localhost:8080/api/v1/drones/available_drones' \
    --header 'Content-Type: application/json'
   ````
5. Check drone battery level for a given drone - 
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/drones/${droneId}/battery_level' \
    --header 'Content-Type: application/json'
   ```