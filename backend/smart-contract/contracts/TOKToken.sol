// SPDX-License-Identifier: GPL-3.0

pragma solidity ^0.8.7;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract TOKToken is ERC20 {
    address public creator;

    constructor() ERC20("TOKToken", "TOK") {
        // mint 10000 coins to creator of contract
        _mint(msg.sender, 10000 * 10 ** 18);
        creator = msg.sender;
    }

    function getCreator() public view returns (address) {
        return creator;
    }

    // function to allow users to request tokens from creator
    function requestTokens(uint256 amount) public {
        // faucet is only available to non-creator address
        require(msg.sender != creator, "Faucet is not available to creator");

        // ensure creator has enough coins before transferring
        require(
            balanceOf(creator) >= amount,
            "Insufficient balance for requested amount"
        );
        require(amount < 100 * 10 ** 18, "Can only request less than 100");

        // transfer 'amount' of tokens from creator to the requester
        _transfer(creator, msg.sender, amount);
    }
}
