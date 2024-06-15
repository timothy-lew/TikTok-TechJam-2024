const { assert, expect } = require('chai');
const { network, deployments, ethers, getNamedAccounts } = require('hardhat');
const { developmentChains } = require('../helper-hardhat-config');

!developmentChains.includes(network.name)
  ? describe.skip
  : describe('TOK', function () {
      let tok; // tok smart contract
      let deployer; // contract creator address
      let trader1;
      beforeEach(async () => {
        const accounts = await getNamedAccounts();
        deployer = accounts.deployer;
        trader1 = accounts.trader1;
        await deployments.fixture(['all']);
        tok = await ethers.getContract('TOKToken', deployer);
        apaTrader = await ethers.getContract('TOKToken', trader1);
      });

      describe('constructor', function () {
        it('check TOK contract deployed correctly', async () => {
          const response = await tok.symbol();
          assert.equal(response, 'TOK');
        });

        it('get creator', async () => {
          const creatorAddress = await tok.getCreator();
          assert.equal(deployer, creatorAddress);
        });

        it('send TOK tokens to another address', async () => {
          const [deployer, secondAccount] = await ethers.getSigners();
          const amountToSend = ethers.utils.parseUnits('20', 18); // Send 20 TOK tokens

          const initialBalanceDeployer = await tok.balanceOf(deployer.address);
          const initialBalanceSecondAccount = await tok.balanceOf(
            secondAccount.address
          );

          const tx = await tok
            .connect(deployer)
            .transfer(secondAccount.address, amountToSend);
          await tx.wait();

          const finalBalanceDeployer = await tok.balanceOf(deployer.address);
          const finalBalanceSecondAccount = await tok.balanceOf(
            secondAccount.address
          );

          assert.equal(
            finalBalanceDeployer.toString(),
            initialBalanceDeployer.sub(amountToSend).toString()
          );
          assert.equal(
            finalBalanceSecondAccount.toString(),
            initialBalanceSecondAccount.add(amountToSend).toString()
          );
        });
      });

      it('should allow a user to request tokens from faucet', async function () {
        const initialBalance = await tok.balanceOf(trader1);
        const amountToRequest = ethers.utils.parseEther('70');

        await apaTrader.requestTokens(amountToRequest);
        const finalBalance = await tok.balanceOf(trader1);

        const expectedFinalBalance = initialBalance.add(amountToRequest);

        assert.equal(
          finalBalance.toString(),
          expectedFinalBalance.toString(),
          'trader1 did not receive the requested tokens'
        );
      });

      it('request too much from faucet', async function () {
        const amountToRequest = ethers.utils.parseEther('200');

        await expect(
          apaTrader.requestTokens(amountToRequest)
        ).to.be.revertedWith('Can only request less than 100');
      });

      it('not enough balance', async function () {
        const amountToRequest = ethers.utils.parseEther('20000');

        await expect(
          apaTrader.requestTokens(amountToRequest)
        ).to.be.revertedWith('Insufficient balance for requested amount');
      });
    });
