package com.zuehlke.blockchain.model;

import java.math.BigInteger;
import java.util.List;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
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
public class Proxy extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    @Deprecated
    protected Proxy(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Proxy(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Proxy(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Proxy(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    @Deprecated
    public static Proxy load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Proxy(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Proxy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Proxy(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Proxy load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Proxy(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Proxy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Proxy(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Proxy> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Proxy.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Proxy> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Proxy.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<Proxy> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Proxy.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Proxy> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Proxy.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
