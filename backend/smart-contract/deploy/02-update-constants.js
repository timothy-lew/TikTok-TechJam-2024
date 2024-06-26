const {
  contractJson,
  abiJson,
  abiFile,
  binaryFile,
} = require('../helper-hardhat-config');
const fs = require('fs');
const { network } = require('hardhat');

module.exports = async () => {
  console.log('Writing to constants...');
  await updateContractAddresses();
  await updateAbi();
  await updateBytecode();
  console.log('constants written!');
};

async function updateAbi() {
  const tokToken = await ethers.getContract('TOKToken');
  fs.writeFileSync(
    abiJson,
    tokToken.interface.format(ethers.utils.FormatTypes.json)
  );

  fs.writeFileSync(
    abiFile,
    tokToken.interface.format(ethers.utils.FormatTypes.json)
  );
}

async function updateBytecode() {
  const tokToken = await ethers.getContractFactory('TOKToken');
  fs.writeFileSync(binaryFile, tokToken.bytecode);
}

async function updateContractAddresses() {
  const tokToken = await ethers.getContract('TOKToken');
  const contractAddress = JSON.parse(fs.readFileSync(contractJson, 'utf8'));
  console.log(contractAddress);
  contractAddress[network.config.chainId.toString()] = tokToken.address;
  fs.writeFileSync(contractJson, JSON.stringify(contractAddress));
}
module.exports.tags = ['all', 'frontend'];
