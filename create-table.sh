#!/bin/bash

aws dynamodb create-table --endpoint-url http://localhost:4566  \
--table-name Product \
--attribute-definitions \
	AttributeName=ID,AttributeType=S \
--key-schema \
	AttributeName=ID,KeyType=HASH \
--provisioned-throughput \
ReadCapacityUnits=5,WriteCapacityUnits=5 \
--global-secondary-indexes \
'[
  {
    "IndexName": "IDIndex",
    "KeySchema": [
      {
        "AttributeName": "ID",
        "KeyType": "HASH"
      }
    ],
    "Projection": {
      "ProjectionType": "ALL"
    },
    "ProvisionedThroughput": {
      "ReadCapacityUnits": 10,
      "WriteCapacityUnits": 5
    }
  }
]'