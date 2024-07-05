// seed.js
const db = db.getSiblingDB('shopping_platform');

// Drop existing database
db.dropDatabase();

// Array of collection names and their corresponding file names
const collections = [
  { name: 'buyer_profiles', file: 'shopping_platform.buyer_profiles.json' },
  { name: 'conversionRate', file: 'shopping_platform.conversionRate.json' },
  { name: 'discountRates', file: 'shopping_platform.discountRates.json' },
  { name: 'giftCards', file: 'shopping_platform.giftCards.json' },
  { name: 'items', file: 'shopping_platform.items.json' },
  { name: 'seller_profiles', file: 'shopping_platform.seller_profiles.json' },
  { name: 'transactions', file: 'shopping_platform.transactions.json' },
  { name: 'users', file: 'shopping_platform.users.json' },
  { name: 'wallets', file: 'shopping_platform.wallets.json' }
];

// Import data for each collection
collections.forEach(({ name, file }) => {
  print(`Importing ${file}...`);
  const data = JSON.parse(cat(`/data/${file}`));
  db[name].insertMany(data);
});

print("!!!!!!Import and seeding of database completed.!!!!!!!");