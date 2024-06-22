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
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint64;
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
public class IBridge extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVEOUTBOX = "activeOutbox";

    public static final String FUNC_ALLOWEDDELAYEDINBOXLIST = "allowedDelayedInboxList";

    public static final String FUNC_ALLOWEDDELAYEDINBOXES = "allowedDelayedInboxes";

    public static final String FUNC_ALLOWEDOUTBOXLIST = "allowedOutboxList";

    public static final String FUNC_ALLOWEDOUTBOXES = "allowedOutboxes";

    public static final String FUNC_DELAYEDINBOXACCS = "delayedInboxAccs";

    public static final String FUNC_DELAYEDMESSAGECOUNT = "delayedMessageCount";

    public static final String FUNC_ENQUEUEDELAYEDMESSAGE = "enqueueDelayedMessage";

    public static final String FUNC_ENQUEUESEQUENCERMESSAGE = "enqueueSequencerMessage";

    public static final String FUNC_EXECUTECALL = "executeCall";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_ROLLUP = "rollup";

    public static final String FUNC_SEQUENCERINBOX = "sequencerInbox";

    public static final String FUNC_SEQUENCERINBOXACCS = "sequencerInboxAccs";

    public static final String FUNC_SEQUENCERMESSAGECOUNT = "sequencerMessageCount";

    public static final String FUNC_SEQUENCERREPORTEDSUBMESSAGECOUNT = "sequencerReportedSubMessageCount";

    public static final String FUNC_SETDELAYEDINBOX = "setDelayedInbox";

    public static final String FUNC_SETOUTBOX = "setOutbox";

    public static final String FUNC_SETSEQUENCERINBOX = "setSequencerInbox";

    public static final String FUNC_SUBMITBATCHSPENDINGREPORT = "submitBatchSpendingReport";

    public static final Event BRIDGECALLTRIGGERED_EVENT = new Event("BridgeCallTriggered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event INBOXTOGGLE_EVENT = new Event("InboxToggle", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event MESSAGEDELIVERED_EVENT = new Event("MessageDelivered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint64>() {}));
    ;

    public static final Event OUTBOXTOGGLE_EVENT = new Event("OutboxToggle", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event SEQUENCERINBOXUPDATED_EVENT = new Event("SequencerInboxUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected IBridge(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IBridge(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IBridge(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IBridge(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<BridgeCallTriggeredEventResponse> getBridgeCallTriggeredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BRIDGECALLTRIGGERED_EVENT, transactionReceipt);
        ArrayList<BridgeCallTriggeredEventResponse> responses = new ArrayList<BridgeCallTriggeredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BridgeCallTriggeredEventResponse typedResponse = new BridgeCallTriggeredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.outbox = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static BridgeCallTriggeredEventResponse getBridgeCallTriggeredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(BRIDGECALLTRIGGERED_EVENT, log);
        BridgeCallTriggeredEventResponse typedResponse = new BridgeCallTriggeredEventResponse();
        typedResponse.log = log;
        typedResponse.outbox = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<BridgeCallTriggeredEventResponse> bridgeCallTriggeredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getBridgeCallTriggeredEventFromLog(log));
    }

    public Flowable<BridgeCallTriggeredEventResponse> bridgeCallTriggeredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BRIDGECALLTRIGGERED_EVENT));
        return bridgeCallTriggeredEventFlowable(filter);
    }

    public static List<InboxToggleEventResponse> getInboxToggleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(INBOXTOGGLE_EVENT, transactionReceipt);
        ArrayList<InboxToggleEventResponse> responses = new ArrayList<InboxToggleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InboxToggleEventResponse typedResponse = new InboxToggleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.inbox = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.enabled = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static InboxToggleEventResponse getInboxToggleEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(INBOXTOGGLE_EVENT, log);
        InboxToggleEventResponse typedResponse = new InboxToggleEventResponse();
        typedResponse.log = log;
        typedResponse.inbox = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.enabled = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<InboxToggleEventResponse> inboxToggleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getInboxToggleEventFromLog(log));
    }

    public Flowable<InboxToggleEventResponse> inboxToggleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INBOXTOGGLE_EVENT));
        return inboxToggleEventFlowable(filter);
    }

    public static List<MessageDeliveredEventResponse> getMessageDeliveredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MESSAGEDELIVERED_EVENT, transactionReceipt);
        ArrayList<MessageDeliveredEventResponse> responses = new ArrayList<MessageDeliveredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MessageDeliveredEventResponse typedResponse = new MessageDeliveredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.messageIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.beforeInboxAcc = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.inbox = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.kind = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.messageDataHash = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.baseFeeL1 = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MessageDeliveredEventResponse getMessageDeliveredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MESSAGEDELIVERED_EVENT, log);
        MessageDeliveredEventResponse typedResponse = new MessageDeliveredEventResponse();
        typedResponse.log = log;
        typedResponse.messageIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.beforeInboxAcc = (byte[]) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.inbox = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.kind = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.sender = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.messageDataHash = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.baseFeeL1 = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
        return typedResponse;
    }

    public Flowable<MessageDeliveredEventResponse> messageDeliveredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMessageDeliveredEventFromLog(log));
    }

    public Flowable<MessageDeliveredEventResponse> messageDeliveredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MESSAGEDELIVERED_EVENT));
        return messageDeliveredEventFlowable(filter);
    }

    public static List<OutboxToggleEventResponse> getOutboxToggleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OUTBOXTOGGLE_EVENT, transactionReceipt);
        ArrayList<OutboxToggleEventResponse> responses = new ArrayList<OutboxToggleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OutboxToggleEventResponse typedResponse = new OutboxToggleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.outbox = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.enabled = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OutboxToggleEventResponse getOutboxToggleEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OUTBOXTOGGLE_EVENT, log);
        OutboxToggleEventResponse typedResponse = new OutboxToggleEventResponse();
        typedResponse.log = log;
        typedResponse.outbox = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.enabled = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<OutboxToggleEventResponse> outboxToggleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOutboxToggleEventFromLog(log));
    }

    public Flowable<OutboxToggleEventResponse> outboxToggleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OUTBOXTOGGLE_EVENT));
        return outboxToggleEventFlowable(filter);
    }

    public static List<SequencerInboxUpdatedEventResponse> getSequencerInboxUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SEQUENCERINBOXUPDATED_EVENT, transactionReceipt);
        ArrayList<SequencerInboxUpdatedEventResponse> responses = new ArrayList<SequencerInboxUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SequencerInboxUpdatedEventResponse typedResponse = new SequencerInboxUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newSequencerInbox = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SequencerInboxUpdatedEventResponse getSequencerInboxUpdatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SEQUENCERINBOXUPDATED_EVENT, log);
        SequencerInboxUpdatedEventResponse typedResponse = new SequencerInboxUpdatedEventResponse();
        typedResponse.log = log;
        typedResponse.newSequencerInbox = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<SequencerInboxUpdatedEventResponse> sequencerInboxUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSequencerInboxUpdatedEventFromLog(log));
    }

    public Flowable<SequencerInboxUpdatedEventResponse> sequencerInboxUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SEQUENCERINBOXUPDATED_EVENT));
        return sequencerInboxUpdatedEventFlowable(filter);
    }

    public RemoteFunctionCall<String> activeOutbox() {
        final Function function = new Function(FUNC_ACTIVEOUTBOX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> allowedDelayedInboxList(BigInteger param0) {
        final Function function = new Function(
                FUNC_ALLOWEDDELAYEDINBOXLIST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> allowedDelayedInboxes(String inbox) {
        final Function function = new Function(FUNC_ALLOWEDDELAYEDINBOXES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, inbox)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> allowedOutboxList(BigInteger param0) {
        final Function function = new Function(
                FUNC_ALLOWEDOUTBOXLIST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> allowedOutboxes(String outbox) {
        final Function function = new Function(FUNC_ALLOWEDOUTBOXES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, outbox)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<byte[]> delayedInboxAccs(BigInteger param0) {
        final Function function = new Function(FUNC_DELAYEDINBOXACCS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<BigInteger> delayedMessageCount() {
        final Function function = new Function(FUNC_DELAYEDMESSAGECOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> enqueueDelayedMessage(BigInteger kind, String sender, byte[] messageDataHash, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_ENQUEUEDELAYEDMESSAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(kind), 
                new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.generated.Bytes32(messageDataHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> enqueueSequencerMessage(byte[] dataHash, BigInteger afterDelayedMessagesRead, BigInteger prevMessageCount, BigInteger newMessageCount) {
        final Function function = new Function(
                FUNC_ENQUEUESEQUENCERMESSAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(dataHash), 
                new org.web3j.abi.datatypes.generated.Uint256(afterDelayedMessagesRead), 
                new org.web3j.abi.datatypes.generated.Uint256(prevMessageCount), 
                new org.web3j.abi.datatypes.generated.Uint256(newMessageCount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> executeCall(String to, BigInteger value, byte[] data) {
        final Function function = new Function(
                FUNC_EXECUTECALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(value), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(String rollup_) {
        final Function function = new Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, rollup_)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> rollup() {
        final Function function = new Function(FUNC_ROLLUP, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> sequencerInbox() {
        final Function function = new Function(FUNC_SEQUENCERINBOX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<byte[]> sequencerInboxAccs(BigInteger param0) {
        final Function function = new Function(FUNC_SEQUENCERINBOXACCS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<BigInteger> sequencerMessageCount() {
        final Function function = new Function(FUNC_SEQUENCERMESSAGECOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> sequencerReportedSubMessageCount() {
        final Function function = new Function(FUNC_SEQUENCERREPORTEDSUBMESSAGECOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setDelayedInbox(String inbox, Boolean enabled) {
        final Function function = new Function(
                FUNC_SETDELAYEDINBOX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, inbox), 
                new org.web3j.abi.datatypes.Bool(enabled)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setOutbox(String inbox, Boolean enabled) {
        final Function function = new Function(
                FUNC_SETOUTBOX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, inbox), 
                new org.web3j.abi.datatypes.Bool(enabled)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setSequencerInbox(String _sequencerInbox) {
        final Function function = new Function(
                FUNC_SETSEQUENCERINBOX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _sequencerInbox)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> submitBatchSpendingReport(String batchPoster, byte[] dataHash) {
        final Function function = new Function(
                FUNC_SUBMITBATCHSPENDINGREPORT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, batchPoster), 
                new org.web3j.abi.datatypes.generated.Bytes32(dataHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IBridge load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IBridge(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IBridge load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IBridge(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IBridge load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IBridge(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IBridge load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IBridge(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IBridge> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IBridge.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IBridge> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IBridge.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<IBridge> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IBridge.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IBridge> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IBridge.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class BridgeCallTriggeredEventResponse extends BaseEventResponse {
        public String outbox;

        public String to;

        public BigInteger value;

        public byte[] data;
    }

    public static class InboxToggleEventResponse extends BaseEventResponse {
        public String inbox;

        public Boolean enabled;
    }

    public static class MessageDeliveredEventResponse extends BaseEventResponse {
        public BigInteger messageIndex;

        public byte[] beforeInboxAcc;

        public String inbox;

        public BigInteger kind;

        public String sender;

        public byte[] messageDataHash;

        public BigInteger baseFeeL1;

        public BigInteger timestamp;
    }

    public static class OutboxToggleEventResponse extends BaseEventResponse {
        public String outbox;

        public Boolean enabled;
    }

    public static class SequencerInboxUpdatedEventResponse extends BaseEventResponse {
        public String newSequencerInbox;
    }
}
