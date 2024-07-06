require('hardhat-deploy');
require('dotenv').config();
require('@nomiclabs/hardhat-waffle');

/**
 * @type import('hardhat/config').HardhatUserConfig
 */

module.exports = {
  defaultNetwork: 'hardhat',
  networks: {
    hardhat: {
      chainId: 31337,
      initialBaseFeePerGas: 0,
    },
    localhost: {
      chainId: 31337,
      initialBaseFeePerGas: 0,
    },
  },
  namedAccounts: {
    deployer: {
      default: 0, // here this will by default take the first account as deployer
      1: 0, // similarly on mainnet it will take the first account as deployer. Note though that depending on how hardhat network are configured, the account 0 on one network can be different than on another
    },
    trader1: {
      default: 1,
    },
    trader2: {
      default: 2,
    },
  },
  solidity: {
    compilers: [
      {
        version: '0.8.7',
      },
      {
        version: '0.8.20',
      },
      {
        version: '0.4.24',
      },
    ],
  },
  mocha: {
    timeout: 500000, // 500 seconds max for running tests
  },
};
