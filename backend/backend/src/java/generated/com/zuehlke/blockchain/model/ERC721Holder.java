package com.zuehlke.blockchain.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
public class ERC721Holder extends Contract {
    public static final String BINARY = "6080604052348015600e575f80fd5b506101aa8061001c5f395ff3fe608060405234801561000f575f80fd5b5060043610610029575f3560e01c8063150b7a021461002d575b5f80fd5b61004b61003b366004610097565b630a85bd0160e11b949350505050565b6040516001600160e01b0319909116815260200160405180910390f35b80356001600160a01b038116811461007e575f80fd5b919050565b634e487b7160e01b5f52604160045260245ffd5b5f805f80608085870312156100aa575f80fd5b6100b385610068565b93506100c160208601610068565b925060408501359150606085013567ffffffffffffffff8111156100e3575f80fd5b8501601f810187136100f3575f80fd5b803567ffffffffffffffff81111561010d5761010d610083565b604051601f8201601f19908116603f0116810167ffffffffffffffff8111828210171561013c5761013c610083565b604052818152828201602001891015610153575f80fd5b816020840160208301375f602083830101528093505050509295919450925056fea2646970667358221220afe1ed438fbc76ae32a9b043c4762344d5b99e151d657d9950877c1a667bf32264736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ONERC721RECEIVED = "onERC721Received";

    @Deprecated
    protected ERC721Holder(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ERC721Holder(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ERC721Holder(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ERC721Holder(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
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

    @Deprecated
    public static ERC721Holder load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC721Holder(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ERC721Holder load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC721Holder(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ERC721Holder load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ERC721Holder(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ERC721Holder load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ERC721Holder(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ERC721Holder> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ERC721Holder.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ERC721Holder> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ERC721Holder.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<ERC721Holder> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ERC721Holder.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ERC721Holder> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ERC721Holder.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
