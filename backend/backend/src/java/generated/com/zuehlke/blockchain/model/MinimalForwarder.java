package com.zuehlke.blockchain.model;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes1;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple7;
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
public class MinimalForwarder extends Contract {
    public static final String BINARY = "610160604052348015610010575f80fd5b50604080518082018252601081526f26b4b734b6b0b62337b93bb0b93232b960811b60208083019190915282518084019093526005835264302e302e3160d81b908301529061005f825f610108565b6101205261006e816001610108565b61014052815160208084019190912060e052815190820120610100524660a0526100fa60e05161010051604080517f8b73c3c69bb8fe3d512ecc4cf759cc79239f7b179b0ffacaa9a75d522b39400f60208201529081019290925260608201524660808201523060a08201525f9060c00160405160208183030381529060405280519060200120905090565b60805250503060c05261032a565b5f6020835110156101235761011c8361013a565b9050610134565b8161012e8482610218565b5060ff90505b92915050565b5f80829050601f8151111561016d578260405163305a27a960e01b815260040161016491906102d2565b60405180910390fd5b805161017882610307565b179392505050565b634e487b7160e01b5f52604160045260245ffd5b600181811c908216806101a857607f821691505b6020821081036101c657634e487b7160e01b5f52602260045260245ffd5b50919050565b601f82111561021357805f5260205f20601f840160051c810160208510156101f15750805b601f840160051c820191505b81811015610210575f81556001016101fd565b50505b505050565b81516001600160401b0381111561023157610231610180565b6102458161023f8454610194565b846101cc565b6020601f821160018114610277575f83156102605750848201515b5f19600385901b1c1916600184901b178455610210565b5f84815260208120601f198516915b828110156102a65787850151825560209485019460019092019101610286565b50848210156102c357868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b602081525f82518060208401528060208501604085015e5f604082850101526040601f19601f83011684010191505092915050565b805160208083015191908110156101c6575f1960209190910360031b1b16919050565b60805160a05160c05160e051610100516101205161014051610c6761037b5f395f6102ca01525f6102a001525f6106a301525f61067b01525f6105d601525f61060001525f61062a0152610c675ff3fe60806040526004361061003e575f3560e01c80632d0335ab1461004257806347153f821461008957806384b0196e146100aa578063bf5d3bdb146100d1575b5f80fd5b34801561004d575f80fd5b5061007661005c36600461096a565b6001600160a01b03165f9081526002602052604090205490565b6040519081526020015b60405180910390f35b61009c610097366004610997565b610100565b604051610080929190610a61565b3480156100b5575f80fd5b506100be610293565b6040516100809796959493929190610a83565b3480156100dc575f80fd5b506100f06100eb366004610997565b610319565b6040519015158152602001610080565b5f606061010e858585610319565b61017a5760405162461bcd60e51b815260206004820152603260248201527f4d696e696d616c466f727761726465723a207369676e617475726520646f6573604482015271081b9bdd081b585d18da081c995c5d595cdd60721b60648201526084015b60405180910390fd5b61018960808601356001610b19565b60025f610199602089018961096a565b6001600160a01b03166001600160a01b031681526020019081526020015f20819055505f808660200160208101906101d1919061096a565b6001600160a01b0316606088013560408901356101f160a08b018b610b38565b6101fe60208d018d61096a565b60405160200161021093929190610b7b565b60408051601f198184030181529082905261022a91610ba1565b5f60405180830381858888f193505050503d805f8114610265576040519150601f19603f3d011682016040523d82523d5f602084013e61026a565b606091505b50909250905061027f603f6060890135610bb7565b5a1161028757fe5b90969095509350505050565b5f606080828080836102c57f000000000000000000000000000000000000000000000000000000000000000083610494565b6102f07f00000000000000000000000000000000000000000000000000000000000000006001610494565b604080515f80825260208201909252600f60f81b9b939a50919850469750309650945092509050565b5f8061042a84848080601f0160208091040260200160405190810160405280939291908181526020018383808284375f9201919091525061042492507fdd8f4b70b0f4393e889bd39128a30628a78b61816a9eb8199759e7a349657e489150610387905060208a018a61096a565b61039760408b0160208c0161096a565b60408b013560608c013560808d01356103b360a08f018f610b38565b6040516103c1929190610bd6565b6040805191829003822060208301989098526001600160a01b0396871690820152949093166060850152608084019190915260a083015260c082015260e0810191909152610100016040516020818303038152906040528051906020012061053f565b9061056b565b9050608085013560025f610441602089018961096a565b6001600160a01b03166001600160a01b031681526020019081526020015f205414801561048b5750610476602086018661096a565b6001600160a01b0316816001600160a01b0316145b95945050505050565b606060ff83146104ae576104a78361058d565b9050610539565b8180546104ba90610be5565b80601f01602080910402602001604051908101604052809291908181526020018280546104e690610be5565b80156105315780601f1061050857610100808354040283529160200191610531565b820191905f5260205f20905b81548152906001019060200180831161051457829003601f168201915b505050505090505b92915050565b5f61053961054b6105ca565b8360405161190160f01b8152600281019290925260228201526042902090565b5f805f61057885856106f8565b915091506105858161073a565b509392505050565b60605f61059983610886565b6040805160208082528183019092529192505f91906020820181803683375050509182525060208101929092525090565b5f306001600160a01b037f00000000000000000000000000000000000000000000000000000000000000001614801561062257507f000000000000000000000000000000000000000000000000000000000000000046145b1561064c57507f000000000000000000000000000000000000000000000000000000000000000090565b6106f3604080517f8b73c3c69bb8fe3d512ecc4cf759cc79239f7b179b0ffacaa9a75d522b39400f60208201527f0000000000000000000000000000000000000000000000000000000000000000918101919091527f000000000000000000000000000000000000000000000000000000000000000060608201524660808201523060a08201525f9060c00160405160208183030381529060405280519060200120905090565b905090565b5f80825160410361072c576020830151604084015160608501515f1a610720878285856108ad565b94509450505050610733565b505f905060025b9250929050565b5f81600481111561074d5761074d610c1d565b036107555750565b600181600481111561076957610769610c1d565b036107b65760405162461bcd60e51b815260206004820152601860248201527f45434453413a20696e76616c6964207369676e617475726500000000000000006044820152606401610171565b60028160048111156107ca576107ca610c1d565b036108175760405162461bcd60e51b815260206004820152601f60248201527f45434453413a20696e76616c6964207369676e6174757265206c656e677468006044820152606401610171565b600381600481111561082b5761082b610c1d565b036108835760405162461bcd60e51b815260206004820152602260248201527f45434453413a20696e76616c6964207369676e6174757265202773272076616c604482015261756560f01b6064820152608401610171565b50565b5f60ff8216601f81111561053957604051632cd44ac360e21b815260040160405180910390fd5b5f807f7fffffffffffffffffffffffffffffff5d576e7357a4501ddfe92f46681b20a08311156108e257505f90506003610961565b604080515f8082526020820180845289905260ff881692820192909252606081018690526080810185905260019060a0016020604051602081039080840390855afa158015610933573d5f803e3d5ffd5b5050604051601f1901519150506001600160a01b03811661095b575f60019250925050610961565b91505f90505b94509492505050565b5f6020828403121561097a575f80fd5b81356001600160a01b0381168114610990575f80fd5b9392505050565b5f805f604084860312156109a9575f80fd5b833567ffffffffffffffff8111156109bf575f80fd5b840160c081870312156109d0575f80fd5b9250602084013567ffffffffffffffff8111156109eb575f80fd5b8401601f810186136109fb575f80fd5b803567ffffffffffffffff811115610a11575f80fd5b866020828401011115610a22575f80fd5b939660209190910195509293505050565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b8215158152604060208201525f610a7b6040830184610a33565b949350505050565b60ff60f81b8816815260e060208201525f610aa160e0830189610a33565b8281036040840152610ab38189610a33565b606084018890526001600160a01b038716608085015260a0840186905283810360c0850152845180825260208087019350909101905f5b81811015610b08578351835260209384019390920191600101610aea565b50909b9a5050505050505050505050565b8082018082111561053957634e487b7160e01b5f52601160045260245ffd5b5f808335601e19843603018112610b4d575f80fd5b83018035915067ffffffffffffffff821115610b67575f80fd5b602001915036819003821315610733575f80fd5b8284823760609190911b6bffffffffffffffffffffffff19169101908152601401919050565b5f82518060208501845e5f920191825250919050565b5f82610bd157634e487b7160e01b5f52601260045260245ffd5b500490565b818382375f9101908152919050565b600181811c90821680610bf957607f821691505b602082108103610c1757634e487b7160e01b5f52602260045260245ffd5b50919050565b634e487b7160e01b5f52602160045260245ffdfea2646970667358221220bd7a30e138dfa501d3a0e0f46de21b92a743001bb4b5e70f1c740e76193a8e8b64736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_EIP712DOMAIN = "eip712Domain";

    public static final String FUNC_EXECUTE = "execute";

    public static final String FUNC_GETNONCE = "getNonce";

    public static final String FUNC_VERIFY = "verify";

    public static final Event EIP712DOMAINCHANGED_EVENT = new Event("EIP712DomainChanged", 
            Arrays.<TypeReference<?>>asList());
    ;

    @Deprecated
    protected MinimalForwarder(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MinimalForwarder(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MinimalForwarder(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MinimalForwarder(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<EIP712DomainChangedEventResponse> getEIP712DomainChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(EIP712DOMAINCHANGED_EVENT, transactionReceipt);
        ArrayList<EIP712DomainChangedEventResponse> responses = new ArrayList<EIP712DomainChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EIP712DomainChangedEventResponse typedResponse = new EIP712DomainChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static EIP712DomainChangedEventResponse getEIP712DomainChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(EIP712DOMAINCHANGED_EVENT, log);
        EIP712DomainChangedEventResponse typedResponse = new EIP712DomainChangedEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<EIP712DomainChangedEventResponse> eIP712DomainChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getEIP712DomainChangedEventFromLog(log));
    }

    public Flowable<EIP712DomainChangedEventResponse> eIP712DomainChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EIP712DOMAINCHANGED_EVENT));
        return eIP712DomainChangedEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple7<byte[], String, String, BigInteger, String, byte[], List<BigInteger>>> eip712Domain() {
        final Function function = new Function(FUNC_EIP712DOMAIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes1>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<Tuple7<byte[], String, String, BigInteger, String, byte[], List<BigInteger>>>(function,
                new Callable<Tuple7<byte[], String, String, BigInteger, String, byte[], List<BigInteger>>>() {
                    @Override
                    public Tuple7<byte[], String, String, BigInteger, String, byte[], List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<byte[], String, String, BigInteger, String, byte[], List<BigInteger>>(
                                (byte[]) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (byte[]) results.get(5).getValue(), 
                                convertToNative((List<Uint256>) results.get(6).getValue()));
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> execute(ForwardRequest req, byte[] signature, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_EXECUTE, 
                Arrays.<Type>asList(req, 
                new org.web3j.abi.datatypes.DynamicBytes(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> getNonce(String from) {
        final Function function = new Function(FUNC_GETNONCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> verify(ForwardRequest req, byte[] signature) {
        final Function function = new Function(FUNC_VERIFY, 
                Arrays.<Type>asList(req, 
                new org.web3j.abi.datatypes.DynamicBytes(signature)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static MinimalForwarder load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MinimalForwarder(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MinimalForwarder load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MinimalForwarder(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MinimalForwarder load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MinimalForwarder(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MinimalForwarder load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MinimalForwarder(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MinimalForwarder> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MinimalForwarder.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<MinimalForwarder> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MinimalForwarder.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<MinimalForwarder> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MinimalForwarder.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<MinimalForwarder> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MinimalForwarder.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class ForwardRequest extends DynamicStruct {
        public String from;

        public String to;

        public BigInteger value;

        public BigInteger gas;

        public BigInteger nonce;

        public byte[] data;

        public ForwardRequest(String from, String to, BigInteger value, BigInteger gas, BigInteger nonce, byte[] data) {
            super(new org.web3j.abi.datatypes.Address(160, from), 
                    new org.web3j.abi.datatypes.Address(160, to), 
                    new org.web3j.abi.datatypes.generated.Uint256(value), 
                    new org.web3j.abi.datatypes.generated.Uint256(gas), 
                    new org.web3j.abi.datatypes.generated.Uint256(nonce), 
                    new org.web3j.abi.datatypes.DynamicBytes(data));
            this.from = from;
            this.to = to;
            this.value = value;
            this.gas = gas;
            this.nonce = nonce;
            this.data = data;
        }

        public ForwardRequest(Address from, Address to, Uint256 value, Uint256 gas, Uint256 nonce, DynamicBytes data) {
            super(from, to, value, gas, nonce, data);
            this.from = from.getValue();
            this.to = to.getValue();
            this.value = value.getValue();
            this.gas = gas.getValue();
            this.nonce = nonce.getValue();
            this.data = data.getValue();
        }
    }

    public static class EIP712DomainChangedEventResponse extends BaseEventResponse {
    }
}
