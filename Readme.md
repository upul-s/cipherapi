### Answers to Questions 
Write a small description of your solutions time and space complexity
1.Since we are processing each character of the message - Time complexity is O(n)
2.Since we are using a constant space - Space complexity is O(1).

Can you avoid the brute force approach?
We cannot avoid, but we can make it bit harder to break by using different shift numbers in different places/ indexes with a maintaining pattern.
Ex: message "testcypher" , rotation = 1  --- pattern 4,8,5,2
First index shift in 4 : t --> 4+1 --> y
Second index shift in 8 : e--> 9 --> m
third index shift in 5
forth index shift in 2
Then repeat the same pattern for the remaining indexes.

-- Use of a randomly generated private key is another way to make this harder to break

## The contents and how to run
In the zip-file you will find the java project containing the cipher api service .I have prepared the dockerization ,

To get things running you have to do the following:

1. just run run.sh file in project folder.


### Database
As a developer I don't want to use the in-memory database but for this project I have used in-memory database

### Monitoring
As a developer I want to see the health of our services on a page. So I have added actuator endpoints if we want to use prometheus and grafana later we can integrate



## Endpoints
1. Get a list from the restaurant service:<br> ```GET localhost:8080/api/v1/ciphers```
2. Update a restaurant status<br>```POST localhost:8080/api/v1/ciphers```

The POST  requires the following payload:
```json
{
  "message":"test",
  "rotation":5
}
```
