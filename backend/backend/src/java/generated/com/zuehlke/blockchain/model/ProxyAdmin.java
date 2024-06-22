package com.zuehlke.blockchain.model;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
public class ProxyAdmin extends Contract {
    public static final String BINARY = "6080604052348015600e575f80fd5b50601633601a565b6069565b5f80546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b610652806100765f395ff3fe608060405260043610610079575f3560e01c80639623609d1161004c5780639623609d1461010957806399a88ec41461011c578063f2fde38b1461013b578063f3b7dead1461015a575f80fd5b8063204e1c7a1461007d578063715018a6146100b85780637eff275e146100ce5780638da5cb5b146100ed575b5f80fd5b348015610088575f80fd5b5061009c610097366004610479565b610179565b6040516001600160a01b03909116815260200160405180910390f35b3480156100c3575f80fd5b506100cc610204565b005b3480156100d9575f80fd5b506100cc6100e836600461049b565b610217565b3480156100f8575f80fd5b505f546001600160a01b031661009c565b6100cc6101173660046104e6565b61027a565b348015610127575f80fd5b506100cc61013636600461049b565b6102e5565b348015610146575f80fd5b506100cc610155366004610479565b61031b565b348015610165575f80fd5b5061009c610174366004610479565b610399565b5f805f836001600160a01b031660405161019d90635c60da1b60e01b815260040190565b5f60405180830381855afa9150503d805f81146101d5576040519150601f19603f3d011682016040523d82523d5f602084013e6101da565b606091505b5091509150816101e8575f80fd5b808060200190518101906101fc91906105bd565b949350505050565b61020c6103bd565b6102155f610416565b565b61021f6103bd565b6040516308f2839760e41b81526001600160a01b038281166004830152831690638f283970906024015b5f604051808303815f87803b158015610260575f80fd5b505af1158015610272573d5f803e3d5ffd5b505050505050565b6102826103bd565b60405163278f794360e11b81526001600160a01b03841690634f1ef2869034906102b290869086906004016105d8565b5f604051808303818588803b1580156102c9575f80fd5b505af11580156102db573d5f803e3d5ffd5b5050505050505050565b6102ed6103bd565b604051631b2ce7f360e11b81526001600160a01b038281166004830152831690633659cfe690602401610249565b6103236103bd565b6001600160a01b03811661038d5760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b60648201526084015b60405180910390fd5b61039681610416565b50565b5f805f836001600160a01b031660405161019d906303e1469160e61b815260040190565b5f546001600160a01b031633146102155760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e65726044820152606401610384565b5f80546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b6001600160a01b0381168114610396575f80fd5b5f60208284031215610489575f80fd5b813561049481610465565b9392505050565b5f80604083850312156104ac575f80fd5b82356104b781610465565b915060208301356104c781610465565b809150509250929050565b634e487b7160e01b5f52604160045260245ffd5b5f805f606084860312156104f8575f80fd5b833561050381610465565b9250602084013561051381610465565b9150604084013567ffffffffffffffff81111561052e575f80fd5b8401601f8101861361053e575f80fd5b803567ffffffffffffffff811115610558576105586104d2565b604051601f8201601f19908116603f0116810167ffffffffffffffff81118282101715610587576105876104d2565b60405281815282820160200188101561059e575f80fd5b816020840160208301375f602083830101528093505050509250925092565b5f602082840312156105cd575f80fd5b815161049481610465565b60018060a01b0383168152604060208201525f82518060408401528060208501606085015e5f606082850101526060601f19601f830116840101915050939250505056fea2646970667358221220d0b2ee51ee3ffd7bfa1408d0b661fa0fc630cd2e6a82aa2e98f8505a2730818064736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_CHANGEPROXYADMIN = "changeProxyAdmin";

    public static final String FUNC_GETPROXYADMIN = "getProxyAdmin";

    public static final String FUNC_GETPROXYIMPLEMENTATION = "getProxyImplementation";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_UPGRADE = "upgrade";

    public static final String FUNC_UPGRADEANDCALL = "upgradeAndCall";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected ProxyAdmin(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ProxyAdmin(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ProxyAdmin(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ProxyAdmin(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnershipTransferredEventResponse getOwnershipTransferredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
        OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
        typedResponse.log = log;
        typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnershipTransferredEventFromLog(log));
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> changeProxyAdmin(String proxy, String newAdmin) {
        final Function function = new Function(
                FUNC_CHANGEPROXYADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, proxy), 
                new org.web3j.abi.datatypes.Address(160, newAdmin)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getProxyAdmin(String proxy) {
        final Function function = new Function(FUNC_GETPROXYADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, proxy)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getProxyImplementation(String proxy) {
        final Function function = new Function(FUNC_GETPROXYIMPLEMENTATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, proxy)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> upgrade(String proxy, String implementation) {
        final Function function = new Function(
                FUNC_UPGRADE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, proxy), 
                new org.web3j.abi.datatypes.Address(160, implementation)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> upgradeAndCall(String proxy, String implementation, byte[] data, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_UPGRADEANDCALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, proxy), 
                new org.web3j.abi.datatypes.Address(160, implementation), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static ProxyAdmin load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ProxyAdmin(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ProxyAdmin load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ProxyAdmin(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ProxyAdmin load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ProxyAdmin(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ProxyAdmin load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ProxyAdmin(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ProxyAdmin> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ProxyAdmin.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ProxyAdmin> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ProxyAdmin.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<ProxyAdmin> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ProxyAdmin.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ProxyAdmin> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ProxyAdmin.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }
}
