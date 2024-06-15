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

5. Install node 16.14.2

```console
nodenv install 16.14.2
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

3. Deploy locally

```
yarn hardhat deploy --network hardhat/localhost
```

4.0. Import local hardhat network to metamask. (https://docs.metamask.io/wallet/how-to/get-started-building/run-devnet/)  
4.1. Import coin to metamask by specifying the contract address.  
4.2. If you get a nonce error in metamask, reset the metamask nonce settings for both wallets

5. Run tests

```
yarn test
```
