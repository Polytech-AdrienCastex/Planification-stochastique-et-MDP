package agent.planningagent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import environnement.Action;
import environnement.Etat;
import environnement.MDP;
import environnement.gridworld.ActionGridworld;


/**
 * Cet agent met a jour sa fonction de valeur avec value iteration 
 * et choisit ses actions selon la politique calculee.
 * @author laetitiamatignon
 *
 */
public class ValueIterationAgent_OLD extends PlanningValueAgent{
	//*** VOTRE CODE
	

	
	/**
	 * 
	 * @param gamma
	 * @param mdp
	 */
	public ValueIterationAgent_OLD(double gamma,MDP mdp) {
		super(mdp);
		
		//*** VOTRE CODE
	
	
	}
	
	
	public ValueIterationAgent_OLD(MDP mdp) {
		this(0.9,mdp);

	}
	
	/**
	 * 
	 * Mise a jour de V: effectue UNE iteration de value iteration 
	 */
	@Override
	public void updateV(){
		this.delta=0.0;
		//*** VOTRE CODE
		
	
		
		
		// mise a jour vmax et vmin pour affichage
		// ...
		
		//******************* a laisser a la fin de la methode
		this.notifyObs();
	}
	
	
	/**
	 * renvoi l'action donnee par la politique 
	 */
	@Override
	public Action getAction(Etat e) {
		//*** VOTRE CODE
		
	
		return ActionGridworld.NONE;
	}
	@Override
	public double getValeur(Etat _e) {
		//*** VOTRE CODE
		
		return 0.0;
	}
	/**
	 * renvoi action(s) de plus forte(s) valeur(s) dans etat (plusieurs actions sont renvoyees si valeurs identiques, liste vide si aucune action n'est possible)
	 */
	@Override
	public List<Action> getPolitique(Etat _e) {
		List<Action> l = new ArrayList<Action>();
		//*** VOTRE CODE
		
		
		return l;
		
	}
	
	@Override
	public void reset() {
		super.reset();
		//*** VOTRE CODE
		
		
		
		
		
		/*-----------------*/
		this.notifyObs();

	}


	@Override
	public void setGamma(double arg0) {
		//*** VOTRE CODE
		
	}

	
}
