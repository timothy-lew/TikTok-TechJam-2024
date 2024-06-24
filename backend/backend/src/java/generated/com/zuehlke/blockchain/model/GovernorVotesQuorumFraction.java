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
import org.web3j.abi.datatypes.Array;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes1;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint48;
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
public class GovernorVotesQuorumFraction extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_BALLOT_TYPEHASH = "BALLOT_TYPEHASH";

    public static final String FUNC_CLOCK_MODE = "CLOCK_MODE";

    public static final String FUNC_COUNTING_MODE = "COUNTING_MODE";

    public static final String FUNC_EXTENDED_BALLOT_TYPEHASH = "EXTENDED_BALLOT_TYPEHASH";

    public static final String FUNC_CANCEL = "cancel";

    public static final String FUNC_CASTVOTE = "castVote";

    public static final String FUNC_CASTVOTEBYSIG = "castVoteBySig";

    public static final String FUNC_CASTVOTEWITHREASON = "castVoteWithReason";

    public static final String FUNC_CASTVOTEWITHREASONANDPARAMS = "castVoteWithReasonAndParams";

    public static final String FUNC_CASTVOTEWITHREASONANDPARAMSBYSIG = "castVoteWithReasonAndParamsBySig";

    public static final String FUNC_CLOCK = "clock";

    public static final String FUNC_EIP712DOMAIN = "eip712Domain";

    public static final String FUNC_EXECUTE = "execute";

    public static final String FUNC_GETVOTES = "getVotes";

    public static final String FUNC_GETVOTESWITHPARAMS = "getVotesWithParams";

    public static final String FUNC_HASVOTED = "hasVoted";

    public static final String FUNC_HASHPROPOSAL = "hashProposal";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_ONERC1155BATCHRECEIVED = "onERC1155BatchReceived";

    public static final String FUNC_ONERC1155RECEIVED = "onERC1155Received";

    public static final String FUNC_ONERC721RECEIVED = "onERC721Received";

    public static final String FUNC_PROPOSALDEADLINE = "proposalDeadline";

    public static final String FUNC_PROPOSALPROPOSER = "proposalProposer";

    public static final String FUNC_PROPOSALSNAPSHOT = "proposalSnapshot";

    public static final String FUNC_PROPOSALTHRESHOLD = "proposalThreshold";

    public static final String FUNC_PROPOSE = "propose";

    public static final String FUNC_QUORUM = "quorum";

    public static final String FUNC_QUORUMDENOMINATOR = "quorumDenominator";

    public static final String FUNC_quorumNumerator = "quorumNumerator";

    public static final String FUNC_RELAY = "relay";

    public static final String FUNC_STATE = "state";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_TOKEN = "token";

    public static final String FUNC_UPDATEQUORUMNUMERATOR = "updateQuorumNumerator";

    public static final String FUNC_VERSION = "version";

    public static final String FUNC_VOTINGDELAY = "votingDelay";

    public static final String FUNC_VOTINGPERIOD = "votingPeriod";

    public static final Event EIP712DOMAINCHANGED_EVENT = new Event("EIP712DomainChanged", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event PROPOSALCANCELED_EVENT = new Event("ProposalCanceled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event PROPOSALCREATED_EVENT = new Event("ProposalCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<DynamicBytes>>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event PROPOSALEXECUTED_EVENT = new Event("ProposalExecuted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event QUORUMNUMERATORUPDATED_EVENT = new Event("QuorumNumeratorUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VOTECAST_EVENT = new Event("VoteCast", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event VOTECASTWITHPARAMS_EVENT = new Event("VoteCastWithParams", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    @Deprecated
    protected GovernorVotesQuorumFraction(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected GovernorVotesQuorumFraction(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected GovernorVotesQuorumFraction(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected GovernorVotesQuorumFraction(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public static List<ProposalCanceledEventResponse> getProposalCanceledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PROPOSALCANCELED_EVENT, transactionReceipt);
        ArrayList<ProposalCanceledEventResponse> responses = new ArrayList<ProposalCanceledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProposalCanceledEventResponse typedResponse = new ProposalCanceledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ProposalCanceledEventResponse getProposalCanceledEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PROPOSALCANCELED_EVENT, log);
        ProposalCanceledEventResponse typedResponse = new ProposalCanceledEventResponse();
        typedResponse.log = log;
        typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ProposalCanceledEventResponse> proposalCanceledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getProposalCanceledEventFromLog(log));
    }

    public Flowable<ProposalCanceledEventResponse> proposalCanceledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALCANCELED_EVENT));
        return proposalCanceledEventFlowable(filter);
    }

    public static List<ProposalCreatedEventResponse> getProposalCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PROPOSALCREATED_EVENT, transactionReceipt);
        ArrayList<ProposalCreatedEventResponse> responses = new ArrayList<ProposalCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProposalCreatedEventResponse typedResponse = new ProposalCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.proposer = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.targets = (List<String>) ((Array) eventValues.getNonIndexedValues().get(2)).getNativeValueCopy();
            typedResponse.values = (List<BigInteger>) ((Array) eventValues.getNonIndexedValues().get(3)).getNativeValueCopy();
            typedResponse.signatures = (List<String>) ((Array) eventValues.getNonIndexedValues().get(4)).getNativeValueCopy();
            typedResponse.calldatas = (List<byte[]>) ((Array) eventValues.getNonIndexedValues().get(5)).getNativeValueCopy();
            typedResponse.voteStart = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.voteEnd = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
            typedResponse.description = (String) eventValues.getNonIndexedValues().get(8).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ProposalCreatedEventResponse getProposalCreatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PROPOSALCREATED_EVENT, log);
        ProposalCreatedEventResponse typedResponse = new ProposalCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.proposer = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.targets = (List<String>) ((Array) eventValues.getNonIndexedValues().get(2)).getNativeValueCopy();
        typedResponse.values = (List<BigInteger>) ((Array) eventValues.getNonIndexedValues().get(3)).getNativeValueCopy();
        typedResponse.signatures = (List<String>) ((Array) eventValues.getNonIndexedValues().get(4)).getNativeValueCopy();
        typedResponse.calldatas = (List<byte[]>) ((Array) eventValues.getNonIndexedValues().get(5)).getNativeValueCopy();
        typedResponse.voteStart = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
        typedResponse.voteEnd = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
        typedResponse.description = (String) eventValues.getNonIndexedValues().get(8).getValue();
        return typedResponse;
    }

    public Flowable<ProposalCreatedEventResponse> proposalCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getProposalCreatedEventFromLog(log));
    }

    public Flowable<ProposalCreatedEventResponse> proposalCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALCREATED_EVENT));
        return proposalCreatedEventFlowable(filter);
    }

    public static List<ProposalExecutedEventResponse> getProposalExecutedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PROPOSALEXECUTED_EVENT, transactionReceipt);
        ArrayList<ProposalExecutedEventResponse> responses = new ArrayList<ProposalExecutedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProposalExecutedEventResponse typedResponse = new ProposalExecutedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ProposalExecutedEventResponse getProposalExecutedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PROPOSALEXECUTED_EVENT, log);
        ProposalExecutedEventResponse typedResponse = new ProposalExecutedEventResponse();
        typedResponse.log = log;
        typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ProposalExecutedEventResponse> proposalExecutedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getProposalExecutedEventFromLog(log));
    }

    public Flowable<ProposalExecutedEventResponse> proposalExecutedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALEXECUTED_EVENT));
        return proposalExecutedEventFlowable(filter);
    }

    public static List<QuorumNumeratorUpdatedEventResponse> getQuorumNumeratorUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(QUORUMNUMERATORUPDATED_EVENT, transactionReceipt);
        ArrayList<QuorumNumeratorUpdatedEventResponse> responses = new ArrayList<QuorumNumeratorUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            QuorumNumeratorUpdatedEventResponse typedResponse = new QuorumNumeratorUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oldQuorumNumerator = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newQuorumNumerator = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static QuorumNumeratorUpdatedEventResponse getQuorumNumeratorUpdatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(QUORUMNUMERATORUPDATED_EVENT, log);
        QuorumNumeratorUpdatedEventResponse typedResponse = new QuorumNumeratorUpdatedEventResponse();
        typedResponse.log = log;
        typedResponse.oldQuorumNumerator = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.newQuorumNumerator = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<QuorumNumeratorUpdatedEventResponse> quorumNumeratorUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getQuorumNumeratorUpdatedEventFromLog(log));
    }

    public Flowable<QuorumNumeratorUpdatedEventResponse> quorumNumeratorUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(QUORUMNUMERATORUPDATED_EVENT));
        return quorumNumeratorUpdatedEventFlowable(filter);
    }

    public static List<VoteCastEventResponse> getVoteCastEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(VOTECAST_EVENT, transactionReceipt);
        ArrayList<VoteCastEventResponse> responses = new ArrayList<VoteCastEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VoteCastEventResponse typedResponse = new VoteCastEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voter = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.support = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.weight = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.reason = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static VoteCastEventResponse getVoteCastEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(VOTECAST_EVENT, log);
        VoteCastEventResponse typedResponse = new VoteCastEventResponse();
        typedResponse.log = log;
        typedResponse.voter = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.support = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.weight = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.reason = (String) eventValues.getNonIndexedValues().get(3).getValue();
        return typedResponse;
    }

    public Flowable<VoteCastEventResponse> voteCastEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getVoteCastEventFromLog(log));
    }

    public Flowable<VoteCastEventResponse> voteCastEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTECAST_EVENT));
        return voteCastEventFlowable(filter);
    }

    public static List<VoteCastWithParamsEventResponse> getVoteCastWithParamsEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(VOTECASTWITHPARAMS_EVENT, transactionReceipt);
        ArrayList<VoteCastWithParamsEventResponse> responses = new ArrayList<VoteCastWithParamsEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VoteCastWithParamsEventResponse typedResponse = new VoteCastWithParamsEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voter = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.support = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.weight = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.reason = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.params = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static VoteCastWithParamsEventResponse getVoteCastWithParamsEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(VOTECASTWITHPARAMS_EVENT, log);
        VoteCastWithParamsEventResponse typedResponse = new VoteCastWithParamsEventResponse();
        typedResponse.log = log;
        typedResponse.voter = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.support = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.weight = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.reason = (String) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.params = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
        return typedResponse;
    }

    public Flowable<VoteCastWithParamsEventResponse> voteCastWithParamsEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getVoteCastWithParamsEventFromLog(log));
    }

    public Flowable<VoteCastWithParamsEventResponse> voteCastWithParamsEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTECASTWITHPARAMS_EVENT));
        return voteCastWithParamsEventFlowable(filter);
    }

    public RemoteFunctionCall<byte[]> BALLOT_TYPEHASH() {
        final Function function = new Function(FUNC_BALLOT_TYPEHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<String> CLOCK_MODE() {
        final Function function = new Function(FUNC_CLOCK_MODE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> COUNTING_MODE() {
        final Function function = new Function(FUNC_COUNTING_MODE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<byte[]> EXTENDED_BALLOT_TYPEHASH() {
        final Function function = new Function(FUNC_EXTENDED_BALLOT_TYPEHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> cancel(List<String> targets, List<BigInteger> values, List<byte[]> calldatas, byte[] descriptionHash) {
        final Function function = new Function(
                FUNC_CANCEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(targets, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(values, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                        org.web3j.abi.datatypes.DynamicBytes.class,
                        org.web3j.abi.Utils.typeMap(calldatas, org.web3j.abi.datatypes.DynamicBytes.class)), 
                new org.web3j.abi.datatypes.generated.Bytes32(descriptionHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> castVote(BigInteger proposalId, BigInteger support) {
        final Function function = new Function(
                FUNC_CASTVOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(proposalId), 
                new org.web3j.abi.datatypes.generated.Uint8(support)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> castVoteBySig(BigInteger proposalId, BigInteger support, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_CASTVOTEBYSIG, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(proposalId), 
                new org.web3j.abi.datatypes.generated.Uint8(support), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> castVoteWithReason(BigInteger proposalId, BigInteger support, String reason) {
        final Function function = new Function(
                FUNC_CASTVOTEWITHREASON, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(proposalId), 
                new org.web3j.abi.datatypes.generated.Uint8(support), 
                new org.web3j.abi.datatypes.Utf8String(reason)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> castVoteWithReasonAndParams(BigInteger proposalId, BigInteger support, String reason, byte[] params) {
        final Function function = new Function(
                FUNC_CASTVOTEWITHREASONANDPARAMS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(proposalId), 
                new org.web3j.abi.datatypes.generated.Uint8(support), 
                new org.web3j.abi.datatypes.Utf8String(reason), 
                new org.web3j.abi.datatypes.DynamicBytes(params)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> castVoteWithReasonAndParamsBySig(BigInteger proposalId, BigInteger support, String reason, byte[] params, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_CASTVOTEWITHREASONANDPARAMSBYSIG, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(proposalId), 
                new org.web3j.abi.datatypes.generated.Uint8(support), 
                new org.web3j.abi.datatypes.Utf8String(reason), 
                new org.web3j.abi.datatypes.DynamicBytes(params), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> clock() {
        final Function function = new Function(FUNC_CLOCK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint48>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteFunctionCall<TransactionReceipt> execute(List<String> targets, List<BigInteger> values, List<byte[]> calldatas, byte[] descriptionHash, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_EXECUTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(targets, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(values, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                        org.web3j.abi.datatypes.DynamicBytes.class,
                        org.web3j.abi.Utils.typeMap(calldatas, org.web3j.abi.datatypes.DynamicBytes.class)), 
                new org.web3j.abi.datatypes.generated.Bytes32(descriptionHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> getVotes(String account, BigInteger timepoint) {
        final Function function = new Function(FUNC_GETVOTES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(timepoint)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getVotesWithParams(String account, BigInteger timepoint, byte[] params) {
        final Function function = new Function(FUNC_GETVOTESWITHPARAMS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(timepoint), 
                new org.web3j.abi.datatypes.DynamicBytes(params)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> hasVoted(BigInteger proposalId, String account) {
        final Function function = new Function(FUNC_HASVOTED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(proposalId), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> hashProposal(List<String> targets, List<BigInteger> values, List<byte[]> calldatas, byte[] descriptionHash) {
        final Function function = new Function(FUNC_HASHPROPOSAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(targets, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(values, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                        org.web3j.abi.datatypes.DynamicBytes.class,
                        org.web3j.abi.Utils.typeMap(calldatas, org.web3j.abi.datatypes.DynamicBytes.class)), 
                new org.web3j.abi.datatypes.generated.Bytes32(descriptionHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> onERC1155BatchReceived(String param0, String param1, List<BigInteger> param2, List<BigInteger> param3, byte[] param4) {
        final Function function = new Function(
                FUNC_ONERC1155BATCHRECEIVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.Address(160, param1), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(param2, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(param3, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicBytes(param4)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> onERC1155Received(String param0, String param1, BigInteger param2, BigInteger param3, byte[] param4) {
        final Function function = new Function(
                FUNC_ONERC1155RECEIVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.Address(160, param1), 
                new org.web3j.abi.datatypes.generated.Uint256(param2), 
                new org.web3j.abi.datatypes.generated.Uint256(param3), 
                new org.web3j.abi.datatypes.DynamicBytes(param4)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> onERC721Received(String param0, String param1, BigInteger param2, byte[] param3) {
        final Function function = new Function(
                FUNC_ONERC721RECEIVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.Address(160, param1), 
                new org.web3j.abi.datatypes.generated.Uint256(param2), 
                new org.web3j.abi.datatypes.DynamicBytes(param3)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> proposalDeadline(BigInteger proposalId) {
        final Function function = new Function(FUNC_PROPOSALDEADLINE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> proposalProposer(BigInteger proposalId) {
        final Function function = new Function(FUNC_PROPOSALPROPOSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> proposalSnapshot(BigInteger proposalId) {
        final Function function = new Function(FUNC_PROPOSALSNAPSHOT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> proposalThreshold() {
        final Function function = new Function(FUNC_PROPOSALTHRESHOLD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> propose(List<String> targets, List<BigInteger> values, List<byte[]> calldatas, String description) {
        final Function function = new Function(
                FUNC_PROPOSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(targets, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(values, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                        org.web3j.abi.datatypes.DynamicBytes.class,
                        org.web3j.abi.Utils.typeMap(calldatas, org.web3j.abi.datatypes.DynamicBytes.class)), 
                new org.web3j.abi.datatypes.Utf8String(description)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> quorum(BigInteger timepoint) {
        final Function function = new Function(FUNC_QUORUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(timepoint)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> quorumDenominator() {
        final Function function = new Function(FUNC_QUORUMDENOMINATOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> quorumNumerator(BigInteger timepoint) {
        final Function function = new Function(FUNC_quorumNumerator, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(timepoint)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> quorumNumerator() {
        final Function function = new Function(FUNC_quorumNumerator, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> relay(String target, BigInteger value, byte[] data, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_RELAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, target), 
                new org.web3j.abi.datatypes.generated.Uint256(value), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> state(BigInteger proposalId) {
        final Function function = new Function(FUNC_STATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final Function function = new Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> token() {
        final Function function = new Function(FUNC_TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> updateQuorumNumerator(BigInteger newQuorumNumerator) {
        final Function function = new Function(
                FUNC_UPDATEQUORUMNUMERATOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(newQuorumNumerator)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> version() {
        final Function function = new Function(FUNC_VERSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> votingDelay() {
        final Function function = new Function(FUNC_VOTINGDELAY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> votingPeriod() {
        final Function function = new Function(FUNC_VOTINGPERIOD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static GovernorVotesQuorumFraction load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new GovernorVotesQuorumFraction(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static GovernorVotesQuorumFraction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new GovernorVotesQuorumFraction(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static GovernorVotesQuorumFraction load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new GovernorVotesQuorumFraction(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static GovernorVotesQuorumFraction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new GovernorVotesQuorumFraction(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<GovernorVotesQuorumFraction> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(GovernorVotesQuorumFraction.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<GovernorVotesQuorumFraction> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(GovernorVotesQuorumFraction.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<GovernorVotesQuorumFraction> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(GovernorVotesQuorumFraction.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<GovernorVotesQuorumFraction> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(GovernorVotesQuorumFraction.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class EIP712DomainChangedEventResponse extends BaseEventResponse {
    }

    public static class ProposalCanceledEventResponse extends BaseEventResponse {
        public BigInteger proposalId;
    }

    public static class ProposalCreatedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;

        public String proposer;

        public List<String> targets;

        public List<BigInteger> values;

        public List<String> signatures;

        public List<byte[]> calldatas;

        public BigInteger voteStart;

        public BigInteger voteEnd;

        public String description;
    }

    public static class ProposalExecutedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;
    }

    public static class QuorumNumeratorUpdatedEventResponse extends BaseEventResponse {
        public BigInteger oldQuorumNumerator;

        public BigInteger newQuorumNumerator;
    }

    public static class VoteCastEventResponse extends BaseEventResponse {
        public String voter;

        public BigInteger proposalId;

        public BigInteger support;

        public BigInteger weight;

        public String reason;
    }

    public static class VoteCastWithParamsEventResponse extends BaseEventResponse {
        public String voter;

        public BigInteger proposalId;

        public BigInteger support;

        public BigInteger weight;

        public String reason;

        public byte[] params;
    }
}
