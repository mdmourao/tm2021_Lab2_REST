package tm2021.fcul.node.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import jakarta.inject.Singleton;
import tm2021.fcul.api.Node;
import tm2021.fcul.api.Retransmition;
import tm2021.fcul.api.service.RestNode;
import tm2021.fcul.node.NodeProjeto;

@Singleton
public class NodeResource implements RestNode {

   static public Map<String,Node> listNodes = new HashMap<>();

	private static Logger Log = Logger.getLogger(NodeResource.class.getName());
	
	public NodeResource() {
	}


	@Override
	public int updateAmount(String nodeId, int amount, Node node) {
		Log.info("updateAmount : nodeID: = " + nodeId + "; amount = " + amount + " ; node = " + node);
		Node n = listNodes.get(nodeId);
		if(n==null){
			n = new Node(nodeId, 0);
			listNodes.put(nodeId, n);
		}
		n.setAmount(n.getAmount() + amount);
		return amount;
	}

	@Override
	public int informNodes(String idTrans, Retransmition retran) {
		Log.info("oi");
		return 12;
	}

	public Node getNode(String id){
		return listNodes.get(id);
	}



	public int getAmount(String id){
		return listNodes.get(id).getAmount();
	}

	public void addNode(Node n){
		listNodes.put(n.getNodeId(),n);
	}
}
