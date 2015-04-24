package agent.planningagent;

import environnement.Action;
import environnement.Etat;
import environnement.IllegalActionException;
import environnement.MDP;
import environnement.gridworld.ActionGridworld;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.stream.Stream;
import util.HashMapUtil;


/**
 * Cet agent met a jour sa fonction de valeur avec value iteration 
 * et choisit ses actions selon la politique calculee.
 * @author laetitiamatignon
 *
 */
public class ValueIterationAgent extends PlanningValueAgent{
	//*** VOTRE CODE
	

	
	/**
	 * 
	 * @param gamma
	 * @param mdp
	 */
	public ValueIterationAgent(double gamma, MDP mdp)
        {
            super(mdp);
            
            setGamma(gamma);
            
            v = new HashMapUtil();
	}
	
	
	public ValueIterationAgent(MDP mdp)
        {
            this(0.9,mdp);
	}
	
        
        protected HashMapUtil v;
        
        
	/**
	 * 
	 * Mise a jour de V: effectue UNE iteration de value iteration 
	 */
	@Override
	public void updateV()
        {
            final HashMapUtil newV = new HashMapUtil();
            
            for(Etat e : mdp.getEtatsAccessibles())
            {
                newV.put(e,
                        Stream.of(getActions())
                        .mapToDouble(a -> getValeur(e, a))
                        .max()
                        .getAsDouble());
            }
            
            super.delta = newV.entrySet()
                    .stream()
                    .filter(e -> e.getValue() != null && e.getKey() != null)
                    .mapToDouble(e -> Math.abs(e.getValue() - v.get(e.getKey())))
                    .max()
                    .getAsDouble();
            
            v = newV;
            
            super.vmax = v.entrySet().stream().mapToDouble(e -> e.getValue()).max().getAsDouble();
            super.vmin = v.entrySet().stream().mapToDouble(e -> e.getValue()).min().getAsDouble();
            
            this.notifyObs();
	}
	
	
	/**
	 * renvoi l'action donnee par la politique 
	 */
	@Override
	public Action getAction(Etat e)
        {
            List<Action> actions = getPolitique(e);
            
            if(actions.isEmpty())
            {
                System.out.println("***********************************");
                System.out.println("EMPTY");
                System.out.println("***********************************");
                return ActionGridworld.NONE;
            }
            else
                return actions.get(0);
	}
	@Override
	public double getValeur(Etat _e)
        {
            return v.get(_e);
	}
	/**
	 * renvoi action(s) de plus forte(s) valeur(s) dans etat (plusieurs actions sont renvoyees si valeurs identiques, liste vide si aucune action n'est possible)
	 */
	@Override
	public List<Action> getPolitique(Etat _e)
        {
            List<Action> selectedActions = new ArrayList<>();
            double value = 0.0;
            
            for(ActionGridworld a : getActions())
            {
                double tempValue = getValeur(_e, a);

                if(value < tempValue)
                {
                    selectedActions.clear();
                    value = tempValue;
                }
                
                selectedActions.add(a);
            }

            return selectedActions;
	}
        protected double getValeur(Etat _e, Action a)
        {
            try
            {
                if(_e.)
                return mdp.getEtatTransitionProba(_e, a)
                        .entrySet()
                        .stream()
                        .mapToDouble(Ts -> getValeur(_e, Ts.getKey(), Ts.getValue(), a))
                        .sum();
            }
            catch (Exception ex)
            {
                if(!(ex instanceof IllegalActionException))
                    ex.printStackTrace();
                return 0.0;
            }
        }
        
        protected double getValeur(Etat from, Etat to, double t, Action action)
        {
            return t * (mdp.getRecompense(from, action, to) + gamma * getValeur(to));
        }
        protected double getValeur(Etat from, Etat to, double t, double r)
        {
            return t * (r + gamma * getValeur(to));
        }
	
	@Override
	public void reset()
        {
            super.reset();
            
            final HashMapUtil newV = new HashMapUtil();
            mdp.getEtatsAccessibles().stream().forEach(e -> newV.put(e, 0.0));
            v = newV;

            this.notifyObs();
	}

        
        private double gamma;

	@Override
	public void setGamma(double gamma)
        {
            this.gamma = gamma;
	}

	
        
        private ActionGridworld[] getActions()
        {
            return new ActionGridworld[]
                {
                    ActionGridworld.EST,
                    ActionGridworld.OUEST,
                    ActionGridworld.NORD,
                    ActionGridworld.SUD
                };
        }
}
