
/*
 RedeemData---锁定脚本
    public final Script redeemScript;
    public final List<ECKey> keys;
 ---签名数据时候用的类
 ---This class aggregates data required to spend transaction output
*/
//1、单签P2PK,P2PKH  ：a single key +++ CHECKSIG program as redeemScript
//2、多签MS,P2SH     ：multiple keys one of which will be a full key and the rest are watch only,
// redeem script will be a CHECKMULTISIG program. Keys will be sorted in the same order
// they appear in a program (lexicographical order)


//类ProposedTransaction.partialTx        TX
//类ProposedTransaction.keyPaths         HashMap<Script, List<ChildNumber>>

**         redeemData.getFullKey()  私钥
**pubKey = redeemData.keys.get(0)   公钥

输出公钥脚本scriptPubKey = txIn.getConnectedOutput().getScriptPubKey();

propTx.keyPaths.put(输出公钥脚本，公钥路径)


之前：       inputScript = txIn.getScriptSig(); //incomplete inputScript with OP_0 in place of one or more signatures

byte[] script = redeemData.redeemScript.getProgram();

signature = tx.calculateSignature(i, key, script, Transaction.SigHash.ALL, false);
// i 第几笔交易下标 Which input to calculate the signature for, as an index.
// key = redeemData.getFullKey()。                  The private key used to calculate the signature.
// script = redeemData.redeemScript.getProgram()
// hashType === a part of a scriptSig signature on the inputs
// anyoneCanPay

签名脚本:    inputScript = scriptPubKey.getScriptSigWithSignature(inputScript, signature.encodeToBitcoin(), sigIndex);
//getScriptSigWithSignature
加入签名脚本：txIn.setScriptSig(inputScript);

//OP_RETURN is a script opcode used to mark a transaction output as invalid.
//脚本操作符，  交易标记为无效    OP_RETURN outputs can be used to burn bitcoins


