const express = require("express");
const cors = require("cors");
const dotenv = require('dotenv');
const apiRouter = require('./api');

dotenv.config();
const app = express();
app.use(cors());
app.use(express.json());

//use Api routes
app.use('/api', apiRouter);



//Start Port
const PORT = process.env.PORT || 5001;
app.listen(PORT, () =>{
    console.log(`Server is running on port ${PORT}`);
});
