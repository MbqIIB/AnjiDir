<<<<<<< HEAD
iib
===

This is part of a larger 'Convert to EMI' module that's getting built in Service First.


Creating a new security profile to provide the identity for a HTTPRequest
==========================================================================

1. Create a new broker registry entry
mqsisetdbparms brokerName -n securityIdName -u username -p password

2. Create a new configurable service that refers to the broker registry entry
mqsicreateconfigurableservice broker name -c SecurityProfiles -o securityProfileName -n "propagation,idToPropagateToTransport,transportPropagationConfig" -v "TRUE,STATIC ID,securityIdName"

3. Assign the newly created security profile to the flow (this is part of the dev.env file)
=======
AnjiDir
=======
>>>>>>> 89b83cc290f13f4631fcf4048ff4afc96472a4a6
