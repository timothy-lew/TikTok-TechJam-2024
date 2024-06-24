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
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
import org.web3j.tuples.generated.Tuple3;
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
public class IArbSys extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_ARBBLOCKHASH = "arbBlockHash";

    public static final String FUNC_ARBBLOCKNUMBER = "arbBlockNumber";

    public static final String FUNC_ARBCHAINID = "arbChainID";

    public static final String FUNC_ARBOSVERSION = "arbOSVersion";

    public static final String FUNC_GETSTORAGEGASAVAILABLE = "getStorageGasAvailable";

    public static final String FUNC_ISTOPLEVELCALL = "isTopLevelCall";

    public static final String FUNC_MAPL1SENDERCONTRACTADDRESSTOL2ALIAS = "mapL1SenderContractAddressToL2Alias";

    public static final String FUNC_MYCALLERSADDRESSWITHOUTALIASING = "myCallersAddressWithoutAliasing";

    public static final String FUNC_SENDMERKLETREESTATE = "sendMerkleTreeState";

    public static final String FUNC_SENDTXTOL1 = "sendTxToL1";

    public static final String FUNC_WASMYCALLERSADDRESSALIASED = "wasMyCallersAddressAliased";

    public static final String FUNC_WITHDRAWETH = "withdrawEth";

    public static final Event L2TOL1TRANSACTION_EVENT = new Event("L2ToL1Transaction", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event L2TOL1TX_EVENT = new Event("L2ToL1Tx", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event SENDMERKLEUPDATE_EVENT = new Event("SendMerkleUpdate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected IArbSys(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IArbSys(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IArbSys(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IArbSys(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<L2ToL1TransactionEventResponse> getL2ToL1TransactionEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(L2TOL1TRANSACTION_EVENT, transactionReceipt);
        ArrayList<L2ToL1TransactionEventResponse> responses = new ArrayList<L2ToL1TransactionEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            L2ToL1TransactionEventResponse typedResponse = new L2ToL1TransactionEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.destination = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.uniqueId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.batchNumber = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.caller = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.indexInBatch = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.arbBlockNum = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.ethBlockNum = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.callvalue = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static L2ToL1TransactionEventResponse getL2ToL1TransactionEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(L2TOL1TRANSACTION_EVENT, log);
        L2ToL1TransactionEventResponse typedResponse = new L2ToL1TransactionEventResponse();
        typedResponse.log = log;
        typedResponse.destination = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.uniqueId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.batchNumber = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.caller = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.indexInBatch = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.arbBlockNum = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.ethBlockNum = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.callvalue = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
        typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(6).getValue();
        return typedResponse;
    }

    public Flowable<L2ToL1TransactionEventResponse> l2ToL1TransactionEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getL2ToL1TransactionEventFromLog(log));
    }

    public Flowable<L2ToL1TransactionEventResponse> l2ToL1TransactionEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(L2TOL1TRANSACTION_EVENT));
        return l2ToL1TransactionEventFlowable(filter);
    }

    public static List<L2ToL1TxEventResponse> getL2ToL1TxEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(L2TOL1TX_EVENT, transactionReceipt);
        ArrayList<L2ToL1TxEventResponse> responses = new ArrayList<L2ToL1TxEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            L2ToL1TxEventResponse typedResponse = new L2ToL1TxEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.destination = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.hash = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.position = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.caller = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.arbBlockNum = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.ethBlockNum = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.callvalue = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static L2ToL1TxEventResponse getL2ToL1TxEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(L2TOL1TX_EVENT, log);
        L2ToL1TxEventResponse typedResponse = new L2ToL1TxEventResponse();
        typedResponse.log = log;
        typedResponse.destination = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.hash = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.position = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.caller = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.arbBlockNum = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.ethBlockNum = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.callvalue = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
        return typedResponse;
    }

    public Flowable<L2ToL1TxEventResponse> l2ToL1TxEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getL2ToL1TxEventFromLog(log));
    }

    public Flowable<L2ToL1TxEventResponse> l2ToL1TxEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(L2TOL1TX_EVENT));
        return l2ToL1TxEventFlowable(filter);
    }

    public static List<SendMerkleUpdateEventResponse> getSendMerkleUpdateEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SENDMERKLEUPDATE_EVENT, transactionReceipt);
        ArrayList<SendMerkleUpdateEventResponse> responses = new ArrayList<SendMerkleUpdateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SendMerkleUpdateEventResponse typedResponse = new SendMerkleUpdateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.reserved = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.hash = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.position = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SendMerkleUpdateEventResponse getSendMerkleUpdateEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SENDMERKLEUPDATE_EVENT, log);
        SendMerkleUpdateEventResponse typedResponse = new SendMerkleUpdateEventResponse();
        typedResponse.log = log;
        typedResponse.reserved = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.hash = (byte[]) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.position = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<SendMerkleUpdateEventResponse> sendMerkleUpdateEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSendMerkleUpdateEventFromLog(log));
    }

    public Flowable<SendMerkleUpdateEventResponse> sendMerkleUpdateEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SENDMERKLEUPDATE_EVENT));
        return sendMerkleUpdateEventFlowable(filter);
    }

    public RemoteFunctionCall<byte[]> arbBlockHash(BigInteger arbBlockNum) {
        final Function function = new Function(FUNC_ARBBLOCKHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(arbBlockNum)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<BigInteger> arbBlockNumber() {
        final Function function = new Function(FUNC_ARBBLOCKNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> arbChainID() {
        final Function function = new Function(FUNC_ARBCHAINID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> arbOSVersion() {
        final Function function = new Function(FUNC_ARBOSVERSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getStorageGasAvailable() {
        final Function function = new Function(FUNC_GETSTORAGEGASAVAILABLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> isTopLevelCall() {
        final Function function = new Function(FUNC_ISTOPLEVELCALL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> mapL1SenderContractAddressToL2Alias(String sender, String unused) {
        final Function function = new Function(FUNC_MAPL1SENDERCONTRACTADDRESSTOL2ALIAS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.Address(160, unused)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> myCallersAddressWithoutAliasing() {
        final Function function = new Function(FUNC_MYCALLERSADDRESSWITHOUTALIASING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, byte[], List<byte[]>>> sendMerkleTreeState() {
        final Function function = new Function(FUNC_SENDMERKLETREESTATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, byte[], List<byte[]>>>(function,
                new Callable<Tuple3<BigInteger, byte[], List<byte[]>>>() {
                    @Override
                    public Tuple3<BigInteger, byte[], List<byte[]>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, byte[], List<byte[]>>(
                                (BigInteger) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                convertToNative((List<Bytes32>) results.get(2).getValue()));
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> sendTxToL1(String destination, byte[] data, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SENDTXTOL1, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, destination), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<Boolean> wasMyCallersAddressAliased() {
        final Function function = new Function(FUNC_WASMYCALLERSADDRESSALIASED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawEth(String destination, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_WITHDRAWETH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, destination)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static IArbSys load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IArbSys(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IArbSys load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IArbSys(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IArbSys load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IArbSys(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IArbSys load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IArbSys(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IArbSys> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IArbSys.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IArbSys> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IArbSys.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<IArbSys> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IArbSys.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IArbSys> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IArbSys.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class L2ToL1TransactionEventResponse extends BaseEventResponse {
        public String destination;

        public BigInteger uniqueId;

        public BigInteger batchNumber;

        public String caller;

        public BigInteger indexInBatch;

        public BigInteger arbBlockNum;

        public BigInteger ethBlockNum;

        public BigInteger timestamp;

        public BigInteger callvalue;

        public byte[] data;
    }

    public static class L2ToL1TxEventResponse extends BaseEventResponse {
        public String destination;

        public BigInteger hash;

        public BigInteger position;

        public String caller;

        public BigInteger arbBlockNum;

        public BigInteger ethBlockNum;

        public BigInteger timestamp;

        public BigInteger callvalue;

        public byte[] data;
    }

    public static class SendMerkleUpdateEventResponse extends BaseEventResponse {
        public BigInteger reserved;

        public byte[] hash;

        public BigInteger position;
    }
}
