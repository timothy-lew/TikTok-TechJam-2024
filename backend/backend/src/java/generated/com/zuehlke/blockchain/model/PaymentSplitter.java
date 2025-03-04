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
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
public class PaymentSplitter extends Contract {
    public static final String BINARY = "60806040526040516110b23803806110b2833981016040819052610022916103f0565b80518251146100935760405162461bcd60e51b815260206004820152603260248201527f5061796d656e7453706c69747465723a2070617965657320616e6420736861726044820152710cae640d8cadccee8d040dad2e6dac2e8c6d60731b60648201526084015b60405180910390fd5b5f8251116100e35760405162461bcd60e51b815260206004820152601a60248201527f5061796d656e7453706c69747465723a206e6f20706179656573000000000000604482015260640161008a565b5f5b825181101561013857610130838281518110610103576101036104c0565b602002602001015183838151811061011d5761011d6104c0565b602002602001015161014060201b60201c565b6001016100e5565b5050506104f9565b6001600160a01b0382166101ab5760405162461bcd60e51b815260206004820152602c60248201527f5061796d656e7453706c69747465723a206163636f756e74206973207468652060448201526b7a65726f206164647265737360a01b606482015260840161008a565b5f81116101fa5760405162461bcd60e51b815260206004820152601d60248201527f5061796d656e7453706c69747465723a20736861726573206172652030000000604482015260640161008a565b6001600160a01b0382165f90815260026020526040902054156102735760405162461bcd60e51b815260206004820152602b60248201527f5061796d656e7453706c69747465723a206163636f756e7420616c726561647960448201526a206861732073686172657360a81b606482015260840161008a565b60048054600181019091557f8a35acfbc15ff81a39ae7d344fd709f28e8600b4aa8c65c6b64bfe7fe36bd19b0180546001600160a01b0319166001600160a01b0384169081179091555f908152600260205260408120829055546102d89082906104d4565b5f55604080516001600160a01b0384168152602081018390527f40c340f65e17194d14ddddb073d3c9f888e3cb52b5aae0c6c7706b4fbc905fac910160405180910390a15050565b634e487b7160e01b5f52604160045260245ffd5b604051601f8201601f191681016001600160401b038111828210171561035c5761035c610320565b604052919050565b5f6001600160401b0382111561037c5761037c610320565b5060051b60200190565b5f82601f830112610395575f80fd5b81516103a86103a382610364565b610334565b8082825260208201915060208360051b8601019250858311156103c9575f80fd5b602085015b838110156103e65780518352602092830192016103ce565b5095945050505050565b5f8060408385031215610401575f80fd5b82516001600160401b03811115610416575f80fd5b8301601f81018513610426575f80fd5b80516104346103a382610364565b8082825260208201915060208360051b850101925087831115610455575f80fd5b6020840193505b8284101561048b5783516001600160a01b038116811461047a575f80fd5b82526020938401939091019061045c565b6020870151909550925050506001600160401b038111156104aa575f80fd5b6104b685828601610386565b9150509250929050565b634e487b7160e01b5f52603260045260245ffd5b808201808211156104f357634e487b7160e01b5f52601160045260245ffd5b92915050565b610bac806105065f395ff3fe60806040526004361061009d575f3560e01c80639852595c116100625780639852595c146101a2578063a3f8eace146101d6578063c45ac050146101f5578063ce7c2ac214610214578063d79779b214610248578063e33b7de31461027c575f80fd5b806319165587146100ea5780633a98ef391461010b578063406072a91461012d57806348b750441461014c5780638b83209b1461016b575f80fd5b366100e6577f6ef95f06320e7a25a04a175ca677b7052bdd97131872c2192525a629f51be77033604080516001600160a01b0390921682523460208301520160405180910390a1005b5f80fd5b3480156100f5575f80fd5b50610109610104366004610977565b610290565b005b348015610116575f80fd5b505f545b6040519081526020015b60405180910390f35b348015610138575f80fd5b5061011a610147366004610992565b61037b565b348015610157575f80fd5b50610109610166366004610992565b6103a7565b348015610176575f80fd5b5061018a6101853660046109c9565b6104b3565b6040516001600160a01b039091168152602001610124565b3480156101ad575f80fd5b5061011a6101bc366004610977565b6001600160a01b03165f9081526003602052604090205490565b3480156101e1575f80fd5b5061011a6101f0366004610977565b6104e1565b348015610200575f80fd5b5061011a61020f366004610992565b610527565b34801561021f575f80fd5b5061011a61022e366004610977565b6001600160a01b03165f9081526002602052604090205490565b348015610253575f80fd5b5061011a610262366004610977565b6001600160a01b03165f9081526005602052604090205490565b348015610287575f80fd5b5060015461011a565b6001600160a01b0381165f908152600260205260409020546102cd5760405162461bcd60e51b81526004016102c4906109e0565b60405180910390fd5b5f6102d7826104e1565b9050805f036102f85760405162461bcd60e51b81526004016102c490610a26565b8060015f8282546103099190610a85565b90915550506001600160a01b0382165f90815260036020526040902080548201905561033582826105ca565b604080516001600160a01b0384168152602081018390527fdf20fd1e76bc69d672e4814fafb2c449bba3a5369d8359adf9e05e6fde87b056910160405180910390a15050565b6001600160a01b038083165f908152600660209081526040808320938516835292905220545b92915050565b6001600160a01b0381165f908152600260205260409020546103db5760405162461bcd60e51b81526004016102c4906109e0565b5f6103e68383610527565b9050805f036104075760405162461bcd60e51b81526004016102c490610a26565b6001600160a01b0383165f908152600560205260408120805483929061042e908490610a85565b90915550506001600160a01b038084165f9081526006602090815260408083209386168352929052208054820190556104688383836106e4565b604080516001600160a01b038481168252602082018490528516917f3be5b7a71e84ed12875d241991c70855ac5817d847039e17a9d895c1ceb0f18a910160405180910390a2505050565b5f600482815481106104c7576104c7610a98565b5f918252602090912001546001600160a01b031692915050565b5f806104ec60015490565b6104f69047610a85565b9050610520838261051b866001600160a01b03165f9081526003602052604090205490565b610736565b9392505050565b6001600160a01b0382165f9081526005602052604081205481906040516370a0823160e01b81523060048201526001600160a01b038616906370a0823190602401602060405180830381865afa158015610583573d5f803e3d5ffd5b505050506040513d601f19601f820116820180604052508101906105a79190610aac565b6105b19190610a85565b90506105c2838261051b878761037b565b949350505050565b8047101561061a5760405162461bcd60e51b815260206004820152601d60248201527f416464726573733a20696e73756666696369656e742062616c616e636500000060448201526064016102c4565b5f826001600160a01b0316826040515f6040518083038185875af1925050503d805f8114610663576040519150601f19603f3d011682016040523d82523d5f602084013e610668565b606091505b50509050806106df5760405162461bcd60e51b815260206004820152603a60248201527f416464726573733a20756e61626c6520746f2073656e642076616c75652c207260448201527f6563697069656e74206d6179206861766520726576657274656400000000000060648201526084016102c4565b505050565b604080516001600160a01b038416602482015260448082018490528251808303909101815260649091019091526020810180516001600160e01b031663a9059cbb60e01b1790526106df908490610770565b5f80546001600160a01b03851682526002602052604082205483919061075c9086610ac3565b6107669190610ada565b6105c29190610af9565b5f6107c4826040518060400160405280602081526020017f5361666545524332303a206c6f772d6c6576656c2063616c6c206661696c6564815250856001600160a01b03166108439092919063ffffffff16565b905080515f14806107e45750808060200190518101906107e49190610b0c565b6106df5760405162461bcd60e51b815260206004820152602a60248201527f5361666545524332303a204552433230206f7065726174696f6e20646964206e6044820152691bdd081cdd58d8d9595960b21b60648201526084016102c4565b60606105c284845f85855f80866001600160a01b031685876040516108689190610b2b565b5f6040518083038185875af1925050503d805f81146108a2576040519150601f19603f3d011682016040523d82523d5f602084013e6108a7565b606091505b50915091506108b8878383876108c3565b979650505050505050565b606083156109315782515f0361092a576001600160a01b0385163b61092a5760405162461bcd60e51b815260206004820152601d60248201527f416464726573733a2063616c6c20746f206e6f6e2d636f6e747261637400000060448201526064016102c4565b50816105c2565b6105c283838151156109465781518083602001fd5b8060405162461bcd60e51b81526004016102c49190610b41565b6001600160a01b0381168114610974575f80fd5b50565b5f60208284031215610987575f80fd5b813561052081610960565b5f80604083850312156109a3575f80fd5b82356109ae81610960565b915060208301356109be81610960565b809150509250929050565b5f602082840312156109d9575f80fd5b5035919050565b60208082526026908201527f5061796d656e7453706c69747465723a206163636f756e7420686173206e6f2060408201526573686172657360d01b606082015260800190565b6020808252602b908201527f5061796d656e7453706c69747465723a206163636f756e74206973206e6f742060408201526a191d59481c185e5b595b9d60aa1b606082015260800190565b634e487b7160e01b5f52601160045260245ffd5b808201808211156103a1576103a1610a71565b634e487b7160e01b5f52603260045260245ffd5b5f60208284031215610abc575f80fd5b5051919050565b80820281158282048414176103a1576103a1610a71565b5f82610af457634e487b7160e01b5f52601260045260245ffd5b500490565b818103818111156103a1576103a1610a71565b5f60208284031215610b1c575f80fd5b81518015158114610520575f80fd5b5f82518060208501845e5f920191825250919050565b602081525f82518060208401528060208501604085015e5f604082850101526040601f19601f8301168401019150509291505056fea2646970667358221220be4c7851e130c176bd397f151f30dc0c71313cc784c8a4e5a4ae8c4d41823aee64736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_PAYEE = "payee";

    public static final String FUNC_releasable = "releasable";

    public static final String FUNC_release = "release";

    public static final String FUNC_released = "released";

    public static final String FUNC_SHARES = "shares";

    public static final String FUNC_totalReleased = "totalReleased";

    public static final String FUNC_TOTALSHARES = "totalShares";

    public static final Event ERC20PAYMENTRELEASED_EVENT = new Event("ERC20PaymentReleased", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PAYEEADDED_EVENT = new Event("PayeeAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PAYMENTRECEIVED_EVENT = new Event("PaymentReceived", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PAYMENTRELEASED_EVENT = new Event("PaymentReleased", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected PaymentSplitter(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PaymentSplitter(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PaymentSplitter(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PaymentSplitter(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ERC20PaymentReleasedEventResponse> getERC20PaymentReleasedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ERC20PAYMENTRELEASED_EVENT, transactionReceipt);
        ArrayList<ERC20PaymentReleasedEventResponse> responses = new ArrayList<ERC20PaymentReleasedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ERC20PaymentReleasedEventResponse typedResponse = new ERC20PaymentReleasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.token = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ERC20PaymentReleasedEventResponse getERC20PaymentReleasedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ERC20PAYMENTRELEASED_EVENT, log);
        ERC20PaymentReleasedEventResponse typedResponse = new ERC20PaymentReleasedEventResponse();
        typedResponse.log = log;
        typedResponse.token = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.to = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<ERC20PaymentReleasedEventResponse> eRC20PaymentReleasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getERC20PaymentReleasedEventFromLog(log));
    }

    public Flowable<ERC20PaymentReleasedEventResponse> eRC20PaymentReleasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ERC20PAYMENTRELEASED_EVENT));
        return eRC20PaymentReleasedEventFlowable(filter);
    }

    public static List<PayeeAddedEventResponse> getPayeeAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAYEEADDED_EVENT, transactionReceipt);
        ArrayList<PayeeAddedEventResponse> responses = new ArrayList<PayeeAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PayeeAddedEventResponse typedResponse = new PayeeAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.shares = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PayeeAddedEventResponse getPayeeAddedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PAYEEADDED_EVENT, log);
        PayeeAddedEventResponse typedResponse = new PayeeAddedEventResponse();
        typedResponse.log = log;
        typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.shares = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<PayeeAddedEventResponse> payeeAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPayeeAddedEventFromLog(log));
    }

    public Flowable<PayeeAddedEventResponse> payeeAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAYEEADDED_EVENT));
        return payeeAddedEventFlowable(filter);
    }

    public static List<PaymentReceivedEventResponse> getPaymentReceivedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAYMENTRECEIVED_EVENT, transactionReceipt);
        ArrayList<PaymentReceivedEventResponse> responses = new ArrayList<PaymentReceivedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PaymentReceivedEventResponse typedResponse = new PaymentReceivedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PaymentReceivedEventResponse getPaymentReceivedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PAYMENTRECEIVED_EVENT, log);
        PaymentReceivedEventResponse typedResponse = new PaymentReceivedEventResponse();
        typedResponse.log = log;
        typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<PaymentReceivedEventResponse> paymentReceivedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPaymentReceivedEventFromLog(log));
    }

    public Flowable<PaymentReceivedEventResponse> paymentReceivedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAYMENTRECEIVED_EVENT));
        return paymentReceivedEventFlowable(filter);
    }

    public static List<PaymentReleasedEventResponse> getPaymentReleasedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAYMENTRELEASED_EVENT, transactionReceipt);
        ArrayList<PaymentReleasedEventResponse> responses = new ArrayList<PaymentReleasedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PaymentReleasedEventResponse typedResponse = new PaymentReleasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PaymentReleasedEventResponse getPaymentReleasedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PAYMENTRELEASED_EVENT, log);
        PaymentReleasedEventResponse typedResponse = new PaymentReleasedEventResponse();
        typedResponse.log = log;
        typedResponse.to = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<PaymentReleasedEventResponse> paymentReleasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPaymentReleasedEventFromLog(log));
    }

    public Flowable<PaymentReleasedEventResponse> paymentReleasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAYMENTRELEASED_EVENT));
        return paymentReleasedEventFlowable(filter);
    }

    public RemoteFunctionCall<String> payee(BigInteger index) {
        final Function function = new Function(FUNC_PAYEE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> releasable(String account) {
        final Function function = new Function(FUNC_releasable, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> releasable(String token, String account) {
        final Function function = new Function(FUNC_releasable, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> release(String account) {
        final Function function = new Function(
                FUNC_release, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> release(String token, String account) {
        final Function function = new Function(
                FUNC_release, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> released(String token, String account) {
        final Function function = new Function(FUNC_released, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> released(String account) {
        final Function function = new Function(FUNC_released, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> shares(String account) {
        final Function function = new Function(FUNC_SHARES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> totalReleased(String token) {
        final Function function = new Function(FUNC_totalReleased, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> totalReleased() {
        final Function function = new Function(FUNC_totalReleased, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> totalShares() {
        final Function function = new Function(FUNC_TOTALSHARES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static PaymentSplitter load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PaymentSplitter(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PaymentSplitter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PaymentSplitter(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PaymentSplitter load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PaymentSplitter(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PaymentSplitter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PaymentSplitter(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PaymentSplitter> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialWeiValue, List<String> payees, List<BigInteger> shares_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(payees, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(shares_, org.web3j.abi.datatypes.generated.Uint256.class))));
        return deployRemoteCall(PaymentSplitter.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<PaymentSplitter> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialWeiValue, List<String> payees, List<BigInteger> shares_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(payees, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(shares_, org.web3j.abi.datatypes.generated.Uint256.class))));
        return deployRemoteCall(PaymentSplitter.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<PaymentSplitter> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, List<String> payees, List<BigInteger> shares_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(payees, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(shares_, org.web3j.abi.datatypes.generated.Uint256.class))));
        return deployRemoteCall(PaymentSplitter.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<PaymentSplitter> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, List<String> payees, List<BigInteger> shares_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(payees, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(shares_, org.web3j.abi.datatypes.generated.Uint256.class))));
        return deployRemoteCall(PaymentSplitter.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor, initialWeiValue);
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

    public static class ERC20PaymentReleasedEventResponse extends BaseEventResponse {
        public String token;

        public String to;

        public BigInteger amount;
    }

    public static class PayeeAddedEventResponse extends BaseEventResponse {
        public String account;

        public BigInteger shares;
    }

    public static class PaymentReceivedEventResponse extends BaseEventResponse {
        public String from;

        public BigInteger amount;
    }

    public static class PaymentReleasedEventResponse extends BaseEventResponse {
        public String to;

        public BigInteger amount;
    }
}
