package com.zuehlke.blockchain.model;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public class ERC777PresetFixedSupply extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b50604051611f7e380380611f7e83398101604081905261002e91610668565b848484600261003d848261080d565b50600361004a838261080d565b50805161005e906004906020840190610527565b505f5b81518110156100ba57600160055f848481518110610081576100816108c7565b6020908102919091018101516001600160a01b031682528101919091526040015f20805460ff1916911515919091179055600101610061565b506040516329965a1d60e01b815230600482018190527fac7fbab5f54a3ca8194167523c6753bfeb96a445279294b6125b68cce217705460248301526044820152731820a4b7618bde71dce8cdc73aab6c95905fad24906329965a1d906064015f604051808303815f87803b158015610131575f80fd5b505af1158015610143573d5f803e3d5ffd5b50506040516329965a1d60e01b815230600482018190527faea199e31a596269b42cdafd93407f14436db6e4cad65417994c2eb37381e05a60248301526044820152731820a4b7618bde71dce8cdc73aab6c95905fad2492506329965a1d91506064015f604051808303815f87803b1580156101bd575f80fd5b505af11580156101cf573d5f803e3d5ffd5b50505050505050610204818360405180602001604052805f81525060405180602001604052805f81525061020e60201b60201c565b50505050506109db565b61021c848484846001610222565b50505050565b6001600160a01b03851661027d5760405162461bcd60e51b815260206004820181905260248201527f4552433737373a206d696e7420746f20746865207a65726f206164647265737360448201526064015b60405180910390fd5b5f3390508460015f82825461029291906108db565b90915550506001600160a01b0386165f90815260208190526040812080548792906102be9084906108db565b909155506102d39050815f888888888861036a565b856001600160a01b0316816001600160a01b03167f2fe5be0146f74c5bce36c0b80911af6c7d86ff27e89d5cfa61fc681327954e5d87878760405161031a9392919061092e565b60405180910390a36040518581526001600160a01b038716905f907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9060200160405180910390a3505050505050565b60405163555ddc6560e11b81526001600160a01b03861660048201527fb281fc8c12954d22544db45de3159a39272895b169a852b314f9cc762e44c53b60248201525f90731820a4b7618bde71dce8cdc73aab6c95905fad249063aabbb8ca90604401602060405180830381865afa1580156103e8573d5f803e3d5ffd5b505050506040513d601f19601f8201168201806040525081019061040c9190610962565b90506001600160a01b03811615610483576040516223de2960e01b81526001600160a01b038216906223de2990610451908b908b908b908b908b908b90600401610982565b5f604051808303815f87803b158015610468575f80fd5b505af115801561047a573d5f803e3d5ffd5b5050505061051d565b811561051d576001600160a01b0386163b1561051d5760405162461bcd60e51b815260206004820152604d60248201527f4552433737373a20746f6b656e20726563697069656e7420636f6e747261637460448201527f20686173206e6f20696d706c656d656e74657220666f7220455243373737546f60648201526c1ad95b9cd49958da5c1a595b9d609a1b608482015260a401610274565b5050505050505050565b828054828255905f5260205f2090810192821561057a579160200282015b8281111561057a57825182546001600160a01b0319166001600160a01b03909116178255602090920191600190910190610545565b5061058692915061058a565b5090565b5b80821115610586575f815560010161058b565b634e487b7160e01b5f52604160045260245ffd5b604051601f8201601f191681016001600160401b03811182821017156105da576105da61059e565b604052919050565b5f82601f8301126105f1575f80fd5b81516001600160401b0381111561060a5761060a61059e565b61061d601f8201601f19166020016105b2565b818152846020838601011115610631575f80fd5b8160208501602083015e5f918101602001919091529392505050565b80516001600160a01b0381168114610663575f80fd5b919050565b5f805f805f60a0868803121561067c575f80fd5b85516001600160401b03811115610691575f80fd5b61069d888289016105e2565b602088015190965090506001600160401b038111156106ba575f80fd5b6106c6888289016105e2565b604088015190955090506001600160401b038111156106e3575f80fd5b8601601f810188136106f3575f80fd5b80516001600160401b0381111561070c5761070c61059e565b8060051b61071c602082016105b2565b9182526020818401810192908101908b841115610737575f80fd5b6020850194505b838510156107605761074f8561064d565b82526020948501949091019061073e565b60608b0151909750955061077d93505060808901915061064d9050565b90509295509295909350565b600181811c9082168061079d57607f821691505b6020821081036107bb57634e487b7160e01b5f52602260045260245ffd5b50919050565b601f82111561080857805f5260205f20601f840160051c810160208510156107e65750805b601f840160051c820191505b81811015610805575f81556001016107f2565b50505b505050565b81516001600160401b038111156108265761082661059e565b61083a816108348454610789565b846107c1565b6020601f82116001811461086c575f83156108555750848201515b5f19600385901b1c1916600184901b178455610805565b5f84815260208120601f198516915b8281101561089b578785015182556020948501946001909201910161087b565b50848210156108b857868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b634e487b7160e01b5f52603260045260245ffd5b808201808211156108fa57634e487b7160e01b5f52601160045260245ffd5b92915050565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b838152606060208201525f6109466060830185610900565b82810360408401526109588185610900565b9695505050505050565b5f60208284031215610972575f80fd5b61097b8261064d565b9392505050565b6001600160a01b0387811682528681166020830152851660408201526060810184905260c0608082018190525f906109bc90830185610900565b82810360a08401526109ce8185610900565b9998505050505050505050565b611596806109e85f395ff3fe608060405234801561000f575f80fd5b5060043610610111575f3560e01c8063959b8c3f1161009e578063d95b63711161006e578063d95b637114610224578063dd62ed3e14610237578063fad8b32a1461026f578063fc673c4f14610282578063fe9d930314610295575f80fd5b8063959b8c3f146101e357806395d89b41146101f65780639bd9bbc6146101fe578063a9059cbb14610211575f80fd5b806323b872dd116100e457806323b872dd1461017d578063313ce56714610190578063556f0dc71461019f57806362ad1b83146101a657806370a08231146101bb575f80fd5b806306e485381461011557806306fdde0314610133578063095ea7b31461014857806318160ddd1461016b575b5f80fd5b61011d6102a8565b60405161012a9190611054565b60405180910390f35b61013b610308565b60405161012a91906110cd565b61015b6101563660046110f6565b61038f565b604051901515815260200161012a565b6001545b60405190815260200161012a565b61015b61018b366004611120565b6103a8565b6040516012815260200161012a565b600161016f565b6101b96101b43660046111fd565b6103ea565b005b61016f6101c936600461128f565b6001600160a01b03165f9081526020819052604090205490565b6101b96101f136600461128f565b61042f565b61013b610548565b6101b961020c3660046112aa565b610557565b61015b61021f3660046110f6565b610579565b61015b6102323660046112ff565b6105ad565b61016f6102453660046112ff565b6001600160a01b039182165f90815260086020908152604080832093909416825291909152205490565b6101b961027d36600461128f565b61064b565b6101b9610290366004611336565b610762565b6101b96102a33660046113b6565b61079a565b606060048054806020026020016040519081016040528092919081815260200182805480156102fe57602002820191905f5260205f20905b81546001600160a01b031681526001909101906020018083116102e0575b5050505050905090565b606060028054610317906113fa565b80601f0160208091040260200160405190810160405280929190818152602001828054610343906113fa565b80156102fe5780601f10610365576101008083540402835291602001916102fe565b820191905f5260205f20905b81548152906001019060200180831161037157509395945050505050565b5f3361039c8185856107b8565b60019150505b92915050565b5f336103b58582856108de565b6103df85858560405180602001604052805f81525060405180602001604052805f8152505f610968565b506001949350505050565b6103f433866105ad565b6104195760405162461bcd60e51b815260040161041090611432565b60405180910390fd5b61042885858585856001610968565b5050505050565b6001600160a01b03811633036104935760405162461bcd60e51b8152602060048201526024808201527f4552433737373a20617574686f72697a696e672073656c66206173206f70657260448201526330ba37b960e11b6064820152608401610410565b6001600160a01b0381165f9081526005602052604090205460ff16156104e257335f9081526007602090815260408083206001600160a01b03851684529091529020805460ff19169055610510565b335f9081526006602090815260408083206001600160a01b03851684529091529020805460ff191660011790555b60405133906001600160a01b038316907ff4caeb2d6ca8932a215a353d0703c326ec2d81fc68170f320eb2ab49e9df61f9905f90a350565b606060038054610317906113fa565b6105743384848460405180602001604052805f8152506001610968565b505050565b5f6105a433848460405180602001604052805f81525060405180602001604052805f8152505f610968565b50600192915050565b5f816001600160a01b0316836001600160a01b0316148061061557506001600160a01b0383165f9081526005602052604090205460ff16801561061557506001600160a01b038083165f9081526007602090815260408083209387168352929052205460ff16155b8061064457506001600160a01b038083165f9081526006602090815260408083209387168352929052205460ff165b9392505050565b336001600160a01b038216036106ad5760405162461bcd60e51b815260206004820152602160248201527f4552433737373a207265766f6b696e672073656c66206173206f70657261746f6044820152603960f91b6064820152608401610410565b6001600160a01b0381165f9081526005602052604090205460ff16156106ff57335f9081526007602090815260408083206001600160a01b03851684529091529020805460ff1916600117905561072a565b335f9081526006602090815260408083206001600160a01b03851684529091529020805460ff191690555b60405133906001600160a01b038316907f50546e66e5f44d728365dc3908c63bc5cfeeab470722c1677e3073a6ac294aa1905f90a350565b61076c33856105ad565b6107885760405162461bcd60e51b815260040161041090611432565b61079484848484610a64565b50505050565b6107b433838360405180602001604052805f815250610a64565b5050565b6001600160a01b03831661081c5760405162461bcd60e51b815260206004820152602560248201527f4552433737373a20617070726f76652066726f6d20746865207a65726f206164604482015264647265737360d81b6064820152608401610410565b6001600160a01b03821661087e5760405162461bcd60e51b815260206004820152602360248201527f4552433737373a20617070726f766520746f20746865207a65726f206164647260448201526265737360e81b6064820152608401610410565b6001600160a01b038381165f8181526008602090815260408083209487168084529482529182902085905590518481527f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925910160405180910390a3505050565b6001600160a01b038381165f908152600860209081526040808320938616835292905220545f198114610794578181101561095b5760405162461bcd60e51b815260206004820152601e60248201527f4552433737373a20696e73756666696369656e7420616c6c6f77616e636500006044820152606401610410565b61079484848484036107b8565b6001600160a01b0386166109cd5760405162461bcd60e51b815260206004820152602660248201527f4552433737373a207472616e736665722066726f6d20746865207a65726f206160448201526564647265737360d01b6064820152608401610410565b6001600160a01b038516610a2f5760405162461bcd60e51b8152602060048201526024808201527f4552433737373a207472616e7366657220746f20746865207a65726f206164646044820152637265737360e01b6064820152608401610410565b33610a3e818888888888610c14565b610a4c818888888888610d33565b610a5b81888888888888610e97565b50505050505050565b6001600160a01b038416610ac55760405162461bcd60e51b815260206004820152602260248201527f4552433737373a206275726e2066726f6d20746865207a65726f206164647265604482015261737360f01b6064820152608401610410565b33610ad481865f878787610c14565b6001600160a01b0385165f9081526020819052604090205484811015610b485760405162461bcd60e51b815260206004820152602360248201527f4552433737373a206275726e20616d6f756e7420657863656564732062616c616044820152626e636560e81b6064820152608401610410565b6001600160a01b0386165f908152602081905260408120868303905560018054879290610b76908490611492565b92505081905550856001600160a01b0316826001600160a01b03167fa78a9be3a7b862d26933ad85fb11d80ef66b8f972d7cbba06621d583943a4098878787604051610bc4939291906114a5565b60405180910390a36040518581525f906001600160a01b038816907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9060200160405180910390a3505050505050565b60405163555ddc6560e11b81526001600160a01b03861660048201527f29ddb589b1fb5fc7cf394961c1adf5f8c6454761adf795e67fe149f658abe89560248201525f90731820a4b7618bde71dce8cdc73aab6c95905fad249063aabbb8ca90604401602060405180830381865afa158015610c92573d5f803e3d5ffd5b505050506040513d601f19601f82011682018060405250810190610cb691906114d9565b90506001600160a01b03811615610a5b57604051633ad5cbc160e11b81526001600160a01b038216906375ab978290610cfd908a908a908a908a908a908a906004016114f4565b5f604051808303815f87803b158015610d14575f80fd5b505af1158015610d26573d5f803e3d5ffd5b5050505050505050505050565b6001600160a01b0385165f9081526020819052604090205483811015610dab5760405162461bcd60e51b815260206004820152602760248201527f4552433737373a207472616e7366657220616d6f756e7420657863656564732060448201526662616c616e636560c81b6064820152608401610410565b6001600160a01b038087165f90815260208190526040808220878503905591871681529081208054869290610de190849061154d565b92505081905550846001600160a01b0316866001600160a01b0316886001600160a01b03167f06b541ddaa720db2b10a4d0cdac39b8d360425fc073085fac19bc82614677987878787604051610e39939291906114a5565b60405180910390a4846001600160a01b0316866001600160a01b03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef86604051610e8691815260200190565b60405180910390a350505050505050565b60405163555ddc6560e11b81526001600160a01b03861660048201527fb281fc8c12954d22544db45de3159a39272895b169a852b314f9cc762e44c53b60248201525f90731820a4b7618bde71dce8cdc73aab6c95905fad249063aabbb8ca90604401602060405180830381865afa158015610f15573d5f803e3d5ffd5b505050506040513d601f19601f82011682018060405250810190610f3991906114d9565b90506001600160a01b03811615610fb0576040516223de2960e01b81526001600160a01b038216906223de2990610f7e908b908b908b908b908b908b906004016114f4565b5f604051808303815f87803b158015610f95575f80fd5b505af1158015610fa7573d5f803e3d5ffd5b5050505061104a565b811561104a576001600160a01b0386163b1561104a5760405162461bcd60e51b815260206004820152604d60248201527f4552433737373a20746f6b656e20726563697069656e7420636f6e747261637460448201527f20686173206e6f20696d706c656d656e74657220666f7220455243373737546f60648201526c1ad95b9cd49958da5c1a595b9d609a1b608482015260a401610410565b5050505050505050565b602080825282518282018190525f918401906040840190835b818110156110945783516001600160a01b031683526020938401939092019160010161106d565b509095945050505050565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b602081525f610644602083018461109f565b6001600160a01b03811681146110f3575f80fd5b50565b5f8060408385031215611107575f80fd5b8235611112816110df565b946020939093013593505050565b5f805f60608486031215611132575f80fd5b833561113d816110df565b9250602084013561114d816110df565b929592945050506040919091013590565b634e487b7160e01b5f52604160045260245ffd5b5f82601f830112611181575f80fd5b813567ffffffffffffffff81111561119b5761119b61115e565b604051601f8201601f19908116603f0116810167ffffffffffffffff811182821017156111ca576111ca61115e565b6040528181528382016020018510156111e1575f80fd5b816020850160208301375f918101602001919091529392505050565b5f805f805f60a08688031215611211575f80fd5b853561121c816110df565b9450602086013561122c816110df565b935060408601359250606086013567ffffffffffffffff81111561124e575f80fd5b61125a88828901611172565b925050608086013567ffffffffffffffff811115611276575f80fd5b61128288828901611172565b9150509295509295909350565b5f6020828403121561129f575f80fd5b8135610644816110df565b5f805f606084860312156112bc575f80fd5b83356112c7816110df565b925060208401359150604084013567ffffffffffffffff8111156112e9575f80fd5b6112f586828701611172565b9150509250925092565b5f8060408385031215611310575f80fd5b823561131b816110df565b9150602083013561132b816110df565b809150509250929050565b5f805f8060808587031215611349575f80fd5b8435611354816110df565b935060208501359250604085013567ffffffffffffffff811115611376575f80fd5b61138287828801611172565b925050606085013567ffffffffffffffff81111561139e575f80fd5b6113aa87828801611172565b91505092959194509250565b5f80604083850312156113c7575f80fd5b82359150602083013567ffffffffffffffff8111156113e4575f80fd5b6113f085828601611172565b9150509250929050565b600181811c9082168061140e57607f821691505b60208210810361142c57634e487b7160e01b5f52602260045260245ffd5b50919050565b6020808252602c908201527f4552433737373a2063616c6c6572206973206e6f7420616e206f70657261746f60408201526b39103337b9103437b63232b960a11b606082015260800190565b634e487b7160e01b5f52601160045260245ffd5b818103818111156103a2576103a261147e565b838152606060208201525f6114bd606083018561109f565b82810360408401526114cf818561109f565b9695505050505050565b5f602082840312156114e9575f80fd5b8151610644816110df565b6001600160a01b0387811682528681166020830152851660408201526060810184905260c0608082018190525f9061152e9083018561109f565b82810360a0840152611540818561109f565b9998505050505050505050565b808201808211156103a2576103a261147e56fea26469706673582212209849cec4e3775ba9a1d81ebc9d46353a32039601e4c6ff45b86e420464fa72e464736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_AUTHORIZEOPERATOR = "authorizeOperator";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DEFAULTOPERATORS = "defaultOperators";

    public static final String FUNC_GRANULARITY = "granularity";

    public static final String FUNC_ISOPERATORFOR = "isOperatorFor";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OPERATORBURN = "operatorBurn";

    public static final String FUNC_OPERATORSEND = "operatorSend";

    public static final String FUNC_REVOKEOPERATOR = "revokeOperator";

    public static final String FUNC_SEND = "send";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event AUTHORIZEDOPERATOR_EVENT = new Event("AuthorizedOperator", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event BURNED_EVENT = new Event("Burned", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event MINTED_EVENT = new Event("Minted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event REVOKEDOPERATOR_EVENT = new Event("RevokedOperator", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event SENT_EVENT = new Event("Sent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected ERC777PresetFixedSupply(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ERC777PresetFixedSupply(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ERC777PresetFixedSupply(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ERC777PresetFixedSupply(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public static List<AuthorizedOperatorEventResponse> getAuthorizedOperatorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(AUTHORIZEDOPERATOR_EVENT, transactionReceipt);
        ArrayList<AuthorizedOperatorEventResponse> responses = new ArrayList<AuthorizedOperatorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AuthorizedOperatorEventResponse typedResponse = new AuthorizedOperatorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenHolder = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static AuthorizedOperatorEventResponse getAuthorizedOperatorEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(AUTHORIZEDOPERATOR_EVENT, log);
        AuthorizedOperatorEventResponse typedResponse = new AuthorizedOperatorEventResponse();
        typedResponse.log = log;
        typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.tokenHolder = (String) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<AuthorizedOperatorEventResponse> authorizedOperatorEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getAuthorizedOperatorEventFromLog(log));
    }

    public Flowable<AuthorizedOperatorEventResponse> authorizedOperatorEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(AUTHORIZEDOPERATOR_EVENT));
        return authorizedOperatorEventFlowable(filter);
    }

    public static List<BurnedEventResponse> getBurnedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BURNED_EVENT, transactionReceipt);
        ArrayList<BurnedEventResponse> responses = new ArrayList<BurnedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BurnedEventResponse typedResponse = new BurnedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static BurnedEventResponse getBurnedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(BURNED_EVENT, log);
        BurnedEventResponse typedResponse = new BurnedEventResponse();
        typedResponse.log = log;
        typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<BurnedEventResponse> burnedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getBurnedEventFromLog(log));
    }

    public Flowable<BurnedEventResponse> burnedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BURNED_EVENT));
        return burnedEventFlowable(filter);
    }

    public static List<MintedEventResponse> getMintedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MINTED_EVENT, transactionReceipt);
        ArrayList<MintedEventResponse> responses = new ArrayList<MintedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MintedEventResponse typedResponse = new MintedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MintedEventResponse getMintedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MINTED_EVENT, log);
        MintedEventResponse typedResponse = new MintedEventResponse();
        typedResponse.log = log;
        typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<MintedEventResponse> mintedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMintedEventFromLog(log));
    }

    public Flowable<MintedEventResponse> mintedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MINTED_EVENT));
        return mintedEventFlowable(filter);
    }

    public static List<RevokedOperatorEventResponse> getRevokedOperatorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(REVOKEDOPERATOR_EVENT, transactionReceipt);
        ArrayList<RevokedOperatorEventResponse> responses = new ArrayList<RevokedOperatorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RevokedOperatorEventResponse typedResponse = new RevokedOperatorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenHolder = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RevokedOperatorEventResponse getRevokedOperatorEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(REVOKEDOPERATOR_EVENT, log);
        RevokedOperatorEventResponse typedResponse = new RevokedOperatorEventResponse();
        typedResponse.log = log;
        typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.tokenHolder = (String) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<RevokedOperatorEventResponse> revokedOperatorEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRevokedOperatorEventFromLog(log));
    }

    public Flowable<RevokedOperatorEventResponse> revokedOperatorEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REVOKEDOPERATOR_EVENT));
        return revokedOperatorEventFlowable(filter);
    }

    public static List<SentEventResponse> getSentEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SENT_EVENT, transactionReceipt);
        ArrayList<SentEventResponse> responses = new ArrayList<SentEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SentEventResponse typedResponse = new SentEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SentEventResponse getSentEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SENT_EVENT, log);
        SentEventResponse typedResponse = new SentEventResponse();
        typedResponse.log = log;
        typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<SentEventResponse> sentEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSentEventFromLog(log));
    }

    public Flowable<SentEventResponse> sentEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SENT_EVENT));
        return sentEventFlowable(filter);
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

    public RemoteFunctionCall<BigInteger> allowance(String holder, String spender) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, holder), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> authorizeOperator(String operator) {
        final Function function = new Function(
                FUNC_AUTHORIZEOPERATOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String tokenHolder) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, tokenHolder)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger amount, byte[] data) {
        final Function function = new Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> defaultOperators() {
        final Function function = new Function(FUNC_DEFAULTOPERATORS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> granularity() {
        final Function function = new Function(FUNC_GRANULARITY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> isOperatorFor(String operator, String tokenHolder) {
        final Function function = new Function(FUNC_ISOPERATORFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Address(160, tokenHolder)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> operatorBurn(String account, BigInteger amount, byte[] data, byte[] operatorData) {
        final Function function = new Function(
                FUNC_OPERATORBURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(data), 
                new org.web3j.abi.datatypes.DynamicBytes(operatorData)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> operatorSend(String sender, String recipient, BigInteger amount, byte[] data, byte[] operatorData) {
        final Function function = new Function(
                FUNC_OPERATORSEND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(data), 
                new org.web3j.abi.datatypes.DynamicBytes(operatorData)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeOperator(String operator) {
        final Function function = new Function(
                FUNC_REVOKEOPERATOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> send(String recipient, BigInteger amount, byte[] data) {
        final Function function = new Function(
                FUNC_SEND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteFunctionCall<TransactionReceipt> transfer(String recipient, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String holder, String recipient, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, holder), 
                new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ERC777PresetFixedSupply load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC777PresetFixedSupply(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ERC777PresetFixedSupply load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC777PresetFixedSupply(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ERC777PresetFixedSupply load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ERC777PresetFixedSupply(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ERC777PresetFixedSupply load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ERC777PresetFixedSupply(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ERC777PresetFixedSupply> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String symbol, List<String> defaultOperators, BigInteger initialSupply, String owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Address(160, owner)));
        return deployRemoteCall(ERC777PresetFixedSupply.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<ERC777PresetFixedSupply> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String symbol, List<String> defaultOperators, BigInteger initialSupply, String owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Address(160, owner)));
        return deployRemoteCall(ERC777PresetFixedSupply.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ERC777PresetFixedSupply> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol, List<String> defaultOperators, BigInteger initialSupply, String owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Address(160, owner)));
        return deployRemoteCall(ERC777PresetFixedSupply.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ERC777PresetFixedSupply> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol, List<String> defaultOperators, BigInteger initialSupply, String owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Address(160, owner)));
        return deployRemoteCall(ERC777PresetFixedSupply.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class AuthorizedOperatorEventResponse extends BaseEventResponse {
        public String operator;

        public String tokenHolder;
    }

    public static class BurnedEventResponse extends BaseEventResponse {
        public String operator;

        public String from;

        public BigInteger amount;

        public byte[] data;

        public byte[] operatorData;
    }

    public static class MintedEventResponse extends BaseEventResponse {
        public String operator;

        public String to;

        public BigInteger amount;

        public byte[] data;

        public byte[] operatorData;
    }

    public static class RevokedOperatorEventResponse extends BaseEventResponse {
        public String operator;

        public String tokenHolder;
    }

    public static class SentEventResponse extends BaseEventResponse {
        public String operator;

        public String from;

        public String to;

        public BigInteger amount;

        public byte[] data;

        public byte[] operatorData;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
