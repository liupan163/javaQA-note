
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


//之前：       inputScript = txIn.getScriptSig(); //incomplete inputScript with OP_0 in place of one or more signatures

byte[] script = redeemData.redeemScript.getProgram();

signature = tx.calculateSignature(i, key, script, Transaction.SigHash.ALL, false);
// i 第几笔交易下标 Which input to calculate the signature for, as an index.
// key = redeemData.getFullKey()。                  The private key used to calculate the signature.
// script = redeemData.redeemScript.getProgram()
// hashType === a part of a scriptSig signature on the inputs
// anyoneCanPay

//签名脚本:    inputScript = scriptPubKey.getScriptSigWithSignature(inputScript, signature.encodeToBitcoin(), sigIndex);
//getScriptSigWithSignature
//加入签名脚本：txIn.setScriptSig(inputScript);

/*
OP_RETURN is a script opcode used to mark a transaction output as invalid.
OP_RETURN outputs can be used to burn bitcoins
脚本操作符，无效交易标记--目的是为了在比特币中存储信息.
方法是在交易输出脚本中加入OP_RETURN标志&&OP_RETURN一笔交易里面只能有一个
*/




//--------------------------PeerGroup-------------------------------
/*
*<ol>
*   接受钱包交易广播             <li>So the wallet receives broadcast transactions.</li>
*   pending状态的交易通知出去    <li>Announcing pending transactions that didn't get into the chain yet to our peers.</li>
*   设置fast catchup起始时间    <li>Set the fast catchup time using {@link PeerGroup#setFastCatchupTimeSecs(long)}, to optimize chain download.</li>
*</ol>
*/
/*
接受tx交易广播，对应
        peerGroup中, wallet.setTransactionBroadcaster(this)
        ......
        tx.broadcast = broadcaster.broadcastTransaction(tx)
*/


//isMine（）方法，作用:返回这个输出是个key，还是个钱包里面的地址

//txFee计算：  //false --- 发送者付，  true --- 接受者付，多个接受者时候，均摊splite
//public boolean recipientsPayFees = false;
/**
 * If false (default value), tx fee is paid by the sender If true, tx fee is paid by the recipient/s. If there is
 * more than one recipient, the tx fee is split equally between them regardless of output value and size.
 */
