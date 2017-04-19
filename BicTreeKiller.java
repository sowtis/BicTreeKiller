package scripts.BicTreeKiller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api.util.abc.ABCUtil;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.MessageListening07;
import org.tribot.script.interfaces.Painting;

import scripts.BicTreeKiller.Node;
import scripts.BicTreeKiller.Actions.Alch;
import scripts.BicTreeKiller.Actions.EatFood;
import scripts.BicTreeKiller.Actions.KillSpirit;
import scripts.BicTreeKiller.Actions.Loot;

@ScriptManifest(authors = { "Bic" }, category = "Combat", name = "Kills Tree Spirits in Enchanted Valley from a safespot. Picks up natures and alchs axes.", gameMode = 1)
public class BicTreeKiller extends Script implements Painting, MessageListening07 {

	public static ABCUtil abc = new ABCUtil();
	private final List<Node> nodes = new ArrayList<>();

	@Override
	public void run() {
		status = "Starting up";

		Mouse.setSpeed(General.random(88, 108));
		General.useAntiBanCompliance(true);

		Collections.addAll(nodes, new Alch(), new EatFood(), new KillSpirit(),
				new Loot());

		loop(20, 40);
	}

	private void loop(int min, int max) {
		while (true) {
			for (final Node node : nodes) {
				if (node.validate()) {
					node.execute();
					sleep(General.random(min, max)); // time in between
														// executing nodes
				}
			}
		}
	}

	public static String status = "";
	public static String lastServerMessage = "";
	public static int eatAt = abc.generateEatAtHP();

	@Override
	public void onPaint(Graphics arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clanMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duelRequestReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void personalMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serverMessageReceived(String arg0) {
		if (arg0.contains("You swing your axe"))
			lastServerMessage = "You swing";
		
	}

	@Override
	public void tradeRequestReceived(String arg0) {
		// TODO Auto-generated method stub
		
	}

}
