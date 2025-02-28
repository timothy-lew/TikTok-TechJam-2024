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
public class RefundEscrow extends Contract {
    public static final String BINARY = "60a060405234801561000f575f80fd5b50604051610af3380380610af383398101604081905261002e91610111565b610037336100c2565b6001600160a01b0381166100a75760405162461bcd60e51b815260206004820152602d60248201527f526566756e64457363726f773a2062656e65666963696172792069732074686560448201526c207a65726f206164647265737360981b606482015260840160405180910390fd5b6001600160a01b03166080526002805460ff1916905561013e565b5f80546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b5f60208284031215610121575f80fd5b81516001600160a01b0381168114610137575f80fd5b9392505050565b60805161099761015c5f395f818160b7015261050501526109975ff3fe6080604052600436106100a5575f3560e01c80638da5cb5b116100625780638da5cb5b146101805780639af6549a1461019c578063c19d93fb146101b0578063e3a9db1a146101cf578063f2fde38b14610211578063f340fa0114610230575f80fd5b806338af3eed146100a957806343d726d6146100f457806351cff8d91461010a578063685ca19414610129578063715018a6146101585780638c52dc411461016c575b5f80fd5b3480156100b4575f80fd5b507f00000000000000000000000000000000000000000000000000000000000000005b6040516001600160a01b0390911681526020015b60405180910390f35b3480156100ff575f80fd5b50610108610243565b005b348015610115575f80fd5b506101086101243660046108e0565b6102fd565b348015610134575f80fd5b506101486101433660046108e0565b61037a565b60405190151581526020016100eb565b348015610163575f80fd5b5061010861039b565b348015610177575f80fd5b506101086103ae565b34801561018b575f80fd5b505f546001600160a01b03166100d7565b3480156101a7575f80fd5b5061010861046d565b3480156101bb575f80fd5b5060025460ff166040516100eb9190610916565b3480156101da575f80fd5b506102036101e93660046108e0565b6001600160a01b03165f9081526001602052604090205490565b6040519081526020016100eb565b34801561021c575f80fd5b5061010861022b3660046108e0565b61052b565b61010861023e3660046108e0565b6105a1565b61024b610623565b5f60025460ff16600281111561026357610263610902565b146102c75760405162461bcd60e51b815260206004820152602960248201527f526566756e64457363726f773a2063616e206f6e6c7920636c6f7365207768696044820152686c652061637469766560b81b60648201526084015b60405180910390fd5b6002805460ff1916811790556040517f088672c3a6e342f7cd94a65ba63b79df24a8973927b4d05d803c44bbf787d12f905f90a1565b6103068161037a565b61036e5760405162461bcd60e51b815260206004820152603360248201527f436f6e646974696f6e616c457363726f773a207061796565206973206e6f7420604482015272616c6c6f77656420746f20776974686472617760681b60648201526084016102be565b6103778161067c565b50565b5f600160025460ff16600281111561039457610394610902565b1492915050565b6103a3610623565b6103ac5f6106f2565b565b6103b6610623565b5f60025460ff1660028111156103ce576103ce610902565b146104365760405162461bcd60e51b815260206004820152603260248201527f526566756e64457363726f773a2063616e206f6e6c7920656e61626c6520726560448201527166756e6473207768696c652061637469766560701b60648201526084016102be565b6002805460ff191660011790556040517f599d8e5a83cffb867d051598c4d70e805d59802d8081c1c7d6dffc5b6aca2b89905f90a1565b6002805460ff16600281111561048557610485610902565b146104f85760405162461bcd60e51b815260206004820152603860248201527f526566756e64457363726f773a2062656e65666963696172792063616e206f6e60448201527f6c79207769746864726177207768696c6520636c6f736564000000000000000060648201526084016102be565b6103ac6001600160a01b037f00000000000000000000000000000000000000000000000000000000000000001647610741565b610533610623565b6001600160a01b0381166105985760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b60648201526084016102be565b610377816106f2565b5f60025460ff1660028111156105b9576105b9610902565b1461061a5760405162461bcd60e51b815260206004820152602b60248201527f526566756e64457363726f773a2063616e206f6e6c79206465706f736974207760448201526a68696c652061637469766560a81b60648201526084016102be565b6103778161085b565b5f546001600160a01b031633146103ac5760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657260448201526064016102be565b610684610623565b6001600160a01b0381165f8181526001602052604081208054919055906106ab9082610741565b816001600160a01b03167f7084f5476618d8e60b11ef0d7d3f06914655adb8793e28ff7f018d4c76d505d5826040516106e691815260200190565b60405180910390a25050565b5f80546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b804710156107915760405162461bcd60e51b815260206004820152601d60248201527f416464726573733a20696e73756666696369656e742062616c616e636500000060448201526064016102be565b5f826001600160a01b0316826040515f6040518083038185875af1925050503d805f81146107da576040519150601f19603f3d011682016040523d82523d5f602084013e6107df565b606091505b50509050806108565760405162461bcd60e51b815260206004820152603a60248201527f416464726573733a20756e61626c6520746f2073656e642076616c75652c207260448201527f6563697069656e74206d6179206861766520726576657274656400000000000060648201526084016102be565b505050565b610863610623565b6001600160a01b0381165f9081526001602052604081208054349283929161088c90849061093c565b90915550506040518181526001600160a01b038316907f2da466a7b24304f47e87fa2e1e5a81b9831ce54fec19055ce277ca2f39ba42c4906020016106e6565b6001600160a01b0381168114610377575f80fd5b5f602082840312156108f0575f80fd5b81356108fb816108cc565b9392505050565b634e487b7160e01b5f52602160045260245ffd5b602081016003831061093657634e487b7160e01b5f52602160045260245ffd5b91905290565b8082018082111561095b57634e487b7160e01b5f52601160045260245ffd5b9291505056fea26469706673582212204f61dfd590557e1560af3958e2771218bca7dadb45ff0edea0f268150fd83be164736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_BENEFICIARY = "beneficiary";

    public static final String FUNC_BENEFICIARYWITHDRAW = "beneficiaryWithdraw";

    public static final String FUNC_CLOSE = "close";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_DEPOSITSOF = "depositsOf";

    public static final String FUNC_ENABLEREFUNDS = "enableRefunds";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_STATE = "state";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_WITHDRAWALALLOWED = "withdrawalAllowed";

    public static final Event DEPOSITED_EVENT = new Event("Deposited", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event REFUNDSCLOSED_EVENT = new Event("RefundsClosed", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event REFUNDSENABLED_EVENT = new Event("RefundsEnabled", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event WITHDRAWN_EVENT = new Event("Withdrawn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected RefundEscrow(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RefundEscrow(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RefundEscrow(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RefundEscrow(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<DepositedEventResponse> getDepositedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DEPOSITED_EVENT, transactionReceipt);
        ArrayList<DepositedEventResponse> responses = new ArrayList<DepositedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DepositedEventResponse typedResponse = new DepositedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.payee = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.weiAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DepositedEventResponse getDepositedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DEPOSITED_EVENT, log);
        DepositedEventResponse typedResponse = new DepositedEventResponse();
        typedResponse.log = log;
        typedResponse.payee = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.weiAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<DepositedEventResponse> depositedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDepositedEventFromLog(log));
    }

    public Flowable<DepositedEventResponse> depositedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEPOSITED_EVENT));
        return depositedEventFlowable(filter);
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

    public static List<RefundsClosedEventResponse> getRefundsClosedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(REFUNDSCLOSED_EVENT, transactionReceipt);
        ArrayList<RefundsClosedEventResponse> responses = new ArrayList<RefundsClosedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RefundsClosedEventResponse typedResponse = new RefundsClosedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RefundsClosedEventResponse getRefundsClosedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(REFUNDSCLOSED_EVENT, log);
        RefundsClosedEventResponse typedResponse = new RefundsClosedEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<RefundsClosedEventResponse> refundsClosedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRefundsClosedEventFromLog(log));
    }

    public Flowable<RefundsClosedEventResponse> refundsClosedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REFUNDSCLOSED_EVENT));
        return refundsClosedEventFlowable(filter);
    }

    public static List<RefundsEnabledEventResponse> getRefundsEnabledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(REFUNDSENABLED_EVENT, transactionReceipt);
        ArrayList<RefundsEnabledEventResponse> responses = new ArrayList<RefundsEnabledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RefundsEnabledEventResponse typedResponse = new RefundsEnabledEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RefundsEnabledEventResponse getRefundsEnabledEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(REFUNDSENABLED_EVENT, log);
        RefundsEnabledEventResponse typedResponse = new RefundsEnabledEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<RefundsEnabledEventResponse> refundsEnabledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRefundsEnabledEventFromLog(log));
    }

    public Flowable<RefundsEnabledEventResponse> refundsEnabledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REFUNDSENABLED_EVENT));
        return refundsEnabledEventFlowable(filter);
    }

    public static List<WithdrawnEventResponse> getWithdrawnEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(WITHDRAWN_EVENT, transactionReceipt);
        ArrayList<WithdrawnEventResponse> responses = new ArrayList<WithdrawnEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            WithdrawnEventResponse typedResponse = new WithdrawnEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.payee = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.weiAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static WithdrawnEventResponse getWithdrawnEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(WITHDRAWN_EVENT, log);
        WithdrawnEventResponse typedResponse = new WithdrawnEventResponse();
        typedResponse.log = log;
        typedResponse.payee = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.weiAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<WithdrawnEventResponse> withdrawnEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getWithdrawnEventFromLog(log));
    }

    public Flowable<WithdrawnEventResponse> withdrawnEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWN_EVENT));
        return withdrawnEventFlowable(filter);
    }

    public RemoteFunctionCall<String> beneficiary() {
        final Function function = new Function(FUNC_BENEFICIARY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> beneficiaryWithdraw() {
        final Function function = new Function(
                FUNC_BENEFICIARYWITHDRAW, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> close() {
        final Function function = new Function(
                FUNC_CLOSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deposit(String refundee, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, refundee)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> depositsOf(String payee) {
        final Function function = new Function(FUNC_DEPOSITSOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, payee)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> enableRefunds() {
        final Function function = new Function(
                FUNC_ENABLEREFUNDS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteFunctionCall<BigInteger> state() {
        final Function function = new Function(FUNC_STATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(String payee) {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, payee)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> withdrawalAllowed(String param0) {
        final Function function = new Function(FUNC_WITHDRAWALALLOWED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static RefundEscrow load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RefundEscrow(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RefundEscrow load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RefundEscrow(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RefundEscrow load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new RefundEscrow(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RefundEscrow load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RefundEscrow(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RefundEscrow> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String beneficiary_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, beneficiary_)));
        return deployRemoteCall(RefundEscrow.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<RefundEscrow> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String beneficiary_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, beneficiary_)));
        return deployRemoteCall(RefundEscrow.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<RefundEscrow> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String beneficiary_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, beneficiary_)));
        return deployRemoteCall(RefundEscrow.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<RefundEscrow> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String beneficiary_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, beneficiary_)));
        return deployRemoteCall(RefundEscrow.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class DepositedEventResponse extends BaseEventResponse {
        public String payee;

        public BigInteger weiAmount;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class RefundsClosedEventResponse extends BaseEventResponse {
    }

    public static class RefundsEnabledEventResponse extends BaseEventResponse {
    }

    public static class WithdrawnEventResponse extends BaseEventResponse {
        public String payee;

        public BigInteger weiAmount;
    }
}
