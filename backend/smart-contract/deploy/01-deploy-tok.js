const { network } = require('hardhat');
const {
  networkConfig,
  developmentChains,
} = require('../helper-hardhat-config');
// const { verify } = require('../utils/verify');
require('dotenv').config();

module.exports = async ({ getNamedAccounts, deployments }) => {
  const { deploy, log } = deployments;
  const { deployer } = await getNamedAccounts(); // get from hardhat.config.json

  log('----------------------------------------------------');
  log('Deploying TOK and waiting for confirmations...');
  const TOK = await deploy('TOKToken', {
    from: deployer,
    log: true,
    // we need to wait if on a live network so we can verify properly
    waitConfirmations: network.config.blockConfirmations || 1,
  });
  // log(TOK);
  log(`TOKToken deployed at ${TOK.address}`);
};

module.exports.tags = ['all', 'apa'];
