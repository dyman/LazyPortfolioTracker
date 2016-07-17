var express = require('express');
var router = express.Router();


var pgp = require("pg-promise")(/*options*/);
var db = pgp("postgres://FAS:FAS@127.0.0.1:5432/FAS");



//db.all("Select * from quote", function(error,rows){
//		console.log("data:",rows);		
//	}
//	)

db.any("select * from quote")
    .then(function (data) {
        console.log("DATA:", data); // print data;
    })
    .catch(function (error) {
        console.log("ERROR:", error); // print the error;
    })
    

db.one("SELECT $1 as value", 123)
    .then(function (data) {
        console.log("DATA:", data.value);
    })
    .catch(function (error) {
        console.log("ERROR:", error);
    });


/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('this is gonna be the quote page');
});

module.exports = router;
