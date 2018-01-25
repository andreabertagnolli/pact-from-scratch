# Pact-from-scratch

just playing...

## Pact broker
Start it

```sudo docker-compose up```

## Create contracts

```mvn clean test```

## Pact standalone

Install it from  
https://github.com/pact-foundation/pact-ruby-standalone

Check if you can deploy a service:   
```./bin/pact-broker can-i-deploy --broker-base-url localhost:1312 --pacticipant cook --latest```