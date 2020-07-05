# Centime First service

Create 
Db Connection: 
Host a database in local or aws (postgres or mysql or inmemory db(H2)).
Configure your microservice to connect to db.
Create Hibernate Entity Class  based on the below table requirement to auto create tables in hosted db.
Populate the db from a post request with the below data or directly insert into db.

Expose endpoints to fetch requests based on Id and complete list of objects.
While getting the complete table, Modify the result set(arrays or list) to form nested object structure.
Below is the nested output in json. Associate color to each object according to table.

Create a table with fields ID, Name, Color, ParentId and populate with below data.
