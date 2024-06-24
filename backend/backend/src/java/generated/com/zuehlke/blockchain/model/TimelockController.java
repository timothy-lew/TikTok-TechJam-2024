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
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
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
public class TimelockController extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b5060405161220338038061220383398101604081905261002e9161039f565b6100455f80516020612183833981519152806101d9565b6100695f805160206121a38339815191525f805160206121838339815191526101d9565b61008d5f805160206121c38339815191525f805160206121838339815191526101d9565b6100b15f805160206121e38339815191525f805160206121838339815191526101d9565b6100c85f8051602061218383398151915230610223565b6001600160a01b038116156100ee576100ee5f8051602061218383398151915282610223565b5f5b835181101561015a5761012f5f805160206121a383398151915285838151811061011c5761011c61041e565b602002602001015161022360201b60201c565b6101525f805160206121e383398151915285838151811061011c5761011c61041e565b6001016100f0565b505f5b8251811015610191576101895f805160206121c383398151915284838151811061011c5761011c61041e565b60010161015d565b506002849055604080515f8152602081018690527f11c24f4ead16507c69ac467fbd5e4eed5fb5c699626d2cc6d66421df253886d5910160405180910390a150505050610432565b5f82815260208190526040808220600101805490849055905190918391839186917fbd79b86ffe0ab8e8776151514217cd7cacd52c909f66475c3af44e129f0b00ff9190a4505050565b61022d8282610231565b5050565b5f828152602081815260408083206001600160a01b038516845290915290205460ff1661022d575f828152602081815260408083206001600160a01b03851684529091529020805460ff191660011790556102893390565b6001600160a01b0316816001600160a01b0316837f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a45050565b634e487b7160e01b5f52604160045260245ffd5b80516001600160a01b03811681146102f7575f80fd5b919050565b5f82601f83011261030b575f80fd5b81516001600160401b03811115610324576103246102cd565b604051600582901b90603f8201601f191681016001600160401b0381118282101715610352576103526102cd565b60405291825260208185018101929081018684111561036f575f80fd5b6020860192505b8383101561039557610387836102e1565b815260209283019201610376565b5095945050505050565b5f805f80608085870312156103b2575f80fd5b845160208601519094506001600160401b038111156103cf575f80fd5b6103db878288016102fc565b604087015190945090506001600160401b038111156103f8575f80fd5b610404878288016102fc565b925050610413606086016102e1565b905092959194509250565b634e487b7160e01b5f52603260045260245ffd5b611d448061043f5f395ff3fe6080604052600436106101b2575f3560e01c80638065657f116100e7578063bc197c8111610087578063d547741f11610062578063d547741f1461055d578063e38335e51461057c578063f23a6e611461058f578063f27a0c92146105ba575f80fd5b8063bc197c81146104e8578063c4d252f514610513578063d45c443514610532575f80fd5b806391d14854116100c257806391d1485414610464578063a217fddf14610483578063b08e51c014610496578063b1c5f427146104c9575f80fd5b80638065657f146103f35780638f2a0bb0146104125780638f61f4f514610431575f80fd5b8063248a9ca31161015257806331d507501161012d57806331d507501461037757806336568abe14610396578063584b153e146103b557806364d62353146103d4575f80fd5b8063248a9ca3146102fb5780632ab0f529146103295780632f2ff15d14610358575f80fd5b80630d3cf6fc1161018d5780630d3cf6fc14610253578063134008d31461028657806313bc9f2014610299578063150b7a02146102b8575f80fd5b806301d5062a146101bd57806301ffc9a7146101de57806307bd026514610212575f80fd5b366101b957005b5f80fd5b3480156101c8575f80fd5b506101dc6101d73660046113e0565b6105ce565b005b3480156101e9575f80fd5b506101fd6101f836600461144e565b6106a2565b60405190151581526020015b60405180910390f35b34801561021d575f80fd5b506102457fd8aa0f3194971a2a116679f7c2090f6939c8d4e01a2a8d7e41d55e5351469e6381565b604051908152602001610209565b34801561025e575f80fd5b506102457f5f58e3a2316349923ce3780f8d587db2d72378aed66a8261c916544fa6846ca581565b6101dc610294366004611475565b6106cc565b3480156102a4575f80fd5b506101fd6102b33660046114db565b61077e565b3480156102c3575f80fd5b506102e26102d23660046115a1565b630a85bd0160e11b949350505050565b6040516001600160e01b03199091168152602001610209565b348015610306575f80fd5b506102456103153660046114db565b5f9081526020819052604090206001015490565b348015610334575f80fd5b506101fd6103433660046114db565b5f908152600160208190526040909120541490565b348015610363575f80fd5b506101dc610372366004611604565b6107a3565b348015610382575f80fd5b506101fd6103913660046114db565b6107cc565b3480156103a1575f80fd5b506101dc6103b0366004611604565b6107e4565b3480156103c0575f80fd5b506101fd6103cf3660046114db565b610867565b3480156103df575f80fd5b506101dc6103ee3660046114db565b61087c565b3480156103fe575f80fd5b5061024561040d366004611475565b610920565b34801561041d575f80fd5b506101dc61042c36600461166e565b61095e565b34801561043c575f80fd5b506102457fb09aa5aeb3702cfd50b6b62bc4532604938f21248a27a1d5ca736082b6819cc181565b34801561046f575f80fd5b506101fd61047e366004611604565b610ae7565b34801561048e575f80fd5b506102455f81565b3480156104a1575f80fd5b506102457ffd643c72710c63c0180259aba6b2d05451e3591a24e58b62239378085726f78381565b3480156104d4575f80fd5b506102456104e3366004611720565b610b0f565b3480156104f3575f80fd5b506102e2610502366004611849565b63bc197c8160e01b95945050505050565b34801561051e575f80fd5b506101dc61052d3660046114db565b610b53565b34801561053d575f80fd5b5061024561054c3660046114db565b5f9081526001602052604090205490565b348015610568575f80fd5b506101dc610577366004611604565b610c27565b6101dc61058a366004611720565b610c4b565b34801561059a575f80fd5b506102e26105a93660046118f5565b63f23a6e6160e01b95945050505050565b3480156105c5575f80fd5b50600254610245565b7fb09aa5aeb3702cfd50b6b62bc4532604938f21248a27a1d5ca736082b6819cc16105f881610dc9565b5f610607898989898989610920565b90506106138184610dd6565b5f817f4cf4410cc57040e44862ef0f45f3dd5a5e02db8eb8add648d4b0e236f1d07dca8b8b8b8b8b8a60405161064e96959493929190611970565b60405180910390a3831561069757807f20fda5fd27a1ea7bf5b9567f143ac5470bb059374a27e8f67cb44f946f6d03878560405161068e91815260200190565b60405180910390a25b505050505050505050565b5f6001600160e01b03198216630271189760e51b14806106c657506106c682610ec4565b92915050565b7fd8aa0f3194971a2a116679f7c2090f6939c8d4e01a2a8d7e41d55e5351469e636106f7815f610ae7565b610705576107058133610ef8565b5f610714888888888888610920565b90506107208185610f51565b61072c88888888610fec565b5f817fc2617efa69bab66782fa219543714338489c4e9e178271560a91b82c3f612b588a8a8a8a60405161076394939291906119ac565b60405180910390a3610774816110bb565b5050505050505050565b5f8181526001602052604081205460018111801561079c5750428111155b9392505050565b5f828152602081905260409020600101546107bd81610dc9565b6107c783836110f3565b505050565b5f8181526001602052604081205481905b1192915050565b6001600160a01b03811633146108595760405162461bcd60e51b815260206004820152602f60248201527f416363657373436f6e74726f6c3a2063616e206f6e6c792072656e6f756e636560448201526e103937b632b9903337b91039b2b63360891b60648201526084015b60405180910390fd5b6108638282611176565b5050565b5f8181526001602081905260408220546107dd565b3330146108df5760405162461bcd60e51b815260206004820152602b60248201527f54696d656c6f636b436f6e74726f6c6c65723a2063616c6c6572206d7573742060448201526a62652074696d656c6f636b60a81b6064820152608401610850565b60025460408051918252602082018390527f11c24f4ead16507c69ac467fbd5e4eed5fb5c699626d2cc6d66421df253886d5910160405180910390a1600255565b5f86868686868660405160200161093c96959493929190611970565b6040516020818303038152906040528051906020012090509695505050505050565b7fb09aa5aeb3702cfd50b6b62bc4532604938f21248a27a1d5ca736082b6819cc161098881610dc9565b8887146109a75760405162461bcd60e51b8152600401610850906119d3565b8885146109c65760405162461bcd60e51b8152600401610850906119d3565b5f6109d78b8b8b8b8b8b8b8b610b0f565b90506109e38184610dd6565b5f5b8a811015610a985780827f4cf4410cc57040e44862ef0f45f3dd5a5e02db8eb8add648d4b0e236f1d07dca8e8e85818110610a2257610a22611a16565b9050602002016020810190610a379190611a2a565b8d8d86818110610a4957610a49611a16565b905060200201358c8c87818110610a6257610a62611a16565b9050602002810190610a749190611a43565b8c8b604051610a8896959493929190611970565b60405180910390a36001016109e5565b508315610ada57807f20fda5fd27a1ea7bf5b9567f143ac5470bb059374a27e8f67cb44f946f6d038785604051610ad191815260200190565b60405180910390a25b5050505050505050505050565b5f918252602082815260408084206001600160a01b0393909316845291905290205460ff1690565b5f8888888888888888604051602001610b2f989796959493929190611b19565b60405160208183030381529060405280519060200120905098975050505050505050565b7ffd643c72710c63c0180259aba6b2d05451e3591a24e58b62239378085726f783610b7d81610dc9565b610b8682610867565b610bec5760405162461bcd60e51b815260206004820152603160248201527f54696d656c6f636b436f6e74726f6c6c65723a206f7065726174696f6e2063616044820152701b9b9bdd0818994818d85b98d95b1b1959607a1b6064820152608401610850565b5f828152600160205260408082208290555183917fbaa1eb22f2a492ba1a5fea61b8df4d27c6c8b5f3971e63bb58fa14ff72eedb7091a25050565b5f82815260208190526040902060010154610c4181610dc9565b6107c78383611176565b7fd8aa0f3194971a2a116679f7c2090f6939c8d4e01a2a8d7e41d55e5351469e63610c76815f610ae7565b610c8457610c848133610ef8565b878614610ca35760405162461bcd60e51b8152600401610850906119d3565b878414610cc25760405162461bcd60e51b8152600401610850906119d3565b5f610cd38a8a8a8a8a8a8a8a610b0f565b9050610cdf8185610f51565b5f5b89811015610db3575f8b8b83818110610cfc57610cfc611a16565b9050602002016020810190610d119190611a2a565b90505f8a8a84818110610d2657610d26611a16565b905060200201359050365f8a8a86818110610d4357610d43611a16565b9050602002810190610d559190611a43565b91509150610d6584848484610fec565b84867fc2617efa69bab66782fa219543714338489c4e9e178271560a91b82c3f612b5886868686604051610d9c94939291906119ac565b60405180910390a350505050806001019050610ce1565b50610dbd816110bb565b50505050505050505050565b610dd38133610ef8565b50565b610ddf826107cc565b15610e445760405162461bcd60e51b815260206004820152602f60248201527f54696d656c6f636b436f6e74726f6c6c65723a206f7065726174696f6e20616c60448201526e1c9958591e481cd8da19591d5b1959608a1b6064820152608401610850565b600254811015610ea55760405162461bcd60e51b815260206004820152602660248201527f54696d656c6f636b436f6e74726f6c6c65723a20696e73756666696369656e746044820152652064656c617960d01b6064820152608401610850565b610eaf8142611bcc565b5f928352600160205260409092209190915550565b5f6001600160e01b03198216637965db0b60e01b14806106c657506301ffc9a760e01b6001600160e01b03198316146106c6565b610f028282610ae7565b61086357610f0f816111da565b610f1a8360206111ec565b604051602001610f2b929190611bf6565b60408051601f198184030181529082905262461bcd60e51b825261085091600401611c54565b610f5a8261077e565b610f765760405162461bcd60e51b815260040161085090611c89565b801580610f9157505f81815260016020819052604090912054145b6108635760405162461bcd60e51b815260206004820152602660248201527f54696d656c6f636b436f6e74726f6c6c65723a206d697373696e6720646570656044820152656e64656e637960d01b6064820152608401610850565b5f846001600160a01b0316848484604051611008929190611cd3565b5f6040518083038185875af1925050503d805f8114611042576040519150601f19603f3d011682016040523d82523d5f602084013e611047565b606091505b50509050806110b45760405162461bcd60e51b815260206004820152603360248201527f54696d656c6f636b436f6e74726f6c6c65723a20756e6465726c79696e6720746044820152721c985b9cd858dd1a5bdb881c995d995c9d1959606a1b6064820152608401610850565b5050505050565b6110c48161077e565b6110e05760405162461bcd60e51b815260040161085090611c89565b5f90815260016020819052604090912055565b6110fd8282610ae7565b610863575f828152602081815260408083206001600160a01b03851684529091529020805460ff191660011790556111323390565b6001600160a01b0316816001600160a01b0316837f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a45050565b6111808282610ae7565b15610863575f828152602081815260408083206001600160a01b0385168085529252808320805460ff1916905551339285917ff6391f5c32d9c69d2a47ea670b442974b53935d1edc7fd64eb21e047a839171b9190a45050565b60606106c66001600160a01b03831660145b60605f6111fa836002611ce2565b611205906002611bcc565b6001600160401b0381111561121c5761121c6114f2565b6040519080825280601f01601f191660200182016040528015611246576020820181803683370190505b509050600360fc1b815f8151811061126057611260611a16565b60200101906001600160f81b03191690815f1a905350600f60fb1b8160018151811061128e5761128e611a16565b60200101906001600160f81b03191690815f1a9053505f6112b0846002611ce2565b6112bb906001611bcc565b90505b6001811115611332576f181899199a1a9b1b9c1cb0b131b232b360811b85600f16601081106112ef576112ef611a16565b1a60f81b82828151811061130557611305611a16565b60200101906001600160f81b03191690815f1a90535060049490941c9361132b81611cf9565b90506112be565b50831561079c5760405162461bcd60e51b815260206004820181905260248201527f537472696e67733a20686578206c656e67746820696e73756666696369656e746044820152606401610850565b80356001600160a01b0381168114611397575f80fd5b919050565b5f8083601f8401126113ac575f80fd5b5081356001600160401b038111156113c2575f80fd5b6020830191508360208285010111156113d9575f80fd5b9250929050565b5f805f805f805f60c0888a0312156113f6575f80fd5b6113ff88611381565b96506020880135955060408801356001600160401b03811115611420575f80fd5b61142c8a828b0161139c565b989b979a50986060810135976080820135975060a09091013595509350505050565b5f6020828403121561145e575f80fd5b81356001600160e01b03198116811461079c575f80fd5b5f805f805f8060a0878903121561148a575f80fd5b61149387611381565b95506020870135945060408701356001600160401b038111156114b4575f80fd5b6114c089828a0161139c565b979a9699509760608101359660809091013595509350505050565b5f602082840312156114eb575f80fd5b5035919050565b634e487b7160e01b5f52604160045260245ffd5b604051601f8201601f191681016001600160401b038111828210171561152e5761152e6114f2565b604052919050565b5f82601f830112611545575f80fd5b81356001600160401b0381111561155e5761155e6114f2565b611571601f8201601f1916602001611506565b818152846020838601011115611585575f80fd5b816020850160208301375f918101602001919091529392505050565b5f805f80608085870312156115b4575f80fd5b6115bd85611381565b93506115cb60208601611381565b92506040850135915060608501356001600160401b038111156115ec575f80fd5b6115f887828801611536565b91505092959194509250565b5f8060408385031215611615575f80fd5b8235915061162560208401611381565b90509250929050565b5f8083601f84011261163e575f80fd5b5081356001600160401b03811115611654575f80fd5b6020830191508360208260051b85010111156113d9575f80fd5b5f805f805f805f805f60c08a8c031215611686575f80fd5b89356001600160401b0381111561169b575f80fd5b6116a78c828d0161162e565b909a5098505060208a01356001600160401b038111156116c5575f80fd5b6116d18c828d0161162e565b90985096505060408a01356001600160401b038111156116ef575f80fd5b6116fb8c828d0161162e565b9a9d999c50979a969997986060880135976080810135975060a0013595509350505050565b5f805f805f805f8060a0898b031215611737575f80fd5b88356001600160401b0381111561174c575f80fd5b6117588b828c0161162e565b90995097505060208901356001600160401b03811115611776575f80fd5b6117828b828c0161162e565b90975095505060408901356001600160401b038111156117a0575f80fd5b6117ac8b828c0161162e565b999c989b509699959896976060870135966080013595509350505050565b5f82601f8301126117d9575f80fd5b81356001600160401b038111156117f2576117f26114f2565b8060051b61180260208201611506565b9182526020818501810192908101908684111561181d575f80fd5b6020860192505b8383101561183f578235825260209283019290910190611824565b9695505050505050565b5f805f805f60a0868803121561185d575f80fd5b61186686611381565b945061187460208701611381565b935060408601356001600160401b0381111561188e575f80fd5b61189a888289016117ca565b93505060608601356001600160401b038111156118b5575f80fd5b6118c1888289016117ca565b92505060808601356001600160401b038111156118dc575f80fd5b6118e888828901611536565b9150509295509295909350565b5f805f805f60a08688031215611909575f80fd5b61191286611381565b945061192060208701611381565b9350604086013592506060860135915060808601356001600160401b038111156118dc575f80fd5b81835281816020850137505f828201602090810191909152601f909101601f19169091010190565b60018060a01b038716815285602082015260a060408201525f61199760a083018688611948565b60608301949094525060800152949350505050565b60018060a01b0385168152836020820152606060408201525f61183f606083018486611948565b60208082526023908201527f54696d656c6f636b436f6e74726f6c6c65723a206c656e677468206d69736d616040820152620e8c6d60eb1b606082015260800190565b634e487b7160e01b5f52603260045260245ffd5b5f60208284031215611a3a575f80fd5b61079c82611381565b5f808335601e19843603018112611a58575f80fd5b8301803591506001600160401b03821115611a71575f80fd5b6020019150368190038213156113d9575f80fd5b5f8383855260208501945060208460051b820101835f5b86811015611b0d57838303601f19018852813536879003601e19018112611ac1575f80fd5b86016020810190356001600160401b03811115611adc575f80fd5b803603821315611aea575f80fd5b611af5858284611948565b60209a8b019a90955093909301925050600101611a9c565b50909695505050505050565b60a080825281018890525f8960c08301825b8b811015611b59576001600160a01b03611b4484611381565b16825260209283019290910190600101611b2b565b5083810360208501528881526001600160fb1b03891115611b78575f80fd5b8860051b9150818a60208301370182810360209081016040850152611ba09082018789611a85565b60608401959095525050608001529695505050505050565b634e487b7160e01b5f52601160045260245ffd5b808201808211156106c6576106c6611bb8565b5f81518060208401855e5f93019283525090919050565b7f416363657373436f6e74726f6c3a206163636f756e742000000000000000000081525f611c276017830185611bdf565b7001034b99036b4b9b9b4b733903937b6329607d1b8152611c4b6011820185611bdf565b95945050505050565b602081525f82518060208401528060208501604085015e5f604082850101526040601f19601f83011684010191505092915050565b6020808252602a908201527f54696d656c6f636b436f6e74726f6c6c65723a206f7065726174696f6e206973604082015269206e6f7420726561647960b01b606082015260800190565b818382375f9101908152919050565b80820281158282048414176106c6576106c6611bb8565b5f81611d0757611d07611bb8565b505f19019056fea2646970667358221220cfb9301845b4e9d394c3e004726903a2ee031ae36fa8bdc18354b8069e62887b64736f6c634300081a00335f58e3a2316349923ce3780f8d587db2d72378aed66a8261c916544fa6846ca5b09aa5aeb3702cfd50b6b62bc4532604938f21248a27a1d5ca736082b6819cc1d8aa0f3194971a2a116679f7c2090f6939c8d4e01a2a8d7e41d55e5351469e63fd643c72710c63c0180259aba6b2d05451e3591a24e58b62239378085726f783";

    private static String librariesLinkedBinary;

    public static final String FUNC_CANCELLER_ROLE = "CANCELLER_ROLE";

    public static final String FUNC_DEFAULT_ADMIN_ROLE = "DEFAULT_ADMIN_ROLE";

    public static final String FUNC_EXECUTOR_ROLE = "EXECUTOR_ROLE";

    public static final String FUNC_PROPOSER_ROLE = "PROPOSER_ROLE";

    public static final String FUNC_TIMELOCK_ADMIN_ROLE = "TIMELOCK_ADMIN_ROLE";

    public static final String FUNC_CANCEL = "cancel";

    public static final String FUNC_EXECUTE = "execute";

    public static final String FUNC_EXECUTEBATCH = "executeBatch";

    public static final String FUNC_GETMINDELAY = "getMinDelay";

    public static final String FUNC_GETROLEADMIN = "getRoleAdmin";

    public static final String FUNC_GETTIMESTAMP = "getTimestamp";

    public static final String FUNC_GRANTROLE = "grantRole";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_HASHOPERATION = "hashOperation";

    public static final String FUNC_HASHOPERATIONBATCH = "hashOperationBatch";

    public static final String FUNC_ISOPERATION = "isOperation";

    public static final String FUNC_ISOPERATIONDONE = "isOperationDone";

    public static final String FUNC_ISOPERATIONPENDING = "isOperationPending";

    public static final String FUNC_ISOPERATIONREADY = "isOperationReady";

    public static final String FUNC_ONERC1155BATCHRECEIVED = "onERC1155BatchReceived";

    public static final String FUNC_ONERC1155RECEIVED = "onERC1155Received";

    public static final String FUNC_ONERC721RECEIVED = "onERC721Received";

    public static final String FUNC_RENOUNCEROLE = "renounceRole";

    public static final String FUNC_REVOKEROLE = "revokeRole";

    public static final String FUNC_SCHEDULE = "schedule";

    public static final String FUNC_SCHEDULEBATCH = "scheduleBatch";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_UPDATEDELAY = "updateDelay";

    public static final Event CALLEXECUTED_EVENT = new Event("CallExecuted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event CALLSALT_EVENT = new Event("CallSalt", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event CALLSCHEDULED_EVENT = new Event("CallScheduled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CANCELLED_EVENT = new Event("Cancelled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}));
    ;

    public static final Event MINDELAYCHANGE_EVENT = new Event("MinDelayChange", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
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
    protected TimelockController(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TimelockController(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TimelockController(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TimelockController(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<CallExecutedEventResponse> getCallExecutedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CALLEXECUTED_EVENT, transactionReceipt);
        ArrayList<CallExecutedEventResponse> responses = new ArrayList<CallExecutedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CallExecutedEventResponse typedResponse = new CallExecutedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.index = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.target = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static CallExecutedEventResponse getCallExecutedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CALLEXECUTED_EVENT, log);
        CallExecutedEventResponse typedResponse = new CallExecutedEventResponse();
        typedResponse.log = log;
        typedResponse.id = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.index = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.target = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<CallExecutedEventResponse> callExecutedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getCallExecutedEventFromLog(log));
    }

    public Flowable<CallExecutedEventResponse> callExecutedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CALLEXECUTED_EVENT));
        return callExecutedEventFlowable(filter);
    }

    public static List<CallSaltEventResponse> getCallSaltEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CALLSALT_EVENT, transactionReceipt);
        ArrayList<CallSaltEventResponse> responses = new ArrayList<CallSaltEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CallSaltEventResponse typedResponse = new CallSaltEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.salt = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static CallSaltEventResponse getCallSaltEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CALLSALT_EVENT, log);
        CallSaltEventResponse typedResponse = new CallSaltEventResponse();
        typedResponse.log = log;
        typedResponse.id = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.salt = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<CallSaltEventResponse> callSaltEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getCallSaltEventFromLog(log));
    }

    public Flowable<CallSaltEventResponse> callSaltEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CALLSALT_EVENT));
        return callSaltEventFlowable(filter);
    }

    public static List<CallScheduledEventResponse> getCallScheduledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CALLSCHEDULED_EVENT, transactionReceipt);
        ArrayList<CallScheduledEventResponse> responses = new ArrayList<CallScheduledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CallScheduledEventResponse typedResponse = new CallScheduledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.index = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.target = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.predecessor = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.delay = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static CallScheduledEventResponse getCallScheduledEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CALLSCHEDULED_EVENT, log);
        CallScheduledEventResponse typedResponse = new CallScheduledEventResponse();
        typedResponse.log = log;
        typedResponse.id = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.index = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.target = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.predecessor = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.delay = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        return typedResponse;
    }

    public Flowable<CallScheduledEventResponse> callScheduledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getCallScheduledEventFromLog(log));
    }

    public Flowable<CallScheduledEventResponse> callScheduledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CALLSCHEDULED_EVENT));
        return callScheduledEventFlowable(filter);
    }

    public static List<CancelledEventResponse> getCancelledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CANCELLED_EVENT, transactionReceipt);
        ArrayList<CancelledEventResponse> responses = new ArrayList<CancelledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CancelledEventResponse typedResponse = new CancelledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static CancelledEventResponse getCancelledEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CANCELLED_EVENT, log);
        CancelledEventResponse typedResponse = new CancelledEventResponse();
        typedResponse.log = log;
        typedResponse.id = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<CancelledEventResponse> cancelledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getCancelledEventFromLog(log));
    }

    public Flowable<CancelledEventResponse> cancelledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CANCELLED_EVENT));
        return cancelledEventFlowable(filter);
    }

    public static List<MinDelayChangeEventResponse> getMinDelayChangeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MINDELAYCHANGE_EVENT, transactionReceipt);
        ArrayList<MinDelayChangeEventResponse> responses = new ArrayList<MinDelayChangeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MinDelayChangeEventResponse typedResponse = new MinDelayChangeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oldDuration = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newDuration = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MinDelayChangeEventResponse getMinDelayChangeEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MINDELAYCHANGE_EVENT, log);
        MinDelayChangeEventResponse typedResponse = new MinDelayChangeEventResponse();
        typedResponse.log = log;
        typedResponse.oldDuration = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.newDuration = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<MinDelayChangeEventResponse> minDelayChangeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMinDelayChangeEventFromLog(log));
    }

    public Flowable<MinDelayChangeEventResponse> minDelayChangeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MINDELAYCHANGE_EVENT));
        return minDelayChangeEventFlowable(filter);
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

    public RemoteFunctionCall<byte[]> CANCELLER_ROLE() {
        final Function function = new Function(FUNC_CANCELLER_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> DEFAULT_ADMIN_ROLE() {
        final Function function = new Function(FUNC_DEFAULT_ADMIN_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> EXECUTOR_ROLE() {
        final Function function = new Function(FUNC_EXECUTOR_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> PROPOSER_ROLE() {
        final Function function = new Function(FUNC_PROPOSER_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> TIMELOCK_ADMIN_ROLE() {
        final Function function = new Function(FUNC_TIMELOCK_ADMIN_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> cancel(byte[] id) {
        final Function function = new Function(
                FUNC_CANCEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> execute(String target, BigInteger value, byte[] payload, byte[] predecessor, byte[] salt, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_EXECUTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, target), 
                new org.web3j.abi.datatypes.generated.Uint256(value), 
                new org.web3j.abi.datatypes.DynamicBytes(payload), 
                new org.web3j.abi.datatypes.generated.Bytes32(predecessor), 
                new org.web3j.abi.datatypes.generated.Bytes32(salt)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> executeBatch(List<String> targets, List<BigInteger> values, List<byte[]> payloads, byte[] predecessor, byte[] salt, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_EXECUTEBATCH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(targets, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(values, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                        org.web3j.abi.datatypes.DynamicBytes.class,
                        org.web3j.abi.Utils.typeMap(payloads, org.web3j.abi.datatypes.DynamicBytes.class)), 
                new org.web3j.abi.datatypes.generated.Bytes32(predecessor), 
                new org.web3j.abi.datatypes.generated.Bytes32(salt)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> getMinDelay() {
        final Function function = new Function(FUNC_GETMINDELAY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<byte[]> getRoleAdmin(byte[] role) {
        final Function function = new Function(FUNC_GETROLEADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<BigInteger> getTimestamp(byte[] id) {
        final Function function = new Function(FUNC_GETTIMESTAMP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteFunctionCall<byte[]> hashOperation(String target, BigInteger value, byte[] data, byte[] predecessor, byte[] salt) {
        final Function function = new Function(FUNC_HASHOPERATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, target), 
                new org.web3j.abi.datatypes.generated.Uint256(value), 
                new org.web3j.abi.datatypes.DynamicBytes(data), 
                new org.web3j.abi.datatypes.generated.Bytes32(predecessor), 
                new org.web3j.abi.datatypes.generated.Bytes32(salt)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> hashOperationBatch(List<String> targets, List<BigInteger> values, List<byte[]> payloads, byte[] predecessor, byte[] salt) {
        final Function function = new Function(FUNC_HASHOPERATIONBATCH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(targets, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(values, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                        org.web3j.abi.datatypes.DynamicBytes.class,
                        org.web3j.abi.Utils.typeMap(payloads, org.web3j.abi.datatypes.DynamicBytes.class)), 
                new org.web3j.abi.datatypes.generated.Bytes32(predecessor), 
                new org.web3j.abi.datatypes.generated.Bytes32(salt)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<Boolean> isOperation(byte[] id) {
        final Function function = new Function(FUNC_ISOPERATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isOperationDone(byte[] id) {
        final Function function = new Function(FUNC_ISOPERATIONDONE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isOperationPending(byte[] id) {
        final Function function = new Function(FUNC_ISOPERATIONPENDING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isOperationReady(byte[] id) {
        final Function function = new Function(FUNC_ISOPERATIONREADY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
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

    public RemoteFunctionCall<TransactionReceipt> schedule(String target, BigInteger value, byte[] data, byte[] predecessor, byte[] salt, BigInteger delay) {
        final Function function = new Function(
                FUNC_SCHEDULE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, target), 
                new org.web3j.abi.datatypes.generated.Uint256(value), 
                new org.web3j.abi.datatypes.DynamicBytes(data), 
                new org.web3j.abi.datatypes.generated.Bytes32(predecessor), 
                new org.web3j.abi.datatypes.generated.Bytes32(salt), 
                new org.web3j.abi.datatypes.generated.Uint256(delay)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> scheduleBatch(List<String> targets, List<BigInteger> values, List<byte[]> payloads, byte[] predecessor, byte[] salt, BigInteger delay) {
        final Function function = new Function(
                FUNC_SCHEDULEBATCH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(targets, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(values, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                        org.web3j.abi.datatypes.DynamicBytes.class,
                        org.web3j.abi.Utils.typeMap(payloads, org.web3j.abi.datatypes.DynamicBytes.class)), 
                new org.web3j.abi.datatypes.generated.Bytes32(predecessor), 
                new org.web3j.abi.datatypes.generated.Bytes32(salt), 
                new org.web3j.abi.datatypes.generated.Uint256(delay)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final Function function = new Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> updateDelay(BigInteger newDelay) {
        final Function function = new Function(
                FUNC_UPDATEDELAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(newDelay)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static TimelockController load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TimelockController(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TimelockController load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TimelockController(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TimelockController load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TimelockController(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TimelockController load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TimelockController(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TimelockController> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger minDelay, List<String> proposers, List<String> executors, String admin) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(minDelay), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(proposers, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(executors, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.Address(160, admin)));
        return deployRemoteCall(TimelockController.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<TimelockController> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger minDelay, List<String> proposers, List<String> executors, String admin) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(minDelay), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(proposers, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(executors, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.Address(160, admin)));
        return deployRemoteCall(TimelockController.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TimelockController> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger minDelay, List<String> proposers, List<String> executors, String admin) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(minDelay), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(proposers, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(executors, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.Address(160, admin)));
        return deployRemoteCall(TimelockController.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TimelockController> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger minDelay, List<String> proposers, List<String> executors, String admin) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(minDelay), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(proposers, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(executors, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.Address(160, admin)));
        return deployRemoteCall(TimelockController.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class CallExecutedEventResponse extends BaseEventResponse {
        public byte[] id;

        public BigInteger index;

        public String target;

        public BigInteger value;

        public byte[] data;
    }

    public static class CallSaltEventResponse extends BaseEventResponse {
        public byte[] id;

        public byte[] salt;
    }

    public static class CallScheduledEventResponse extends BaseEventResponse {
        public byte[] id;

        public BigInteger index;

        public String target;

        public BigInteger value;

        public byte[] data;

        public byte[] predecessor;

        public BigInteger delay;
    }

    public static class CancelledEventResponse extends BaseEventResponse {
        public byte[] id;
    }

    public static class MinDelayChangeEventResponse extends BaseEventResponse {
        public BigInteger oldDuration;

        public BigInteger newDuration;
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
