## Run Spring Boot application

At ```/backend``` folder

1. Make a copy of .env.example and rename it to .env
- fill up necessary details: DB_URL, DB_USERNAME, DB_PASSWORD

2. Export the variables to your environment by running
```
export $(cat .env | xargs)
```

3. Finally just run the command
```
mvn spring-boot:run
```