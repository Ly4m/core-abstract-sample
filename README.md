# Shoe Stock Management API

## How to Run

* Build the project by running `mvn clean package`
* Once successfully built, run the service by using the following command:
```
java -jar controller/target/controller-1.1-SNAPSHOT.jar 
```


## REST APIs Endpoints

### Retrieve Shoes
```
GET http://localhost:8080/shoes/search
Accept: application/json
Cache-Control: no-cache
Version: 3
```

### Retrieve the stock with its state
```
GET http://localhost:8080/stock
Accept: */*
Cache-Control: no-cache
Version: 3
```


### Replace the whole stock with the given shoes
```
PATCH http://localhost:8080/stock
Content-Type: application/json
Cache-Control: no-cache
Version: 3

{
  "stock": [
    {
      "name": "Nike",
      "color": "BLUE",
      "quantity": 29,
      "size": 8
    }
  ]
}
```



### Add the given shoe to the stock
```
PATCH http://localhost:8080/stock/shoes
Content-Type: application/json
Cache-Control: no-cache
Version: 3

{
  "name": "Nike",
  "size": 8,
  "color": "BLUE"
}
```

## How to run test
Run `mvn clean test` 
