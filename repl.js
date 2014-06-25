var arDrone = require('ar-drone');		// library aanroepen
var client  = arDrone.createClient();	// drone client maken

client.takeoff();						// opstijgen
 
client 
  .after(7000, function() {				// wacht 3 seconden
    this.land();						// zet robot op de grond
  });

