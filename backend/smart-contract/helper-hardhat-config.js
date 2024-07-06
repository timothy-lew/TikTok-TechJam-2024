const networkConfig = {
  31337: {
    name: 'localhost',
  },
  11155111: {
    name: 'sepolia',
  },
};

const developmentChains = ['hardhat', 'localhost'];
const contractJson = './constants/contractAddress.json';
const abiJson = './constants/abi.json';
const abiFile = './constants/TOKToken.abi';
const binaryFile = './constants/TOKToken.bin';

module.exports = {
  networkConfig,
  developmentChains,
  contractJson,
  abiJson,
  abiFile,
  binaryFile,
};
