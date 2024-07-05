#!/bin/bash
set -e

# MongoDB connection details
MONGO_HOST="localhost"
MONGO_PORT="27017"
MONGO_DB="${MONGO_INITDB_DATABASE}"
MONGO_USER="${MONGO_INITDB_ROOT_USERNAME}"
MONGO_PASS="${MONGO_INITDB_ROOT_PASSWORD}"

echo "Dropping existing database and creating a new one..."
mongosh --host $MONGO_HOST --port $MONGO_PORT -u $MONGO_USER -p $MONGO_PASS --authenticationDatabase admin <<EOF
use $MONGO_DB
db.dropDatabase()
EOF

# Array of JSON file names
JSON_FILES=(
  "shopping_platform.buyer_profiles.json"
  "shopping_platform.conversionRate.json"
  "shopping_platform.discountRates.json"
  "shopping_platform.giftCards.json"
  "shopping_platform.items.json"
  "shopping_platform.seller_profiles.json"
  "shopping_platform.transactions.json"
  "shopping_platform.users.json"
  "shopping_platform.wallets.json"
)

# Loop through each JSON file and import it
for file in "${JSON_FILES[@]}"; do
  echo "Importing $file..."
  collection=$(echo $file | sed 's/shopping_platform\.\(.*\)\.json/\1/')
  mongoimport --host $MONGO_HOST --port $MONGO_PORT -u $MONGO_USER -p $MONGO_PASS \
    --authenticationDatabase admin --db $MONGO_DB --collection $collection \
    --file /data/$file --jsonArray
done

echo "Import completed."