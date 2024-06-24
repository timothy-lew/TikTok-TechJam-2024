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
public class VestingWallet extends Contract {
    public static final String BINARY = "60e0604052604051610c50380380610c50833981016040819052610022916100cf565b6001600160a01b03831661008f5760405162461bcd60e51b815260206004820152602a60248201527f56657374696e6757616c6c65743a2062656e6566696369617279206973207a65604482015269726f206164647265737360b01b606482015260840160405180910390fd5b6001600160a01b039092166080526001600160401b0390811660a0521660c05261011c565b80516001600160401b03811681146100ca575f80fd5b919050565b5f805f606084860312156100e1575f80fd5b83516001600160a01b03811681146100f7575f80fd5b9250610105602085016100b4565b9150610113604085016100b4565b90509250925092565b60805160a05160c051610aef6101615f395f818160e8015281816104b0015261050501525f61044f01525f8181610150015281816102dc01526103f00152610aef5ff3fe60806040526004361061009d575f3560e01c806386d1a69f1161006257806386d1a69f1461019957806396132521146101ad5780639852595c146101c0578063a3f8eace146101f4578063be9a655514610213578063fbccedae14610227575f80fd5b80630a17b06b146100a85780630fb5a6b4146100da578063191655871461011657806338af3eed14610137578063810ec23b1461017a575f80fd5b366100a457005b5f80fd5b3480156100b3575f80fd5b506100c76100c236600461094f565b61023b565b6040519081526020015b60405180910390f35b3480156100e5575f80fd5b507f000000000000000000000000000000000000000000000000000000000000000067ffffffffffffffff166100c7565b348015610121575f80fd5b5061013561013036600461097e565b61025d565b005b348015610142575f80fd5b506040516001600160a01b037f00000000000000000000000000000000000000000000000000000000000000001681526020016100d1565b348015610185575f80fd5b506100c7610194366004610997565b610305565b3480156101a4575f80fd5b50610135610398565b3480156101b8575f80fd5b505f546100c7565b3480156101cb575f80fd5b506100c76101da36600461097e565b6001600160a01b03165f9081526001602052604090205490565b3480156101ff575f80fd5b506100c761020e36600461097e565b610418565b34801561021e575f80fd5b506100c7610444565b348015610232575f80fd5b506100c7610472565b5f6102576102475f5490565b61025190476109dc565b8361048d565b92915050565b5f61026782610418565b6001600160a01b0383165f908152600160205260408120805492935083929091906102939084906109dc565b90915550506040518181526001600160a01b038316907fc0e523490dd523c33b1878c9eb14ff46991e3f5b2cd33710918618f2a39cba1b9060200160405180910390a2610301827f000000000000000000000000000000000000000000000000000000000000000083610565565b5050565b6001600160a01b0382165f90815260016020526040812054610391906040516370a0823160e01b81523060048201526001600160a01b038616906370a0823190602401602060405180830381865afa158015610363573d5f803e3d5ffd5b505050506040513d601f19601f8201168201806040525081019061038791906109ef565b61025191906109dc565b9392505050565b5f6103a1610472565b9050805f808282546103b391906109dc565b90915550506040518181527fda9d4e5f101b8b9b1c5b76d0c5a9f7923571acfc02376aa076b75a8c080c956b9060200160405180910390a16104157f0000000000000000000000000000000000000000000000000000000000000000826105bc565b50565b6001600160a01b0381165f9081526001602052604081205461043a8342610305565b6102579190610a06565b67ffffffffffffffff7f00000000000000000000000000000000000000000000000000000000000000001690565b5f805461047e4261023b565b6104889190610a06565b905090565b5f610496610444565b8267ffffffffffffffff1610156104ae57505f610257565b7f000000000000000000000000000000000000000000000000000000000000000067ffffffffffffffff166104e1610444565b6104eb91906109dc565b8267ffffffffffffffff161115610503575081610257565b7f000000000000000000000000000000000000000000000000000000000000000067ffffffffffffffff16610536610444565b61054a9067ffffffffffffffff8516610a06565b6105549085610a19565b61055e9190610a30565b9050610257565b604080516001600160a01b038416602482015260448082018490528251808303909101815260649091019091526020810180516001600160e01b031663a9059cbb60e01b1790526105b79084906106d6565b505050565b804710156106115760405162461bcd60e51b815260206004820152601d60248201527f416464726573733a20696e73756666696369656e742062616c616e636500000060448201526064015b60405180910390fd5b5f826001600160a01b0316826040515f6040518083038185875af1925050503d805f811461065a576040519150601f19603f3d011682016040523d82523d5f602084013e61065f565b606091505b50509050806105b75760405162461bcd60e51b815260206004820152603a60248201527f416464726573733a20756e61626c6520746f2073656e642076616c75652c207260448201527f6563697069656e74206d617920686176652072657665727465640000000000006064820152608401610608565b5f61072a826040518060400160405280602081526020017f5361666545524332303a206c6f772d6c6576656c2063616c6c206661696c6564815250856001600160a01b03166107a99092919063ffffffff16565b905080515f148061074a57508080602001905181019061074a9190610a4f565b6105b75760405162461bcd60e51b815260206004820152602a60248201527f5361666545524332303a204552433230206f7065726174696f6e20646964206e6044820152691bdd081cdd58d8d9595960b21b6064820152608401610608565b60606107b784845f856107bf565b949350505050565b6060824710156108205760405162461bcd60e51b815260206004820152602660248201527f416464726573733a20696e73756666696369656e742062616c616e636520666f6044820152651c8818d85b1b60d21b6064820152608401610608565b5f80866001600160a01b0316858760405161083b9190610a6e565b5f6040518083038185875af1925050503d805f8114610875576040519150601f19603f3d011682016040523d82523d5f602084013e61087a565b606091505b509150915061088b87838387610896565b979650505050505050565b606083156109045782515f036108fd576001600160a01b0385163b6108fd5760405162461bcd60e51b815260206004820152601d60248201527f416464726573733a2063616c6c20746f206e6f6e2d636f6e74726163740000006044820152606401610608565b50816107b7565b6107b783838151156109195781518083602001fd5b8060405162461bcd60e51b81526004016106089190610a84565b803567ffffffffffffffff8116811461094a575f80fd5b919050565b5f6020828403121561095f575f80fd5b61039182610933565b80356001600160a01b038116811461094a575f80fd5b5f6020828403121561098e575f80fd5b61039182610968565b5f80604083850312156109a8575f80fd5b6109b183610968565b91506109bf60208401610933565b90509250929050565b634e487b7160e01b5f52601160045260245ffd5b80820180821115610257576102576109c8565b5f602082840312156109ff575f80fd5b5051919050565b81810381811115610257576102576109c8565b8082028115828204841417610257576102576109c8565b5f82610a4a57634e487b7160e01b5f52601260045260245ffd5b500490565b5f60208284031215610a5f575f80fd5b81518015158114610391575f80fd5b5f82518060208501845e5f920191825250919050565b602081525f82518060208401528060208501604085015e5f604082850101526040601f19601f8301168401019150509291505056fea264697066735822122033889f09fd373d12e6040d96be5cfdf0db1b73eab2ab177cbdba6684fbd76aac64736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_BENEFICIARY = "beneficiary";

    public static final String FUNC_DURATION = "duration";

    public static final String FUNC_releasable = "releasable";

    public static final String FUNC_release = "release";

    public static final String FUNC_released = "released";

    public static final String FUNC_START = "start";

    public static final String FUNC_vestedAmount = "vestedAmount";

    public static final Event ERC20RELEASED_EVENT = new Event("ERC20Released", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ETHERRELEASED_EVENT = new Event("EtherReleased", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected VestingWallet(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected VestingWallet(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected VestingWallet(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected VestingWallet(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ERC20ReleasedEventResponse> getERC20ReleasedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ERC20RELEASED_EVENT, transactionReceipt);
        ArrayList<ERC20ReleasedEventResponse> responses = new ArrayList<ERC20ReleasedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ERC20ReleasedEventResponse typedResponse = new ERC20ReleasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.token = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ERC20ReleasedEventResponse getERC20ReleasedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ERC20RELEASED_EVENT, log);
        ERC20ReleasedEventResponse typedResponse = new ERC20ReleasedEventResponse();
        typedResponse.log = log;
        typedResponse.token = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ERC20ReleasedEventResponse> eRC20ReleasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getERC20ReleasedEventFromLog(log));
    }

    public Flowable<ERC20ReleasedEventResponse> eRC20ReleasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ERC20RELEASED_EVENT));
        return eRC20ReleasedEventFlowable(filter);
    }

    public static List<EtherReleasedEventResponse> getEtherReleasedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ETHERRELEASED_EVENT, transactionReceipt);
        ArrayList<EtherReleasedEventResponse> responses = new ArrayList<EtherReleasedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EtherReleasedEventResponse typedResponse = new EtherReleasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static EtherReleasedEventResponse getEtherReleasedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ETHERRELEASED_EVENT, log);
        EtherReleasedEventResponse typedResponse = new EtherReleasedEventResponse();
        typedResponse.log = log;
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<EtherReleasedEventResponse> etherReleasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getEtherReleasedEventFromLog(log));
    }

    public Flowable<EtherReleasedEventResponse> etherReleasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ETHERRELEASED_EVENT));
        return etherReleasedEventFlowable(filter);
    }

    public RemoteFunctionCall<String> beneficiary() {
        final Function function = new Function(FUNC_BENEFICIARY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> duration() {
        final Function function = new Function(FUNC_DURATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> releasable(String token) {
        final Function function = new Function(FUNC_releasable, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> releasable() {
        final Function function = new Function(FUNC_releasable, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> release(String token) {
        final Function function = new Function(
                FUNC_release, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> release() {
        final Function function = new Function(
                FUNC_release, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> released() {
        final Function function = new Function(FUNC_released, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> released(String token) {
        final Function function = new Function(FUNC_released, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> start() {
        final Function function = new Function(FUNC_START, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> vestedAmount(BigInteger timestamp) {
        final Function function = new Function(FUNC_vestedAmount, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint64(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> vestedAmount(String token, BigInteger timestamp) {
        final Function function = new Function(FUNC_vestedAmount, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token), 
                new org.web3j.abi.datatypes.generated.Uint64(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static VestingWallet load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new VestingWallet(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static VestingWallet load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new VestingWallet(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static VestingWallet load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new VestingWallet(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static VestingWallet load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new VestingWallet(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<VestingWallet> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialWeiValue, String beneficiaryAddress, BigInteger startTimestamp, BigInteger durationSeconds) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, beneficiaryAddress), 
                new org.web3j.abi.datatypes.generated.Uint64(startTimestamp), 
                new org.web3j.abi.datatypes.generated.Uint64(durationSeconds)));
        return deployRemoteCall(VestingWallet.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<VestingWallet> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialWeiValue, String beneficiaryAddress, BigInteger startTimestamp, BigInteger durationSeconds) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, beneficiaryAddress), 
                new org.web3j.abi.datatypes.generated.Uint64(startTimestamp), 
                new org.web3j.abi.datatypes.generated.Uint64(durationSeconds)));
        return deployRemoteCall(VestingWallet.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<VestingWallet> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String beneficiaryAddress, BigInteger startTimestamp, BigInteger durationSeconds) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, beneficiaryAddress), 
                new org.web3j.abi.datatypes.generated.Uint64(startTimestamp), 
                new org.web3j.abi.datatypes.generated.Uint64(durationSeconds)));
        return deployRemoteCall(VestingWallet.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<VestingWallet> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String beneficiaryAddress, BigInteger startTimestamp, BigInteger durationSeconds) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, beneficiaryAddress), 
                new org.web3j.abi.datatypes.generated.Uint64(startTimestamp), 
                new org.web3j.abi.datatypes.generated.Uint64(durationSeconds)));
        return deployRemoteCall(VestingWallet.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor, initialWeiValue);
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

    public static class ERC20ReleasedEventResponse extends BaseEventResponse {
        public String token;

        public BigInteger amount;
    }

    public static class EtherReleasedEventResponse extends BaseEventResponse {
        public BigInteger amount;
    }
}
