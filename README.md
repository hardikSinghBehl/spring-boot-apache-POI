## Reference POC projects to work with excel files using Apache POI library and Java Spring-boot
##### All Proof of concepts are deployed to heroku with their links in their README.md file 

### Proof of concepts included in this repo (Summary)
* **Create excel file with single sheet from database records**
  * Insert 300 dummy employees records in H2 DB on startup using @PostConstruct
  * endpoint exposed to download created excel sheet from the inserted records at runtime
* **Create excel file with multiple sheet from database records**
  * insert super_heroes from different comics in DB at startup (Marvel, Detective Comics)
  * endpoint exposed to download multiple sheet excel file created at runtime
  * each sheet contains records of super_heroes from one comic
  * one sheet for all superheroes from marvel comics, one for DC and so on.
* **Validate data inside excel sheet for errors and return errors as List.of(String)**
  * endpoint exposed for user to download excel template (required columns)
  * user fills in data (rows) to downloaded template
  * exception is thrown is file extension or format(column names and order) is different
  * the errors include leaving cells blank, duplicate email-id, wrong email-id, invalid number type etc
  * each row is validated for errors, if errors exists a list is returned detailing out the error messages along with it's cell position
* **Validate data inside excel sheet for errors and detail out errors in given excel file itself and return the same**
  * same as above (first 4)
  * each cell of each row is validated for errors and if a cell is found to have any error it is colored in RED
  * a new sheet in the same excel file is created detailing out the error messages (type) and the cell position (row, column)
  * the above modified file is returned (downloaded automatically to users screen) if errors exists
* **Read data from excel sheet to database**
  * endpoint exposing to download required excel sheet template (required columns)
  * sheet is validated for errors, if no errors are found a unique 6 digit code and the hex of the validated file is generated, the code is returned as response
  * the code is stored in a cache as key with the validated file's hex being it's value
  * user hits /employee/bulk-upload API with given code and validated file (error thrown if code is invalid or file other than the validated one is provided)
  * each row is read from the given sheet and corresponding records are created in the database
