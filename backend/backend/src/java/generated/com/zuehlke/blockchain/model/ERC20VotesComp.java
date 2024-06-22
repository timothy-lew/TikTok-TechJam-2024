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
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes1;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint224;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.abi.datatypes.generated.Uint48;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.abi.datatypes.generated.Uint96;
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
public class ERC20VotesComp extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_CLOCK_MODE = "CLOCK_MODE";

    public static final String FUNC_DOMAIN_SEPARATOR = "DOMAIN_SEPARATOR";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_CHECKPOINTS = "checkpoints";

    public static final String FUNC_CLOCK = "clock";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_DELEGATE = "delegate";

    public static final String FUNC_DELEGATEBYSIG = "delegateBySig";

    public static final String FUNC_DELEGATES = "delegates";

    public static final String FUNC_EIP712DOMAIN = "eip712Domain";

    public static final String FUNC_GETCURRENTVOTES = "getCurrentVotes";

    public static final String FUNC_GETPASTTOTALSUPPLY = "getPastTotalSupply";

    public static final String FUNC_GETPASTVOTES = "getPastVotes";

    public static final String FUNC_GETPRIORVOTES = "getPriorVotes";

    public static final String FUNC_GETVOTES = "getVotes";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_NONCES = "nonces";

    public static final String FUNC_NUMCHECKPOINTS = "numCheckpoints";

    public static final String FUNC_PERMIT = "permit";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event DELEGATECHANGED_EVENT = new Event("DelegateChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event DELEGATEVOTESCHANGED_EVENT = new Event("DelegateVotesChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event EIP712DOMAINCHANGED_EVENT = new Event("EIP712DomainChanged", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected ERC20VotesComp(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ERC20VotesComp(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ERC20VotesComp(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ERC20VotesComp(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public static List<DelegateChangedEventResponse> getDelegateChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DELEGATECHANGED_EVENT, transactionReceipt);
        ArrayList<DelegateChangedEventResponse> responses = new ArrayList<DelegateChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DelegateChangedEventResponse typedResponse = new DelegateChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.delegator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.fromDelegate = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.toDelegate = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DelegateChangedEventResponse getDelegateChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DELEGATECHANGED_EVENT, log);
        DelegateChangedEventResponse typedResponse = new DelegateChangedEventResponse();
        typedResponse.log = log;
        typedResponse.delegator = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.fromDelegate = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.toDelegate = (String) eventValues.getIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<DelegateChangedEventResponse> delegateChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDelegateChangedEventFromLog(log));
    }

    public Flowable<DelegateChangedEventResponse> delegateChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DELEGATECHANGED_EVENT));
        return delegateChangedEventFlowable(filter);
    }

    public static List<DelegateVotesChangedEventResponse> getDelegateVotesChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DELEGATEVOTESCHANGED_EVENT, transactionReceipt);
        ArrayList<DelegateVotesChangedEventResponse> responses = new ArrayList<DelegateVotesChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DelegateVotesChangedEventResponse typedResponse = new DelegateVotesChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.delegate = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.previousBalance = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newBalance = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DelegateVotesChangedEventResponse getDelegateVotesChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DELEGATEVOTESCHANGED_EVENT, log);
        DelegateVotesChangedEventResponse typedResponse = new DelegateVotesChangedEventResponse();
        typedResponse.log = log;
        typedResponse.delegate = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.previousBalance = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.newBalance = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<DelegateVotesChangedEventResponse> delegateVotesChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDelegateVotesChangedEventFromLog(log));
    }

    public Flowable<DelegateVotesChangedEventResponse> delegateVotesChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DELEGATEVOTESCHANGED_EVENT));
        return delegateVotesChangedEventFlowable(filter);
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

    public RemoteFunctionCall<String> CLOCK_MODE() {
        final Function function = new Function(FUNC_CLOCK_MODE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<byte[]> DOMAIN_SEPARATOR() {
        final Function function = new Function(FUNC_DOMAIN_SEPARATOR, 
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

    public RemoteFunctionCall<Checkpoint> checkpoints(String account, BigInteger pos) {
        final Function function = new Function(FUNC_CHECKPOINTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint32(pos)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Checkpoint>() {}));
        return executeRemoteCallSingleValueReturn(function, Checkpoint.class);
    }

    public RemoteFunctionCall<BigInteger> clock() {
        final Function function = new Function(FUNC_CLOCK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint48>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteFunctionCall<TransactionReceipt> delegate(String delegatee) {
        final Function function = new Function(
                FUNC_DELEGATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, delegatee)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> delegateBySig(String delegatee, BigInteger nonce, BigInteger expiry, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_DELEGATEBYSIG, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, delegatee), 
                new org.web3j.abi.datatypes.generated.Uint256(nonce), 
                new org.web3j.abi.datatypes.generated.Uint256(expiry), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> delegates(String account) {
        final Function function = new Function(FUNC_DELEGATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteFunctionCall<BigInteger> getCurrentVotes(String account) {
        final Function function = new Function(FUNC_GETCURRENTVOTES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint96>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getPastTotalSupply(BigInteger timepoint) {
        final Function function = new Function(FUNC_GETPASTTOTALSUPPLY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(timepoint)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getPastVotes(String account, BigInteger timepoint) {
        final Function function = new Function(FUNC_GETPASTVOTES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(timepoint)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getPriorVotes(String account, BigInteger blockNumber) {
        final Function function = new Function(FUNC_GETPRIORVOTES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(blockNumber)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint96>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getVotes(String account) {
        final Function function = new Function(FUNC_GETVOTES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger addedValue) {
        final Function function = new Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(addedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> nonces(String owner) {
        final Function function = new Function(FUNC_NONCES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> numCheckpoints(String account) {
        final Function function = new Function(FUNC_NUMCHECKPOINTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> permit(String owner, String spender, BigInteger value, BigInteger deadline, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_PERMIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(value), 
                new org.web3j.abi.datatypes.generated.Uint256(deadline), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
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

    @Deprecated
    public static ERC20VotesComp load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC20VotesComp(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ERC20VotesComp load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC20VotesComp(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ERC20VotesComp load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ERC20VotesComp(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ERC20VotesComp load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ERC20VotesComp(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ERC20VotesComp> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ERC20VotesComp.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ERC20VotesComp> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ERC20VotesComp.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<ERC20VotesComp> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ERC20VotesComp.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ERC20VotesComp> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ERC20VotesComp.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class Checkpoint extends StaticStruct {
        public BigInteger fromBlock;

        public BigInteger votes;

        public Checkpoint(BigInteger fromBlock, BigInteger votes) {
            super(new org.web3j.abi.datatypes.generated.Uint32(fromBlock), 
                    new org.web3j.abi.datatypes.generated.Uint224(votes));
            this.fromBlock = fromBlock;
            this.votes = votes;
        }

        public Checkpoint(Uint32 fromBlock, Uint224 votes) {
            super(fromBlock, votes);
            this.fromBlock = fromBlock.getValue();
            this.votes = votes.getValue();
        }
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class DelegateChangedEventResponse extends BaseEventResponse {
        public String delegator;

        public String fromDelegate;

        public String toDelegate;
    }

    public static class DelegateVotesChangedEventResponse extends BaseEventResponse {
        public String delegate;

        public BigInteger previousBalance;

        public BigInteger newBalance;
    }

    public static class EIP712DomainChangedEventResponse extends BaseEventResponse {
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
