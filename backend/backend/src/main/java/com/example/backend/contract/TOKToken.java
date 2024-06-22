package com.example.backend.contract;

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
 * <p>Generated with web3j version 1.6.0.
 */
@SuppressWarnings("rawtypes")
public class TOKToken extends Contract {
    public static final String BINARY = "0x60806040523480156200001157600080fd5b506040518060400160405280600881526020017f544f4b546f6b656e0000000000000000000000000000000000000000000000008152506040518060400160405280600381526020017f544f4b000000000000000000000000000000000000000000000000000000000081525081600390816200008f9190620004f8565b508060049081620000a19190620004f8565b505050620000c03369021e19e0c9bab24000006200010760201b60201c565b33600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550620006fa565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff160362000179576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401620001709062000640565b60405180910390fd5b6200018d600083836200027460201b60201c565b8060026000828254620001a1919062000691565b92505081905550806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055508173ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef83604051620002549190620006dd565b60405180910390a362000270600083836200027960201b60201c565b5050565b505050565b505050565b600081519050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b600060028204905060018216806200030057607f821691505b602082108103620003165762000315620002b8565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b600060088302620003807fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8262000341565b6200038c868362000341565b95508019841693508086168417925050509392505050565b6000819050919050565b6000819050919050565b6000620003d9620003d3620003cd84620003a4565b620003ae565b620003a4565b9050919050565b6000819050919050565b620003f583620003b8565b6200040d6200040482620003e0565b8484546200034e565b825550505050565b600090565b6200042462000415565b62000431818484620003ea565b505050565b5b8181101562000459576200044d6000826200041a565b60018101905062000437565b5050565b601f821115620004a85762000472816200031c565b6200047d8462000331565b810160208510156200048d578190505b620004a56200049c8562000331565b83018262000436565b50505b505050565b600082821c905092915050565b6000620004cd60001984600802620004ad565b1980831691505092915050565b6000620004e88383620004ba565b9150826002028217905092915050565b62000503826200027e565b67ffffffffffffffff8111156200051f576200051e62000289565b5b6200052b8254620002e7565b620005388282856200045d565b600060209050601f8311600181146200057057600084156200055b578287015190505b620005678582620004da565b865550620005d7565b601f19841662000580866200031c565b60005b82811015620005aa5784890151825560018201915060208501945060208101905062000583565b86831015620005ca5784890151620005c6601f891682620004ba565b8355505b6001600288020188555050505b505050505050565b600082825260208201905092915050565b7f45524332303a206d696e7420746f20746865207a65726f206164647265737300600082015250565b600062000628601f83620005df565b91506200063582620005f0565b602082019050919050565b600060208201905081810360008301526200065b8162000619565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60006200069e82620003a4565b9150620006ab83620003a4565b9250828201905080821115620006c657620006c562000662565b5b92915050565b620006d781620003a4565b82525050565b6000602082019050620006f46000830184620006cc565b92915050565b611677806200070a6000396000f3fe608060405234801561001057600080fd5b50600436106100ea5760003560e01c8063395093511161008c578063a457c2d711610066578063a457c2d714610263578063a9059cbb14610293578063dd62ed3e146102c3578063eef9c27c146102f3576100ea565b806339509351146101e557806370a082311461021557806395d89b4114610245576100ea565b80630ee2cb10116100c85780630ee2cb101461015b57806318160ddd1461017957806323b872dd14610197578063313ce567146101c7576100ea565b806302d05d3f146100ef57806306fdde031461010d578063095ea7b31461012b575b600080fd5b6100f761030f565b6040516101049190610d1e565b60405180910390f35b610115610335565b6040516101229190610dc9565b60405180910390f35b61014560048036038101906101409190610e52565b6103c7565b6040516101529190610ead565b60405180910390f35b6101636103ea565b6040516101709190610d1e565b60405180910390f35b610181610414565b60405161018e9190610ed7565b60405180910390f35b6101b160048036038101906101ac9190610ef2565b61041e565b6040516101be9190610ead565b60405180910390f35b6101cf61044d565b6040516101dc9190610f61565b60405180910390f35b6101ff60048036038101906101fa9190610e52565b610456565b60405161020c9190610ead565b60405180910390f35b61022f600480360381019061022a9190610f7c565b61048d565b60405161023c9190610ed7565b60405180910390f35b61024d6104d5565b60405161025a9190610dc9565b60405180910390f35b61027d60048036038101906102789190610e52565b610567565b60405161028a9190610ead565b60405180910390f35b6102ad60048036038101906102a89190610e52565b6105de565b6040516102ba9190610ead565b60405180910390f35b6102dd60048036038101906102d89190610fa9565b610601565b6040516102ea9190610ed7565b60405180910390f35b61030d60048036038101906103089190610fe9565b610688565b005b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60606003805461034490611045565b80601f016020809104026020016040519081016040528092919081815260200182805461037090611045565b80156103bd5780601f10610392576101008083540402835291602001916103bd565b820191906000526020600020905b8154815290600101906020018083116103a057829003601f168201915b5050505050905090565b6000806103d2610800565b90506103df818585610808565b600191505092915050565b6000600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000600254905090565b600080610429610800565b90506104368582856109d1565b610441858585610a5d565b60019150509392505050565b60006012905090565b600080610461610800565b90506104828185856104738589610601565b61047d91906110a5565b610808565b600191505092915050565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6060600480546104e490611045565b80601f016020809104026020016040519081016040528092919081815260200182805461051090611045565b801561055d5780601f106105325761010080835404028352916020019161055d565b820191906000526020600020905b81548152906001019060200180831161054057829003601f168201915b5050505050905090565b600080610572610800565b905060006105808286610601565b9050838110156105c5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105bc9061114b565b60405180910390fd5b6105d28286868403610808565b60019250505092915050565b6000806105e9610800565b90506105f6818585610a5d565b600191505092915050565b6000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1603610718576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161070f906111dd565b60405180910390fd5b80610744600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1661048d565b1015610785576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161077c9061126f565b60405180910390fd5b68056bc75e2d6310000081106107d0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107c7906112db565b60405180910390fd5b6107fd600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff163383610a5d565b50565b600033905090565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1603610877576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161086e9061136d565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16036108e6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108dd906113ff565b60405180910390fd5b80600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925836040516109c49190610ed7565b60405180910390a3505050565b60006109dd8484610601565b90507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8114610a575781811015610a49576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a409061146b565b60405180910390fd5b610a568484848403610808565b5b50505050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1603610acc576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610ac3906114fd565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1603610b3b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b329061158f565b60405180910390fd5b610b46838383610cd3565b60008060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905081811015610bcc576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610bc390611621565b60405180910390fd5b8181036000808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550816000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055508273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef84604051610cba9190610ed7565b60405180910390a3610ccd848484610cd8565b50505050565b505050565b505050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610d0882610cdd565b9050919050565b610d1881610cfd565b82525050565b6000602082019050610d336000830184610d0f565b92915050565b600081519050919050565b600082825260208201905092915050565b60005b83811015610d73578082015181840152602081019050610d58565b60008484015250505050565b6000601f19601f8301169050919050565b6000610d9b82610d39565b610da58185610d44565b9350610db5818560208601610d55565b610dbe81610d7f565b840191505092915050565b60006020820190508181036000830152610de38184610d90565b905092915050565b600080fd5b610df981610cfd565b8114610e0457600080fd5b50565b600081359050610e1681610df0565b92915050565b6000819050919050565b610e2f81610e1c565b8114610e3a57600080fd5b50565b600081359050610e4c81610e26565b92915050565b60008060408385031215610e6957610e68610deb565b5b6000610e7785828601610e07565b9250506020610e8885828601610e3d565b9150509250929050565b60008115159050919050565b610ea781610e92565b82525050565b6000602082019050610ec26000830184610e9e565b92915050565b610ed181610e1c565b82525050565b6000602082019050610eec6000830184610ec8565b92915050565b600080600060608486031215610f0b57610f0a610deb565b5b6000610f1986828701610e07565b9350506020610f2a86828701610e07565b9250506040610f3b86828701610e3d565b9150509250925092565b600060ff82169050919050565b610f5b81610f45565b82525050565b6000602082019050610f766000830184610f52565b92915050565b600060208284031215610f9257610f91610deb565b5b6000610fa084828501610e07565b91505092915050565b60008060408385031215610fc057610fbf610deb565b5b6000610fce85828601610e07565b9250506020610fdf85828601610e07565b9150509250929050565b600060208284031215610fff57610ffe610deb565b5b600061100d84828501610e3d565b91505092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061105d57607f821691505b6020821081036110705761106f611016565b5b50919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60006110b082610e1c565b91506110bb83610e1c565b92508282019050808211156110d3576110d2611076565b5b92915050565b7f45524332303a2064656372656173656420616c6c6f77616e63652062656c6f7760008201527f207a65726f000000000000000000000000000000000000000000000000000000602082015250565b6000611135602583610d44565b9150611140826110d9565b604082019050919050565b6000602082019050818103600083015261116481611128565b9050919050565b7f466175636574206973206e6f7420617661696c61626c6520746f20637265617460008201527f6f72000000000000000000000000000000000000000000000000000000000000602082015250565b60006111c7602283610d44565b91506111d28261116b565b604082019050919050565b600060208201905081810360008301526111f6816111ba565b9050919050565b7f496e73756666696369656e742062616c616e636520666f72207265717565737460008201527f656420616d6f756e740000000000000000000000000000000000000000000000602082015250565b6000611259602983610d44565b9150611264826111fd565b604082019050919050565b600060208201905081810360008301526112888161124c565b9050919050565b7f43616e206f6e6c792072657175657374206c657373207468616e203130300000600082015250565b60006112c5601e83610d44565b91506112d08261128f565b602082019050919050565b600060208201905081810360008301526112f4816112b8565b9050919050565b7f45524332303a20617070726f76652066726f6d20746865207a65726f2061646460008201527f7265737300000000000000000000000000000000000000000000000000000000602082015250565b6000611357602483610d44565b9150611362826112fb565b604082019050919050565b600060208201905081810360008301526113868161134a565b9050919050565b7f45524332303a20617070726f766520746f20746865207a65726f20616464726560008201527f7373000000000000000000000000000000000000000000000000000000000000602082015250565b60006113e9602283610d44565b91506113f48261138d565b604082019050919050565b60006020820190508181036000830152611418816113dc565b9050919050565b7f45524332303a20696e73756666696369656e7420616c6c6f77616e6365000000600082015250565b6000611455601d83610d44565b91506114608261141f565b602082019050919050565b6000602082019050818103600083015261148481611448565b9050919050565b7f45524332303a207472616e736665722066726f6d20746865207a65726f20616460008201527f6472657373000000000000000000000000000000000000000000000000000000602082015250565b60006114e7602583610d44565b91506114f28261148b565b604082019050919050565b60006020820190508181036000830152611516816114da565b9050919050565b7f45524332303a207472616e7366657220746f20746865207a65726f206164647260008201527f6573730000000000000000000000000000000000000000000000000000000000602082015250565b6000611579602383610d44565b91506115848261151d565b604082019050919050565b600060208201905081810360008301526115a88161156c565b9050919050565b7f45524332303a207472616e7366657220616d6f756e742065786365656473206260008201527f616c616e63650000000000000000000000000000000000000000000000000000602082015250565b600061160b602683610d44565b9150611616826115af565b604082019050919050565b6000602082019050818103600083015261163a816115fe565b905091905056fea264697066735822122018681626b1e36176dc8ba6d0e72851130dd062bc80c858bdf2a9b7f540ae6fb864736f6c63430008140033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_CREATOR = "creator";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_GETCREATOR = "getCreator";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_REQUESTTOKENS = "requestTokens";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected TOKToken(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TOKToken(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TOKToken(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TOKToken(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ApprovalEventResponse> getApprovalEvents(
            TransactionReceipt transactionReceipt) {
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

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public static List<TransferEventResponse> getTransferEvents(
            TransactionReceipt transactionReceipt) {
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

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> allowance(String owner, String spender) {
        final Function function = new Function(
                FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger amount) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> balanceOf(String account) {
        final Function function = new Function(
                FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> creator() {
        final Function function = new Function(
                FUNC_CREATOR, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> decimals() {
        final Function function = new Function(
                FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender,
            BigInteger subtractedValue) {
        final Function function = new Function(
                FUNC_DECREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(subtractedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getCreator() {
        final Function function = new Function(
                FUNC_GETCREATOR, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender,
            BigInteger addedValue) {
        final Function function = new Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(addedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> name() {
        final Function function = new Function(
                FUNC_NAME, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> requestTokens(BigInteger amount) {
        final Function function = new Function(
                FUNC_REQUESTTOKENS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> symbol() {
        final Function function = new Function(
                FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> totalSupply() {
        final Function function = new Function(
                FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to,
            BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static TOKToken load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new TOKToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TOKToken load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TOKToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TOKToken load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new TOKToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TOKToken load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TOKToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TOKToken> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TOKToken.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<TOKToken> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TOKToken.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TOKToken> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TOKToken.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TOKToken> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TOKToken.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
