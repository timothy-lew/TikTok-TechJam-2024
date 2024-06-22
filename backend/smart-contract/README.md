# Smart Contracts

# Install nodejs

1. Install nodenv

```console
brew install nodenv
```

2. Set up nodenv in your shell by adding this to ~/.zshrc

```console
which nodenv > /dev/null && eval "$(nodenv init -)"
```

3. Restart your terminal so your changes take effect.

4. Verify that nodenv is properly set up using this nodenv-doctor script:

```console
$ curl -fsSL https://github.com/nodenv/nodenv-installer/raw/master/bin/nodenv-doctor | bash
Checking for `nodenv' in PATH: /usr/local/bin/nodenv
Checking for nodenv shims in PATH: OK
Checking `nodenv install' support: /usr/local/bin/nodenv-install (node-build 3.0.22-4-g49c4cb9)
Counting installed Node versions: none
  There aren't any Node versions installed under `~/.nodenv/versions'.
  You can install Node versions like so: nodenv install 2.2.4
Auditing installed plugins: OK
```

5. Install node 20.9.0

```console
nodenv install 20.9.0
```

6. Installs shims for all Node executables known to nodenv

```console
nodenv rehash
```

7. Set node version locally

```console
nodenv local 20.9.0
```

8. Verify node version

```console
node --version
```

# Install node dependencies

1. Installation

```console
npm i
```

### Error: `distutils not found` when running `npm install`

If you encounter the error `distutils not found` when running `npm install`, you can refer to the solution provided in this [Stack Overflow post](https://stackoverflow.com/questions/77251296/distutils-not-found-when-running-npm-install).

# Solidity

1. Compile contracts

```console
yarn hardhat compile
```

2. Run local blockchain on hardhat

```console
yarn hardhat node
```

# Metamask Wallet Setup

1. **Install Metamask Extension**

   - Install the Metamask extension on Google Chrome.

2. **Import TikTok's Wallet**

   - Click on "Add Account" -> "Import Account".
   - Enter the following private key for TikTok's account: `0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80`.

3. **Import User's Wallet**

   - Repeat step 2 using the following private key for User's account: `0x59c6995e998f97a5a0044966f0945389dc9e86dae88c7a8412f4603b6b78690d`.

4. **Import Local Hardhat Network**

   - Follow the instructions on [Metamask Docs](https://docs.metamask.io/wallet/how-to/get-started-building/run-devnet/) to import the local Hardhat network with the following configuration:
     - **Network Name:** localhost-hardhat
     - **RPC URL:** http://127.0.0.1:8545/
     - **Chain ID:** 31337
     - **Currency Symbol:** ETH

5. **Run Local Blockchain Network and deploy TOKToken contract**

   - Run the following command to start up the local blockchain network:
     ```sh
     yarn hardhat node
     ```

6. **Import TOKToken to Metamask**

   - Import `TOKToken` to Metamask by specifying the contract address: `0x5FbDB2315678afecb367f032d93F642f64180aa3`.

7. **Reset Nonce Settings**

   - If you encounter a nonce error in Metamask, reset the nonce settings for both wallets ([Reset Nonce](https://docs.metamask.io/wallet/how-to/run-devnet/#reset-your-local-nonce-calculation)).

8. Run tests

```
yarn test
```

6. Generate wrapper code for Java (only needed for Timothy)

```
web3j generate solidity -b ./constants/TOKToken.bin -a ./constants/TOKToken.abi -o ../backend/src/main/java -p com.example.backend.contract
```
