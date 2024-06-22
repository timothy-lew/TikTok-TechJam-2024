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
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint48;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
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
public class IAccessControlDefaultAdminRules extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACCEPTDEFAULTADMINTRANSFER = "acceptDefaultAdminTransfer";

    public static final String FUNC_BEGINDEFAULTADMINTRANSFER = "beginDefaultAdminTransfer";

    public static final String FUNC_CANCELDEFAULTADMINTRANSFER = "cancelDefaultAdminTransfer";

    public static final String FUNC_CHANGEDEFAULTADMINDELAY = "changeDefaultAdminDelay";

    public static final String FUNC_DEFAULTADMIN = "defaultAdmin";

    public static final String FUNC_DEFAULTADMINDELAY = "defaultAdminDelay";

    public static final String FUNC_DEFAULTADMINDELAYINCREASEWAIT = "defaultAdminDelayIncreaseWait";

    public static final String FUNC_GETROLEADMIN = "getRoleAdmin";

    public static final String FUNC_GRANTROLE = "grantRole";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_PENDINGDEFAULTADMIN = "pendingDefaultAdmin";

    public static final String FUNC_PENDINGDEFAULTADMINDELAY = "pendingDefaultAdminDelay";

    public static final String FUNC_RENOUNCEROLE = "renounceRole";

    public static final String FUNC_REVOKEROLE = "revokeRole";

    public static final String FUNC_ROLLBACKDEFAULTADMINDELAY = "rollbackDefaultAdminDelay";

    public static final Event DEFAULTADMINDELAYCHANGECANCELED_EVENT = new Event("DefaultAdminDelayChangeCanceled", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event DEFAULTADMINDELAYCHANGESCHEDULED_EVENT = new Event("DefaultAdminDelayChangeScheduled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint48>() {}, new TypeReference<Uint48>() {}));
    ;

    public static final Event DEFAULTADMINTRANSFERCANCELED_EVENT = new Event("DefaultAdminTransferCanceled", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event DEFAULTADMINTRANSFERSCHEDULED_EVENT = new Event("DefaultAdminTransferScheduled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint48>() {}));
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

    @Deprecated
    protected IAccessControlDefaultAdminRules(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IAccessControlDefaultAdminRules(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IAccessControlDefaultAdminRules(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IAccessControlDefaultAdminRules(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<DefaultAdminDelayChangeCanceledEventResponse> getDefaultAdminDelayChangeCanceledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DEFAULTADMINDELAYCHANGECANCELED_EVENT, transactionReceipt);
        ArrayList<DefaultAdminDelayChangeCanceledEventResponse> responses = new ArrayList<DefaultAdminDelayChangeCanceledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DefaultAdminDelayChangeCanceledEventResponse typedResponse = new DefaultAdminDelayChangeCanceledEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DefaultAdminDelayChangeCanceledEventResponse getDefaultAdminDelayChangeCanceledEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DEFAULTADMINDELAYCHANGECANCELED_EVENT, log);
        DefaultAdminDelayChangeCanceledEventResponse typedResponse = new DefaultAdminDelayChangeCanceledEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<DefaultAdminDelayChangeCanceledEventResponse> defaultAdminDelayChangeCanceledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDefaultAdminDelayChangeCanceledEventFromLog(log));
    }

    public Flowable<DefaultAdminDelayChangeCanceledEventResponse> defaultAdminDelayChangeCanceledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEFAULTADMINDELAYCHANGECANCELED_EVENT));
        return defaultAdminDelayChangeCanceledEventFlowable(filter);
    }

    public static List<DefaultAdminDelayChangeScheduledEventResponse> getDefaultAdminDelayChangeScheduledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DEFAULTADMINDELAYCHANGESCHEDULED_EVENT, transactionReceipt);
        ArrayList<DefaultAdminDelayChangeScheduledEventResponse> responses = new ArrayList<DefaultAdminDelayChangeScheduledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DefaultAdminDelayChangeScheduledEventResponse typedResponse = new DefaultAdminDelayChangeScheduledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newDelay = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.effectSchedule = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DefaultAdminDelayChangeScheduledEventResponse getDefaultAdminDelayChangeScheduledEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DEFAULTADMINDELAYCHANGESCHEDULED_EVENT, log);
        DefaultAdminDelayChangeScheduledEventResponse typedResponse = new DefaultAdminDelayChangeScheduledEventResponse();
        typedResponse.log = log;
        typedResponse.newDelay = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.effectSchedule = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<DefaultAdminDelayChangeScheduledEventResponse> defaultAdminDelayChangeScheduledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDefaultAdminDelayChangeScheduledEventFromLog(log));
    }

    public Flowable<DefaultAdminDelayChangeScheduledEventResponse> defaultAdminDelayChangeScheduledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEFAULTADMINDELAYCHANGESCHEDULED_EVENT));
        return defaultAdminDelayChangeScheduledEventFlowable(filter);
    }

    public static List<DefaultAdminTransferCanceledEventResponse> getDefaultAdminTransferCanceledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DEFAULTADMINTRANSFERCANCELED_EVENT, transactionReceipt);
        ArrayList<DefaultAdminTransferCanceledEventResponse> responses = new ArrayList<DefaultAdminTransferCanceledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DefaultAdminTransferCanceledEventResponse typedResponse = new DefaultAdminTransferCanceledEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DefaultAdminTransferCanceledEventResponse getDefaultAdminTransferCanceledEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DEFAULTADMINTRANSFERCANCELED_EVENT, log);
        DefaultAdminTransferCanceledEventResponse typedResponse = new DefaultAdminTransferCanceledEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<DefaultAdminTransferCanceledEventResponse> defaultAdminTransferCanceledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDefaultAdminTransferCanceledEventFromLog(log));
    }

    public Flowable<DefaultAdminTransferCanceledEventResponse> defaultAdminTransferCanceledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEFAULTADMINTRANSFERCANCELED_EVENT));
        return defaultAdminTransferCanceledEventFlowable(filter);
    }

    public static List<DefaultAdminTransferScheduledEventResponse> getDefaultAdminTransferScheduledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DEFAULTADMINTRANSFERSCHEDULED_EVENT, transactionReceipt);
        ArrayList<DefaultAdminTransferScheduledEventResponse> responses = new ArrayList<DefaultAdminTransferScheduledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DefaultAdminTransferScheduledEventResponse typedResponse = new DefaultAdminTransferScheduledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAdmin = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.acceptSchedule = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DefaultAdminTransferScheduledEventResponse getDefaultAdminTransferScheduledEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DEFAULTADMINTRANSFERSCHEDULED_EVENT, log);
        DefaultAdminTransferScheduledEventResponse typedResponse = new DefaultAdminTransferScheduledEventResponse();
        typedResponse.log = log;
        typedResponse.newAdmin = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.acceptSchedule = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<DefaultAdminTransferScheduledEventResponse> defaultAdminTransferScheduledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDefaultAdminTransferScheduledEventFromLog(log));
    }

    public Flowable<DefaultAdminTransferScheduledEventResponse> defaultAdminTransferScheduledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEFAULTADMINTRANSFERSCHEDULED_EVENT));
        return defaultAdminTransferScheduledEventFlowable(filter);
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

    public RemoteFunctionCall<TransactionReceipt> acceptDefaultAdminTransfer() {
        final Function function = new Function(
                FUNC_ACCEPTDEFAULTADMINTRANSFER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> beginDefaultAdminTransfer(String newAdmin) {
        final Function function = new Function(
                FUNC_BEGINDEFAULTADMINTRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newAdmin)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> cancelDefaultAdminTransfer() {
        final Function function = new Function(
                FUNC_CANCELDEFAULTADMINTRANSFER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> changeDefaultAdminDelay(BigInteger newDelay) {
        final Function function = new Function(
                FUNC_CHANGEDEFAULTADMINDELAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint48(newDelay)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> defaultAdmin() {
        final Function function = new Function(FUNC_DEFAULTADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> defaultAdminDelay() {
        final Function function = new Function(FUNC_DEFAULTADMINDELAY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint48>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> defaultAdminDelayIncreaseWait() {
        final Function function = new Function(FUNC_DEFAULTADMINDELAYINCREASEWAIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint48>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<byte[]> getRoleAdmin(byte[] role) {
        final Function function = new Function(FUNC_GETROLEADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
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

    public RemoteFunctionCall<Tuple2<String, BigInteger>> pendingDefaultAdmin() {
        final Function function = new Function(FUNC_PENDINGDEFAULTADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint48>() {}));
        return new RemoteFunctionCall<Tuple2<String, BigInteger>>(function,
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> pendingDefaultAdminDelay() {
        final Function function = new Function(FUNC_PENDINGDEFAULTADMINDELAY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint48>() {}, new TypeReference<Uint48>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, BigInteger>>(function,
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
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

    public RemoteFunctionCall<TransactionReceipt> rollbackDefaultAdminDelay() {
        final Function function = new Function(
                FUNC_ROLLBACKDEFAULTADMINDELAY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IAccessControlDefaultAdminRules load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IAccessControlDefaultAdminRules(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IAccessControlDefaultAdminRules load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IAccessControlDefaultAdminRules(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IAccessControlDefaultAdminRules load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IAccessControlDefaultAdminRules(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IAccessControlDefaultAdminRules load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IAccessControlDefaultAdminRules(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IAccessControlDefaultAdminRules> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IAccessControlDefaultAdminRules.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IAccessControlDefaultAdminRules> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IAccessControlDefaultAdminRules.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<IAccessControlDefaultAdminRules> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IAccessControlDefaultAdminRules.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IAccessControlDefaultAdminRules> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IAccessControlDefaultAdminRules.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class DefaultAdminDelayChangeCanceledEventResponse extends BaseEventResponse {
    }

    public static class DefaultAdminDelayChangeScheduledEventResponse extends BaseEventResponse {
        public BigInteger newDelay;

        public BigInteger effectSchedule;
    }

    public static class DefaultAdminTransferCanceledEventResponse extends BaseEventResponse {
    }

    public static class DefaultAdminTransferScheduledEventResponse extends BaseEventResponse {
        public String newAdmin;

        public BigInteger acceptSchedule;
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
}
