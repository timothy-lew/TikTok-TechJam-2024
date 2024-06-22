package com.zuehlke.blockchain.model;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.11.3.
 */
@SuppressWarnings("rawtypes")
public class ERC20PresetMinterPauser extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b50604051611c40380380611c4083398101604081905261002e91610291565b8181600561003c8382610379565b5060066100498282610379565b50506007805460ff19169055506100605f336100bb565b61008a7f9f2df0fed2c77648de5860a4cc508cd0818c85b8b8a1ab4ceeef8d981c8956a6336100bb565b6100b47f65d7a28e3265b37a6474929f336521b332c1681b933f6cb9f3376673440d862a336100bb565b5050610433565b6100c582826100c9565b5050565b6100d382826100ef565b5f8281526001602052604090206100ea908261018b565b505050565b5f828152602081815260408083206001600160a01b038516845290915290205460ff166100c5575f828152602081815260408083206001600160a01b03851684529091529020805460ff191660011790556101473390565b6001600160a01b0316816001600160a01b0316837f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a45050565b5f61019f836001600160a01b0384166101a8565b90505b92915050565b5f8181526001830160205260408120546101ed57508154600181810184555f8481526020808220909301849055845484825282860190935260409020919091556101a2565b505f6101a2565b634e487b7160e01b5f52604160045260245ffd5b5f82601f830112610217575f80fd5b81516001600160401b03811115610230576102306101f4565b604051601f8201601f19908116603f011681016001600160401b038111828210171561025e5761025e6101f4565b604052818152838201602001851015610275575f80fd5b8160208501602083015e5f918101602001919091529392505050565b5f80604083850312156102a2575f80fd5b82516001600160401b038111156102b7575f80fd5b6102c385828601610208565b602085015190935090506001600160401b038111156102e0575f80fd5b6102ec85828601610208565b9150509250929050565b600181811c9082168061030a57607f821691505b60208210810361032857634e487b7160e01b5f52602260045260245ffd5b50919050565b601f8211156100ea57805f5260205f20601f840160051c810160208510156103535750805b601f840160051c820191505b81811015610372575f815560010161035f565b5050505050565b81516001600160401b03811115610392576103926101f4565b6103a6816103a084546102f6565b8461032e565b6020601f8211600181146103d8575f83156103c15750848201515b5f19600385901b1c1916600184901b178455610372565b5f84815260208120601f198516915b8281101561040757878501518255602094850194600190920191016103e7565b508482101561042457868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b611800806104405f395ff3fe608060405234801561000f575f80fd5b50600436106101bb575f3560e01c806370a08231116100f3578063a457c2d711610093578063d53913931161006e578063d5391393146103a2578063d547741f146103c9578063dd62ed3e146103dc578063e63ab1e9146103ef575f80fd5b8063a457c2d714610369578063a9059cbb1461037c578063ca15c8731461038f575f80fd5b80639010d07c116100ce5780639010d07c1461031c57806391d148541461034757806395d89b411461035a578063a217fddf14610362575f80fd5b806370a08231146102d957806379cc6790146103015780638456cb5914610314575f80fd5b8063313ce5671161015e5780633f4ba83a116101395780633f4ba83a146102a057806340c10f19146102a857806342966c68146102bb5780635c975abb146102ce575f80fd5b8063313ce5671461026b57806336568abe1461027a578063395093511461028d575f80fd5b806318160ddd1161019957806318160ddd1461020f57806323b872dd14610221578063248a9ca3146102345780632f2ff15d14610256575f80fd5b806301ffc9a7146101bf57806306fdde03146101e7578063095ea7b3146101fc575b5f80fd5b6101d26101cd366004611500565b610416565b60405190151581526020015b60405180910390f35b6101ef610440565b6040516101de9190611527565b6101d261020a366004611577565b6104d0565b6004545b6040519081526020016101de565b6101d261022f36600461159f565b6104e7565b6102136102423660046115d9565b5f9081526020819052604090206001015490565b6102696102643660046115f0565b61050a565b005b604051601281526020016101de565b6102696102883660046115f0565b610533565b6101d261029b366004611577565b6105b6565b6102696105d7565b6102696102b6366004611577565b61067d565b6102696102c93660046115d9565b61071c565b60075460ff166101d2565b6102136102e736600461161a565b6001600160a01b03165f9081526002602052604090205490565b61026961030f366004611577565b610729565b61026961073e565b61032f61032a366004611633565b6107e2565b6040516001600160a01b0390911681526020016101de565b6101d26103553660046115f0565b610800565b6101ef610828565b6102135f81565b6101d2610377366004611577565b610837565b6101d261038a366004611577565b6108b1565b61021361039d3660046115d9565b6108be565b6102137f9f2df0fed2c77648de5860a4cc508cd0818c85b8b8a1ab4ceeef8d981c8956a681565b6102696103d73660046115f0565b6108d4565b6102136103ea366004611653565b6108f8565b6102137f65d7a28e3265b37a6474929f336521b332c1681b933f6cb9f3376673440d862a81565b5f6001600160e01b03198216635a05180f60e01b148061043a575061043a82610922565b92915050565b60606005805461044f9061167b565b80601f016020809104026020016040519081016040528092919081815260200182805461047b9061167b565b80156104c65780601f1061049d576101008083540402835291602001916104c6565b820191905f5260205f20905b8154815290600101906020018083116104a957829003601f168201915b5050505050905090565b5f336104dd818585610956565b5060019392505050565b5f336104f4858285610a79565b6104ff858585610af1565b506001949350505050565b5f8281526020819052604090206001015461052481610ca5565b61052e8383610caf565b505050565b6001600160a01b03811633146105a85760405162461bcd60e51b815260206004820152602f60248201527f416363657373436f6e74726f6c3a2063616e206f6e6c792072656e6f756e636560448201526e103937b632b9903337b91039b2b63360891b60648201526084015b60405180910390fd5b6105b28282610cd0565b5050565b5f336104dd8185856105c883836108f8565b6105d291906116c7565b610956565b6106017f65d7a28e3265b37a6474929f336521b332c1681b933f6cb9f3376673440d862a33610800565b6106735760405162461bcd60e51b815260206004820152603960248201527f45524332305072657365744d696e7465725061757365723a206d75737420686160448201527f76652070617573657220726f6c6520746f20756e706175736500000000000000606482015260840161059f565b61067b610cf1565b565b6106a77f9f2df0fed2c77648de5860a4cc508cd0818c85b8b8a1ab4ceeef8d981c8956a633610800565b6107125760405162461bcd60e51b815260206004820152603660248201527f45524332305072657365744d696e7465725061757365723a206d7573742068616044820152751d99481b5a5b9d195c881c9bdb19481d1bc81b5a5b9d60521b606482015260840161059f565b6105b28282610d43565b6107263382610e0d565b50565b610734823383610a79565b6105b28282610e0d565b6107687f65d7a28e3265b37a6474929f336521b332c1681b933f6cb9f3376673440d862a33610800565b6107da5760405162461bcd60e51b815260206004820152603760248201527f45524332305072657365744d696e7465725061757365723a206d75737420686160448201527f76652070617573657220726f6c6520746f207061757365000000000000000000606482015260840161059f565b61067b610f4a565b5f8281526001602052604081206107f99083610f87565b9392505050565b5f918252602082815260408084206001600160a01b0393909316845291905290205460ff1690565b60606006805461044f9061167b565b5f338161084482866108f8565b9050838110156108a45760405162461bcd60e51b815260206004820152602560248201527f45524332303a2064656372656173656420616c6c6f77616e63652062656c6f77604482015264207a65726f60d81b606482015260840161059f565b6104ff8286868403610956565b5f336104dd818585610af1565b5f81815260016020526040812061043a90610f92565b5f828152602081905260409020600101546108ee81610ca5565b61052e8383610cd0565b6001600160a01b039182165f90815260036020908152604080832093909416825291909152205490565b5f6001600160e01b03198216637965db0b60e01b148061043a57506301ffc9a760e01b6001600160e01b031983161461043a565b6001600160a01b0383166109b85760405162461bcd60e51b8152602060048201526024808201527f45524332303a20617070726f76652066726f6d20746865207a65726f206164646044820152637265737360e01b606482015260840161059f565b6001600160a01b038216610a195760405162461bcd60e51b815260206004820152602260248201527f45524332303a20617070726f766520746f20746865207a65726f206164647265604482015261737360f01b606482015260840161059f565b6001600160a01b038381165f8181526003602090815260408083209487168084529482529182902085905590518481527f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925910160405180910390a3505050565b5f610a8484846108f8565b90505f198114610aeb5781811015610ade5760405162461bcd60e51b815260206004820152601d60248201527f45524332303a20696e73756666696369656e7420616c6c6f77616e6365000000604482015260640161059f565b610aeb8484848403610956565b50505050565b6001600160a01b038316610b555760405162461bcd60e51b815260206004820152602560248201527f45524332303a207472616e736665722066726f6d20746865207a65726f206164604482015264647265737360d81b606482015260840161059f565b6001600160a01b038216610bb75760405162461bcd60e51b815260206004820152602360248201527f45524332303a207472616e7366657220746f20746865207a65726f206164647260448201526265737360e81b606482015260840161059f565b610bc2838383610f9b565b6001600160a01b0383165f9081526002602052604090205481811015610c395760405162461bcd60e51b815260206004820152602660248201527f45524332303a207472616e7366657220616d6f756e7420657863656564732062604482015265616c616e636560d01b606482015260840161059f565b6001600160a01b038085165f8181526002602052604080822086860390559286168082529083902080548601905591517fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef90610c989086815260200190565b60405180910390a3610aeb565b6107268133610fa6565b610cb98282610fff565b5f82815260016020526040902061052e9082611082565b610cda8282611096565b5f82815260016020526040902061052e90826110fa565b610cf961110e565b6007805460ff191690557f5db9ee0a495bf2e6ff9c91a7834c1ba4fdd244a5e8aa4e537bd38aeae4b073aa335b6040516001600160a01b03909116815260200160405180910390a1565b6001600160a01b038216610d995760405162461bcd60e51b815260206004820152601f60248201527f45524332303a206d696e7420746f20746865207a65726f206164647265737300604482015260640161059f565b610da45f8383610f9b565b8060045f828254610db591906116c7565b90915550506001600160a01b0382165f818152600260209081526040808320805486019055518481527fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef910160405180910390a35050565b6001600160a01b038216610e6d5760405162461bcd60e51b815260206004820152602160248201527f45524332303a206275726e2066726f6d20746865207a65726f206164647265736044820152607360f81b606482015260840161059f565b610e78825f83610f9b565b6001600160a01b0382165f9081526002602052604090205481811015610eeb5760405162461bcd60e51b815260206004820152602260248201527f45524332303a206275726e20616d6f756e7420657863656564732062616c616e604482015261636560f01b606482015260840161059f565b6001600160a01b0383165f8181526002602090815260408083208686039055600480548790039055518581529192917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef910160405180910390a3505050565b610f52611157565b6007805460ff191660011790557f62e78cea01bee320cd4e420270b5ea74000d11b0c9f74754ebdbfc544b05a258610d263390565b5f6107f9838361119d565b5f61043a825490565b61052e8383836111c3565b610fb08282610800565b6105b257610fbd81611229565b610fc883602061123b565b604051602001610fd99291906116f1565b60408051601f198184030181529082905262461bcd60e51b825261059f91600401611527565b6110098282610800565b6105b2575f828152602081815260408083206001600160a01b03851684529091529020805460ff1916600117905561103e3390565b6001600160a01b0316816001600160a01b0316837f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a45050565b5f6107f9836001600160a01b0384166113d1565b6110a08282610800565b156105b2575f828152602081815260408083206001600160a01b0385168085529252808320805460ff1916905551339285917ff6391f5c32d9c69d2a47ea670b442974b53935d1edc7fd64eb21e047a839171b9190a45050565b5f6107f9836001600160a01b03841661141d565b60075460ff1661067b5760405162461bcd60e51b815260206004820152601460248201527314185d5cd8589b194e881b9bdd081c185d5cd95960621b604482015260640161059f565b60075460ff161561067b5760405162461bcd60e51b815260206004820152601060248201526f14185d5cd8589b194e881c185d5cd95960821b604482015260640161059f565b5f825f0182815481106111b2576111b261174f565b905f5260205f200154905092915050565b60075460ff161561052e5760405162461bcd60e51b815260206004820152602a60248201527f45524332305061757361626c653a20746f6b656e207472616e736665722077686044820152691a5b19481c185d5cd95960b21b606482015260840161059f565b606061043a6001600160a01b03831660145b60605f611249836002611763565b6112549060026116c7565b67ffffffffffffffff81111561126c5761126c61177a565b6040519080825280601f01601f191660200182016040528015611296576020820181803683370190505b509050600360fc1b815f815181106112b0576112b061174f565b60200101906001600160f81b03191690815f1a905350600f60fb1b816001815181106112de576112de61174f565b60200101906001600160f81b03191690815f1a9053505f611300846002611763565b61130b9060016116c7565b90505b6001811115611382576f181899199a1a9b1b9c1cb0b131b232b360811b85600f166010811061133f5761133f61174f565b1a60f81b8282815181106113555761135561174f565b60200101906001600160f81b03191690815f1a90535060049490941c9361137b8161178e565b905061130e565b5083156107f95760405162461bcd60e51b815260206004820181905260248201527f537472696e67733a20686578206c656e67746820696e73756666696369656e74604482015260640161059f565b5f81815260018301602052604081205461141657508154600181810184555f84815260208082209093018490558454848252828601909352604090209190915561043a565b505f61043a565b5f81815260018301602052604081205480156114f7575f61143f6001836117a3565b85549091505f90611452906001906117a3565b90508181146114b1575f865f0182815481106114705761147061174f565b905f5260205f200154905080875f0184815481106114905761149061174f565b5f918252602080832090910192909255918252600188019052604090208390555b85548690806114c2576114c26117b6565b600190038181905f5260205f20015f90559055856001015f8681526020019081526020015f205f90556001935050505061043a565b5f91505061043a565b5f60208284031215611510575f80fd5b81356001600160e01b0319811681146107f9575f80fd5b602081525f82518060208401528060208501604085015e5f604082850101526040601f19601f83011684010191505092915050565b80356001600160a01b0381168114611572575f80fd5b919050565b5f8060408385031215611588575f80fd5b6115918361155c565b946020939093013593505050565b5f805f606084860312156115b1575f80fd5b6115ba8461155c565b92506115c86020850161155c565b929592945050506040919091013590565b5f602082840312156115e9575f80fd5b5035919050565b5f8060408385031215611601575f80fd5b823591506116116020840161155c565b90509250929050565b5f6020828403121561162a575f80fd5b6107f98261155c565b5f8060408385031215611644575f80fd5b50508035926020909101359150565b5f8060408385031215611664575f80fd5b61166d8361155c565b91506116116020840161155c565b600181811c9082168061168f57607f821691505b6020821081036116ad57634e487b7160e01b5f52602260045260245ffd5b50919050565b634e487b7160e01b5f52601160045260245ffd5b8082018082111561043a5761043a6116b3565b5f81518060208401855e5f93019283525090919050565b7f416363657373436f6e74726f6c3a206163636f756e742000000000000000000081525f61172260178301856116da565b7001034b99036b4b9b9b4b733903937b6329607d1b815261174660118201856116da565b95945050505050565b634e487b7160e01b5f52603260045260245ffd5b808202811582820484141761043a5761043a6116b3565b634e487b7160e01b5f52604160045260245ffd5b5f8161179c5761179c6116b3565b505f190190565b8181038181111561043a5761043a6116b3565b634e487b7160e01b5f52603160045260245ffdfea264697066735822122066cb4e3b37e9d5f6e2673e336388e489833c315de4b564f3fe8824c975ed2dac64736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_DEFAULT_ADMIN_ROLE = "DEFAULT_ADMIN_ROLE";

    public static final String FUNC_MINTER_ROLE = "MINTER_ROLE";

    public static final String FUNC_PAUSER_ROLE = "PAUSER_ROLE";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_BURNFROM = "burnFrom";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_GETROLEADMIN = "getRoleAdmin";

    public static final String FUNC_GETROLEMEMBER = "getRoleMember";

    public static final String FUNC_GETROLEMEMBERCOUNT = "getRoleMemberCount";

    public static final String FUNC_GRANTROLE = "grantRole";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_PAUSE = "pause";

    public static final String FUNC_PAUSED = "paused";

    public static final String FUNC_RENOUNCEROLE = "renounceRole";

    public static final String FUNC_REVOKEROLE = "revokeRole";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_UNPAUSE = "unpause";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PAUSED_EVENT = new Event("Paused", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event ROLEADMINCHANGED_EVENT = new Event("RoleAdminChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>(true) {}));
    ;

    public static final Event ROLEGRANTED_EVENT = new Event("RoleGranted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event ROLEREVOKED_EVENT = new Event("RoleRevoked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event UNPAUSED_EVENT = new Event("Unpaused", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected ERC20PresetMinterPauser(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ERC20PresetMinterPauser(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ERC20PresetMinterPauser(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ERC20PresetMinterPauser(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ApprovalEventResponse getApprovalEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(APPROVAL_EVENT, log);
        ApprovalEventResponse typedResponse = new ApprovalEventResponse();
        typedResponse.log = log;
        typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getApprovalEventFromLog(log));
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public static List<PausedEventResponse> getPausedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAUSED_EVENT, transactionReceipt);
        ArrayList<PausedEventResponse> responses = new ArrayList<PausedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PausedEventResponse typedResponse = new PausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PausedEventResponse getPausedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PAUSED_EVENT, log);
        PausedEventResponse typedResponse = new PausedEventResponse();
        typedResponse.log = log;
        typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPausedEventFromLog(log));
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAUSED_EVENT));
        return pausedEventFlowable(filter);
    }

    public static List<RoleAdminChangedEventResponse> getRoleAdminChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ROLEADMINCHANGED_EVENT, transactionReceipt);
        ArrayList<RoleAdminChangedEventResponse> responses = new ArrayList<RoleAdminChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleAdminChangedEventResponse typedResponse = new RoleAdminChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.previousAdminRole = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.newAdminRole = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RoleAdminChangedEventResponse getRoleAdminChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ROLEADMINCHANGED_EVENT, log);
        RoleAdminChangedEventResponse typedResponse = new RoleAdminChangedEventResponse();
        typedResponse.log = log;
        typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.previousAdminRole = (byte[]) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.newAdminRole = (byte[]) eventValues.getIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<RoleAdminChangedEventResponse> roleAdminChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRoleAdminChangedEventFromLog(log));
    }

    public Flowable<RoleAdminChangedEventResponse> roleAdminChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLEADMINCHANGED_EVENT));
        return roleAdminChangedEventFlowable(filter);
    }

    public static List<RoleGrantedEventResponse> getRoleGrantedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ROLEGRANTED_EVENT, transactionReceipt);
        ArrayList<RoleGrantedEventResponse> responses = new ArrayList<RoleGrantedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleGrantedEventResponse typedResponse = new RoleGrantedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RoleGrantedEventResponse getRoleGrantedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ROLEGRANTED_EVENT, log);
        RoleGrantedEventResponse typedResponse = new RoleGrantedEventResponse();
        typedResponse.log = log;
        typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<RoleGrantedEventResponse> roleGrantedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRoleGrantedEventFromLog(log));
    }

    public Flowable<RoleGrantedEventResponse> roleGrantedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLEGRANTED_EVENT));
        return roleGrantedEventFlowable(filter);
    }

    public static List<RoleRevokedEventResponse> getRoleRevokedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ROLEREVOKED_EVENT, transactionReceipt);
        ArrayList<RoleRevokedEventResponse> responses = new ArrayList<RoleRevokedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleRevokedEventResponse typedResponse = new RoleRevokedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RoleRevokedEventResponse getRoleRevokedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ROLEREVOKED_EVENT, log);
        RoleRevokedEventResponse typedResponse = new RoleRevokedEventResponse();
        typedResponse.log = log;
        typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<RoleRevokedEventResponse> roleRevokedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRoleRevokedEventFromLog(log));
    }

    public Flowable<RoleRevokedEventResponse> roleRevokedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLEREVOKED_EVENT));
        return roleRevokedEventFlowable(filter);
    }

    public static List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TransferEventResponse getTransferEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TRANSFER_EVENT, log);
        TransferEventResponse typedResponse = new TransferEventResponse();
        typedResponse.log = log;
        typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTransferEventFromLog(log));
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public static List<UnpausedEventResponse> getUnpausedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(UNPAUSED_EVENT, transactionReceipt);
        ArrayList<UnpausedEventResponse> responses = new ArrayList<UnpausedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UnpausedEventResponse typedResponse = new UnpausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static UnpausedEventResponse getUnpausedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(UNPAUSED_EVENT, log);
        UnpausedEventResponse typedResponse = new UnpausedEventResponse();
        typedResponse.log = log;
        typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getUnpausedEventFromLog(log));
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UNPAUSED_EVENT));
        return unpausedEventFlowable(filter);
    }

    public RemoteFunctionCall<byte[]> DEFAULT_ADMIN_ROLE() {
        final Function function = new Function(FUNC_DEFAULT_ADMIN_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> MINTER_ROLE() {
        final Function function = new Function(FUNC_MINTER_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> PAUSER_ROLE() {
        final Function function = new Function(FUNC_PAUSER_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger amount) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger amount) {
        final Function function = new Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> burnFrom(String account, BigInteger amount) {
        final Function function = new Function(
                FUNC_BURNFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger subtractedValue) {
        final Function function = new Function(
                FUNC_DECREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(subtractedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<byte[]> getRoleAdmin(byte[] role) {
        final Function function = new Function(FUNC_GETROLEADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<String> getRoleMember(byte[] role, BigInteger index) {
        final Function function = new Function(FUNC_GETROLEMEMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getRoleMemberCount(byte[] role) {
        final Function function = new Function(FUNC_GETROLEMEMBERCOUNT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> grantRole(byte[] role, String account) {
        final Function function = new Function(
                FUNC_GRANTROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> hasRole(byte[] role, String account) {
        final Function function = new Function(FUNC_HASROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger addedValue) {
        final Function function = new Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(addedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pause() {
        final Function function = new Function(
                FUNC_PAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> paused() {
        final Function function = new Function(FUNC_PAUSED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceRole(byte[] role, String account) {
        final Function function = new Function(
                FUNC_RENOUNCEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeRole(byte[] role, String account) {
        final Function function = new Function(
                FUNC_REVOKEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final Function function = new Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> unpause() {
        final Function function = new Function(
                FUNC_UNPAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ERC20PresetMinterPauser load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC20PresetMinterPauser(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ERC20PresetMinterPauser load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC20PresetMinterPauser(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ERC20PresetMinterPauser load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ERC20PresetMinterPauser(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ERC20PresetMinterPauser load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ERC20PresetMinterPauser(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ERC20PresetMinterPauser> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol)));
        return deployRemoteCall(ERC20PresetMinterPauser.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<ERC20PresetMinterPauser> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol)));
        return deployRemoteCall(ERC20PresetMinterPauser.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ERC20PresetMinterPauser> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol)));
        return deployRemoteCall(ERC20PresetMinterPauser.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ERC20PresetMinterPauser> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol)));
        return deployRemoteCall(ERC20PresetMinterPauser.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class PausedEventResponse extends BaseEventResponse {
        public String account;
    }

    public static class RoleAdminChangedEventResponse extends BaseEventResponse {
        public byte[] role;

        public byte[] previousAdminRole;

        public byte[] newAdminRole;
    }

    public static class RoleGrantedEventResponse extends BaseEventResponse {
        public byte[] role;

        public String account;

        public String sender;
    }

    public static class RoleRevokedEventResponse extends BaseEventResponse {
        public byte[] role;

        public String account;

        public String sender;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }

    public static class UnpausedEventResponse extends BaseEventResponse {
        public String account;
    }
}
