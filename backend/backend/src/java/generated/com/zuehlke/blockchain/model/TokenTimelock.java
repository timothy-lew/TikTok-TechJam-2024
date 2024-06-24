package com.zuehlke.blockchain.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
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
public class TokenTimelock extends Contract {
    public static final String BINARY = "60e060405234801561000f575f80fd5b5060405161077b38038061077b83398101604081905261002e916100ce565b42811161009c5760405162461bcd60e51b815260206004820152603260248201527f546f6b656e54696d656c6f636b3a2072656c656173652074696d65206973206260448201527165666f72652063757272656e742074696d6560701b606482015260840160405180910390fd5b6001600160a01b03928316608052911660a05260c05261010e565b6001600160a01b03811681146100cb575f80fd5b50565b5f805f606084860312156100e0575f80fd5b83516100eb816100b7565b60208501519093506100fc816100b7565b80925050604084015190509250925092565b60805160a05160c05161062b6101505f395f8181609c015260ed01525f81816050015261029101525f818160c70152818161017e015261026f015261062b5ff3fe608060405234801561000f575f80fd5b506004361061004a575f3560e01c806338af3eed1461004e57806386d1a69f1461008d578063b91d400114610097578063fc0c546a146100c5575b5f80fd5b7f00000000000000000000000000000000000000000000000000000000000000005b6040516001600160a01b0390911681526020015b60405180910390f35b6100956100eb565b005b6040517f00000000000000000000000000000000000000000000000000000000000000008152602001610084565b7f0000000000000000000000000000000000000000000000000000000000000000610070565b7f000000000000000000000000000000000000000000000000000000000000000042101561017b5760405162461bcd60e51b815260206004820152603260248201527f546f6b656e54696d656c6f636b3a2063757272656e742074696d65206973206260448201527165666f72652072656c656173652074696d6560701b60648201526084015b60405180910390fd5b5f7f00000000000000000000000000000000000000000000000000000000000000006040516370a0823160e01b81523060048201526001600160a01b0391909116906370a0823190602401602060405180830381865afa1580156101e1573d5f803e3d5ffd5b505050506040513d601f19601f82011682018060405250810190610205919061056d565b90505f81116102625760405162461bcd60e51b815260206004820152602360248201527f546f6b656e54696d656c6f636b3a206e6f20746f6b656e7320746f2072656c6560448201526261736560e81b6064820152608401610172565b6102b66001600160a01b037f0000000000000000000000000000000000000000000000000000000000000000167f0000000000000000000000000000000000000000000000000000000000000000836102b9565b50565b604080516001600160a01b038416602482015260448082018490528251808303909101815260649091019091526020810180516001600160e01b031663a9059cbb60e01b17905261030b908490610310565b505050565b5f610364826040518060400160405280602081526020017f5361666545524332303a206c6f772d6c6576656c2063616c6c206661696c6564815250856001600160a01b03166103e39092919063ffffffff16565b905080515f14806103845750808060200190518101906103849190610584565b61030b5760405162461bcd60e51b815260206004820152602a60248201527f5361666545524332303a204552433230206f7065726174696f6e20646964206e6044820152691bdd081cdd58d8d9595960b21b6064820152608401610172565b60606103f184845f856103f9565b949350505050565b60608247101561045a5760405162461bcd60e51b815260206004820152602660248201527f416464726573733a20696e73756666696369656e742062616c616e636520666f6044820152651c8818d85b1b60d21b6064820152608401610172565b5f80866001600160a01b0316858760405161047591906105aa565b5f6040518083038185875af1925050503d805f81146104af576040519150601f19603f3d011682016040523d82523d5f602084013e6104b4565b606091505b50915091506104c5878383876104d0565b979650505050505050565b6060831561053e5782515f03610537576001600160a01b0385163b6105375760405162461bcd60e51b815260206004820152601d60248201527f416464726573733a2063616c6c20746f206e6f6e2d636f6e74726163740000006044820152606401610172565b50816103f1565b6103f183838151156105535781518083602001fd5b8060405162461bcd60e51b815260040161017291906105c0565b5f6020828403121561057d575f80fd5b5051919050565b5f60208284031215610594575f80fd5b815180151581146105a3575f80fd5b9392505050565b5f82518060208501845e5f920191825250919050565b602081525f82518060208401528060208501604085015e5f604082850101526040601f19601f8301168401019150509291505056fea2646970667358221220e8f9b27c092c3d2c4aa683fd1996c621df504b73d4cf9f1f7959816902beb84f64736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_BENEFICIARY = "beneficiary";

    public static final String FUNC_RELEASE = "release";

    public static final String FUNC_RELEASETIME = "releaseTime";

    public static final String FUNC_TOKEN = "token";

    @Deprecated
    protected TokenTimelock(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TokenTimelock(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TokenTimelock(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TokenTimelock(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> beneficiary() {
        final Function function = new Function(FUNC_BENEFICIARY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> release() {
        final Function function = new Function(
                FUNC_RELEASE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> releaseTime() {
        final Function function = new Function(FUNC_RELEASETIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> token() {
        final Function function = new Function(FUNC_TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static TokenTimelock load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenTimelock(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TokenTimelock load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenTimelock(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TokenTimelock load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TokenTimelock(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TokenTimelock load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TokenTimelock(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TokenTimelock> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String token_, String beneficiary_, BigInteger releaseTime_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token_), 
                new org.web3j.abi.datatypes.Address(160, beneficiary_), 
                new org.web3j.abi.datatypes.generated.Uint256(releaseTime_)));
        return deployRemoteCall(TokenTimelock.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<TokenTimelock> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String token_, String beneficiary_, BigInteger releaseTime_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token_), 
                new org.web3j.abi.datatypes.Address(160, beneficiary_), 
                new org.web3j.abi.datatypes.generated.Uint256(releaseTime_)));
        return deployRemoteCall(TokenTimelock.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TokenTimelock> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String token_, String beneficiary_, BigInteger releaseTime_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token_), 
                new org.web3j.abi.datatypes.Address(160, beneficiary_), 
                new org.web3j.abi.datatypes.generated.Uint256(releaseTime_)));
        return deployRemoteCall(TokenTimelock.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TokenTimelock> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String token_, String beneficiary_, BigInteger releaseTime_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token_), 
                new org.web3j.abi.datatypes.Address(160, beneficiary_), 
                new org.web3j.abi.datatypes.generated.Uint256(releaseTime_)));
        return deployRemoteCall(TokenTimelock.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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
}
