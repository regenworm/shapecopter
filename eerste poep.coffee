arDrone = require("ar-drone") # include dependencies
drone = arDrone.createClient() # initialize interface with drone

drone.takeoff() # take off
drone.move .5 # move drone at half maximum speed
drone.after 5000, -> # after 5 seconds
	@stop() # stop @(shorthand for this)


